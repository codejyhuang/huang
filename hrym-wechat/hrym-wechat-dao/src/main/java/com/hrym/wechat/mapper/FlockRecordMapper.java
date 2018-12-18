package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.FlockItem;
import com.hrym.wechat.entity.FlockRecordDO;
import com.hrym.wechat.smallProgram.FlockCountReportParam;
import com.hrym.wechat.vo.FlockRecordVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/11/9.
 */
@Repository
public interface FlockRecordMapper {

    /**
     * 添加修行记录
     *
     * @param list
     */
    void insertFlockRecord(@Param("list") List<FlockRecordDO> list,
                           @Param("tableName") String tableName);


    /**
     * 查询群的 修行轨迹
     *
     * @param id
     * @return
     */
    @DataSource("slave")
    List<FlockRecordDO> queryByFlockId(@Param("id") Integer id, @Param("list") List<FlockItem> flockItemList,@Param("uuid")Integer uuid);


    /**
     * 查询共修群 动态
     *
     * @param id
     * @param tableName
     * @return
     */
    List<FlockRecordDO> queryByFlockId(@Param("id") Integer id, @Param("tableName") String tableName);

    @DataSource("slave")
    List<FlockRecordVo> queryByIds(@Param("vo") FlockRecordVo vo,
                                   @Param("tableName") String tableName,
                                   @Param("tableName1") String tableName1,
                                   @Param("year") Integer year);

    FlockRecordVo findLessonSum(@Param("vo") FlockRecordVo vo,
                                @Param("tableName") String tableName,
                                @Param("tableName1") String tableName1,
                                @Param("year") Integer year);

    List<FlockRecordVo> queryByCUstomIds(@Param("vo") FlockRecordVo vo,
                                         @Param("tableName") String tableName,
                                         @Param("tableName1") String tableName1,
                                         @Param("year") Integer year);

    FlockRecordVo findCustomSum(@Param("vo") FlockRecordVo vo,
                                @Param("tableName") String tableName,
                                @Param("tableName1") String tableName1,
                                @Param("year") Integer year);


    /**
     * 功课动态查询修行记录
     *
     * @param flockId
     * @param itemId
     * @param itemContentId
     * @param type
     * @return
     */
    List<FlockRecordDO> queryByFlockIdAndItemIdAndItemContentIdAndType(@Param("flockId") Integer flockId,
                                                                       @Param("itemId") Integer itemId,
                                                                       @Param("itemContentId") Integer itemContentId,
                                                                       @Param("type") Integer type,
                                                                       @Param("userId")Integer userId);

    /**
     * 功课 sum 根据时间类型 统计查询 修行记录
     *
     * @param flockId
     * @param itemId
     * @param itemContentId
     * @param type
     * @param timeType
     * @return
     */
    List<FlockRecordDO> queryByFlockIdAndItemIdAndItemContentIdAndTypeGroupBy(@Param("flockId") Integer flockId,
                                                                              @Param("itemId") Integer itemId,
                                                                              @Param("itemContentId") Integer itemContentId,
                                                                              @Param("type") Integer type,
                                                                              @Param("timeType") Integer timeType);

    /**
     * 查询今日功课记录 group by uuid
     *
     * @param flockId
     * @param itemId
     * @param itemContentId
     * @param type
     * @return
     */
    List<FlockRecordDO> queryByFlockIdAndItemIdAndItemContentIdAndTypeAndToday(@Param("flockId") Integer flockId,
                                                                               @Param("itemId") Integer itemId,
                                                                               @Param("itemContentId") Integer itemContentId,
                                                                               @Param("type") Integer type,
                                                                               @Param("tableName") String tableName,
                                                                               @Param("ymd") Integer ymd
    );

    /**
     * 查询本年 或者 本月的功课上报记录  group by uuid
     *
     * @param flockId
     * @param itemId
     * @param itemContentId
     * @param type
     * @return
     */
    List<FlockRecordDO> queryByFlockIdAndItemIdAndItemContentIdAndTypeAndMonthOrYear(@Param("flockId") Integer flockId,
                                                                                     @Param("itemId") Integer itemId,
                                                                                     @Param("itemContentId") Integer itemContentId,
                                                                                     @Param("type") Integer type,
                                                                                     @Param("tableName") String tableName,
                                                                                     @Param("year") Integer year,
                                                                                     @Param("month") Integer month);


    /**
     * 查询本用户的  累计报数数据
     *
     * @param flockId
     * @param itemId
     * @param itemContentId
     * @param type
     * @param tableName
     * @param year
     * @param ymd
     * @param timeType
     * @return
     */
    FlockRecordDO queryByUuid(@Param("flockId") Integer flockId,
                              @Param("itemId") Integer itemId,
                              @Param("itemContentId") Integer itemContentId,
                              @Param("type") Integer type,
                              @Param("tableName") String tableName,
                              @Param("year") Integer year,
                              @Param("ymd") Integer ymd,
                              @Param("month") Integer month,
                              @Param("timeType") Integer timeType,
                              @Param("uuid") Integer uuid);

    /**
     * 查询  年 月 日 分组的数据
     *
     * @param flockId
     * @param itemId
     * @param itemContentId
     * @param type
     * @param tableName
     * @param ymd
     * @param year
     * @param month
     * @return
     */
    List<FlockRecordDO> queryByFlockIdAndItemIdAndItemContentIdAndTimeType(@Param("flockId") Integer flockId,
                                                                           @Param("itemId") Integer itemId,
                                                                           @Param("itemContentId") Integer itemContentId,
                                                                           @Param("type") Integer type,
                                                                           @Param("timeType") Integer timeType,
                                                                           @Param("tableName") String tableName,
                                                                           @Param("ymd") Integer ymd,
                                                                           @Param("year") Integer year,
                                                                           @Param("month") Integer month);


    void insertFlockRecordByFlockId(@Param("record") FlockRecordDO record, @Param("tableName") String tableName);

    /**
     * 针对功课  查询用户报数量
     *
     * @param itemId
     * @param itemContentId
     * @param type
     * @param userId
     * @return
     */
    @DataSource("slave")
    Double queryItemNum(@Param("itemId") Integer itemId,
                        @Param("itemContentId") Integer itemContentId,
                        @Param("type") Integer type,
                        @Param("userId") Integer userId,
                        @Param("tableName") String tableName,
                        @Param("format") Integer format,
                        @Param("timeType") Integer timeType,
                        @Param("state")Integer state);


    /**
     * 查询用户  功课   上报记录
     * @param itemId
     * @param itemContentId
     * @param type
     * @param userId
     * @param state
     * @return
     */
    @DataSource("slave")
    List<FlockRecordDO> queryRecordByItemIdAndItemContentIdAndTypeAndUuid(@Param("itemId") Integer itemId,
                                                                          @Param("itemContentId") Integer itemContentId,
                                                                          @Param("type") Integer type,
                                                                          @Param("userId") Integer userId,
                                                                          @Param("state")Integer state);



    void insertHistoryFlockRecord(@Param("li") FlockCountReportParam param,@Param("tableName") String tableName);

    /**
     * 逻辑删除 record 记录
     * @param recordId
     */
    int logicRemoveByPrimaryKey(@Param("recordId") Integer recordId,@Param("tableName") String tableName);

    /**
     *
     * @param id
     * @param i
     */
    void updateLikeNumByPrimaryKey(@Param("id") Integer id,@Param("tableName")String tableName,@Param("count") int i);
}