package com.hrym.rpc.auth.dao.mapper.meditationMapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.meditation.MeditationContent;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by mj on 2018/5/28.
 */
public interface MeditationContentMapper {

    /**
     * 查询所有功课内容 条件功课id
     * @param itemId
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_resource_content_meditation where item_id=#{itemId}")
    List<MeditationContent> findAllByItemId(Integer itemId);
}
