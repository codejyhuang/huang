package com.hrym.wechat.service;

import com.hrym.common.util.DateUtil;
import com.hrym.wechat.entity.Meditation;
import com.hrym.wechat.entity.WechatUsers;
import com.hrym.wechat.mapper.MeditationMapper;
import com.hrym.wechat.mapper.WechatUsersMapper;
import com.hrym.wechat.smallProgram.MeditationParam;
import com.hrym.wechat.smallProgram.MeditationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hrym13 on 2018/4/19.
 */

@Service
public class MeditationServiceImpl implements MeditationService {

    @Autowired
    private MeditationMapper meditationMapper;
    @Autowired
    private WechatUsersMapper wechatUsersMapper;

    /**
     * 存人与群信息
     * @param param
     */
    @Override
    public void insertMeditation(MeditationParam param) {

        //根据openid查找userid
        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

       //根据微信ID查找该群是否存在
        Meditation med = meditationMapper.findMeditationByGId(param.getGroupId(),param.getUserId());

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
            meditation.setGroupId(param.getGroupId());
            meditation.setCreatedTime(DateUtil.currentSecond());
            meditationMapper.insertMeditation(meditation);
        }else {
            meditation.setUpdateTime(DateUtil.currentSecond());
            meditationMapper.updateMeditation(meditation);
        }


    }
}
