package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.app.dao.MsgInfoMgrMapper;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.PageInfo;
import com.hrym.common.message.KafkaProducerSingleton;
import com.hrym.common.message.MessageBean;
import com.hrym.common.message.MessageConstant;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.dao.model.VO.UserInfoVO;
import com.hrym.rpc.app.dao.model.association.MsgInfo;
import com.hrym.rpc.app.dao.model.task.FeedBack;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.app.util.Result;
import com.hrym.app.service.UserInfoMgrService;
import com.hrym.app.dao.UserInfoMgrMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.hrym.common.util.DateUtil.DATE_PATTON_DEFAULT2;
import static com.hrym.common.util.DateUtil.TIME_PATTON_DEFAULT;

/**
 * Created by hrym13 on 2017/9/7.
 */
@Service
public class UserInfoMgrServiceImpl implements UserInfoMgrService {

    @Autowired
    private UserInfoMgrMapper userInfoMgrMapper;
    @Autowired
    private MsgInfoMgrMapper msgInfoMgrMapper;

    /**
     * 用户详情列表
     * @param userInfo
     * @return
     */
    @Override
    public Map<String, Object> findAllUserlist(UserInfo userInfo) {

        UserInfo user = userInfoMgrMapper.findAllUserlist(userInfo.getUuid());
        Map<String, Object> map = Maps.newHashMap();
        map.put("nickName",user.getNickName());
        map.put("uuid",user.getUuid());
        map.put("realName",user.getRealName());
        map.put("identityCardNo",user.getIdentityCardNo());
        map.put("identityCardUrl1",user.getIdentityCardUrl1());
        map.put("identityCardUrl2",user.getIdentityCardUrl2());
        map.put("createdTime", DateUtil.timestampToDates(user.getCreatedTime(),TIME_PATTON_DEFAULT));
        map.put("updatedTime", DateUtil.timestampToDates(user.getRecentTime(),TIME_PATTON_DEFAULT));
        map.put("ymd", DateUtil.timestampToDates(user.getYmd(),DATE_PATTON_DEFAULT2));
        if (null != user.getIdentityAuthState()){
            switch (user.getIdentityAuthState()){

                case 0 : map.put("identityAuthState","待认证"); break;
                case 1 : map.put("identityAuthState","已认证"); break;
                case 2 : map.put("identityAuthState","未通过"); break;
                case 3 : map.put("identityAuthState","未认证"); break;
            }
        }
        if (null != user.getSex()){
            switch (user.getSex()){

                case 0 : map.put("sex","男"); break;
                case 1 : map.put("sex","女"); break;
            }
        }
        if (null != user.getUserTatus()){
            switch (user.getUserTatus()){
                case 1 : map.put("userTatus","普通用户"); break;
                case 2 : map.put("userTatus","上师"); break;
            }
        }
        if (null !=user.getStatus()){
            switch (user.getStatus()){
                case 1 : map.put("status","正常使用"); break;
                case 0 : map.put("status","已注销"); break;
            }
        }
        if (null != user.getSource()){
            switch (user.getSource()){
                case 0 : map.put("source","手机"); map.put("registrable",user.getMobile()); break;
                case 1 : map.put("source","微信"); map.put("registrable",user.getSocialUid()); break;
                case 2 : map.put("source","QQ"); map.put("registrable",user.getSocialUid()); break;
                case 3 : map.put("source","微博");  map.put("registrable",user.getSocialUid()); break;
                case 4 : map.put("source","web");  map.put("registrable",user.getSocialUid()); break;
            }
        }

        return map;
    }


    /**
     * 用户列表
     * @param
     * @return
     */
    @Override
    public Result findAllUser(Integer page, Integer rows,Integer created_time) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(page,rows,"created_time desc");
        List<UserInfo> userInfos = userInfoMgrMapper.findAllUser();

        PageInfo pageInfo = new PageInfo(userInfos );
        List< Map<String,Object >> list = new ArrayList<>();

