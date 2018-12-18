package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceContentLesson;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceItemLesson;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mj on 2018/6/25.
 */
@Repository
public interface ResourceContentLessonMgrMapper {

    /**
     * 通过功课id查询所有功课内容
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_resource_content_lesson where item_id=#{itemId}")
    List<ResourceContentLesson> findAllContentById(Integer itemId);
}
