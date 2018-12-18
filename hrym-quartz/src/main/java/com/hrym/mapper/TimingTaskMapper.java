package com.hrym.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.Flock;
import com.hrym.wechat.entity.FlockItem;
import com.hrym.wechat.entity.FlockUserBack;
import com.hrym.wechat.entity.WechatUserAccount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 定时任务mapper
 * Created by hrym13 on 2018/12/11.
 */
public interface TimingTaskMapper {
    
    /**
     * 查找所有共修群
     * @return
     */
    @DataSource("slave")
    @Select(" SELECT k.`name`,id,k.creater_id,k.dedication_verses,k.avatar_url,t.avatar,t.nickname,k.item_join_num\n" +
            "       FROM t_flock k left JOIN t_user_account t ON t.uuid = k.creater_id\n" +
            "       WHERE k.state =1")
    List<Flock> queryFlockList();
    
    @DataSource("slave")
    @Select(" SELECT m.day_done_num,w.lesson_name,m.item_id,m.item_content_id,m.type,m.unit\n" +
            "        FROM t_flock_item m\n" +
            "        left JOIN t_flock_lesson_view w on w.item_id =m.item_id AND w.item_content_id = m.item_content_id AND w.type=m.type\n" +
            "        WHERE flock_id =#{id} AND state =0")
    List<FlockItem> queryByFlockItemListByFlockId(Integer id);
    
    @Select(" INSERT INTO t_dedication_verses(item_type,type,time,flag,info,uuid,record_id,ymd,descJson,itemJson)\n" +
            "         VALUES\n" +
            "         (3,0,#{time},0,#{info},0,#{recordId},#{ymd},#{descJson},#{itemJson})")
    void insertTestSpecialBack(FlockUserBack flockUserBack);
    
    @DataSource("slave")
    @Select("SELECT t.uuid,t.nickname FROM t_dedication_verses s LEFT JOIN t_user_account t ON t.uuid = s.uuid " +
            "WHERE s.uuid in (SELECT uuid FROM t_flock_user WHERE flock_id =#{id}) AND s.ymd =#{ymd}\n")
    List<WechatUserAccount> queryUserNameByFlockIdAndYmd(@Param("id") Integer id, @Param("ymd") String ymd);
    
    @Update("update t_dedication_verses set flag =1 ")
    void updateSpecialBackByYmd();
    
    @Update("UPDATE t_flock SET day_done_num=0 ")
    void updateFlockDayDoneNum();
    
    @Update("UPDATE t_bookcase SET today_commit_num =0")
    void updateBookcaseTodayCommitNum();
    
    @Update("UPDATE t_bookcase SET today_commit_num =0")
    void updateFlockItemDayDoneNum();
}