        for (UserInfo u : userInfos){
            Map<String, Object> map = Maps.newHashMap();
            map.put("uuid",u.getUuid());
            map.put("nickName",u.getNickName());
            map.put("mobile",u.getMobile());
            map.put("source",u.getSource());
            if (null != u.getStatus()){
                if (1 == u.getStatus()){
                    map.put("status","正常使用");
                }else {
                    map.put("status","已注销");
                }
            }

            map.put("createdTime", DateUtil.timestampToDates(u.getCreatedTime(),TIME_PATTON_DEFAULT));
            map.put("updatedTime", DateUtil.timestampToDates(u.getRecentTime(),TIME_PATTON_DEFAULT));
            if (null != u.getIdentityAuthState()){
                switch (u.getIdentityAuthState()){
                    case 0 : map.put("identityAuthState","待认证"); break;
                    case 1 : map.put("identityAuthState","已认证"); break;
                    case 2 : map.put("identityAuthState","未通过"); break;
                    case 3 : map.put("identityAuthState","未认证"); break;
                }
            }
            if (null !=u.getStatus()){
                switch (u.getStatus()){
                    case 1 : map.put("status","正常使用"); break;
                    case 0 : map.put("status","已注销"); break;
                }
            }
            if (null != u.getSource()){
                switch (u.getSource()){
                    case 0 : map.put("source","手机"); map.put("registrable",u.getMobile()); break;
                    case 1 : map.put("source","微信"); map.put("registrable",u.getSocialUid()); break;
                    case 2 : map.put("source","QQ"); map.put("registrable",u.getSocialUid()); break;
                    case 3 : map.put("source","微博");  map.put("registrable",u.getSocialUid()); break;
                    case 4 : map.put("source","web");  map.put("registrable",u.getSocialUid()); break;
                }
            }
            list.add(map);
        }
        long total = pageInfo.getTotal();

