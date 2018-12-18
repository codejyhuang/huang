package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.MeditationType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/4/16.
 */
@Repository
public interface MeditationTypeMapper {

    @DataSource("slave")
    @Select(" select * from  ")
    List<MeditationType> findAllMeditationType();

    /**
     * 查找我加入的共修类型
     *
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("SELECT e.is_exit,d.created_time,d.is_top,e.meditation_type_id,e.meditation_type_name,e.meditation_type_intro,e.active_head_url,e.start_time,e.expect_time,e.meditation_type_version,\n" +
            "   (SELECT SUM(count)FROM x_count_record WHERE meditation_type_id=e.meditation_type_id AND user_id=d.user_id AND created_time >=UNIX_TIMESTAMP( date_sub(curdate(),interval 0 day))) as today_number\n" +
            "       FROM x_meditation_type e \n" +
            "       LEFT JOIN x_meditation_record d ON d.meditation_type_id=e.meditation_type_id \n" +
            "       WHERE d.user_id=#{userId} AND e.is_exit = 1 ORDER BY d.is_top DESC,d.created_time DESC")
    List<MeditationType> findMeditationTypeByUserId(Integer userId);

    /**
     * 查找我未加入的共修类型
     *
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select(" SELECT e.is_exit,e.meditation_type_id,e.meditation_type_name,e.meditation_type_intro,e.active_head_url,\n" +
            "e.start_time,e.expect_time,e.meditation_type_version ,\n" +
            "(SELECT COUNT(1) FROM x_meditation_record WHERE meditation_type_id=e.meditation_type_id) as userNumber \n" +
            "FROM x_meditation_type e \n" +
            "WHERE e.meditation_type_id NOT in(SELECT meditation_type_id FROM x_meditation_record WHERE user_id=#{userId}) AND e.is_exit=1")
    List<MeditationType> findMeditationTypeIsNoUserId(Integer userId);

    /**
     * 根据主键ID查找共修类型详细内容
     *
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("SELECT e.is_exit,e.start_time,e.expect_time,e.meditation_type_id,e.meditation_type_name,e.meditation_type_intro,e.meditation_type_version,\n" +
            "(SELECT COUNT(1) FROM x_meditation_record WHERE meditation_type_id=e.meditation_type_id ) as userNumber,\n" +
            "   (SELECT SUM(count)FROM x_count_record WHERE meditation_type_id=e.meditation_type_id AND user_id=#{userId} AND created_time >=UNIX_TIMESTAMP( date_sub(curdate(),interval 0 day))) as today_number\n" +
            " FROM x_meditation_type e WHERE e.meditation_type_id=#{id}")
    MeditationType findMeditationTypeByTypeId(@Param("id") Integer id, @Param("userId") Integer userId);


}
