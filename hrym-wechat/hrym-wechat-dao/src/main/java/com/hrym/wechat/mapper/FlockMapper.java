package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created  on 2018/11/07.
 */
@Repository
public interface FlockMapper {


    /**
     * 查询共修群列表
     * @param uuid
     * @return
     */
    @DataSource("slave")
    List<Flock> listByIds(Integer uuid);


    void createFlock(Flock flock);
    
    /**
     * 根据UUID和群ID
     *
     * @param uuid
     * @param flockId
     * @return
     */
    @DataSource("slave")
    FlockUser listflockUserById(@Param("uuid") Integer uuid, @Param("flockId") Integer flockId);
    
    /**
     * 根据群ID查找信息
     * @param id
     * @return
     */
    @DataSource("slave")
    Flock queryByPrimaryKey(Integer id);


    
    //更新群表里功课累计数和今日上报
    @Update("update t_flock set " +
            "day_done_num = day_done_num+ #{dayDoneNum}," +
            "total_done_num = total_done_num+#{totalDoneNum}," +
            "update_time = #{updateTime} " +
            "where id  = #{id}")
    void updateFlockNum( FlockReport item);

    /**
     * 批量更新  报数
     * @param num
     * @param flockIds
     */
    void batchUpdate(@Param("num") Double num, @Param("flockIds") List<Integer> flockIds);

    /**
     * 逻辑解散共修群   将共修群 状态设置为 已解散
     * @param flockId
     */
    void logicDeleteFlock(@Param("flockId") Integer flockId,@Param("state") Integer state);

    /**
     * 编辑 / 更新 群简介
     * @param intro
     */
    void updateFlockIntro(@Param("id") Integer id,@Param("intro") String intro);

    /**
     * 编辑 群隐私
     * @param id
     * @param privacy
     */
    void updateFlockPrivacy(@Param("id") Integer id, @Param("privacy") Integer privacy);
    /**
     * 编辑 群隐私
     * @param id
     * @param dedicationVerses
     */
    void updateFlockDedicationVerses(@Param("id") Integer id, @Param("de") String dedicationVerses);
    
    
    
    /**
     * 更新共修群功课数量
     * @param id
     * @param size
     */
    void updateFlockItemNum(@Param("id") Integer id,@Param("size") int size);

    /**
     * 更新群 共修人数
     * @param id
     * @param updateNum
     */
    void updateFlockJoinNum(@Param("id") Integer id, @Param("updateNum") int updateNum);
    
    
    List<FlockItem> queryFlockItemList(Integer uuid);

    /**
     * 查询时否有同名功课
     * @param name
     * @return
     */
    Integer queryCountByName(String name);
    
    Flock queryFlockCreatedTimeById(Integer id);

    @DataSource("slave")
    List<Flock> queryByItemIdAndContentIdAndUuid(@Param("itemId") Integer itemId,
                                                 @Param("itemContentId") Integer itemContentId,
                                                 @Param("type") Integer type,
                                                 @Param("userId") Integer userId);

    @DataSource("slave")
    List<Flock> queryJoinFlockList(Integer uuid);
    
    List<Flock> queryFlockList();
    
    /**
     * 备份数据用户总计
     * @return
     */
    TaskCommon totalUserBackup( Integer uuid);
    
    List<TaskCommon> queryItemListDeatil(Integer uuid);
}
