package com.hrym.rpc.auth.dao.mapper;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.InviteDetail;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by mj on 2017/6/23.
 */
public interface UserMapper extends Mapper<UserInfo>{

    /**
     * 增加用户信息
     * @param userInfo
     */
    void saveUserInfo(UserInfo userInfo);

    /**
     * 增加用户邀请明细
     * @param info
     */
    @Insert("insert into t_invite_detail(code,uuid,device_id) values (#{u.code},#{u.uuid},#{u.device_id})")
    void saveInviteDetail(@Param("u") InviteDetail info);

    /**
     * 根据手机号查询
     * @param mobile
     * @return UserInfo
     */
    @DataSource("slave")
    UserInfo findUserInfoByMobile(String mobile);

    /**
     * 根据token查询
     * @param token
     * @return UserInfo
     */
    @DataSource("slave")
    UserInfo findUserInfoByToken(String token);

    /**
     * 更新用户密码
     * @param userInfo
     * @return
     */
    void updatePassword(@Param("userInfo") UserInfo userInfo);

    /**
     * 更新用户信息
     * @param userInfo
     * @param token
     */
    void updateByToken(@Param("u") UserInfo userInfo,@Param("token") String token);

    /**
     * 更新身份证认证信息
     * @param userInfo
     */
    @Update("update t_user_account set identity_auth_state=#{u.identityAuthState},identity_card_no=#{u.identityCardNo}," +
            "identity_card_url1=#{u.identityCardUrl1},identity_card_url2=#{u.identityCardUrl2}," +
            "identity_card_url3=#{u.identityCardUrl3},real_name=#{u.realName} where uuid=#{u.uuid}")
    void updateIdentification(@Param("u") UserInfo userInfo);

    /**
     * 更新手机号认证结果
     * @param mobile
     * @param status
     * @param userId
     */
    @Update("update t_user_account set mobile=#{mobile},phone_auth_state=#{status} where uuid=#{userId}")
    void updateByUserId(@Param("mobile") String mobile,@Param("status")Integer status,@Param("userId") Integer userId);

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("select uuid,nickname,avatar,profile from t_user_account where uuid=#{id}")
    UserInfo findUserByUserId(Integer id);

    /**
     * 根据第三方登录ID查询用户信息
     * @param socialUid
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_user_account where social_uid=#{socialUid}")
    UserInfo findUserBySocialUid(String socialUid);


    @Insert("insert into t_activity_user(uuid,userName,wx,phone,address,email) values (#{uuid},#{userName},#{wx},#{phone},#{address},#{email})")
    void updateUserInfo(@Param("uuid") Integer uuid,
                        @Param("userName") String userName,
                        @Param("wx") String wx,
                        @Param("phone") String phone,
                        @Param("address") String address,
                        @Param("email") String email);

    /**
     * 更新最近在线时间
     * @param time
     * @param uuid
     */
    @Update("update t_user_account set recent_time=#{time} where uuid=#{uuid}")
    void updateTimeByUuid(@Param("time") Integer time, @Param("uuid") Integer uuid);

    /**
     * 根据手机号查询
     * @param mobile
     * @return UserInfo
     */
    @DataSource("slave")
    @Select("select * from t_user_account where mobile=#{mobile}")
    UserInfo findAllUserByMobile(String mobile);

    /**
     * 根据手机号查询
     *
     * @param time
     * @return list
     */
    @DataSource("slave")
    @Select("select mobile from t_user_account where recent_time < #{time} and source=0")
    List<String> getMobilesNeedCallback(long time);
}
