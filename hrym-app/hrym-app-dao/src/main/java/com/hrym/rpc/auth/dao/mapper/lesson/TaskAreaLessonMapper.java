package com.hrym.rpc.auth.dao.mapper.lesson;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.lessonVO.TaskAreaLessonVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by hrym13 on 2018/6/25.
 * 推荐功课
 */
public interface TaskAreaLessonMapper {

    /**
     * 推荐功课列表
     * @param areaType
     * @return
     */
    @DataSource("slave")
    @Select(" SELECT a.create_time,a.area_id,a.title_name,a.item_id,a.area_type,a.title_desc,a.article_url,a.update_time,n.online_num,n.order_num,n.item_pic FROM t_resource_area a \n" +
            " LEFT JOIN t_resource_item_lesson n ON n.item_id=a.item_id \n" +
            " WHERE a.area_type = #{areaType} ")
    List<TaskAreaLessonVO> findTaskAreaLessonByAreaType(Integer areaType);

}
