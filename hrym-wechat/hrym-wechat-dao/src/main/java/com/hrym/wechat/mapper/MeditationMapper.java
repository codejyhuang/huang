package com.hrym.wechat.mapper;

import com.hrym.wechat.entity.Meditation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by hrym13 on 2018/4/19.
 */
@Repository
public interface MeditationMapper {


    /**
     * 微信群与人关系录入
     * @param meditation
     */
    @Insert(" insert into x_meditation" +
            "(openg_id,created_time,meditation_name,meditation_intro,code_url,user_id,meditation_url)values" +
            "( #{openGId}, #{createdTime}, #{meditationName}, #{meditationIntro}, #{codeUrl}, #{userId},#{meditationUrl})")
    void insertMeditation(Meditation meditation);

    /**
     * 更新
     * @param meditation
     */
    @Update(" update x_meditation set update_time = #{updateTime},meditation_name=#{meditationName}," +
            " meditation_intro=#{meditationIntro},code_url=#{codeUrl} where meditation_id=#{meditationId}")
    void updateMeditation(Meditation meditation);

    /**
     * 根据微信ID查看此群是否已经存在
     * @param openGId
     * @return
     */
    @Select(" SELECT * FROM x_meditation WHERE openg_id=#{openGId} and user_id=#{userId}")
    Meditation findMeditationByGId(@Param("openGId") String openGId,@Param("userId") Integer userId);
}
