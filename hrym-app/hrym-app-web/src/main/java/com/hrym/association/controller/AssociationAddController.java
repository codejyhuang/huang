package com.hrym.association.controller;

import com.alibaba.fastjson.JSON;
import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.common.logUtil.GwsLogger;
import com.hrym.common.logUtil.GwsLoggerTypeEnum;
import com.hrym.common.logUtil.LogBean;
import com.hrym.common.util.RedisUtil;
import com.hrym.rpc.app.common.constant.AssociationParam;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.association.AssociationAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 社群控制器类
 * Created by mj on 2017/8/31.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class AssociationAddController extends BaseController {

    @Autowired
    private AssociationAddService associationAddService;

    @RequestMapping(value = "/association")
    @ResponseBody
    @Allowed
    public BaseResult findAssociation(@RequestBody AssociationParam param){

        String cmd = param.getCmd();
        if ("homePage".equals(cmd)){
            return getAssociationHomepage(param);
        }
        if ("banner".equals(cmd)){
            return getBanner();
        }
        if ("associationList".equals(cmd)){
            return getAssociationList(param);
        }
        if ("detail".equals(cmd)){
            return doAssociationDetails(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }


    @RequestMapping(value = "/associationAdd")
    @ResponseBody
    public BaseResult associationAdd(@RequestBody AssociationParam param){

        String cmd = param.getCmd();
        if ("add".equals(cmd)){
            return createAssociation(param);
        }
        if ("myAssociation".equals(cmd)){
            return getMyAssociation(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    /**
     * 社群主页
     * @param param
     * @return
     */
    public BaseResult getAssociationHomepage(AssociationParam param){

        return associationAddService.getAssociationHomepage(param);
    }

    /**
     * 社群主页接口-我加入的社群
     * @param param
     * @return
     */
    public BaseResult getMyAssociation(AssociationParam param){

        return associationAddService.getMyAssociation(param);
    }

    /**
     * 社群主页接口-banner
     * @return
     */
    public BaseResult getBanner(){

        return associationAddService.getBanner();
    }

    /**
     * 社群列表
     * @param param
     * @return
     */
    public BaseResult getAssociationList(AssociationParam param){

        return associationAddService.getAssociationList(param);
    }

    /**
     * 社群详情
     * @param param
     * @return
     */
    public BaseResult doAssociationDetails(AssociationParam param){

        return associationAddService.associationDetails(param);
    }

    /**
     * 创建社群
     * @param param
     * @return
     */
    public BaseResult createAssociation(AssociationParam param){

        BaseResult ret = associationAddService.createAssociation(param);
        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()),UserInfo.class);
        LogBean bean = new LogBean();
        bean.setUuid(user.getUuid());
        bean.setGroup(GwsLoggerTypeEnum.ASSOCIATION.getType());
        bean.setGroupValue(Integer.valueOf(String.valueOf(ret.getData())));
        bean.setGroupValueDesc(param.getAssociationName());
        GwsLogger.info("创建社群",bean);
        return ret;
    }

}
