package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrym.app.dao.ActivityPrayMapper;
import com.hrym.app.dao.UserInfoMgrMapper;
import com.hrym.app.service.ActivityMgrService;
import com.hrym.common.util.DateFormat;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.NumUtil;
import com.hrym.common.util.StringUtil;
import com.hrym.rpc.app.dao.model.VO.activityVO.ActivityPrayVO;
import com.hrym.rpc.app.dao.model.activity.ActivityHelp;
import com.hrym.rpc.app.dao.model.activity.ActivityPray;
import com.hrym.rpc.app.dao.model.activity.WXUser;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mj on 2018/4/19.
 */
@Service
public class ActivityMgrServiceImpl implements ActivityMgrService {


    @Autowired
    private ActivityPrayMapper activityPrayMapper;
    @Autowired
    private UserInfoMgrMapper userInfoMgrMapper;
    /**
     * 祈福活动首页
     * @return
     */
    @Override
    public ActivityPrayVO prayHomePage(String id) {

        ActivityPrayVO prayVO = activityPrayMapper.findPrayInfo(id);
        //查询总人数
        int peopleNum = activityPrayMapper.findTotalNum();

        ActivityPrayVO vo = new ActivityPrayVO();
        //判断用户是否已祈福
        if (null == prayVO){
            vo.setStatus(0);
            vo.setPeopleNum(peopleNum);
        }else {
            vo.setStatus(1);
            vo.setId(prayVO.getId());
            vo.setFromUserId(prayVO.getFromUserId());
            vo.setFromUserName(prayVO.getFromUserName());
            vo.setFromUserAvatar(prayVO.getFromUserAvatar());
            vo.setToUserName(prayVO.getToUserName());
            vo.setHelpNum(prayVO.getHelpNum());
            vo.setPrayContent(prayVO.getPrayContent());
            String percent = NumUtil.getPercent(prayVO.getNum(),peopleNum);
            vo.setPercent(percent);
        }
        List<Map<String,Object>> list = new ArrayList<>();
        List<ActivityPrayVO> prayList = activityPrayMapper.findAll();
        for (ActivityPrayVO pray : prayList){
            Map<String,Object> map = new HashMap<>();
            map.put("nameStr","用户 "+pray.getFromUserName()+" 进行了祈福");
            list.add(map);
        }
        vo.setList(list);
        return vo;
    }


    /**
     * 插入祈福信息
     * @param pray
     */
    @Override
    public void savePrayInfo(ActivityPray pray) {

        String toUserName = pray.getToUserName();
        String content = pray.getPrayContent();
        pray.setToUserName(StringUtil.filterEmoji(toUserName));
        pray.setPrayContent(StringUtil.filterEmoji(content));
        //判断用户是否已祈福
        ActivityPray activityPray = activityPrayMapper.findByFromUserId(pray.getFromUserId());
        if (null == activityPray){
            if (pray.getSource() == 1){
                UserInfo user = userInfoMgrMapper.findAllUserlist(Integer.valueOf(pray.getFromUserId()));
                pray.setFromUserName(user.getNickName());
                pray.setFromUserAvatar(user.getAvatar());
            }
            pray.setCreateTime(DateUtil.currentSecond());
            pray.setUpdateTime(DateUtil.currentSecond());

            activityPrayMapper.savePrayInfo(pray);
        }else {
            pray.setUpdateTime(DateUtil.currentSecond());
            activityPrayMapper.updatePrayInfo(pray);
            activityPrayMapper.deleteActivityHelp(pray.getFromUserId());
        }

    }

    /**
     * 查找所有信息按助力值倒序
     * @return
     */
    @Override
    public List<ActivityPrayVO> findTop10() {

        PageHelper.startPage(1,10,"helpNum desc");
        List<ActivityPrayVO> voList = activityPrayMapper.findAllByHelpNum();
        return voList;
    }

    /**
     * 随机获取库里一条祈福记录
     * @return
     */
    @Override
    public ActivityPrayVO findOne(Integer pageNo) {

        ActivityPrayVO pray = null;

        if (pageNo == null){
            pray = activityPrayMapper.findOneByRand();
        }else {
            pray = activityPrayMapper.findOne(pageNo);
        }
        //查询总人数
        int peopleNum = activityPrayMapper.findTotalNum();
        pray.setPeopleNum(peopleNum);

        return pray;
    }

    /**
     * 为别人助力
     * @param help
     */
    @Override
    public void helpOthers(ActivityHelp help) {

        help.setCreateTime(DateUtil.currentSecond());
        help.setType(0);
        activityPrayMapper.saveActivityHelp(help);
    }


    /**
     * 保存微信授权用户信息
     * @param user
     */
    @Override
    public void saveWXUser(WXUser user) {

        if (StringUtils.isNotBlank(user.getOpenid())){
            user.setCreateTime(DateUtil.currentSecond());
            user.setUpdateTime(DateUtil.currentSecond());
            WXUser wxUser = activityPrayMapper.findWXUserByOpenid(user.getOpenid());
            if (null == wxUser){
                activityPrayMapper.saveWXUser(user);
            }else {
                activityPrayMapper.updateWXUser(user);
            }
        }
    }

    /**
     * 助力主页
     * @param fromId
     * @return
     */
    @Override
    public ActivityPrayVO getHelpHomePage(String fromId,String toId) {

        ActivityPrayVO prayVO = activityPrayMapper.getHelpHomePage(toId);
        ActivityHelp help = activityPrayMapper.findByFromIdAndToId(fromId,toId);

        ActivityPrayVO vo = activityPrayMapper.findPrayInfo(fromId);
        //判断用户是否已祈福
        if (null != vo){
            prayVO.setState(1);
        }
        if (null == help){
            //未助力
            prayVO.setStatus(0);
        }else {
          if (help.getCreateTime() != null){
            String currentTime = DateUtil.timestampToDates(DateUtil.currentSecond(),DateUtil.DATE_PATTON_DEFAULT);
            Integer endTime = DateUtil.getDateToLinuxTime(currentTime+" 00:00:00",DateUtil.TIME_PATTON_DEFAULT);
            if (help.getCreateTime() > endTime){
                prayVO.setStatus(1);
            }else {
                prayVO.setStatus(0);
            }
          }
        }
        return prayVO;
    }

    /**
     * 判断用户是否已经祈福
     * @param uuid
     * @return
     */
    @Override
    public boolean isPray(String uuid) {
        //判断用户是否已经祈福
        ActivityPray activityPray = activityPrayMapper.findByFromUserId(uuid);
        if (null != activityPray){
            return true;
        }
        return false;
    }

    /**
     * 分页获取祈福信息
     * @param pageNo
     * @return
     */
    @Override
    public Map<String,Object> findAllByPageNo(Integer pageNo) {

        PageHelper.startPage(pageNo,5,"update_time desc");
        List<ActivityPrayVO> prayList = activityPrayMapper.findAll();
        for (ActivityPrayVO vo : prayList){
            vo.setTimeStr(DateFormat.format(DateUtil.timestampToDates(vo.getUpdateTime(),DateUtil.TIME_PATTON_DEFAULT)));
        }
        PageInfo pageInfo = new PageInfo(prayList);
        Map<String,Object> map = new HashMap<>();
        map.put("prayList",prayList);
        map.put("isHasNextPage",pageInfo.isHasNextPage());

        return map;
    }

}
