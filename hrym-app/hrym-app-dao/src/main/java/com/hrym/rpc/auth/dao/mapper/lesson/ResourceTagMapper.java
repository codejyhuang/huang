package com.hrym.rpc.auth.dao.mapper.lesson;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceTag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by mj on 2018/6/25.
 */
public interface ResourceTagMapper {

    /**
     * 按照标签类型查找标签
     * @param type
     * @return
     */
    @DataSource("slave")
    @Select("select tag_id,tag_name,tag_type from t_resource_tag where tag_type=#{type}")
    List<ResourceTag> findTagsByTagType(Integer type);
}
