package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.system.DiurnalData;
import com.hrym.rpc.app.dao.model.system.UserData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/3/9.
 */
@Repository
public interface DiurnalDataMapper {

    /**
     * 查找所有的表
     * @return
     */
    @DataSource("slave")
    @Select("SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE  table_name like 'user_data_%'")
    List<DiurnalData> findAllUserDataTable();

    /**
     * 根据表名查找对应的数据
     * @param tableName
     * @return
     */
    @DataSource("/slave")
//    @Select("select * from ${tableName}")
    List<DiurnalData> findAllUserData(@Param(value = "tableName") String tableName);

    /**
     * 当天最近登录数
     * @param tableName
     * @return
     */
    @DataSource("/slave")
    DiurnalData findAllUserCount(@Param(value = "tableName") String tableName);

    /**
     * 当天最近登录数
     * @param tableName
     * @return
     */
    DiurnalData  findUserCount(@Param(value = "tableName") String tableName);


    /**
     * 今日注册数
     * @param tableName
     * @return
     */
    DiurnalData findUserCountByCreateTime(@Param(value = "tableName") String tableName);

}
