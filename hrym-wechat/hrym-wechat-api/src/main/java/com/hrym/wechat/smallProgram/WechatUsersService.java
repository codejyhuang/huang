package com.hrym.wechat.smallProgram;


import java.util.Map;

/**
 * Created by hrym13 on 2018/4/12.
 */
public interface WechatUsersService {
    /**
     * 用户列表接口
     * @return
     */
   //List finAllWechatUsers( WechatUserParam param);

    String insertWechatUser(WechatUserParam param);
    
    /**
     * 专门存取unionId
     * @param unionId
     * @param openId
     */
    Integer insertWechatUserUId(String unionId, String openId);

    /**
     * 专门存取unionId
     * @param user
     */
    Integer insertWechatUserUId(Map<String,Object> user);

}
