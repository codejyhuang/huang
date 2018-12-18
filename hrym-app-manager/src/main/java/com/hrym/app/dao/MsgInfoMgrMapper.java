package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.MsgInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mj on 2017/9/10.
 */
@Repository
public interface MsgInfoMgrMapper {

    /**
     * 添加消息信息
     * @param msgInfo
     */
    @Insert("insert into t_msg_info(msgTypeValue,topic_id,from_id,to_id,content,content_type,parent_idt,isread,status,create_time,msgType) " +
            "values(#{msgTypeValue},#{topicId},#{fromId},#{toId},#{content},#{contentType},#{parentIdt},#{isread},#{status},#{createTime},#{msgType})")
    void insertMsgInfo(MsgInfo msgInfo);


    @DataSource("slave")
    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();

    /**
     * 根据用户id消息类型最大时间更新消息阅读状态
     * @param msgInfo
     */
    @Update("update t_msg_info set isread=#{a.isread} where to_id=#{a.toId} and msgType=#{a.msgType} and content_type=#{a.contentType}")
    void updateIsReadByUuidAndType(@Param("a") MsgInfo msgInfo);

}
