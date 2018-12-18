package com.hrym.app.dao;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.DateVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2017/12/26.
 * 日历
 */

@Repository
public interface TcalendarMgrMapper extends Mapper<DateVO> {

    /**
     * 日历显示
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_date")
    List<DateVO> findAllTcalend();
}
