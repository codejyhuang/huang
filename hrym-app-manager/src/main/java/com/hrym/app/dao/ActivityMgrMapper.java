package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.Banner;
import com.hrym.rpc.app.dao.model.association.Desire;
import com.hrym.rpc.app.dao.model.association.WishActivity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2017/9/21.
 */
@Repository
public interface ActivityMgrMapper {

    /**
     * 查找活动列表
     * @return
     */
    @DataSource("slave")
    @Select("SELECT banner_id,start_time,end_time,banner_title,banner_desc,banner_pic,banner_url,need_login,share_url,need_share,additional_param,need_result,share_channel,sort FROM t_banner")
    List<Banner> findAllBanner();

    /**
     * 根据ID查找内容
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("SELECT banner_id,start_time,end_time,banner_title,banner_desc,banner_pic,banner_url,banner_type,need_login,share_url,need_share,additional_param,need_result,share_channel,sort FROM t_banner " +
            "where banner_id = #{bannerId}")
    Banner findAllBannerById(Integer id);

    /**
     * 根据活动标题查找内容
     * @param
     * @return
     */
    @DataSource("slave")
    @Select("SELECT banner_id,start_time,end_time,banner_title,banner_desc,banner_pic,banner_url,banner_type,need_login,share_url,need_share,additional_param,need_result,share_channel,sort FROM t_banner " +
            "WHERE banner_title LIKE '%${bannerTitle}%'")
    List<Banner> findAllBannerByTitle(@Param("bannerTitle") String bannerTitle);

    /**
     * 时间
     * @param startTime
     * @param endTime
     * @return
     */
    @DataSource("slave")
    @Select("select banner_id,start_time,end_time,banner_title,banner_desc,banner_pic,banner_url,banner_type,need_login,share_url,need_share,additional_param,need_result,share_channel,sort from t_banner where start_time >= #{start} and end_time <= #{end}")
    List<Banner> findAllBannerByTime (@Param("start") Integer startTime, @Param("end") Integer endTime );

    @DataSource("slave")
    @Select("select banner_id,start_time,end_time,banner_title,banner_desc,banner_pic,banner_url,banner_type,need_login,share_url,need_share,additional_param,need_result,share_channel,sort from t_banner where start_time >= #{startTime}")
    List<Banner> findAllBannerBystartTime ( Integer startTime);

    @DataSource("slave")
    @Select("select banner_id,start_time,end_time,banner_title,banner_desc,banner_pic,banner_url,banner_type,need_login,share_url,need_share,additional_param,need_result,share_channel,sort from t_banner where  end_time <= #{endTime}")
    List<Banner> findAllBannerByendTime ( Integer endTime );

    /**
     * 活动添加
     * @param banner
     */
    @Insert("insert into t_banner (start_time,end_time,banner_title,banner_desc,banner_pic,banner_url,banner_type,need_login,share_url,need_share,additional_param,need_result,share_channel,sort)" +
            "values(#{startTime},#{endTime},#{bannerTitle},#{bannerDesc},#{bannerPic},#{bannerUrl},#{bannerType},#{needLogin},#{shareUrl},#{needShare},#{additionalParam},#{needResult},#{shareChannel},#{sort})")
    void insertAllBanner(Banner banner);

    /**
     * 活动更新
     */
    @Update("update t_banner set start_time = #{startTime},end_time = #{endTime},banner_title = #{bannerTitle}," +
            "banner_desc = #{bannerDesc},banner_pic = #{bannerPic},banner_url = #{bannerUrl}, banner_type = #{bannerType},sort = #{sort}," +
            "need_login = #{needLogin},share_url = #{shareUrl},need_share = #{needShare},additional_param = #{additionalParam},need_result = #{needResult},share_channel = #{shareChannel} where banner_id = #{bannerId}")
    void updateAllBanner(Banner banner );

    /**
     * 活动删除
     */
    @Delete("delete from t_banner where banner_id = #{bannerId}")
    void deleteAllBanner(Integer id);

//=========== 活动接口============

    /**
     * 愿望总数查询
     * @return
     */
    @DataSource("slave")
    @Select("SELECT COUNT(*) FROM t_desire")
    Integer findAllCountByDesire();

    /**
     * 愿望清单
     * @return
     */
    @DataSource("slave")
    @Select("SELECT * FROM t_desire")
    List<Desire> findAllByDesire();

    /**
     * 记录愿望
     * @param desire
     * @return
     */
    @Insert("insert into t_desire (desire,created_time)values(#{desire},#{createdTime})")
    void insertALLDesire( Desire desire);

    /**
     * 查找所有的完成任务的记录
     * @return
     */
    @DataSource("slave")
    @Select(" SELECT a.id,a.user_id,a.phone,a.status,a.wish_content,a.winner,a.create_time,b.nickname as userName FROM t_wish_activity a left join t_user_account b on uuid=user_id ")
    List<WishActivity> findAllWishActivity();

    /**
     * 查找完成任务的人数
     * @return
     */
    @DataSource("slave")
    @Select(" SELECT COUNT(1) FROM (SELECT * FROM t_wish_activity GROUP BY user_id) as a WHERE a.status=1 ")
    Integer wishActivityNumber();

    /**
     * 查找完成任务的状态
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("SELECT status FROM t_wish_activity WHERE user_id = #{id} AND status = 1 ")
    List<WishActivity> findStatusByUserId(Integer id);

    /**
     * 查看是否有这个人
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("SELECT * FROM t_wish_activity WHERE user_id = #{userId} ")
    List<WishActivity>  findWishActivityByUserId(Integer userId);
    /**
     * 查看是否有电话号码
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("SELECT * FROM t_wish_activity  WHERE user_id = #{userId} and (phone !=null or phone !='') ")
    List<WishActivity>  findPhoneByUserId(Integer userId);
    /**
     * 记录完成任务记录
     * @param wishActivity
     */
    @Insert("insert into t_wish_activity (user_id,phone,status,create_time,wish_content)values(#{userId},#{phone},#{status},#{createTime},#{wishContent}) ")
    void insertALLWishActivity(WishActivity wishActivity);
    /**
     * 修改完成任务电话号码
     * @param
     */
    @Update("update t_wish_activity set phone = #{wa.phone} where user_id = #{wa.userId}  ")
    void updateALLWishActivity( @Param("wa") WishActivity wa );


//    /**
//     * 修改完成任务状态
//     * @param wishActivity
//     */
//    @Update("update t_wish_activity set status = #{status} where user_id = #{userId}, ")
//    void updateALLWishActivity(WishActivity wishActivity);

    /**
     * 获取活动用户信息
     * @return
     */
    @Select("SELECT * from\n" +
            "(select a.id,a.user_id,a.phone,a.wish_content,b.nickname as userName \n" +
            "from t_wish_activity as a \n" +
            "left join t_user_account as b on a.user_id=b.uuid \n" +
            "where a.status=1 AND a.phone is not null AND a.phone != '') as t\n" +
            "GROUP BY t.user_id ")
    List<WishActivity> findAll();

    /**
     * 更新中奖状态
     * @param id
     */
    @Update("update t_wish_activity set winner=1 where id=#{id}")
    void updateWinnerByUserId(Integer id);

}
