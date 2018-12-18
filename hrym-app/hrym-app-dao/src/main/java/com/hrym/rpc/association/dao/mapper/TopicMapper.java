package com.hrym.rpc.association.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by mj on 2017/9/21.
 */
public interface TopicMapper {

    /**
     * 保存主题信息
     * @param topic
     */
    @Insert("insert t_topic(user_id,topic_desc,topic_type,topic_type_value,url1,url2,url3,url4,url5,url6,url7,url8,url9,create_time,update_time) " +
            "values(#{t.userId},#{t.topicDesc},#{t.topicType},#{t.topicTypeValue},#{t.url1},#{t.url2},#{t.url3},#{t.url4},#{t.url5},#{t.url6}," +
            "#{t.url7},#{t.url8},#{t.url9},#{t.createTime},#{t.updateTime})")
    void saveTopic(@Param("t") Topic topic);

    /**
     * 通过主题类型查询主题
     * @param typeId
     * @param typeValue
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_topic where topic_type=#{typeId} and topic_type_value=#{typeValue} order by create_time DESC")
    List<Topic> findAllTopicByTypeId(@Param("typeId") Integer typeId, @Param("typeValue") Integer typeValue);

    /**
     * 通过主题ID更新点赞数
     * @param unm
     * @param topicId
     */
    @Update("update t_topic set agree_num=agree_num+#{num} where idt_topic=#{topicId}")
    void updateAgreeNumByTopicId(@Param("num") Integer unm, @Param("topicId") Integer topicId);
}
