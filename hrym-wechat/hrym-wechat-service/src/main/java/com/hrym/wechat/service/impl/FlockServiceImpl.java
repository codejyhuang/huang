package com.hrym.wechat.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.LunarCalendar;
import com.hrym.common.util.NumUtil;
import com.hrym.wechat.entity.*;
import com.hrym.wechat.mapper.*;
import com.hrym.wechat.service.IFlockService;
import com.hrym.wechat.smallProgram.FlockRecordParam;
import com.hrym.wechat.smallProgram.ItemParam;
import com.hrym.wechat.smallProgram.WechatFlockParam;
import com.hrym.wechat.smallProgram.WechatFlockUserParam;
import com.hrym.wechat.vo.FlockRecordVo;
import com.hrym.wechat.vo.ItemVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

@Service
public class FlockServiceImpl implements IFlockService {

    @Autowired
    private FlockMapper flockMapper;

    @Autowired
    private FlockItemMapper flockItemMapper;

    @Autowired
    private ItemCustomMapper itemCustomMapper;

    @Autowired
    private ItemLessonMapper itemLessonMapper;

    @Autowired
    private FlockUserMapper flockUserMapper;

    @Autowired
    private FlockRecordMapper flockRecordMapper;

    @Autowired
    private ItemUserUnitMapper userUnitMapper;

    @Autowired
    private TaskCommonMapper taskCommonMapper;

    @Autowired
    private FlockItemUserCountMapper flockItemUserCountMapper;
    
    

    @Autowired
    private UserAccountMapper userAccountMapper;


    /**
     * 首页共修群列表
     *
     * @param uuid
     * @return
     */
    @Override
    public Map<String, Object> listFlockByUuid(Integer uuid) {
        Map<String, Object> map = new HashMap<>();
        List<Flock> flockList = flockMapper.listByIds(uuid);
        if (flockList.size() != 0) {
            for (Flock flock : flockList) {
                if (flock == null) {
                    continue;
                }
                StringBuilder sb = new StringBuilder(80);
                sb.append(flock.getItemJoinNum() + "位师兄共修");
                if (flock.getCreateUser() == null) {
                    WechatUserAccount wechatUserAccount = new WechatUserAccount();
                    wechatUserAccount.setNickName("师兄");
                    wechatUserAccount.setAvatar("http://upd.everglamming.com:8089/group1/M00/00/13/wKgAHFoBUH-ARZFiAAC0d7SdfuU124.jpg");
                    flock.setCreateUser(wechatUserAccount);
                }

                flock.setViewStr(flock.getCreateUser().getNickName() == null ? "师兄" : flock.getCreateUser().getNickName() +
                        "创建于 · " +
                        DateUtil.getTimeChaHour(DateUtil.formatIntDate(flock.getCreateTime()), new Date()));
                if (flock.getAvatarUrl() != null) {
                    flock.getCreateUser().setAvatar(flock.getAvatarUrl());
                }

                List<FlockItem> flockItemList = flock.getFlockItems();
                if (flockItemList.size() != 0) {
                    for (FlockItem flockItem : flockItemList) {
                        sb.append("<<");
                        if (flockItem != null) {
                            sb.append(flockItem.getLessonName() + ">> ");
                        }
                    }
                }
                flock.setLessonStr(sb.toString());
            }
        }
        map.put("flockList", flockList);
        return map;
    }
    
