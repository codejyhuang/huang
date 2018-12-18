package com.hrym.rpc.auth.dao.mapper;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.TaskMusic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by mj on 2017/8/15.
 */
public interface TaskMusicMapper extends Mapper<TaskMusic> {

    /**
     * 通过功课ID和功课内容ID获取音频版本
     * @param itemId
     * @return
     */
    @DataSource("slave")
    List<TaskMusic> findMusicById(@Param("itemId") Integer itemId,@Param("id") Integer id);

    /**
     * 查询禅修音频
     * @param taskId
     * @return
     */
    @DataSource("slave")
    List<TaskMusic> findMusicResourceById(Integer taskId);

    /**
     * 查询所有音频（通过最新时间排序）
     * @return
     */
    @DataSource("slave")
    List<TaskMusic> findAllMusicOrderByTime(@Param("filterStr") String filterStr);

}
