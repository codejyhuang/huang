package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.MeditationType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/4/16.
 */
@Repository
public interface MeditationTypeWXMapper {


    @DataSource("slave")
    @Select(" select * from x_meditation_type")
    List<MeditationType>findAllMeditationType();

}
