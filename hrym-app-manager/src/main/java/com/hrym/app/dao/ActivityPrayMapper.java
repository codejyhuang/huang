package com.hrym.app.dao;

import com.hrym.rpc.app.dao.model.VO.activityVO.ActivityPrayVO;
import com.hrym.rpc.app.dao.model.activity.ActivityHelp;
import com.hrym.rpc.app.dao.model.activity.ActivityPray;
import com.hrym.rpc.app.dao.model.activity.WXUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mj on 2018/4/19.
 */
@Repository
public interface ActivityPrayMapper {

    /**
     * 查找用户祈福信息（条件：用户id）
     * @param id
     * @return
     */
    @Select("select " +
            "a.id," +
            "a.from_user_id," +
            "a.to_user_name," +
            "a.pray_content," +
            "(select count(1) from t_activity_help where to_user_id=#{id}) as helpNum," +
            "a.from_user_name," +
            "a.from_user_avatar," +
            "(select count(1) " +
            "from (select count(1) as count from t_activity_help h right join t_activity_pray p on h.to_user_id=p.from_user_id group by p.from_user_id) " +
            "as t where t.count<helpNum) as num " +
            "from t_activity_pray a " +
            "where a.from_user_id=#{id}")
    ActivityPrayVO findPrayInfo(String id);

    /**
     * 查找用户祈福信息（条件：用户id）
     * @param id
     * @return
     */
    @Select("select * from t_activity_pray where from_user_id=#{id}")
    ActivityPray findByFromUserId(String id);

    /**
     * 查找祈福总人数
     * @return
     */
    @Select("select count(1) from t_activity_pray")
    int findTotalNum();

    /**
     * 随机获取库里一条记录
     * @return
     */
    @Select("select *," +
            "(select count(1) from t_activity_help where to_user_id=t.from_user_id) as helpNum " +
            "from t_activity_pray t " +
            "where id>=((select max(id) from t_activity_pray)-(select min(id) from t_activity_pray))*rand()" +
            "+(select min(id) from t_activity_pray) limit 1")
    ActivityPrayVO findOneByRand();

    /**
     * 插入祈福信息
     * @param pray
     */
    @Insert("insert into t_activity_pray" +
            "(from_user_id,to_user_name,pray_content,from_user_name," +
            "from_user_avatar,source,create_time,update_time) " +
            "values(#{fromUserId},#{toUserName},#{prayContent}," +
            "#{fromUserName},#{fromUserAvatar},#{source},#{createTime},#{updateTime})")
    void savePrayInfo(ActivityPray pray);

    /**
     * 更新祈福信息
     * @param pray
     */
    @Update("update t_activity_pray " +
            "set to_user_name=#{toUserName},pray_content=#{prayContent},update_time=#{updateTime} " +
            "where from_user_id=#{fromUserId}")
    void updatePrayInfo(ActivityPray pray);

    /**
     * 查找所有信息
     * @return
     */
    @Select("select * from t_activity_pray")
    List<ActivityPrayVO> findAll();

    /**
     * 查找所有信息按助力值倒序
     * @return
     */
    @Select("select from_user_id,from_user_name,from_user_avatar," +
            "(select count(1) from t_activity_help where to_user_id=t.from_user_id) as helpNum " +
            "from t_activity_pray t")
    List<ActivityPrayVO> findAllByHelpNum();

    /**
     * 根据id查找祈福信息
     * @param id
     * @return
     */
    @Select("select *," +
            "(select count(1) from t_activity_help where to_user_id=t.from_user_id) as helpNum " +
            "from t_activity_pray t where id=#{id}")
    ActivityPrayVO findOne(Integer id);


    /**
     * 保存微信授权用户信息
     * @param user
     */
    @Insert("insert into t_wx_user(openid,nickname,avatar,create_time,update_time,sex)" +
            "values(#{openid},#{nickname},#{avatar},#{createTime},#{updateTime},#{sex}) ")
    void saveWXUser(WXUser user);

    /**
     * 更新微信授权用户信息
     * @param user
     */
    @Update("update t_wx_user set nickname=#{nickname},avatar=#{avatar},sex=#{sex}," +
            "update_time=#{updateTime} where openid=#{openid}")
    void updateWXUser(WXUser user);

    /**
     * 根据openid查询微信授权用户信息
     * @param openid
     * @return
     */
    @Select("select * from t_wx_user where openid=#{openid}")
    WXUser findWXUserByOpenid(String openid);

    /**
     * 根据助力人和被助力人查找最后一条
     * @param fromId
     * @param toId
     * @return
     */
    @Select("select * from t_activity_help where from_user_id=#{fromId} and to_user_id=#{toId} order by id desc limit 1")
    ActivityHelp findByFromIdAndToId(@Param("fromId") String fromId, @Param("toId") String toId);

    /**
     * 保存活动助力
     * @param help
     */
    @Insert("insert into t_activity_help(from_user_id,to_user_id,create_time,type) " +
            "values(#{fromUserId},#{toUserId},#{createTime},#{type})")
    void saveActivityHelp(ActivityHelp help);

    /**
     * 助力主页
     * @param id
     * @return
     */
    @Select("select * from t_activity_pray where from_user_id=#{id}")
    ActivityPrayVO getHelpHomePage(String id);

    /**
     * 物理删除 助力信息
     * @param toId
     */
    @Delete("delete from t_activity_help where to_user_id=#{toId}")
    void deleteActivityHelp(String toId);
}
