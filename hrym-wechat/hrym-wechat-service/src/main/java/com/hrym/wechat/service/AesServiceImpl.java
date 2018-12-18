package com.hrym.wechat.service;

import com.hrym.common.util.DateUtil;
import com.hrym.wechat.entity.Meditation;
import com.hrym.wechat.entity.WechatUsers;
import com.hrym.wechat.mapper.MeditationMapper;
import com.hrym.wechat.mapper.WechatUsersMapper;
import com.hrym.wechat.smallProgram.AES;
import com.hrym.wechat.smallProgram.AesService;
import com.hrym.wechat.smallProgram.MeditationParam;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hrym13 on 2018/4/26.
 */
@Service
public class AesServiceImpl implements AesService {

    @Autowired
    private MeditationMapper meditationMapper;
    @Autowired
    private WechatUsersMapper wechatUsersMapper;

    /**
     * 群ID解密及存取群与用户信息
     * @param param
     * @return
     */
    @Override
    public Map decryptAes(MeditationParam param) {
        Map map = new HashMap();
        try {
            byte[] resultByte  = AES.decrypt(Base64.decodeBase64( param.getEncryptedData()),
                    Base64.decodeBase64(param.getSessionKey()),
                    Base64.decodeBase64(param.getIv()));
            if(null != resultByte && resultByte.length > 0){
                System.out.println(resultByte);
                String userInfo = new String(resultByte, "UTF-8");

                JSONObject obj = JSONObject.fromObject(userInfo);
               String openGId=  obj.getString("openGId");

               //存用户的聊天群
                //根据openid查找userid
                WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

                //根据微信ID查找该群是否存在
                Meditation med = meditationMapper.findMeditationByGId(openGId,wechatUsers.getUserId());

                Meditation meditation = new Meditation();

                //群二维码
                meditation.setCodeUrl(param.getCodeUrl());
                //群简介
                meditation.setMeditationIntro(param.getMeditationIntro());
                //群名称
                meditation.setMeditationName(param.getMeditationName());
                //群头像
                meditation.setMeditationUrl(param.getMeditationUrl());
                if (null == med){
                    //用户ID
                    meditation.setUserId(wechatUsers.getUserId());
                    //微信群ID
                    meditation.setOpenGId(openGId);
                    meditation.setCreatedTime(DateUtil.currentSecond());
                    meditationMapper.insertMeditation(meditation);
                }else {
                    meditation.setUpdateTime(DateUtil.currentSecond());
                    meditationMapper.updateMeditation(meditation);
                }
                System.out.println(openGId);
                map.put("status", "1");
                map.put("msg", "解密成功");
                map.put("openGId", openGId);
            }else{
                map.put("status", "0");
                map.put("msg", "解密失败");
                map.put("openGId","");
            }
        }catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(map);
        return map;
    }

}
