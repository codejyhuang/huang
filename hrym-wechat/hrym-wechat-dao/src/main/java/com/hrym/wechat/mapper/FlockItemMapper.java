package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlockItemMapper {

    /**
     * 添加群功课 用户的上报统计
     * @param flockItem
     */
    void insert(FlockItem flockItem);

    @DataSource("slave")
    List<FlockItem> queryByFlockId(Integer id);


    @DataSource("slave")
    List<FlockItem> selectByFlockId(Integer id);

    //更新群表里系统功课和经书累计数和今日上报
    @Update("update t_flock_item set " +
            "day_done_num = day_done_num+ #{num}," +
            "total_done_num = total_done_num+#{num} " +
            "where " +
            " item_id = #{itemId}" +
            " and item_content_id = #{itemContentId} and type = #{type} " +
            "AND flock_id in (SELECT flock_id FROM t_flock_user WHERE uuid =#{uuid})")
    void updateFlockItemLesson( @Param("num") Double num,
                          @Param("itemId") Integer itemId,
                          @Param("itemContentId") Integer itemContentId,
                          @Param("type") Integer type,
                                @Param("uuid") Integer uuid);
    
    //更新群表里自建功课累计数和今日上报
    @Update("update t_flock_item set " +
            "day_done_num = day_done_num+ #{num}," +
            "total_done_num = total_done_num+#{num} " +
            "where " +
            " item_id = #{itemId} and type = #{type} " +
            "AND flock_id in (SELECT flock_id FROM t_flock_user WHERE uuid =#{uuid}) ")
    void updateFlockItemCustom( @Param("num") Double num,
                          @Param("itemId") Integer itemId,
                          @Param("type") Integer type,
                                @Param("uuid") Integer uuid);
    
    
    
    //更新单群表里系统功课和经书累计数和今日上报
    @Update("update t_flock_item set " +
            "day_done_num = day_done_num+ #{num}," +
            "total_done_num = total_done_num+#{num} " +
            "where " +
            " item_id = #{itemId}" +
            " and item_content_id = #{itemContentId} and type = #{type} and flock_id = #{flockId} and state = 0")
    void updateFlockItemLessonByFlockId(FlockRecordDO record);
    
    
    //更新单群表里自建功课累计数和今日上报
    @Update("update t_flock_item set " +
            "day_done_num = day_done_num+ #{num}," +
            "total_done_num = total_done_num+#{num} " +
            "where " +
            " item_id = #{itemId} and type = #{type} and flock_id = #{flockId} and state = 0")
    void updateFlockItemCustomByFlockId(FlockRecordDO record);
    
    
    //查找群ID
    @DataSource("slave")
    @Select(" select flock_id from t_flock_item where item_id = #{itemId} " +
            " and item_content_id = #{itemContentId}  and type = #{type} and state = 0 and " +
            " flock_id in (select flock_id from t_flock_user where uuid = #{uuid})")
    List<Integer> findFlockLessonItemById(@Param("itemId") Integer itemId,
                                      @Param("itemContentId") Integer itemContentId,
                                      @Param("type") Integer type,
                                            @Param("uuid") Integer uuid);
    //查找群ID
    @DataSource("slave")
    @Select("select flock_id from t_flock_item where item_id = #{itemId} and type = #{type} and state = 0 and " +
            "flock_id in (select flock_id from t_flock_user where uuid = #{uuid})")
    List<Integer> findFlockCustomItemById(@Param("itemId") Integer itemId,
                                      @Param("type") Integer type,
                                            @Param("uuid") Integer uuid);
    @DataSource("slave")
    @Select("SELECT m.item_id,m.version_name,m.item_content_id FROM ${contentTable} m " +
            "WHERE m.item_content_id= #{itemContentId}")
    FlockItem selectFlockItemList(@Param("itemContentId") Integer itemContentId,
                                        @Param("contentTable") String contentTable );
    
    /**
     * 查找自定义功课名称和群累计
     * @param itemId
     * @param flockId
     * @param type
     * @return
     */
    FlockItem findFlockCustomTotalDoneNum(@Param("itemId") Integer itemId,
                                          @Param("flockId") Integer flockId,
                                          @Param("type") Integer type);
    FlockItem findItemCustomName( Integer itemId);
    
    
    /**
     * 查找经书和功课的名称和群累计
     * @param itemId
     * @param flockId
     * @param type
     * @return
     */
    FlockItem findFlockLessonTotalDoneNum(@Param("itemId") Integer itemId,
                                          @Param("flockId") Integer flockId,
                                          @Param("type") Integer type,
                                          @Param("itemContentId") Integer itemContentId,
                                          @Param("tableName")String tableName);

    /**
     * 查询是否有加过该功课
     * @param id
     * @param itemId
     * @param itemContentId
     * @param type
     */
    FlockItem queryByFlockIdAndItemIdAndContentIdAndType(@Param("flockId") Integer id,
                                                    @Param("itemId") Integer itemId,
                                                    @Param("itemContentId") Integer itemContentId,
                                                    @Param("type") Integer type);


    /**
     * 更新群功课状态
     * @param flockItem
     */
    void updateFlockItem(FlockItem flockItem);


    @Select(" select flock_id from t_flock_item_user_count " +
            " where item_id = #{itemId} and type = #{type} " +
            " and item_content_id = #{itemContentId}" +
            " and uuid=#{uuid}")
    List<Integer>getFlockIdByItemAndUuid(@Param("itemId") Integer itemId,
                                        @Param("type") Integer type,
                                        @Param("itemContentId") Integer itemContentId,
                                        @Param("uuid")Integer uuid);


    /**
     * 更新群功课状态
     * @param id
     * @param i
     */
    void updateStateByFlockId(@Param("flockId") Integer id, @Param("state") int i);

    List<FlockItem> queryByFlockItemListByFlockId(Integer flockId);


    /**
     * 查找群功课
     * @param id
     * @return
     */
    List<FlockItem> queryByFlockIdJoinView(Integer id);
    
    List<FlockItem> queryByFlockItemByFlockId(Integer flockId);
}
