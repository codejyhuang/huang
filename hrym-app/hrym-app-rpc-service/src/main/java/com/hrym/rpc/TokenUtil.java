package com.hrym.rpc;

import com.alibaba.fastjson.JSON;
import com.hrym.common.util.RedisUtil;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import org.apache.commons.lang.StringUtils;

/**
 * Created by mj on 2018/4/16.
 */
public class TokenUtil {

    public static UserInfo getUserByToken(String token){

        UserInfo user = null;
        if (StringUtils.isNotBlank(token)){
            //Redis中获取用户对象
            user = JSON.parseObject(RedisUtil.get(token),UserInfo.class);
        }
        return user;
    }
}
