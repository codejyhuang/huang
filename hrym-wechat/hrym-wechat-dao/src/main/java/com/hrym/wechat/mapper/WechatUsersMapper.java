package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.FlockUserBack;
import com.hrym.wechat.entity.UserEntity;
import com.hrym.wechat.entity.WechatUsers;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/4/12.
 */
public interface WechatUsersMapper {

    /**
     * 查找所有用户列表
     * @return
     */
//    @Select("select * from x_wechat_users")
    @DataSource("slave")
    List<WechatUsers> finAllWechatUsers();

    /**
     * 用户录入
     * @param wechatUsers1
     */
    @Insert(" insert into x_wechat_users(open_id,nick_name,create_time,province,avatar_url,sex,update_time,union_id)" +
               "values(#{openId},#{nickName},#{createTime},#{province},#{avatarUrl},#{sex},#{updateTime},#{unionId}) ")
    void insertWechatUser(WechatUsers wechatUsers1);

    /**
     * 根据open ID
     * @param openId
     * @return
     */
    @Select("SELECT * FROM x_wechat_users WHERE open_id = #{openId}")
    WechatUsers findUserById(String openId);

    /**
     * 查找最新插的ID
     * @return
     */
    @DataSource("master")
    @Select("SELECT LAST_INSERT_ID()")
    int getLastId();

    /**
     * 根据用户ID查找用户相关信息《排行榜》
     * @param userId
     * @return
     */
    @Select(" SELECT * FROM x_wechat_users WHERE user_id=#{userId}")
    WechatUsers findWechatUsersByUserId(Integer userId);
    
    
    
    /**
     * 用户更新
     * @param wechatUsers1
     */
    @Update("update x_wechat_users set nick_name=#{nickName},province=#{province},avatar_url= #{avatarUrl},sex= #{sex},update_time = #{updateTime} where user_id = #{userId}")
    void  updateWechatUser(WechatUsers wechatUsers1);
    /**
     * 用户更新unid
     * @param wechatUsers1
     */
    @Update("update x_wechat_users set union_id = #{unionId},update_time = #{updateTime} where user_id = #{userId}")
    void  updateWechatUserUnId(WechatUsers wechatUsers1);

    @Insert(" insert into t_user_account(social_uid,nickname,source,avatar,created_time,open_id,status,province,sex)" +
            "values(#{unionId},#{nickName},1,#{avatarUrl},#{createdTime},#{openid},1,#{province},#{sex}) ")
    void insertUserAccount(Map<String,Object> user);

    @Select("SELECT * FROM t_user_account WHERE social_uid = #{unionId} or open_id=#{openid} order by uuid asc")
    List<UserEntity> findUserByUnionIdOpenId(Map<String,Object>user);

    @Update("update t_task_plan_custom set uuid = #{uuid} where uuid=#{oldUuid}")
    void updateUuidOfTask(@Param("uuid")Integer uuid,@Param("oldUuid") Integer oldUuid);

    @Update("update t_resource_item_custom set user_id = #{uuid} where user_id=#{oldUuid}")
    void updateUuidOfCustomItem(@Param("uuid")Integer uuid,@Param("oldUuid") Integer oldUuid);

    @Update("update t_task_record_custom set user_id = #{uuid} where user_id=#{oldUuid}")
    void updateUuidOfRecord(@Param("uuid")Integer uuid,@Param("oldUuid") Integer oldUuid);

    @Update("update t_flock_item_user_count set uuid = #{uuid} where uuid=#{oldUuid}")
    void updateUuidOfFlockItemCount(@Param("uuid")Integer uuid,@Param("oldUuid") Integer oldUuid);

    @Update("update t_flock_user set uuid = #{uuid} where uuid=#{oldUuid}")
    void updateUuidOfFlockUser(@Param("uuid")Integer uuid,@Param("oldUuid") Integer oldUuid);

    @Update("update t_flock_record_2018 set uuid = #{uuid} where uuid=#{oldUuid}")
    void updateUuidOfFlockRecord(@Param("uuid")Integer uuid,@Param("oldUuid") Integer oldUuid);

    @Delete("delete from t_user_account where uuid=#{uuid}")
    void deleteUserByUuid(Integer uuid);
    
    //更新userAccount用户名信息
    @Update("update t_user_account set nickname=#{u.nickName},province = #{u.province} ,avatar = #{u.avatarUrl},updated_time = #{updatedTime},open_id = #{u.openid}, social_uid = #{u.unionId},sex = #{u.sex} where uuid = #{uuid} ")
    void updateUserAccountNameByUuid(@Param("u") Map<String, Object> user, @Param("updatedTime") Integer updatedTime,@Param("uuid") Integer uuid);
    
    @Update("update t_flock set creater_id = #{uuid} where creater_id=#{oldUuid}")
    void updateUuidOfFlock(@Param("uuid")Integer uuid,@Param("oldUuid") Integer oldUuid);
    
    FlockUserBack findUserAcountByUserId(Integer uuid);
}
