package com.hrym.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.common.message.RequestLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by hong on 2017/10/20.
 */
public interface MessageMapper {

    /**
     * 通过文章ID查询文章内容
     * @param startCron 提醒开始时间
     * @param endCron   提醒结束时间
     * @return
     */
    @DataSource("slave")
    @Select("select " +
            "plan.task_id," +
            "item.item_id," +
            "item.item_name," +
            "plan.type_id," +
            "plan.remain_cron," +
            "plan.uuid," +
            "t_task_type.type_name " +
            "from t_task_plan plan " +
            "left join t_task_type  on plan.type_id =  t_task_type.type_id " +
            "left join t_resource_item item on plan.item_id = item.item_id " +
            "where plan.remain_cron>=#{startCron} and plan.remain_cron<#{endCron} " +
            "and plan.is_exit = 1 and plan.start_time <= timestampdiff(SECOND,'1970-1-1 8:0:0',now())")
    List<Map> findWorkRemain(@Param("startCron") String startCron, @Param("endCron") String endCron);

    /**
     * 查询数据库表是否存在
     * @param tableName
     * @return
     */
    @Select("SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_name=#{tableName}")
    String findTable(String tableName);

    /**
     * 插入请求日志数据
     * @param log
     */
    @Insert("insert into ${tableName}" +
            "(topic,title,request_url,request_method,cmd,appname,platform,app_version,content,create_time) " +
            "values(#{log.topic},#{log.title},#{log.requestUrl},#{log.requestMethod},#{log.cmd}," +
            "#{log.appname},#{log.platform},#{log.appVersion},#{log.content},#{log.createTime})")
    void saveRequestLog(@Param("log") RequestLog log,@Param("tableName") String tableName);
}
