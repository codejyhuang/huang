package com.hrym.rpc.auth.dao.mapper.meditationMapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.meditation.MeditationItem;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by mj on 2018/5/28.
 */
public interface MeditationItemMapper {

    /**
     * 查询所有禅修功课
     * @return
     */
    @DataSource("slave")
    @Select("select item_id,item_name,item_pic,is_music_or_video,background_pic,update_time from t_resource_item_meditation order by update_time desc")
    List<MeditationItem> findAll();
}
