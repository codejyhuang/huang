package com.hrym.rpc.mine.dao.mapper;

import com.hrym.rpc.app.dao.model.task.FeedBack;
import org.apache.ibatis.annotations.Insert;

/**
 * 意见反馈Mapper
 * Created by mj on 2017/10/16.
 */
public interface FeedbackMapper {

    /**
     * 保存意见反馈
     * @param feedBack
     */
    @Insert("insert into t_feedback(phone,content,state,resolution,create_time,update_time,user_id) " +
            "values(#{phone},#{content},#{state},#{resolution},#{createTime},#{updateTime},#{userId})")
    void saveFeedback(FeedBack feedBack);
}
