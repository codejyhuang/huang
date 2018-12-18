package com.hrym.rpc.auth.api;

import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.AssociationParam;
import com.hrym.rpc.app.common.constant.BookParam;
import com.hrym.rpc.app.common.constant.LogParam;
import com.hrym.rpc.app.dao.model.VO.DateVO;
import com.hrym.rpc.app.dao.model.VO.ResourceArticleVO;
import com.hrym.rpc.app.dao.model.VO.bookVO.TaskItemVO;
import com.hrym.rpc.app.dao.model.association.ResourceArticle;
import com.hrym.rpc.app.dao.model.association.WishActivity;
import com.hrym.rpc.app.util.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by mj on 2017/11/2.
 */
public interface HtmlViewService {

    /**
     * 分享
     * @return
     */
    Map<String,Object> doShare(Integer type,Integer id,double doneNum,double todayCommitNum,String targetNum);

    boolean updateUserInfo(Integer uuid, String userName, String wx, String phone, String address, String email);

    /**
     * 获取日历图片
     * @return
     */
    Map<String,Object> getDatePic(LogParam param);


    /**
     * 获取基于用户的活动信息
     * @return
     */
    Map<String,Object> getActivityInfo(int uuid);

    /**
     * 活动奖品领取
     *
     * @param uuid
     * @param id
     * @return
     */
    Map<String,Object> exchangeActivityPresent(String uuid,String id);

    /**
     * 修改完成任务的状态
     * @param
     * @return
     */
    BaseResult updateAllWishActivity(AssociationParam param);

    /**
     * 经书详情
     * @param itemId
     * @return
     */
    TaskItemVO findBookDetails(Integer itemId);

    /**
     * 新版本文章展示
     * @param articleId
     * @return
     */
    Map<String,Object> findResourceArticle(Integer articleId);

}
