package com.hrym.rpc.auth.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.ThumbsUp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 点赞Mapper
 * Created by mj on 2017/7/18.
 */
public interface ThumbsUpMapper {

    /**
     * 添加点赞信息
     * @param thumbsUp
     */
    void insertThumbsUp(ThumbsUp thumbsUp);

    /**
     * 通过主题ID和用户id查询（0:功课类型；1:社群文章）
     * @param topicId
     * @param userId
     * @return
     */
    @DataSource("slave")
    ThumbsUp findThumsUp(@Param("topicId") Integer topicId,@Param("userId") Integer userId,@Param("topicType") Integer topicType);

    /**
     * 通过主题ID和用户ID更新点赞状态
     * @param type
     * @param topicId
     * @param userId
     */
    void updateThumbsUp(@Param("type") Integer type,@Param("topicId") Integer topicId,@Param("userId") Integer userId,@Param("topicType") Integer topicType);

    /**
     * 通过主题ID查询用户ID和昵称
     * @param topicId
     * @return
     */
    @DataSource("slave")
    List<ThumbsUp> findUserIdByTopicId(@Param("topicId") Integer topicId,@Param("topicType") Integer topicType);


}

