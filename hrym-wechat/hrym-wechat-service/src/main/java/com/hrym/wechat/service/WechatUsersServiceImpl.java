package com.hrym.wechat.service;


import com.hrym.common.util.DateUtil;
import com.hrym.common.util.StringUtil;
import com.hrym.wechat.entity.UserEntity;
import com.hrym.wechat.smallProgram.WechatUserParam;
import com.hrym.wechat.smallProgram.WechatUsersService;
import com.hrym.wechat.entity.WechatUsers;
import com.hrym.wechat.mapper.WechatUsersMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/4/12.
 */
public class WechatUsersServiceImpl implements WechatUsersService {

    @Autowired
    private WechatUsersMapper wechatUsersMapper;


    /**
     * 存取前端获取的用户
     * @return
     */
    @Override
    public String insertWechatUser(WechatUserParam param) {

        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        if (wechatUsers==null){
            WechatUsers wechatUsers1 = new WechatUsers();

            wechatUsers1.setNickName(StringUtil.filterEmoji(param.getNickName()));
            wechatUsers1.setSex(param.getSex());
            if (StringUtils.isNotBlank(param.getAvatarUrl())) {

                wechatUsers1.setAvatarUrl(param.getAvatarUrl());
            } else {

                wechatUsers1.setAvatarUrl("http://upd.everglamming.com:8089/group1/M00/00/13/wKgAHFoBUH-ARZFiAAC0d7SdfuU124.jpg");
            }
            wechatUsers1.setProvince(param.getProvince());
            wechatUsers1.setOpenId(param.getOpenId());
            wechatUsers1.setCreateTime(DateUtil.currentSecond());
            wechatUsers1.setUpdateTime(DateUtil.currentSecond());
            wechatUsersMapper.insertWechatUser(wechatUsers1);
        }else {
            WechatUsers wechatUsers1 = new WechatUsers();
            wechatUsers1.setUserId(wechatUsers.getUserId());
            wechatUsers1.setNickName(param.getNickName());
            System.out.println("+++++++========="+StringUtil.containsEmoji(param.getNickName()));
            System.out.println("======="+StringUtil.StringFilter(param.getNickName()));
            wechatUsers1.setSex(param.getSex());

            if (StringUtils.isNotBlank(param.getAvatarUrl())) {

                wechatUsers1.setAvatarUrl(param.getAvatarUrl());
            } else {

                wechatUsers1.setAvatarUrl("http://upd.everglamming.com:8089/group1/M00/00/13/wKgAHFoBUH-ARZFiAAC0d7SdfuU124.jpg");
            }
            wechatUsers1.setProvince(param.getProvince());
            wechatUsers1.setUpdateTime(DateUtil.currentSecond());
            wechatUsersMapper.updateWechatUser(wechatUsers1);
        }
        String openId= param.getOpenId();

        return openId;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer  insertWechatUserUId(Map<String,Object>user){
        Integer uuid=0;

        List<UserEntity> users = wechatUsersMapper.findUserByUnionIdOpenId(user);
        Integer  updatedTime =DateUtil.currentSecond();

        if(users.size()>1){
            //需要进行账号合并
            uuid = users.get(0).getUuid();
            int num =0;
            for(int i = 0 ;i<users.size();i++){

                if(i==0){
                    continue;
                }
                Integer uuid2 = users.get(i).getUuid();
                //用uuid更新自建功课，任务表，记录表中uuid2的值
                //删除数据库中uuid2的记录
                wechatUsersMapper.updateUuidOfTask(uuid,uuid2);
                wechatUsersMapper.updateUuidOfCustomItem(uuid,uuid2);
                wechatUsersMapper.updateUuidOfRecord(uuid,uuid2);
                wechatUsersMapper.updateUuidOfFlockUser(uuid,uuid2);
                wechatUsersMapper.updateUuidOfFlockItemCount(uuid,uuid2);
                wechatUsersMapper.updateUuidOfFlockRecord(uuid,uuid2);
                wechatUsersMapper.updateUuidOfFlock(uuid,uuid2);
                wechatUsersMapper.deleteUserByUuid(uuid2);
                
            }
            wechatUsersMapper.updateUserAccountNameByUuid(user,updatedTime,uuid);

        }else if(users.size()==1){
            uuid = users.get(0).getUuid();
            wechatUsersMapper.updateUserAccountNameByUuid(user,updatedTime,uuid);

        }else{

            wechatUsersMapper.insertUserAccount(user);
            uuid = wechatUsersMapper.getLastId();
        }

        return uuid;
    }
    @Override
    public Integer insertWechatUserUId(String unionId, String openId) {
    
        Integer uuid=null;

//        List<UserEntity> users = wechatUsersMapper.findUserByUnionId(unionId);
//
//        if(users.size()>1){
//            //需要进行账号合并
//            uuid = users.get(0).getUuid();
//        }else if(users.size()==1){
//            uuid = users.get(0).getUuid();
//
//        }else{
//
//
////            wechatUsersMapper.insertWechatUser();
//        }




        WechatUsers wechatUsers = wechatUsersMapper.findUserById(openId);
        WechatUsers wechatUsers1 = new  WechatUsers();
        wechatUsers1.setOpenId(openId);
        wechatUsers1.setUnionId(unionId);
        wechatUsers1.setUpdateTime(DateUtil.currentSecond());
        
        if (wechatUsers ==null){
            wechatUsers1.setCreateTime(DateUtil.currentSecond());
            wechatUsersMapper.insertWechatUser(wechatUsers1);
            uuid = wechatUsersMapper.getLastId();
        }else {
            wechatUsers1.setUserId(wechatUsers.getUserId());
            uuid = wechatUsers1.getUserId();
            wechatUsersMapper.updateWechatUserUnId(wechatUsers1);
        }
    return uuid;
    
    }
    
    public static void main(String[] args) {
        String string = "\\xF0\\x9F\\x8D\\x80\\xF0\\x9F...";
        System.out.println(StringUtil.containsEmoji(string));
        System.out.println("+++++++++========++++++");
        System.out.println(StringUtil.filterEmoji(string));
    }
}
