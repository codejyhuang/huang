package com.hrym.rpc.auth.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.GongFoMusic;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by mj on 2017/10/28.
 */
public interface GongFoMusicMapper {

    /**
     * 查找供佛音频
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_gongfo_music")
    List<GongFoMusic> findGongFoMusic();
}
