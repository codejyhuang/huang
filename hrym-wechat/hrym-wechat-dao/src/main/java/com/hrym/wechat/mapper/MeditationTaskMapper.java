package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.MeditationTask;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/4/20.
 */
@Repository
public interface MeditationTaskMapper {

    /**
     * 查看总报数
     * @param userId
     * @param scheduleId
     * @return
     */
    @DataSource("slave")
    @Select(" SELECT * FROM x_meditation_task WHERE user_id=#{userId} and schedule_id=#{scheduleId}")
    MeditationTask findMeditationTaskById(@Param("userId") Integer userId,@Param("scheduleId") Integer scheduleId );

    /**
     * 查找共修任务表是否已创建任务
     * @param userId
     * @param scheduleId
     * @return
     */
    @DataSource("slave")
    @Select(" SELECT * FROM x_meditation_task WHERE user_id=#{userId} and schedule_id=#{scheduleId} and meditation_type_id=#{meditationTypeId}")
    MeditationTask MeditationTask(@Param("userId") Integer userId,@Param("scheduleId") Integer scheduleId, @Param("meditationTypeId") Integer meditationTypeId);

    /**
     * 多版本任务总报数录入
     * @param meditationTask
     */
    @Insert(" insert into x_meditation_task (user_id,schedule_id,meditation_type_id,count,created_time,update_time)values(#{userId},#{scheduleId},#{meditationTypeId},#{count},#{createdTime},#{updateTime})")
    void insertMeditationTask(MeditationTask meditationTask);

    /**
     * 任务总报数更新
     * @param task
     */
    @Update(" update x_meditation_task set count= count+#{task.count},update_time= #{task.updateTime} where id = #{task.Id} ")
    void updateMeditationTask(@Param("task") MeditationTask task);

    /**
     * 个人我的排名
     * @param scheduleId
     * @return
     */

    @Select("SELECT d.user_id,d.avatar_url,d.nick_name,c.count,c.rowNum FROM (\n" +
            "SELECT *,(@rowno:=@rowno+1) as rowNum \n" +
            "FROM `x_meditation_task` a,(select (@rowno:=0)) b WHERE schedule_id=#{scheduleId} ORDER BY `count` DESC,id asc) c \n" +
            "left join `x_wechat_users` d on c.user_id=d.user_id \n" +
            "WHERE c.user_id=#{userId}")
    MeditationTask findMysort(@Param("scheduleId") Integer scheduleId,@Param("userId") Integer userId);

    /**
     * 个人排行榜
     * @param scheduleId
     * @return
     */
    @Select("SELECT * FROM x_meditation_task WHERE schedule_id=#{scheduleId} ORDER BY count DESC,id asc")
    List<MeditationTask> findMeditationTask(Integer scheduleId);

    /**
     * 群排行榜
     * @param scheduleId
     * @return
     */
    @Select(" SELECT m.count,m.user_id,w.nick_name,w.avatar_url FROM x_meditation_task m " +
            "LEFT JOIN `x_wechat_users` w ON m.user_id=w.user_id "+
            "LEFT JOIN `x_meditation` n ON m.user_id=n.user_id " +
            "WHERE n.openg_id=#{openGId} AND m.schedule_id=#{scheduleId} ORDER BY m.count DESC,m.id ASC")
    List<MeditationTask> findMeditationTaskByMId(@Param("openGId") String openGId, @Param("scheduleId") Integer scheduleId);

    /**
     * 群排行榜我的排名
     * @param scheduleId
     * @return
     */
    @Select("SELECT d.user_id,d.avatar_url,d.nick_name,c.count,c.rowNum FROM (\n" +
            "            SELECT *,(@rowno:=@rowno+1) as rowNum FROM(\n" +
            "SELECT m.id,m.count,m.user_id,w.nick_name,w.avatar_url FROM x_meditation_task m \n" +
            "            LEFT JOIN `x_wechat_users` w ON m.user_id=w.user_id \n" +
            "            LEFT JOIN `x_meditation` n ON m.user_id=n.user_id \n" +
            "            WHERE n.openg_id=#{openGId} AND m.schedule_id=#{scheduleId} ORDER BY m.count DESC,m.id ASC) a,\n" +
            "(select (@rowno:=0)) b  ORDER BY `count` DESC,a.id asc) c \n" +
            "            left join `x_wechat_users` d on c.user_id=d.user_id \n" +
            "            WHERE c.user_id=#{userId} ")
   MeditationTask findMeditonById(@Param("openGId") String openGId, @Param("scheduleId") Integer scheduleId,@Param("userId") Integer userId);

