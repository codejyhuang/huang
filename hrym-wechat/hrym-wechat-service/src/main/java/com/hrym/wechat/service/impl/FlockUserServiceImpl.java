package com.hrym.wechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.wechat.entity.*;
import com.hrym.wechat.mapper.*;
import com.hrym.wechat.service.IFlockUserService;
import com.hrym.wechat.smallProgram.WechatFlockParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class FlockUserServiceImpl implements IFlockUserService {
    
    @Autowired
    private FlockUserMapper flockUserMapper;
    @Autowired
    private FlockMapper flockMapper;
    @Autowired
    private FlockItemMapper flockItemMapper;
    @Autowired
    private TaskCommonMapper taskCommonMapper;
    @Autowired
    private FlockItemUserCountMapper flockItemUserCountMapper;
    @Autowired
    private ItemLessonMapper itemLessonMapper;
    
    /**
     * 查询群成员列表
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> flockMember(WechatFlockParam param) {
        Map<String, Object> map = new HashMap<>();
        List<FlockUser> adminList = flockUserMapper.queryByFlockId(param.getId(), FlockUser.ROLE_ADMIN, null);
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<FlockUser> flockUserList = flockUserMapper.queryByFlockId(param.getId(), FlockUser.ROLE_MEMBER, param.getKeywords());
        PageInfo pageInfo = new PageInfo(flockUserList);
        //获取群主的用户信息
        FlockUser createUser = adminList.get(0);
        map.put("flockMember", flockUserList);
        map.put("hasNextPage", pageInfo.isHasNextPage());
        map.put("totalPages", pageInfo.getPages());
        map.put("total", pageInfo.getTotal());
        map.put("createUser", createUser);
        map.put("isAdmin", createUser.getUuid().equals(param.getUUid()) ? 1 : 0);
        return map;
    }
    
    /**
     * 加入共修群
     *
     * @param param synchronized
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public synchronized Map<String, Object> joinFlock(WechatFlockParam param) {
        Map<String, Object> map = new HashMap<>();
        FlockUser fUser = flockUserMapper.queryCountByFlockIdAndUuid(param.getId(), param.getUUid());
        if (fUser != null) {
            map.put("state", 1);
            map.put("errorMsg", "您已经加入此共修群,请勿频繁操作");
            return map;
        }
        Flock flock = flockMapper.queryByPrimaryKey(param.getId());
        if (flock != null && Flock.DISSOLVE_STATE.equals(flock.getState())) {
            map.put("state", 1);
            map.put("errorMsg", "此共修群已解散");
            return map;
        }
        FlockUser flockUser = new FlockUser();
        flockUser.setUuid(param.getUUid());
        flockUser.setType(FlockUser.ROLE_MEMBER);
        flockUser.setFlockId(param.getId());
        flockUser.setCreateTime(DateUtil.currentSecond());
        flockUser.setOrder(0);
        flockUserMapper.insert(flockUser);
        //更新共修群 字段信息  同修人数
        flockMapper.updateFlockJoinNum(param.getId(), 1);
        //维护app任务
        String tableName = "";
        //查询加入群的所有功课
        List<FlockItem> flockItemList = flockItemMapper.queryByFlockId(param.getId());
        if (flockItemList.size() != 0) {
            for (FlockItem flockItem : flockItemList) {
                
                itemLessonMapper.updateOnlineNum(flockItem.getItemId(), flockItem.getItemContentId(), flockItem.getType(), 1);
                FlockItemUserCount flockItemUserCount = flockItemUserCountMapper.queryByFlockIdAndItemIdAndItemContentIdAndTypeAndUuid(param.getId(), flockItem.getItemId(),
                        flockItem.getItemContentId(), flockItem.getType(), param.getUUid());
                //如果用户退出过该群  无需添加数据    没有则加入
                if (flockItemUserCount == null) {
                    //维护  功课用户的关系
                    flockItemUserCount = new FlockItemUserCount();
                    flockItemUserCount.setItemId(flockItem.getItemId());
                    flockItemUserCount.setFlockId(param.getId());
                    flockItemUserCount.setItemContentId(flockItem.getItemContentId());
                    flockItemUserCount.setUuid(param.getUUid());
                    flockItemUserCount.setTotal(new Double(0));
                    flockItemUserCount.setType(flockItem.getType());
                    flockItemUserCountMapper.save(flockItemUserCount);
                }
                
                if (flockItem.getType().equals(0)) {
                    tableName = "t_task_plan_lesson";
                } else if (flockItem.getType().equals(1)) {
                    tableName = "t_task_plan_custom";
                } else {
                    tableName = "t_bookcase";
                }
                int count = taskCommonMapper.queryCountByItemIdAndItemContentIdAndTableNameAndUuid(flockItem.getType(), flockItem.getItemId(), flockItem.getItemContentId(), param.getUUid(), tableName, null);
                //未删除的为零
                if (count == 0) {
//                    if (count == 0) {
                        //全部状态为0  创建一条
                        TaskCommon taskCommon = new TaskCommon();
                        taskCommon.setItemId(flockItem.getItemId());
                        taskCommon.setItemContentId(flockItem.getItemContentId());
                        taskCommon.setCreateTime(DateUtil.currentSecond());
                        taskCommon.setUuid(param.getUUid());
                        taskCommonMapper.insert(taskCommon, tableName, flockItem.getType());
//                    }
                } else {
                    int existCount = taskCommonMapper.queryCountByItemIdAndItemContentIdAndTableNameAndUuid(flockItem.getType(), flockItem.getItemId(), flockItem.getItemContentId(), param.getUUid(), tableName, 1);
                    if (existCount==0){
                        if (flockItem.getType().equals(2)) {
                            //有未删除   更新
                            taskCommonMapper.updateBookIsExist(flockItem.getType(), flockItem.getItemId(), flockItem.getItemContentId(), param.getUUid(), tableName);
                        } else {
                            //有未删除   更新
                            taskCommonMapper.updateIsExist(flockItem.getType(), flockItem.getItemId(), flockItem.getItemContentId(), param.getUUid(), tableName);
                        }
                    }

                }
            }
        }
        map.put("state", 0);
        return map;
        
    }
    
    /**
     * 移除群成员
     *
     * @param param
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeFlockUser(WechatFlockParam param) {
        HashSet<Integer> userIds = new HashSet<>(param.getUserIds());
        List<Integer> userIdList = new ArrayList<>(userIds);
        if (userIdList.size() != 0) {
            flockUserMapper.batchRemoveFlockUser(param.getId(), userIdList);
            //更新共修群 字段信息  同修人数
            flockMapper.updateFlockJoinNum(param.getId(), -userIdList.size());
            //修改功课的 online_num
            List<FlockItem> flockItemList = flockItemMapper.queryByFlockId(param.getId());
            for (FlockItem flockItem : flockItemList) {
                itemLessonMapper.updateOnlineNum(flockItem.getItemId(), flockItem.getItemContentId(), flockItem.getType(), -userIdList.size());
            }
        }
    }
    
    /**
     * 退出共修群
     *
     * @param param
     */
    @Override
    public void dropFlock(WechatFlockParam param) {
        flockUserMapper.batchRemoveFlockUser(param.getId(), Arrays.asList(param.getUUid()));
        //更新共修群 字段信息  同修人数
        flockMapper.updateFlockJoinNum(param.getId(), -1);
        //修改功课的 online_num
        List<FlockItem> flockItemList = flockItemMapper.queryByFlockId(param.getId());
        for (FlockItem flockItem : flockItemList) {
            itemLessonMapper.updateOnlineNum(flockItem.getItemId(), flockItem.getItemContentId(), flockItem.getType(), -1);
        }
    }
    
    /**
     * 解散共修群
     *
     * @param param
     */
    @Override
    public Map<String, Object> dissolveFlock(WechatFlockParam param) {
        Map<String, Object> map = new HashMap<>();
        List<FlockUser> flockUserList = flockUserMapper.queryByFlockId(param.getId(), FlockUser.ROLE_MEMBER, null);
        map.put("state", 0);
        if (flockUserList.size() != 0) {
            map.put("state", 1);
            map.put("errorMsg", "请先清空群成员,才可解散共修群");
        }
        flockUserMapper.batchRemoveFlockUser(param.getId(), Arrays.asList(param.getUUid()));
        flockMapper.logicDeleteFlock(param.getId(), Flock.DISSOLVE_STATE);
        //修改功课的 online_num
        List<FlockItem> flockItemList = flockItemMapper.queryByFlockId(param.getId());
        for (FlockItem flockItem : flockItemList) {
            itemLessonMapper.updateOnlineNum(flockItem.getItemId(), flockItem.getItemContentId(), flockItem.getType(), -1);
        }
        flockItemMapper.updateStateByFlockId(param.getId(), 1);
        return map;
    }
    
    @Override
    public Map<String, Object> checkFlockMember(WechatFlockParam param) {
        Map<String, Object> map = new HashMap<>();
        List<FlockUser> flockUserList = flockUserMapper.queryByFlockId(param.getId(), FlockUser.ROLE_MEMBER, null);
        map.put("state", 0);
        if (flockUserList.size() != 0) {
            //有群成员
            map.put("state", 1);
        }
        return map;
    }

}
