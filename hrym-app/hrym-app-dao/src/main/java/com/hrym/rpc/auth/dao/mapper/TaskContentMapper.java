package com.hrym.rpc.auth.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.TaskContent;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by mj on 2017/8/3.
 */
public interface TaskContentMapper {

    /**
     * 通过ID查询功课内容
     *
     * @param itemId
     * @return
     */
    @DataSource("slave")
    List<TaskContent> findContentById(Integer itemId);

    /**
     * 通过功课ID查询txt文档
     *
     * @param itemId
     * @return
     */
    @DataSource("slave")
    @Select("select file_txt from t_resource_content where item_id=#{itemId}")
    TaskContent findByItemId(Integer itemId);

    /**
     * 通过功课内容ID查询txt文档
     *
     * @param contentId
     * @return
     */
    @DataSource("slave")
    @Select("select item_content_id,file_txt,content_version,video_file,version from t_resource_content where item_content_id=#{contentId}")
    TaskContent findByContentId(Integer contentId);
}
