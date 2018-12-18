package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.FlockUserBack;
import com.hrym.wechat.smallProgram.FlockRecordParam;
import com.hrym.wechat.smallProgram.FlockUserBackParam;
import com.hrym.wechat.vo.FlockRecordVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/11/28.
 */
@Repository
public interface FlockUserBackMapper {
    @DataSource("slave")
    FlockUserBack queryLastTimeSpecialBack(FlockUserBackParam param);
    
    @DataSource("slave")
    List<FlockUserBack> queryDedicationVersesList( Integer uuid);
    
    @DataSource("slave")
    FlockUserBack queryDedicationVersesById(Integer id);
    
    
    void updateSpecialBack(@Param("id") Integer id, @Param("info") String info,@Param("time")Integer time);
    
    
    void insertSpecialBack( @Param("p") FlockUserBackParam param, @Param("time") Integer time,@Param("ymd") String ymd);
    
    @DataSource("slave")
    List<FlockUserBack> queryDedicationVerses( Integer uuid);
    
    void insertTestSpecialBack(FlockUserBack flockUserBack);
    
    List<FlockUserBack> queryDedicationVersesRecordList(@Param("flockId") Integer flockId,@Param("ymd") String ymd,@Param("tableName")String tableName);
    
    void deleteSpecialBack(Integer id);

//    void insertTestSpecialBack(List<FlockRecordVo> list);
}
