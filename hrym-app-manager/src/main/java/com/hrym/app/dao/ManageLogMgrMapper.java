package com.hrym.app.dao;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.ManageLogVO;
import com.hrym.rpc.app.dao.model.task.ManageLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/2/2.
 * 日志实体类
 */
@Repository
public interface ManageLogMgrMapper extends Mapper<ManageLog>{

    /**
     * 查找所有的日志
     * @return
     */
    @DataSource("slave")
    @Select("SELECT " +
            "log_id,item_id,item_content_id,create_time,user_id,version_name,item_name,user_name,catalogue_id,catalogue_name,music_id,music_name " +
            "FROM t_manage_log ")
    List<ManageLogVO> findAllManageLog();

    /**
     * 日志生成
     * @param manageLog
     */
    @Insert("insert into t_manage_log (item_id,item_content_id,user_id,create_time,item_name,version_name,catalogue_id,catalogue_name,music_id,music_name,user_name)" +
            "values(#{itemId},#{itemContentId},#{userId},#{createTime},#{itemName},#{versionName},#{catalogueId},#{catalogueName},#{musicId},#{musicName},#{userName})")
    void insertAllManageLog(ManageLog manageLog);

}