    /**
     * 今日个人排行榜
     * @param scheduleId
     * @return
     */
    @Select("SELECT d.count_id,d.schedule_id,d.user_id,d.created_time,s.nick_name,s.avatar_url,sum(count) as count FROM x_count_record d \n" +
            "LEFT JOIN x_wechat_users s ON s.user_id=d.user_id\n" +
            "WHERE schedule_id= #{scheduleId} AND created_time>=UNIX_TIMESTAMP( date_sub(curdate(),interval 0 day)) GROUP BY user_id\n" +
            " ORDER BY count DESC,user_id ASC")
    List<MeditationTask> findTodayOrder(Integer scheduleId);

    /**
     * 今日个人我的排名
     * @param scheduleId
     * @param userId
     * @return
     */
    @Select(" SELECT d.user_id,d.avatar_url,d.nick_name,c.count,c.rowNum FROM ( " +
                        "SELECT *,(@rowno:=@rowno+1) as rowNum FROM ( " +
                            "SELECT d.count_id,d.schedule_id,d.user_id,d.created_time,s.nick_name,sum(count) as count FROM x_count_record d  " +
                            "LEFT JOIN x_wechat_users s ON s.user_id=d.user_id " +
                            "WHERE schedule_id = #{scheduleId} AND created_time>=UNIX_TIMESTAMP( date_sub(curdate(),interval 0 day)) GROUP BY user_id " +
                            "ORDER BY count DESC,user_id ASC) a,(select (@rowno:=0)) b ORDER BY count DESC,user_id asc) c  " +
                            "left join `x_wechat_users` d on c.user_id=d.user_id  " +
                        "WHERE c.user_id = #{userId} ")

    MeditationTask todayMyPerOrder(@Param("scheduleId") Integer scheduleId,@Param("userId") Integer userId);

    /**
     * 今日群排行
     * @param scheduleId
     * @param openGId
     * @return
     */
    @Select("SELECT n.openg_id,d.schedule_id,d.user_id,s.nick_name,s.avatar_url,sum(count) AS count FROM x_count_record d\n" +
            "LEFT JOIN x_meditation n ON n.user_id=d.user_id\n" +
            "LEFT JOIN x_wechat_users s ON s.user_id=d.user_id\n" +
            "WHERE d.schedule_id=#{scheduleId} and d.created_time>=UNIX_TIMESTAMP( date_sub(curdate(),interval 0 day)) AND n.openg_id=#{openGId} \n" +
            "GROUP BY user_id ORDER BY SUM(count) DESC,user_id ASC")
    List<MeditationTask> findTodayGroupOrder(@Param("scheduleId") Integer scheduleId,@Param("openGId") String openGId);

    /**
     * 今日群我的排名
     * @param scheduleId
     * @param openGId
     * @param uesrId
     * @return
     */
    @Select(" SELECT d.user_id,d.avatar_url,d.nick_name,c.count,c.rowNum FROM (\n" +
            "                       SELECT *,(@rowno:=@rowno+1) as rowNum FROM( SELECT n.openg_id,d.schedule_id,d.user_id,s.nick_name,s.avatar_url,sum(count) AS count FROM x_count_record d\n" +
            "LEFT JOIN x_meditation n ON n.user_id=d.user_id\n" +
            "LEFT JOIN x_wechat_users s ON s.user_id=d.user_id\n" +
            "WHERE d.schedule_id = #{scheduleId} and d.created_time>=UNIX_TIMESTAMP( date_sub(curdate(),interval 0 day)) AND n.openg_id = #{openGId} \n" +
            "GROUP BY user_id ORDER BY SUM(count) DESC,user_id ASC ) a,\n" +
            "            (select (@rowno:=0)) b  ORDER BY `count` DESC,a.user_id asc) c \n" +
            "                        left join `x_wechat_users` d on c.user_id=d.user_id \n" +
            "                        WHERE c.user_id = #{userId}")
    MeditationTask findTodayMyGroupOrder(@Param("scheduleId") Integer scheduleId,@Param("openGId") String openGId,@Param("userId") Integer uesrId);


 }
