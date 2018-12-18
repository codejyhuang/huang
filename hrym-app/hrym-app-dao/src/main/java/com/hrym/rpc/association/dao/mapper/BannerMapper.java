package com.hrym.rpc.association.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.Banner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 活动bannerMapper接口
 * Created by mj on 2017/9/21.
 */
public interface BannerMapper {

    /**
     * 根据活动类型查询所有banner活动
     * @param type
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_banner where banner_type=#{type} and start_time<=#{time} and end_time>=#{time} order by sort asc")
    List<Banner> findAllBannerByType(@Param("type") Integer type, @Param("time") Integer time);
}
