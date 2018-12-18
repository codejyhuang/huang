package com.hrym.rpc.auth.dao.mapper;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.CustomTask;
import org.apache.ibatis.annotations.Select;

/**
 * Created by mj on 2018/1/8.
 */
public interface CustomTaskMapper extends Mapper<CustomTask> {

    /**
     * 查找最新插的ID
     * @return
     */
    @DataSource("master")
    @Select("SELECT LAST_INSERT_ID()")
    int getLastId();
}
