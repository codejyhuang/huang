package com.hrym.app.service;

import com.hrym.rpc.app.dao.model.VO.activityVO.ActivityPrayVO;
import com.hrym.rpc.app.dao.model.activity.ActivityHelp;
import com.hrym.rpc.app.dao.model.activity.ActivityPray;
import com.hrym.rpc.app.dao.model.activity.WXUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by mj on 2018/4/19.
 */
public interface ActivityMgrService {

    /**
     * 祈福活动首页
     * @return
     */
    ActivityPrayVO prayHomePage(String id);

    /**
     * 插入祈福信息
     * @param pray
     */
    void savePrayInfo(ActivityPray pray);

    /**
     * 查找所有信息按助力值倒序
     * @return
     */
    List<ActivityPrayVO> findTop10();

    /**
     * 随机获取库里一条祈福记录
     * @return
     */
    ActivityPrayVO findOne(Integer pageNo);

    /**
     * 为别人助力
     * @param help
     */
    void helpOthers(ActivityHelp help);

    /**
     * 保存微信授权用户信息
     * @param user
     */
    void saveWXUser(WXUser user);

    /**
     * 助力主页
     * @param fromId
     * @return
     */
    ActivityPrayVO getHelpHomePage(String fromId,String toId);

    /**
     * 判断用户是否已经祈福
     * @param uuid
     * @return
     */
    boolean isPray(String uuid);

    /**
     * 分页获取祈福信息
     * @param pageNo
     * @return
     */
    Map<String,Object> findAllByPageNo(Integer pageNo);
}
