package com.hrym.association.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.common.logUtil.GwsLogger;
import com.hrym.common.logUtil.GwsLoggerTypeEnum;
import com.hrym.common.logUtil.LogBean;
import com.hrym.rpc.app.common.constant.AssociationParam;
import com.hrym.rpc.app.dao.model.association.Association;
import com.hrym.rpc.association.AssociationHandleService;
import com.hrym.rpc.association.dao.mapper.AssociationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 社群操作控制器类
 * Created by mj on 2017/9/13.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class AssociationHandleController extends BaseController{

    @Autowired
    private AssociationHandleService associationHandleService;

    @RequestMapping(value = "/associationOpt")
    @ResponseBody
    public BaseResult associationOpt(@RequestBody AssociationParam param){

        String cmd = param.getCmd();
        if ("apply".equals(cmd)){
            return associationApply(param);
        }
        if ("approve".equals(cmd)){
            return associationApprove(param);
        }
        if ("exit".equals(cmd)){
            return associationExit(param);
        }
        if ("homePage".equals(cmd)){
            return DoMyAssociationHomePage(param);
        }
        if ("memberList".equals(cmd)){
            return findMemberList(param);
        }
        if ("associationInfo".equals(cmd)){
            return getBackstageInfo(param);
        }
        if ("associationEdit".equals(cmd)){
            return associationEdit(param);
        }
        if ("publish".equals(cmd)){
            return publishTopic(param);
        }
        if ("infoList".equals(cmd)){
            return messageList(param);
        }
        if ("isRead".equals(cmd)){
            return isReadMessage(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    /**
     * 社群申请
     * @return
     */
    public BaseResult associationApply(AssociationParam param){

        BaseResult ret = associationHandleService.associationApply(param);
        LogBean bean = new LogBean();
        bean.setUuid(param.getUserId());
        bean.setGroup(GwsLoggerTypeEnum.ASSOCIATION_APPLY.getType());
        bean.setGroupValue(param.getAssociationId());
        bean.setGroupValueDesc(String.valueOf(ret.getData()));
        bean.setContent(param.getMsg());
        GwsLogger.info("社群申请",bean);


        return ret;
    }

    /**
     * 社群审批
     * @param param
     * @return
     */
    public BaseResult associationApprove(AssociationParam param){

        BaseResult ret = associationHandleService.associationApprove(param);
        if (1 == param.getApplyValue()){
            LogBean bean = new LogBean();
            bean.setUuid(param.getToUserId());
            bean.setGroup(GwsLoggerTypeEnum.ASSOCIATION_APPROVE.getType());
            bean.setGroupValue(param.getAssociationId());
            bean.setGroupValueDesc(String.valueOf(ret.getData()));
            GwsLogger.info("社群审批",bean);
        }

        return ret;
    }

    /**
     * 社群退出
     * @param param
     * @return
     */
    public BaseResult associationExit(AssociationParam param){

        return associationHandleService.associationExit(param);
    }

    /**
     * 我加入的社群首页
     * @param param
     * @return
     */
    public BaseResult DoMyAssociationHomePage(AssociationParam param){

        return associationHandleService.myAssociationHomePage(param);
    }

    /**
     * 社群成员列表
     * @param param
     * @return
     */
    public BaseResult findMemberList(AssociationParam param){

        return associationHandleService.findMemberList(param);
    }

    /**
     * 社群后台信息
     * @param param
     * @return
     */
    public BaseResult getBackstageInfo(AssociationParam param) {

        return associationHandleService.getBackstageInfo(param);
    }

    /**
     * 社群信息修改
     * @param param
     * @return
     */
    public BaseResult associationEdit(AssociationParam param) {

        return associationHandleService.associationEdit(param);
    }

    /**
     * 社群主题发表
     * @param param
     * @return
     */
    public BaseResult publishTopic(AssociationParam param) {

        return associationHandleService.publishTopic(param);
    }

    /**
     * 消息列表
     * @param param
     * @return
     */
    public BaseResult messageList(AssociationParam param) {

        return associationHandleService.messageList(param);
    }

    /**
     * 判断是否已读
     * @param param
     * @return
     */
    public BaseResult isReadMessage(AssociationParam param) {

        return associationHandleService.isReadMessage(param);
    }

}
