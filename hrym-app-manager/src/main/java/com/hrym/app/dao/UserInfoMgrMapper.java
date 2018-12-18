package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.FeedBack;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2017/9/7.
 */
@Repository
public interface UserInfoMgrMapper {

    /**
     * 用户列表
     */
    @DataSource("slave")
    @Select("SELECT uuid,nickname,mobile,source,created_time,identity_card_url1,identity_card_url2," +
            "identity_card_no,real_name,social_uid,recent_time,status,identity_auth_state,sex,ymd,user_tatus FROM t_user_account ")
    List<UserInfo> findAllUser();

    /**
     * 用户审核列表
     */
    @DataSource("slave")
    @Select("SELECT uuid,nickname,mobile,source,created_time,identity_card_url1,identity_card_url2," +
            "identity_card_no,updated_time,social_uid,recent_time,identity_auth_state,sex,ymd,user_tatus FROM t_user_account " +
            "where identity_auth_state = #{status}")
    List<UserInfo> findUserByStatus(Integer status);


    /**
     * 用户称呼搜索
     * @return
     */
    @DataSource("slave")
    @Select("SELECT uuid,nickname,mobile,source,created_time,identity_card_url1,identity_card_url2,identity_card_no,real_name," +
            "social_uid,recent_time,identity_auth_state,sex,ymd,user_tatus FROM t_user_account where nickname like '%${nickName}%'")
    List<UserInfo> searchNickName(@Param("nickName") String nickName);


    /**
     * 用户最近登录时间搜索
     * @return
     */
    @DataSource("slave")
    @Select("SELECT uuid,nickname,mobile,source,created_time,identity_card_url1,identity_card_url2,identity_card_no,real_name," +
            "social_uid,recent_time,identity_auth_state,sex,ymd,user_tatus FROM t_user_account where recent_time >=#{startTime} and recent_time <=#{endTime}")
    List<UserInfo> searchUserInfoByReTime(@Param("startTime")  Integer startTime,@Param("endTime") Integer endTime);

    /**
     * 用户最近登录（>=）时间搜索
     * @return
     */
    @DataSource("slave")
    @Select("SELECT uuid,nickname,mobile,source,created_time,identity_card_url1,identity_card_url2,identity_card_no,real_name," +
            "social_uid,recent_time,identity_auth_state,sex,ymd,user_tatus FROM t_user_account where recent_time >=#{startTime} ")
    List<UserInfo> searchUserInfoByStartReTime(@Param("startTime")  Integer startTime);

    /**
     * 用户最近登录（<=）时间搜索
     * @return
     */
    @DataSource("slave")
    @Select("SELECT uuid,nickname,mobile,source,created_time,identity_card_url1,identity_card_url2,identity_card_no,real_name," +
            "social_uid,recent_time,identity_auth_state,sex,ymd,user_tatus FROM t_user_account where recent_time <=#{endTime}")
    List<UserInfo> searchUserInfoByEndReTime(@Param("endTime") Integer endTime);

    /**
     * 用户详情列表通过ID查找内容
     */
    @DataSource("slave")
    @Select("SELECT real_name,identity_auth_state,identity_card_url2,identity_card_url1,identity_card_no,ymd,updated_time,created_time,source,sex," +
            "mobile,nickname,avatar,name,user_tatus,recent_time,social_uid,uuid FROM t_user_account WHERE uuid = #{uuid}")
    UserInfo findAllUserlist( Integer id);

    /**
     * 认证审核
     * @param
     */
    @Update("update t_user_account set identity_auth_state = #{identityAuthState} where  uuid = #{uuid}")
    void updateCertification(UserInfo userInfo);


    /**
     * 用户反馈信息列表
     * @return
     */
    @DataSource("/slave")
    @Select("select idt_feedback,phone,content,state,resolution,create_time,update_time,feedback_type from t_feedback")
    List<FeedBack> findAllFeedBack();

    /**
     * 反馈处理
     */
    @Update("update t_feedback set update_time = #{updateTime},resolution = #{resolution} ,state = #{state} where idt_feedback = #{idtFeedBack}")
    void updateFeedBack(FeedBack feedBack);

}
