package com.hrym.rpc.auth.dao.mapper;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.DateVO;
import com.hrym.rpc.app.dao.model.association.WishActivity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by mj on 2017/12/26.
 */
public interface DateVOMapper extends Mapper<DateVO> {

    /**
     * 根据当前时间所在区间查询日历
     * @param currentTime
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_date where start_time<=#{currentTime} and end_time>=#{currentTime}")
    DateVO findOneByTime(Integer currentTime);

    /**
     * 获取所有的日历图片
     * @return
     */
    @DataSource("slave")
    @Select(" select * from t_date")
    List<DateVO> findAllDatePic();


    @DataSource("slave")
    @Select("select * from t_present order by id asc")
    List<Map> getPresentInfo();

    @DataSource("slave")
    @Select("select count(distinct device_id) as num from t_invite_detail where upper(code)=#{inviteCode}")
    int getInviteNum(String inviteCode);

    @DataSource("slave")
    @Select("select count(1) as num from t_activity_exchange where uuid =#{uuid}")
    int checkExchange(String uuid);

    @Insert("insert into t_activity_exchange(id,uuid) values(#{id},#{uuid})")
    int addExchange(@Param("uuid")String uuid, @Param("id")String id);

    @DataSource("slave")
    @Select("select stock from t_present where id =#{id}")
    int getStock(String id);

    @Update("update t_present set stock=stock-1 where id=#{id}")
    int updateStock(String id);

    /**
     * 修改完成任务状态
     * @param
     */
    @Update("update t_wish_activity set status = #{status} where user_id = #{userId}")
    void updateALLWishActivity( @Param("userId") Integer userId,@Param("status") Integer status );

    /**
     * 存完成任务的用户
     * @param userId
     * @param status
     */
    @Insert("insert into t_wish_activity (status,user_id,create_time) values(#{status},#{userId},#{createTime}) ")
    void insertAllWishActivity(@Param("userId") Integer userId,@Param("status") Integer status,@Param("createTime") Integer createTime);

    /**
     * 查看是否有这个用户
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_wish_activity where user_id = #{userId} ")
    List<WishActivity> findWishActivityByUuid(Integer userId);

}
