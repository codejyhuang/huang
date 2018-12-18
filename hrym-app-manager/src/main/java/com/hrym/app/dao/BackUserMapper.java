package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.system.BackUser;
import org.apache.ibatis.annotations.*;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaohan on 2017/10/11.
 */
@Repository
public interface BackUserMapper {

    //查询所有菜单
    @DataSource("slave")
    @Select("SELECT user_id,role_id,username,realname,gender,status,telephone,createTime from t_back_user")
    List<BackUser> findAllBackUser();

    //根据名称查询用户信息
    @DataSource("slave")
    @Select("SELECT user_id,role_id,username,realname,gender,status,telephone,createTime from t_back_user where username=#{username}")
    List<BackUser> findByName(@Param("username") String username);

    /**
     * //根据用户ID查询用户信息
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("SELECT u.user_id,u.role_id,u.username,u.realname,u.gender,u.status,u.telephone,u.createTime,t.role_name from t_back_user u " +
            "LEFT JOIN t_role t ON u.role_id=t.role_id where user_id=#{id}")
    BackUser finduserById(@Param("id") Integer id);


    //更改用户的状态
    @Update("update t_back_user set status = #{status} where  user_id = #{userId}")
    void changeStatus(BackUser backUser);

    /**
     * 更改用户权限
     * @param id
     */
    @Update(" update t_back_user set role_id = #{roleId} where  user_id = #{id}")
    void updateRoleId(@Param("id") Integer id,@Param("roleId") Integer roleId);

    /**
     * 用户注册
     * @param backUser
     */
    @Insert("insert into t_back_user " +
            "(user_id,role_id,username,realname,gender,status,telephone,createTime,password) values" +
            "(#{userId},#{roleId},#{username},#{realname},#{gender},#{status},#{telephone},#{createTime},#{password})")
    void insertUser(BackUser backUser);


    /**
     * 获取用户的状态
     */
    @DataSource("slave")
    @Select("SELECT status from t_back_user where user_id = #{userId}")
    Integer getStatus(BackUser backUser);

    /**
     * 登陆时根据用户名查询用户信息
     * @param username
     * @return
     */

    @DataSource("slave")
    @Select("SELECT user_id,role_id,username,status,password from t_back_user where username=#{username}")
    BackUser findByUserName(@Param("username") String username);


    /**
     * 根据用户ID查找用户名《日志需要打印》
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("SELECT user_id,role_id,username,password from t_back_user where user_id=#{id}")
    BackUser findUserNameById(Integer id);

    /**
     * 根据角色ID查找该角色的用户
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("SELECT * FROM t_back_user WHERE role_id = #{id}")
    List<BackUser> findBackUserByRoleId(Integer id);


    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from t_back_user where user_id = #{id}")
    void deleteBackUserByUserId(Integer id);


}
