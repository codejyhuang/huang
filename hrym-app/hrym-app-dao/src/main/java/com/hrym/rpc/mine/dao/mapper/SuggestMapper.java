package com.hrym.rpc.mine.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.Duden;
import com.hrym.rpc.app.dao.model.task.TaskItem;
import org.apache.ibatis.annotations.Select;

/**
 * Created by xiaohan on 2017/11/27.
 */
public interface SuggestMapper {
    /**
     * 通过大词典ID查询详情
     */
    @DataSource("slave")
    @Select("select * from t_duden where text_id=#{textId}")
    Duden findDudenById(Integer textId);
}
