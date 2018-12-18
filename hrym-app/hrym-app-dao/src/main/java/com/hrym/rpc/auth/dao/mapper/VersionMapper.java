package com.hrym.rpc.auth.dao.mapper;


import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.VersionInfo;

/**
 * 版本信息Mapper
 * Created by mj on 2017/7/4.
 */
public interface VersionMapper {

    /**
     * 查询版本信息
     * @return
     */
    @DataSource("slave")
    VersionInfo findAllVersion();

}
