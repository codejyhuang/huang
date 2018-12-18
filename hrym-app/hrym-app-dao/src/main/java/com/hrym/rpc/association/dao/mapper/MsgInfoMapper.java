package com.hrym.rpc.association.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.MsgInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by mj on 2017/9/10.
 */
public interface MsgInfoMapper {

    /**
     * 添加消息信息
     * @param msgInfo
     */
    @Insert("insert into t_msg_info(msgTypeValue,topic_id,from_id,to_id,content,content_type,parent_idt,isread,status,create_time,msgType) " +
            "values(#{msgTypeValue},#{topicId},#{fromId},#{toId},#{content},#{contentType},#{parentIdt},#{isread},#{status},#{createTime},#{msgType})")
    void insertMsgInfo(MsgInfo msgInfo);

    /**
     * 更新申请消息状态
     * @param msgInfo
     */
    @Update("update t_msg_info set content_type=#{a.contentType},isread=#{a.isread} where msgId=#{a.msgId}")
    void updateStatus(@Param("a") MsgInfo msgInfo);

    /**
     * 通过用户ID和查询消息信息
     * @param uuid
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_msg_info where to_id=#{uuid} order by create_time DESC")
    List<MsgInfo> findMsgInfoByType(Integer uuid);

    /**
     * 通过用户ID和消息类型查询消息信息
     * @param uuid
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_msg_info where to_id=#{uuid} and msgType=#{msgType}")
    MsgInfo findMsgInfoByUuidAndType(@Param("uuid") Integer uuid,@Param("msgType") Integer msgType);

    /**
     * 根据社群ID和用户ID查询消息状态
     * @param id
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("select status,content_type,isread from t_msg_info where msgTypeValue=#{id} and from_id=#{userId} and create_time=#{createTime} and msgType=#{msgType}")
    MsgInfo findStatusById(@Param("id") Integer id, @Param("userId") Integer userId, @Param("createTime") Integer createTime, @Param("msgType") Integer msgType);


    /**
     * 根据社群ID和用户ID查询最大时间
     * @param id
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("select max(create_time) from t_msg_info where msgTypeValue=#{id} and from_id=#{userId} and msgType=#{msgType}")
    Integer findMaxCreateTime(@Param("id") Integer id,@Param("userId") Integer userId,@Param("msgType") Integer msgType);

    @DataSource("slave")
    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();

    /**
     * 更新消息阅读状态
     * @param msgInfo
     */
    @Update("update t_msg_info set isread=#{a.isread} where msgId=#{a.msgId}")
    void updateIsRead(@Param("a") MsgInfo msgInfo);

    /**
     * 根据用户id消息类型最大时间更新消息阅读状态
     * @param msgInfo
     */
    @Update("update t_msg_info set isread=#{a.isread} where to_id=#{a.toId} and msgType=#{a.msgType} and content_type=#{a.contentType}")
    void updateIsReadByUuidAndType(@Param("a") MsgInfo msgInfo);

    /**
     * 通过用户ID和是否已读查询消息信息
     * @param uuid
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_msg_info where to_id=#{uuid} and isread=#{isRead}")
    List<MsgInfo> findMsgInfoByToIdAndIsRead(@Param("uuid") Integer uuid,@Param("isRead") Integer isRead);


}