        return new Result(code,message,total,list);
    }

    /**
     * 实名认证审核列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result certification(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(page,rows);
        //0:待认证，1:成功，2:失败，3:未认证
        List<UserInfo> userInfos = userInfoMgrMapper.findUserByStatus(0);

        PageInfo pageInfo = new PageInfo(userInfos);
        List< Map<String,Object >> list = new ArrayList<>();

        for (UserInfo u : userInfos){
            Map<String, Object> map = Maps.newHashMap();
            map.put("uuid",u.getUuid());
            map.put("identityAuthState",u.getIdentityAuthState());
            map.put("nickName",u.getNickName());
            map.put("realName",u.getRealName());
            map.put("identityCardNo",u.getIdentityCardNo());
            map.put("identityCardUrl1",u.getIdentityCardUrl1());
            map.put("identityCardUrl2",u.getIdentityCardUrl2());
            list.add(map);
        }

        long total = pageInfo.getTotal();

        return new Result(code,message,total,list);
    }

    /**
     * 审核认证
     * @param userInfo
     * @return
     */
    @Override
    public Result updateCertification(UserInfo userInfo) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        userInfoMgrMapper.updateCertification(userInfo);

        //定义社群消息对象
        MsgInfo msgInfo = new MsgInfo();
        msgInfo.setMsgTypeValue(userInfo.getUuid());
        msgInfo.setToId(userInfo.getUuid());
        //3表示审核
        msgInfo.setContentType(3);
        //0：未读；1:已读
        msgInfo.setIsread(0);
        if (userInfo.getIdentityAuthState() == 1){
            //0：拒绝；1：同意；2：审核中
            msgInfo.setStatus(1);
        }
        if (userInfo.getIdentityAuthState() == 2){
            //0：拒绝；1：同意；2：审核中
            msgInfo.setStatus(0);
        }
        //1:系统；2：社群；3：功课;4:用户认证
        msgInfo.setMsgType(4);
        msgInfo.setCreateTime(DateUtil.currentSecond());
        //插入一条社群审批消息
        msgInfoMgrMapper.insertMsgInfo(msgInfo);

        MsgInfo msge = new MsgInfo();
        msge.setToId(userInfo.getUuid());
        msge.setIsread(1);
        msge.setMsgType(4);
        msge.setContentType(2);
        //更新申请消息为已读
        msgInfoMgrMapper.updateIsReadByUuidAndType(msge);

        try {
            int id = msgInfoMgrMapper.getLastInsertId();
            //消息推送
            MessageBean msg = new MessageBean();
            msg.setMsgId(id);
            msg.setMsgType(4);       //消息类型(1:系统；2：社群；3：功课;4:用户认证)
            msg.setTitle("慧修行");
            msg.setSendType(3);     //发送方式(1:广播，2：标签，3：点对点)
            msg.setSendTypeValue(String.valueOf(userInfo.getUuid()));
            msg.setStatus(userInfo.getIdentityAuthState());       //社群：0:社群申请，1: 审批通过，2: 审批驳回，3:退出群 用户管理：0:认证中，1:审批通过，2:审批驳回
            msg.setMsgTypeValue(String.valueOf(userInfo.getUuid()));
            if (userInfo.getIdentityAuthState() == 1){
                msg.setContent("实名认证已通过，您已获得“创建社群”权限。点击创建社群");
            }
            if (userInfo.getIdentityAuthState() == 2){
                msg.setContent("抱歉，您提交的实名认证资料有误，烦请核对后重新认证。点击重新认证。");
            }
            KafkaProducerSingleton.getInstance().sendKafkaMessage(MessageConstant.TOPIC_HRYM_MESSAGE,msg);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new Result(code,message,null);
    }

    /**
     * 用户名称搜索
     * @param userInfo
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result searchNickName(UserInfo userInfo, Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null==userInfo.getNickName()){

            return findAllUser(page, rows,userInfo.getCreatedTime());
        }
        //分页核心代码
        PageHelper.startPage(page,rows,"created_time desc");
        List<UserInfo> userInfos = userInfoMgrMapper.searchNickName(userInfo.getNickName());

        PageInfo pageInfo = new PageInfo(userInfos);
        List< Map<String,Object >> list = new ArrayList<>();

        if (0 < userInfos.size()) {
            for (UserInfo u : userInfos) {

                Map<String, Object> map = Maps.newHashMap();
                map.put("uuid", u.getUuid());
                map.put("nickName", u.getNickName());
                map.put("mobile", u.getMobile());
                map.put("source", u.getSource());
                map.put("createdTime", DateUtil.timestampToDates(u.getCreatedTime(), TIME_PATTON_DEFAULT));
                map.put("updatedTime", DateUtil.timestampToDates(u.getRecentTime(), TIME_PATTON_DEFAULT));
                if (null != u.getIdentityAuthState()) {
                    switch (u.getIdentityAuthState()) {

                        case 0:
                            map.put("identityAuthState", "待认证");
                            break;
                        case 1:
                            map.put("identityAuthState", "已认证");
                            break;
                        case 2:
                            map.put("identityAuthState", "未通过");
                            break;
                        case 3:
                            map.put("identityAuthState","未认证");
                            break;

                    }
                }
                if (null !=u.getStatus()){
                    switch (u.getStatus()){
                        case 1 : map.put("status","正常使用");
                        break;
                        case 0 : map.put("status","已注销");
                        break;
                    }
                }
                if (null != u.getSource()) {
                    switch (u.getSource()) {
                        case 0:
                            map.put("source", "手机");map.put("registrable", u.getMobile());
                            break;
                        case 1:
                            map.put("source", "微信");map.put("registrable", u.getSocialUid());
                            break;
                        case 2:
                            map.put("source", "QQ");map.put("registrable", u.getSocialUid());
                            break;
                        case 3:
                            map.put("source", "微博");map.put("registrable", u.getSocialUid());
                            break;
                        case 4:
                            map.put("source", "web");map.put("registrable", u.getSocialUid());
                            break;
                    }
                }
                list.add(map);
            }
        }
        long total = pageInfo.getTotal();
        return new Result(code,message,total,list);

    }


    /**
     * 用户最近登录时间搜索
     * @param userInfoVO
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result searchUserInfoByReTime(UserInfoVO userInfoVO, Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (StringUtils.isNotBlank(userInfoVO.getStartTimeis())){
            int start = DateUtil.getDateToLinuxTime(userInfoVO.getStartTimeis(),TIME_PATTON_DEFAULT);
            userInfoVO.setStartTime(start);
        }
        if(StringUtils.isNotBlank(userInfoVO.getEndTimeis())){
            int end = DateUtil.getDateToLinuxTime(userInfoVO.getEndTimeis(),TIME_PATTON_DEFAULT);
            userInfoVO.setEndTime(end);
        }
        if (null == userInfoVO.getEndTime() && null == userInfoVO.getStartTime()) {

            return findAllUser(page, rows, userInfoVO.getCreatedTime());

        } else if (null == userInfoVO.getEndTime() || null == userInfoVO.getStartTime()) {//两个时间有一个为空，走此程序

            if (null == userInfoVO.getEndTime()){

                //分页核心代码
                PageHelper.startPage(page, rows,"created_time desc");
                List<UserInfo> userInfos = userInfoMgrMapper.searchUserInfoByStartReTime(userInfoVO.getStartTime());

                PageInfo pageInfo = new PageInfo(userInfos);
                List<Map<String, Object>> list = new ArrayList<>();

                if (0 < userInfos.size()) {
                    for (UserInfo u : userInfos) {

                        Map<String, Object> map = Maps.newHashMap();
                        map.put("uuid", u.getUuid());
                        map.put("nickName", u.getNickName());
                        map.put("mobile", u.getMobile());
                        map.put("source", u.getSource());
                        map.put("createdTime", DateUtil.timestampToDates(u.getCreatedTime(), TIME_PATTON_DEFAULT));
                        map.put("updatedTime", DateUtil.timestampToDates(u.getRecentTime(), TIME_PATTON_DEFAULT));
                        if (null != u.getIdentityAuthState()) {
                            switch (u.getIdentityAuthState()) {

                                case 0:
                                    map.put("identityAuthState", "待认证");
                                    break;
                                case 1:
                                    map.put("identityAuthState", "已认证");
                                    break;
                                case 2:
                                    map.put("identityAuthState", "未通过");
                                    break;
                                case 3:
                                    map.put("identityAuthState", "未认证");
                                    break;

                            }
                        }
                        if (null != u.getStatus()) {
                            switch (u.getStatus()) {
                                case 1:
                                    map.put("status", "正常使用");
                                    break;
                                case 0:
                                    map.put("status", "已注销");
                                    break;
                            }
                        }
                        if (null != u.getSource()) {
                            switch (u.getSource()) {
                                case 0:
                                    map.put("source", "手机");
                                    map.put("registrable", u.getMobile());
                                    break;
                                case 1:
                                    map.put("source", "微信");
                                    map.put("registrable", u.getSocialUid());
                                    break;
                                case 2:
                                    map.put("source", "QQ");
                                    map.put("registrable", u.getSocialUid());
                                    break;
                                case 3:
                                    map.put("source", "微博");
                                    map.put("registrable", u.getSocialUid());
                                    break;
                                case 4:
                                    map.put("source", "web");
                                    map.put("registrable", u.getSocialUid());
                                    break;
                            }
                        }
                        list.add(map);
                    }
                }
                long total = pageInfo.getTotal();
                return new Result(code, message, total, list);
            }else {
                //分页核心代码
                PageHelper.startPage(page, rows,"created_time desc");
                List<UserInfo> userInfos = userInfoMgrMapper.searchUserInfoByEndReTime(userInfoVO.getEndTime());

                PageInfo pageInfo = new PageInfo(userInfos);
                List<Map<String, Object>> list = new ArrayList<>();

                if (0 < userInfos.size()) {
                    for (UserInfo u : userInfos) {

                        Map<String, Object> map = Maps.newHashMap();
                        map.put("uuid", u.getUuid());
                        map.put("nickName", u.getNickName());
                        map.put("mobile", u.getMobile());
                        map.put("source", u.getSource());
                        map.put("createdTime", DateUtil.timestampToDates(u.getCreatedTime(), TIME_PATTON_DEFAULT));
                        map.put("updatedTime", DateUtil.timestampToDates(u.getRecentTime(), TIME_PATTON_DEFAULT));
                        if (null != u.getIdentityAuthState()) {
                            switch (u.getIdentityAuthState()) {

                                case 0:
                                    map.put("identityAuthState", "待认证");
                                    break;
                                case 1:
                                    map.put("identityAuthState", "已认证");
                                    break;
                                case 2:
                                    map.put("identityAuthState", "未通过");
                                    break;
                                case 3:
                                    map.put("identityAuthState", "未认证");
                                    break;

                            }
                        }
                        if (null != u.getStatus()) {
                            switch (u.getStatus()) {
                                case 1:
                                    map.put("status", "正常使用");
                                    break;
                                case 0:
                                    map.put("status", "已注销");
                                    break;
                            }
                        }
                        if (null != u.getSource()) {
                            switch (u.getSource()) {
                                case 0:
                                    map.put("source", "手机");
                                    map.put("registrable", u.getMobile());
                                    break;
                                case 1:
                                    map.put("source", "微信");
                                    map.put("registrable", u.getSocialUid());
                                    break;
                                case 2:
                                    map.put("source", "QQ");
                                    map.put("registrable", u.getSocialUid());
                                    break;
                                case 3:
                                    map.put("source", "微博");
                                    map.put("registrable", u.getSocialUid());
                                    break;
                                case 4:
                                    map.put("source", "web");
                                    map.put("registrable", u.getSocialUid());
                                    break;
                            }
                        }
                        list.add(map);
                    }
                }
                long total = pageInfo.getTotal();
                return new Result(code, message, total, list);
            }

        } else {//两个时间都不能为空，走次程序
            //分页核心代码
            PageHelper.startPage(page, rows,"created_time desc");
            List<UserInfo> userInfos = userInfoMgrMapper.searchUserInfoByReTime(userInfoVO.getStartTime(), userInfoVO.getEndTime());

            PageInfo pageInfo = new PageInfo(userInfos);
            List<Map<String, Object>> list = new ArrayList<>();

            if (0 < userInfos.size()) {
                for (UserInfo u : userInfos) {

                    Map<String, Object> map = Maps.newHashMap();
                    map.put("uuid", u.getUuid());
                    map.put("nickName", u.getNickName());
                    map.put("mobile", u.getMobile());
                    map.put("source", u.getSource());
                    map.put("createdTime", DateUtil.timestampToDates(u.getCreatedTime(), TIME_PATTON_DEFAULT));
                    map.put("updatedTime", DateUtil.timestampToDates(u.getRecentTime(), TIME_PATTON_DEFAULT));
                    if (null != u.getIdentityAuthState()) {
                        switch (u.getIdentityAuthState()) {

                            case 0:
                                map.put("identityAuthState", "待认证");
                                break;
                            case 1:
                                map.put("identityAuthState", "已认证");
                                break;
                            case 2:
                                map.put("identityAuthState", "未通过");
                                break;
                            case 3:
                                map.put("identityAuthState", "未认证");
                                break;

                        }
                    }
                    if (null != u.getStatus()) {
                        switch (u.getStatus()) {
                            case 1:
                                map.put("status", "正常使用");
                                break;
                            case 0:
                                map.put("status", "已注销");
                                break;
                        }
                    }
                    if (null != u.getSource()) {
                        switch (u.getSource()) {
                            case 0:
                                map.put("source", "手机");
                                map.put("registrable", u.getMobile());
                                break;
                            case 1:
                                map.put("source", "微信");
                                map.put("registrable", u.getSocialUid());
                                break;
                            case 2:
                                map.put("source", "QQ");
                                map.put("registrable", u.getSocialUid());
                                break;
                            case 3:
                                map.put("source", "微博");
                                map.put("registrable", u.getSocialUid());
                                break;
                            case 4:
                                map.put("source", "web");
                                map.put("registrable", u.getSocialUid());
                                break;
                        }
                    }
                    list.add(map);
                }
            }
            long total = pageInfo.getTotal();
            return new Result(code, message, total, list);

        }
    }

    /**
     * 用户反馈
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllFeedBack(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(page,rows,"create_time desc");
        List<FeedBack> feedBacks = userInfoMgrMapper.findAllFeedBack();

        for (FeedBack f :feedBacks){
            if (null != f.getCreateTime()){
                String create = DateUtil.timestampToDates(f.getCreateTime(),TIME_PATTON_DEFAULT);
                f.setCreateTimeis(create);
            }
            if (null !=f.getUpdateTime()){
                String update = DateUtil.timestampToDates(f.getUpdateTime(),TIME_PATTON_DEFAULT);
                f.setUpdateTimeis(update);
            }
        }
        PageInfo pageInfo = new PageInfo(feedBacks);
        long total = pageInfo.getTotal();
        return new Result(code,message,total,feedBacks);
    }

    /**
     * 反馈内容
     * @param feedBack
     * @return
     */
    public Result updateFeedBack(FeedBack feedBack){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != feedBack.getResolution()) {
            feedBack.setUpdateTime(DateUtil.currentSecond());
            feedBack.setState(2);
            userInfoMgrMapper.updateFeedBack(feedBack);
        }

        return new Result(code,message,null);
    }

}
