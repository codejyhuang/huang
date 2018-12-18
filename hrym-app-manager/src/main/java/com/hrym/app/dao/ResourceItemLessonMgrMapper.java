package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceItemLesson;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mj on 2018/6/25.
 */
@Repository
public interface ResourceItemLessonMgrMapper {

    /**
     * 查询所有功课
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_resource_item_lesson")
    List<ResourceItemLesson> findAll();
}