    /**
     * 创建共修群
     *
     * @param param
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> createFlock(WechatFlockParam param) {
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isNotBlank(param.getName())) {
            map.put("state", 1);
            map.put("errorMsg", "请输入正确的群名称");
            return map;
        }
        
        Integer flockCount = flockMapper.queryCountByName(param.getName());
        if (flockCount != null && flockCount > 0) {
            map.put("state", 2);
            map.put("errorMsg", "该群名已经存在,请重新输入群名称");
            return map;
        }
        


        WechatUserAccount userAccount = new WechatUserAccount();
        userAccount.setUuid(param.getUUid());
        //创建共修群
        Flock flock = new Flock();
        flock.setCreateUser(userAccount);
        flock.setCreateTime(DateUtil.currentSecond());
        flock.setDayDoneNum(new Double(0));
        flock.setTotalDoneNum(new Double(0));
        flock.setDedicationVerses(param.getDedicationVerses());
        flock.setName(param.getName());
        flock.setPrivacy(param.getPrivacy() == null ? Flock.DEFAULT_PRIVACY_STATE : param.getPrivacy());
        flock.setItemJoinNum(1);
        flock.setIntro(param.getIntro());
        flock.setItemNum(param.getVoList().size());
        flockMapper.createFlock(flock);
        //维护群功课关系
        for (ItemVO itemVO : param.getVoList()) {
            //增加系统功课的 online_num
            itemLessonMapper.updateOnlineNum(itemVO.getItemId(), itemVO.getItemContentId(), itemVO.getType(), 1);
            String unit;
            FlockItem flockItem = new FlockItem();
            flockItem.setCreateId(-1);
            flockItem.setDayDoneNum(new Double(0));
            flockItem.setTotalDoneNum(new Double(0));
            flockItem.setFlockId(flock.getId());
            flockItem.setItemId(itemVO.getItemId());
            flockItem.setOrder(0);
            flockItem.setState(0);
            flockItem.setType(itemVO.getType());
            flockItem.setItemContentId(itemVO.getItemContentId() == null ? 0 : itemVO.getItemContentId());
            flockItem.setCreateId(itemVO.getUuid() == null ? 0 : itemVO.getUuid());
            flockItem.setUnit("遍");
            flockItemMapper.insert(flockItem);
            
            
            //维护  功课用户的关系  t_flock_item_user_count
            FlockItemUserCount flockItemUserCount = new FlockItemUserCount();
            flockItemUserCount.setItemId(itemVO.getItemId());
            flockItemUserCount.setFlockId(flock.getId());
            flockItemUserCount.setItemContentId(itemVO.getItemContentId());
            flockItemUserCount.setUuid(param.getUUid());
            flockItemUserCount.setTotal(new Double(0));
            flockItemUserCount.setType(itemVO.getType());
            flockItemUserCountMapper.save(flockItemUserCount);
            
        }
        //维护 群 用户表
        FlockUser flockUser = new FlockUser();
        flockUser.setFlockId(flock.getId());
        flockUser.setType(FlockUser.ROLE_ADMIN);
        flockUser.setUuid(param.getUUid());
        flockUser.setOrder(0);
        flockUser.setCreateTime(DateUtil.currentSecond());
        flockUserMapper.insert(flockUser);
        Integer flockId = flock.getId();
        
        //维护app任务
        String tableName = "";
        //查询加入群的所有功课
//        List<FlockItem> flockItemList = flockItemMapper.queryByFlockId(param.getId());
        if (param.getVoList().size() != 0) {
            for (ItemVO flockItem : param.getVoList()) {
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
                    //全部状态为0  创建一条
                    TaskCommon taskCommon = new TaskCommon();
                    taskCommon.setItemId(flockItem.getItemId());
                    taskCommon.setItemContentId(flockItem.getItemContentId());
                    taskCommon.setCreateTime(DateUtil.currentSecond());
                    taskCommon.setUuid(param.getUUid());
                    taskCommonMapper.insert(taskCommon, tableName, flockItem.getType());
                }
                {
                    int existCount = taskCommonMapper.queryCountByItemIdAndItemContentIdAndTableNameAndUuid(flockItem.getType(), flockItem.getItemId(), flockItem.getItemContentId(), param.getUUid(), tableName, 1);
                    if (existCount == 0) {
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
        
        map.put("flockId", flockId);
        map.put("state", 0);
        
        return map;
        
    }
    
    /**
     * 是否加群
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> invitePage(WechatFlockUserParam param) {
        
        Map<String, Object> map = new HashMap<>();
        
        if (param != null && param.getId() != null && param.getUuid() != null) {
            
            FlockUser flockUser = flockMapper.listflockUserById(param.getUuid(), param.getId());
            //判断是否加群：《1：已加群》
            if (flockUser != null) {
                map.put("isAdd", 1);
            } else {
                map.put("isAdd", 0);
            }
            //查找群信息
            Flock flock = flockMapper.queryByPrimaryKey(param.getId());
            WechatUserAccount userAccount = flock.getCreateUser();
            map.put("flock", flock.getName());
            map.put("id", param.getId());
            map.put("avatar", userAccount.getAvatar());
            map.put("createrId", userAccount.getUuid());
            map.put("flockState", flock.getState());
        }
        return map;
        
    }
    
    /**
     * 群功课统计
     *
     * @param param
     */
    @Override
    public List flockStatistic(FlockRecordParam param) {

        List list = new ArrayList<>();
        String tableName = null;
        String tableName1 = null;
        //系统功课和经书
        String tableLessonName = "t_resource_content_book";
        //0:同年1：不是同年
        Integer year = 0;
        List<FlockRecordVo> flockList = null;
        FlockItem flockItem = null;


        for (FlockRecordVo vo : param.getList()) {
            FlockRecordVo flockRecordVo = new FlockRecordVo();
            FlockRecordVo flockRecordVoSum = null;
            Float numStr = new Float(0);
            if (vo.getFlockId() == null) {
                vo.setFlockId(param.getFlockId());
            }
            Flock flock = flockMapper.queryByPrimaryKey(param.getFlockId());
            flockRecordVo.setFlockName(flock.getName());
            if (StringUtils.isNotBlank(param.getStartTimes())) {
                flockRecordVo.setStartTimes(param.getStartTimes());
                Integer start = DateUtil.getTimeFormat(param.getStartTimes(), DateUtil.DATE_PATTON_DEFAULT);
                vo.setStartTime(start);
            }
            if (StringUtils.isNotBlank(param.getEndTimes())) {
                flockRecordVo.setEndTimes(param.getEndTimes());
                String tims = DateUtil.getAfterAnyDay(param.getEndTimes(), 1, DateUtil.DATE_PATTON_DEFAULT);
                Integer end = DateUtil.getTimeFormat(tims, DateUtil.DATE_PATTON_DEFAULT);
                vo.setEndTime(end);
                
            }
            if (StringUtils.isNotBlank(param.getStartTimes()) && StringUtils.isNotBlank(param.getEndTimes())) {
                //判断统计时间是否同一年
                int a = DateUtil.getYear(param.getEndTimes()) - DateUtil.getYear(param.getStartTimes());
                // a=0表示同年
                if (a == 0) {
                    tableName = "t_flock_record_" + DateUtil.getYear(param.getStartTimes());
                } else {
                    tableName = "t_flock_record_" + DateUtil.getYear(param.getStartTimes());
                    tableName1 = "t_flock_record_" + DateUtil.getYear(param.getStartTimes() + 1);
                    year = 1;
                }
                //判断统计时间是否同一天0:同一天
                try {
                    int daysBetween = DateUtil.daysBetween(vo.getStartTime(), vo.getEndTime());
                    if (daysBetween == 0) {
                        String tims = DateUtil.getAfterAnyDay(param.getStartTimes(), 1, DateUtil.DATE_PATTON_DEFAULT);
                        vo.setEndTime(DateUtil.getDateToLinuxTime(tims, DateUtil.DATE_PATTON_DEFAULT));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //查询当前用户的量词
                String unit = userUnitMapper.queryUnitByUuidAndItemIdAndItemContentIdAndType(param.getUuid(), vo.getItemId(),
                        vo.getItemContentId(), vo.getType());
                unit = StringUtils.isNotBlank(unit) ? unit : "遍";
                
                if (vo.getType() == 1) {
                    flockRecordVoSum = flockRecordMapper.findCustomSum(vo, tableName, tableName1, year);
                    if (flockRecordVoSum != null & flockRecordVoSum != null) {
                        flockRecordVo.setNumStr(NumUtil.getTotalNumStr(flockRecordVoSum.getNum(), "0") + unit);
                    } else {
                        flockRecordVo.setNumStr("0" + unit);
                    }
                    if (param.getPageSize() != null && param.getPageNumber() != null) {
                        PageHelper.startPage(param.getPageSize(), param.getPageNumber());
                    }
                    flockList = flockRecordMapper.queryByCUstomIds(vo, tableName, tableName1, year);
                    
                    
                } else {
                    flockRecordVoSum = flockRecordMapper.findLessonSum(vo, tableName, tableName1, year);
                    if (flockRecordVoSum != null & flockRecordVoSum != null) {
                        flockRecordVo.setNumStr(NumUtil.getTotalNumStr(flockRecordVoSum.getNum(), "0") + unit);
                    }
                    if (param.getPageSize() != null && param.getPageNumber() != null) {
                        PageHelper.startPage(param.getPageSize(), param.getPageNumber());
                    }
                    flockList = flockRecordMapper.queryByIds(vo, tableName, tableName1, year);
                }
                for (FlockRecordVo recordVo : flockList) {
                    recordVo.setNumStr(NumUtil.getTotalNumStr(recordVo.getNum(), "0") + unit);
                }
                
                PageInfo pageInfo = new PageInfo(flockList);
                long totalPages = pageInfo.getPages();
                System.out.println(totalPages);
                //群功课名称
                if ((vo.getType() == 1)) {
                    flockRecordVo.setVersionName(vo.getItemName());
                    flockRecordVo.setItemName(vo.getItemName());
                } else {
                    flockRecordVo.setVersionName(vo.getVersionName());
                    flockRecordVo.setItemName(vo.getItemName());
                }
                if (flockItem != null) {
                    flockRecordVo.setVersionName(flockItem.getVersionName());
                }
                flockRecordVo.setTotal(pageInfo.getTotal());
                flockRecordVo.setPeopleNum(pageInfo.getTotal());
                flockRecordVo.setTotalPages(totalPages);
                flockRecordVo.setFlockRecordVo(flockList);
                flockRecordVo.setItemContentId(vo.getItemContentId());
                flockRecordVo.setItemId(vo.getItemId());
                flockRecordVo.setType(vo.getType());
                flockRecordVo.setHasNextPage(pageInfo.isHasNextPage());
                flockRecordVo.setCurrentPage(param.getPageSize());
                list.add(flockRecordVo);
            }

        }

        return list;
    }

    /**
     * 查找群功课
     * <p>
     * 查找群功课《群统计》
     *
     * @param param
     * @return
     */
    @Override
    public List flockItemList(FlockRecordParam param) {
        
        String contentTable = null;
        
        FlockItem flockItem1 = null;
        List<FlockItem> list = flockItemMapper.queryByFlockId(param.getFlockId());
        for (FlockItem flockItem : list) {
            flockItem.setSrc(0);
            //经书
            if (flockItem.getType() == 2) {
                contentTable = "t_resource_content_book";
            } else {
                contentTable = "t_resource_content_lesson";
            }
            //自定义功课
            if (flockItem.getType() == 1) {
                
                flockItem1 = itemCustomMapper.findCustomName(flockItem.getItemId());
                if (flockItem1 != null) {
                    flockItem.setVersionName(flockItem1.getItemName());
                    flockItem.setItemName(flockItem1.getItemName());
                }
            } else {
                flockItem1 = flockItemMapper.selectFlockItemList(flockItem.getItemContentId(), contentTable);
                if (flockItem1 != null) {
                    flockItem.setVersionName(flockItem1.getVersionName());
                    flockItem.setItemName(flockItem1.getItemName());
                }
            }
            
        }
//        List<FlockItem> list = flockMapper.queryFlockItemListByFlockId(param.getFlockId());
        return list;
        
    }
    
    
    /**
     * 查询共修群页面详细信息
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> selectById(WechatFlockParam param) {
        Map<String, Object> map = new HashMap<>();
        //查询群信息
        Flock flock = flockMapper.queryByPrimaryKey(param.getId());
        //设置报数显示str
        flock.setDayNumStr(NumUtil.getTotalNumStr(flock.getDayDoneNum(), "0"));
        flock.setTotalNumStr(NumUtil.getTotalNumStr(flock.getTotalDoneNum(), "0"));
        WechatUserAccount userAccount = flock.getCreateUser();
        String timeStr = DateUtil.timestampToDates(flock.getCreateTime(), DateUtil.DATA_PATTON_YYYYMMDD2);
        
        //所有群功课
        List<FlockItem> lessonDOList = flockItemMapper.queryByFlockIdJoinView(flock.getId());
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<FlockRecordDO> flockRecordDOList = flockRecordMapper.queryByFlockId(flock.getId(), lessonDOList, param.getUUid());
        //遍历所有功课
        for (FlockRecordDO flockRecordDO : flockRecordDOList) {
            flockRecordDO.setReportStr(NumUtil.getTotalNumStr(flockRecordDO.getNum(), "0") + (StringUtils.isNotBlank(flockRecordDO.getUnit()) ? flockRecordDO.getUnit() : "遍"));
            //遍历所有 群功课
            for (FlockItem lessonDO : lessonDOList) {
                if ((lessonDO.getType().equals(lessonDO.getType())
                        && lessonDO.getType().equals(1)
                        && lessonDO.getItemId().equals(flockRecordDO.getItemId()))
                        
                        || (lessonDO.getType().equals(lessonDO.getType())
                        && lessonDO.getType().equals(0)
                        && lessonDO.getItemId().equals(flockRecordDO.getItemId())
                        && lessonDO.getItemContentId().equals(flockRecordDO.getItemContentId()))
                        
                        || (lessonDO.getType().equals(lessonDO.getType())
                        && lessonDO.getType().equals(2)
                        && lessonDO.getItemId().equals(flockRecordDO.getItemId())
                        && lessonDO.getItemContentId().equals(flockRecordDO.getItemContentId()))
                        ) {
                    flockRecordDO.setLessonName(lessonDO.getLessonName());
                    flockRecordDO.setTimeStr(DateUtil.getTimeChaHour(
                            DateUtil.formatIntDate(flockRecordDO.getCreateTime()), new Date()));
                }
            }
        }
        PageInfo<FlockRecordDO> pageInfo = new PageInfo<>(flockRecordDOList);
        map.put("flock", flock);
        map.put("createTimeStr", timeStr + "   " + DateUtil.dateToWeek(timeStr, DateUtil.DATA_PATTON_YYYYMMDD2));
        try {
            map.put("timeStr", DateUtil.daysBetween(flock.getCreateTime(), DateUtil.currentSecond()) + "天");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Integer> userIds = flockUserMapper.queryUuidListByFlockId(param.getId());
        map.put("isJoin", userIds.contains(param.getUUid()) ? 1 : 0);
        map.put("isAdmin", param.getUUid().equals(userAccount.getUuid()) ? 1 : 0);
        map.put("flockLessonList", lessonDOList);
        map.put("recordList", flockRecordDOList);
        map.put("totalPage", pageInfo.getPages());
        map.put("recordTotal", pageInfo.getTotal());
        map.put("hasNextPage", pageInfo.isHasNextPage());
        return map;
    }
    
    
    /**
     * 查看共修群简介回向
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryFlockIntro(WechatFlockParam param) {
        Map<String, Object> map = new HashMap<>();
        Flock flock = flockMapper.queryByPrimaryKey(param.getId());
        map.put("intro", flock.getIntro() == null ? null : flock.getIntro());
        map.put("privacy", flock.getPrivacy());
        map.put("dedicationVerses", flock.getDedicationVerses());
        return map;
    }
    
    /**
     * 编辑更新群简介回向
     *
     * @param param
     */
    @Override
    public void updateFlock(WechatFlockParam param) {
        if (param.getPrivacy() != null) {
            flockMapper.updateFlockPrivacy(param.getId(), param.getPrivacy());
        }
        // 群简介
        if (StringUtils.isNotBlank(param.getIntro())) {
            
            flockMapper.updateFlockIntro(param.getId(), param.getIntro());
        }
        // 群回向
        if (StringUtils.isNotBlank(param.getDedicationVerses())) {
            
            flockMapper.updateFlockDedicationVerses(param.getId(), param.getDedicationVerses());
        }
    }
    
    /**
     * 添加功课
     *
     * @param param
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addItem(WechatFlockParam param) {
        if (param.getVoList().size() != 0) {
            
            List<ItemVO> voList = param.getVoList();
            FlockItem flockItem = flockItemMapper.queryByFlockIdAndItemIdAndContentIdAndType(param.getId(), voList.get(0).getItemId(), voList.get(0).getItemContentId(), voList.get(0).getType());
            if (flockItem != null && flockItem.getState() == 0) {
                return;
            }
            
            //更新群功课数量
            flockMapper.updateFlockItemNum(param.getId(), param.getVoList().size());
            //查询群用户数量
            Integer count = flockUserMapper.queryCountByFlockId(param.getId());
            //维护群功课关系
            for (ItemVO itemVO : param.getVoList()) {
//                FlockItem flockItem = flockItemMapper.queryByFlockIdAndItemIdAndContentIdAndType(param.getId(), itemVO.getItemId(),
//                        itemVO.getItemContentId(), itemVO.getType());
                if (flockItem != null && flockItem.getState() == 1) {
                    flockItem.setState(0);
                    flockItemMapper.updateFlockItem(flockItem);
                } else if (flockItem == null) {
                    flockItem = new FlockItem();
                    flockItem.setCreateId(itemVO.getUuid());
                    flockItem.setDayDoneNum(new Double(0));
                    flockItem.setTotalDoneNum(new Double(0));
                    flockItem.setFlockId(param.getId());
                    flockItem.setItemId(itemVO.getItemId());
                    flockItem.setOrder(0);
                    flockItem.setType(itemVO.getType());
                    //系统功课
                    flockItem.setItemContentId(itemVO.getItemContentId() == null ? 0 : itemVO.getItemContentId());
                    
                    flockItemMapper.insert(flockItem);
                }
                itemLessonMapper.updateOnlineNum(flockItem.getItemId(), flockItem.getItemContentId(), flockItem.getType(), count);
                
            }
        }
    }

    /**
     * 批量删除功课
     *
     * @param param
     */
    @Override
    public Map<String, Object> removeFlockLesson(WechatFlockParam param) {
        Map<String, Object> map = new HashMap<>();
        if (param.getVoList().size() != 0) {
            Flock flock = flockMapper.queryByPrimaryKey(param.getId());
            if (flock.getItemNum() <= param.getVoList().size()) {
                map.put("state", 1);
                map.put("errorMsg", "不可删除所有群功课");
                return map;
            }
            //查询群用户数量
            Integer count = flockUserMapper.queryCountByFlockId(param.getId());
            Integer i = 0;
            for (ItemVO itemVO : param.getVoList()) {
                int rows = itemLessonMapper.batchLogicRemoveFlockLesson(param.getId(), itemVO.getItemId(), itemVO.getItemContentId(), itemVO.getType(), 1);
                itemLessonMapper.updateOnlineNum(itemVO.getItemId(), itemVO.getItemContentId(), itemVO.getType(), -count);
                i += rows;
            }
            flockMapper.updateFlockItemNum(param.getId(), -i);
            
            map.put("state", 0);
        }
        
        return map;
    }
    
    /**
     * 共修首页报数《查找所有群功课，去重》
     *
     * @param param
     * @return
     */
    @Override
    public List queryFlockItemNameList(WechatFlockParam param) {
        
        String contentTable = "t_resource_content_lesson";
        
        FlockItem flockItem1 = null;
        List<FlockItem> list = null;
        if (param.getUUid() != null) {
            list = flockMapper.queryFlockItemList(param.getUUid());
            if (list != null) {
                for (FlockItem flockItem : list) {
                    //经书
                    if (flockItem.getType() == 2) {
                        contentTable = "t_resource_content_book";
                    } else {
                        contentTable = "t_resource_content_lesson";
                    }
                    //自定义功课
                    if (flockItem.getType() == 1) {
                        
                        flockItem1 = itemCustomMapper.findCustomName(flockItem.getItemId());
                        if (flockItem1 != null) {
                            flockItem.setVersionName(flockItem1.getItemName());
                            flockItem.setItemName(flockItem1.getItemName());
                        }
                    } else {
                        
                        flockItem1 = flockItemMapper.selectFlockItemList(flockItem.getItemContentId(), contentTable);
                        if (flockItem1 != null) {
                            flockItem.setVersionName(flockItem1.getVersionName());
                            flockItem.setItemName(flockItem1.getItemName());
                        }
                    }
                    
                }
            }
        }
        return list;
    }
    
    /**
     * 统计时间
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> flockStatisticTimes(WechatFlockParam param) {
        
        Flock flock = flockMapper.queryFlockCreatedTimeById(param.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("startTimes", DateUtil.timestampToDates(flock.getCreateTime(), DateUtil.DATE_PATTON_DEFAULT));
        map.put("endTimes", DateUtil.timestampToDates(DateUtil.currentSecond(), DateUtil.DATE_PATTON_DEFAULT));
        return map;
    }
    
    /**
     * 设置功课简介、隐私、量词
     *
     * @param param
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void flockItemSetting(ItemParam param) {
        
        ItemUserUnit itemUserUnit = new ItemUserUnit();
        
        itemUserUnit.setType(param.getType());
        itemUserUnit.setItemContentId(param.getItemContentId() == null ? 0 : param.getItemContentId());
        itemUserUnit.setUuid(param.getUserId());
        itemUserUnit.setItemId(param.getItemId());
        itemUserUnit.setUnit(param.getUnit());
        itemUserUnit.setIntro(param.getIntro());
        itemUserUnit.setUpdateTime(DateUtil.currentSecond());
        
        ItemUserUnit userUnit = userUnitMapper.queryItemUserUnit(param.getUserId()
                , param.getItemId()
                , param.getItemContentId()
                , param.getType());
        
        if (userUnit != null) {
            userUnitMapper.updateItemUserUnitById(itemUserUnit);
        } else {
            userUnitMapper.insert(itemUserUnit);
        }
    }
    
    /**
     * 查找功课简介、隐私、量词
     *
     * @param param
     */
    @Override
    public Map<String, Object> queryFlockItemSetting(ItemParam param) {
        
        
        Map<String, Object> map = new HashMap<>();
        
        ItemUserUnit userUnit = userUnitMapper.queryItemUserUnit(param.getUserId()
                , param.getItemId()
                , param.getItemContentId()
                , param.getType());
        if (userUnit != null) {
            map.put("unit", userUnit.getUnit());
            map.put("intro", userUnit.getIntro());
            map.put("id", userUnit.getId());
            
        } else {
            ItemUserUnit itemViewUnit = itemLessonMapper.queryItemViewUnit(param.getItemId(), param.getItemContentId(), param.getType());
            map.put("unit", (itemViewUnit.getUnit() == null ? 0 : itemViewUnit.getUnit()));
            map.put("intro", itemViewUnit.getIntro());
            map.put("id", null);
        }
        map.put("itemId", param.getItemId());
        map.put("itemContentId", param.getItemContentId());
        map.put("type", param.getType());
        return map;
    }
    
    /**
     * 可加入共修列表
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryJoinFlockList(WechatFlockParam param) {
        
        Map<String, Object> maps = new HashMap<>();
        List list = new ArrayList();
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<Flock> flockList = flockMapper.queryJoinFlockList(param.getUUid());
        if (flockList.size() != 0) {
            for (Flock flock : flockList) {
                Map<String, Object> map = new HashMap<>();
                flock.setViewStr(flock.getCreateUser().getNickName() +
                        "创建于 · " +
                        DateUtil.getTimeChaHour(DateUtil.formatIntDate(flock.getCreateTime()), new Date()));
                
                map.put("viewStr", flock.getViewStr());
                map.put("name", flock.getName());
                map.put("intro", flock.getIntro());
                map.put("privacy", flock.getPrivacy());
                map.put("uuid", flock.getCreateUser().getUuid());
                map.put("id", flock.getId());
                if (flock.getAvatarUrl() != null) {
                    flock.getCreateUser().setAvatar(flock.getAvatarUrl());
                    map.put("avatarUrl", flock.getAvatarUrl());
                } else {
                    map.put("avatarUrl", flock.getCreateUser().getAvatar());
                }
                list.add(map);
            }
        }
        PageInfo pageInfo = new PageInfo(flockList);
        long total = pageInfo.getTotal();
        long totalPages = pageInfo.getPages();
        maps.put("totalPages", totalPages);
        maps.put("total", total);
        maps.put("list", list);
        return maps;
    }
    
    /**
     * 群排序
     *
     * @param param
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateFlockUserOrder(WechatFlockParam param) {
        if (param.getFlockList().size() != 0) {
            for (FlockRecordVo fl : param.getFlockList()) {
                
                flockUserMapper.updateFlockUserOrder(fl.getFlockId(), fl.getOrder(), param.getUUid());
                
            }
        }
    }
    
    /**
     * 备份数据
     *
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> backItemDatas(Integer userId) {
        
        Map<String, Object> map = new HashMap<>();
        // 查找用户总计
        TaskCommon taskCommon = flockMapper.totalUserBackup(userId);
        
        map.put("count", taskCommon.getCount());
        map.put("doneNum", taskCommon.getDoneNum());
    
        try {
            int dayDown = DateUtil.daysBetween(taskCommon.getCreateTime(),DateUtil.currentSecond());
            map.put("timeStr",dayDown);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        map.put("timeStr", DateUtil.timestampToDates(
//                (taskCommon.getCreateTime() == null ? DateUtil.currentSecond() : taskCommon.getCreateTime()),
//                DateUtil.TIME_PATTON_DEFAULT));
        map.put("nickName",taskCommon.getNickName());
        
        // 用户功课详细数据
        List<TaskCommon> taskCommonList = flockMapper.queryItemListDeatil(userId);
        //报数详细
        List<AppRecordCommon> recordCommonList = itemLessonMapper.queryItemRecordList(userId);
        map.put("recordCommonList", recordCommonList);
        map.put("taskCommonList", taskCommonList);
        map.put("taskCommon", taskCommon);
        return map;
    }


//    @Scheduled(cron = "0/5 * * * * ?")
//    @Override
//    public void test(){
//        System.out.println("hello world");
//    }
    
    
    public static void main(String[] args) {
        String strs = DateUtil.format(new Date(), "yyyy年MM月dd日") + "  " + DateUtil.dateToWeek() + "  ";
        LunarCalendar cal = new LunarCalendar();
        System.out.println(cal);
        System.out.println(strs + cal.getStr());
    }
    
    
}
