package com.hrym.mapper;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


/**
 * Created by mj on 2018/5/7.
 */
public interface TestMapper {

    @Update("update t_abc set num=0 where id=1")
    void update();
}
