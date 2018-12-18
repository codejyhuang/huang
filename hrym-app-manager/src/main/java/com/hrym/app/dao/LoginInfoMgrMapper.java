package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.LoginInfo;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2017/9/7.
 */
@Repository
public interface LoginInfoMgrMapper {

    /**
     * 用户列表
     */
    @DataSource("slave")
    @Select("SELECT uuid,nickname,mobile,source,created_time,identity_card_url1,identity_card_url2," +
            "identity_card_no,real_name,social_uid,updated_time,identity_auth_state,sex,ymd,user_tatus FROM t_user_account ")
    List<LoginInfo> findAllUser();



}
