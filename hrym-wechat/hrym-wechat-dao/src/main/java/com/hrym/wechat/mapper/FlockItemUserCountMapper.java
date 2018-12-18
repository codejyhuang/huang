package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.FlockItemUserCount;
import com.hrym.wechat.entity.FlockRecordDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/11/10.
 */
@Repository
public interface FlockItemUserCountMapper {
    
    //更新多群系统功课和经书功课人累计总数
    @Update("update t_flock_item_user_count set " +
            "total = total+#{num} " +
            "where " +
            " item_id = #{itemId} " +
            " and item_content_id = #{itemContentId} and type = #{type} and flock_id in (SELECT flock_id FROM t_flock_user WHERE uuid =#{uuid})")
    void updateFlockItemUserCountLesson(@Param("num") Double num,
                                  @Param("itemId") Integer itemId,
                                  @Param("uuid") Integer uuid,
                                  @Param("itemContentId")Integer itemContentId,
                                  @Param("type") Integer type);
    
    //更新多群自建功课人累计总数
    @Update("update t_flock_item_user_count set " +
            "total = ifNull(total,0) + #{num} " +
            "where " +
            " item_id = #{itemId} and type = #{type} and flock_id in (SELECT flock_id FROM t_flock_user WHERE uuid =#{uuid})")
    void updateFlockItemUserCountCustom(@Param("num") Double num,
                                  @Param("itemId") Integer itemId,
                                  @Param("uuid") Integer uuid,
                                  @Param("type") Integer type);

    //更新单群系统功课和经书功课人累计总数
    @Update("update t_flock_item_user_count set " +
            "total = ifNull(total,0) + #{num} " +
            "where " +
            " item_id = #{itemId} and item_content_id = #{itemContentId} and uuid = #{uuid} and type = #{type} and flock_id = #{flockId}")
    void updateFlockItemUserCountLessonByFlockId(FlockRecordDO record);

    //更新单群自建功课人累计总数
    @Update("update t_flock_item_user_count set " +
            "total = ifNull(total,0) + #{num} " +
            "where " +
            " item_id = #{itemId} and type = #{type} and uuid = #{uuid} and flock_id = #{flockId}")
    void updateFlockItemUserCountCustomByFlockId(FlockRecordDO record);

    //添加基于群的功课人的统计记录
    @Insert("insert into t_flock_item_user_count" +
            "(flock_id,item_id,uuid,type,item_content_id)" +
            "values" +
            "(#{flockId},#{itemId},#{uuid},#{type},#{itemContentId})")
    void insert(FlockRecordDO record);

    // 查找人与经书和系统功课
    @DataSource("slave")
    @Select("SELECT id,flock_id FROM t_flock_item_user_count WHERE " +
            "flock_id =#{flockId} AND item_id = #{itemId} AND uuid =#{uuid} and type =#{type} AND item_content_id = #{itemContentId}")
    FlockItemUserCount selectFlockItemLessonUserCount(FlockRecordDO record);

    // 查找人与自建功课
    @DataSource("slave")
    @Select("SELECT id,flock_id FROM t_flock_item_user_count WHERE " +
            "flock_id =#{flockId} AND item_id = #{itemId} AND uuid =#{uuid} and type =#{type} ")
    FlockItemUserCount selectFlockItemCustomUserCount(FlockRecordDO record);
    


    /**
     * 功课累计查询 groupby uuid
     *
     * @param flockId
     * @param itemId
     * @param itemContentId
     * @param type
     * @return
     */
    @DataSource("slave")
    List<FlockRecordDO> queryByFlockIdAndItemIdAndItemContentIdAndType(@Param("flockId") Integer flockId,
                                                                       @Param("itemId") Integer itemId,
                                                                       @Param("itemContentId") Integer itemContentId,
                                                                       @Param("type") Integer type,
                                                                       @Param("userId")Integer userId);

    /**
     * 查询用户功课 群 统计的 记录
     *
     * @param flockId
     * @param itemId
     * @param itemContentId
     * @param type
     * @return
     */
    @DataSource("slave")
    FlockItemUserCount queryByFlockIdAndItemIdAndItemContentIdAndTypeAndUuid(@Param("flockId") Integer flockId,
                                                                              @Param("itemId") Integer itemId,
                                                                              @Param("itemContentId") Integer itemContentId,
                                                                              @Param("type") Integer type,
                                                                              @Param("uuid")Integer uuid);

    void save(FlockItemUserCount flockItemUserCount);


    FlockRecordDO queryByUuidFromItemCount(@Param("flockId") Integer flockId,
                                           @Param("itemId") Integer itemId,
                                           @Param("itemContentId") Integer itemContentId,
                                           @Param("type") Integer type,
                                           @Param("uuid") Integer userId);

    @DataSource("slave")
    Integer queryCountByItemIdAndContentIdAndType(@Param("type") Integer type,
                                              @Param("itemId") Integer itemId,
                                              @Param("itemContentId") Integer itemContentId);
}
