package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.book.Bookcase;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mj on 2018/5/24.
 */
@Repository
public interface BookcaseMgrMapper {

    @DataSource("slave")
    @Select("select * from t_bookcase where item_id=#{itemId} and is_exist=1")
    List<Bookcase> findBookcaseById(Integer itemId);
}
