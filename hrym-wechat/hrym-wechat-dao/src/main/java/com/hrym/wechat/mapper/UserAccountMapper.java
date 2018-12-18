package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.WechatUserAccount;
import org.apache.ibatis.annotations.Select;

public interface UserAccountMapper {
    @DataSource("slave")
    @Select("select avatar,nickName,sex,province from t_user_account where " +
            "uuid = #{userId} ")
    WechatUserAccount queryByUuid(Integer userId);
}
