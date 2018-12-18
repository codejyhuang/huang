package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.wechat.dao.model.MeditationType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/5/1.
 */
@Repository
public interface MeditationTypeMgrMapper {

    /**
     * 查找共修类型列表
     *
     * @return
     */
    @DataSource("/slave")
    @Select("SELECT e.*,\n" +
            "(SELECT COUNT(*) FROM x_meditation_record WHERE meditation_type_id=e.meditation_type_id)as count\n" +
            " FROM x_meditation_type e")
    List<MeditationType> findAllMeditation();

    /**
     * 共修活动了类型录入
     *
     * @param type
     */
    @Insert("insert into x_meditation_type (" +
            "meditation_type_name,meditation_type_intro,author,created_time,update_time,active_head_url,start_time,expect_time,meditation_type_version)" +
            "values(" +
            "#{meditationTypeName},#{meditationTypeIntro},#{author},#{createdTime},#{updateTime},#{activeHeadUrl},#{startTime},#{expectTime},#{meditationTypeVersion})")
    void insertMeditation(MeditationType type);

    /**
     * 共修活动了类型更新
     *
     * @param type
     */
    @Update("update x_meditation_type set meditation_type_name= #{meditationTypeName},meditation_type_intro=#{meditationTypeIntro}," +
            "author=#{author},update_time=#{updateTime},active_head_url=#{activeHeadUrl},start_time=#{startTime},expect_time=#{expectTime},meditation_type_version = #{meditationTypeVersion} " +
            " where meditation_type_id=#{meditationTypeId}")
    void updateMeditation(MeditationType type);

    /**
     * 共修活动了类型删除
     *
     * @param id
     */
    @Delete("delete  from x_meditation_type where meditation_type_id=#{id}")
    void deleteMeditation(Integer id);

    /**
     * 根据ID查找对应的内容
     *
     * @param id
     * @return
     */
    @Select(" select * from x_meditation_type where meditation_type_id = #{id} ")
    MeditationType findMeditationById(Integer id);

    /**
     * 根据共修类型名称查找共修类型列表
     *
     * @param meditationTypeName
     * @return
     */
    @Select("select * from x_meditation_type where meditation_type_name like '%${name}%'")
    List<MeditationType> findMeditationByName(@Param("name") String meditationTypeName);

    /**
     * 查找总用户量
     *
     * @return
     */

    @Select("SELECT COUNT(*) as medCount," +
            "(SELECT COUNT(*) as count FROM x_wechat_users) as count " +
            "FROM " +
            "(SELECT * FROM x_meditation_record GROUP BY user_id ) as a ")
    MeditationType findAllWechatUserCount();
}
