package com.hrym.mine.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.config.ESCommon;
import com.hrym.common.logUtil.GwsLogger;
import com.hrym.common.logUtil.GwsLoggerTypeEnum;
import com.hrym.common.logUtil.LogBean;
import com.hrym.rpc.app.common.constant.MyParam;
import com.hrym.rpc.mine.ESSuggestService;
import com.hrym.rpc.mine.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 推荐控制器类
 * Created by mj on 2017/9/4.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class SuggestController extends BaseController {

    @Autowired
    private SuggestService suggestService;
    @Autowired
    private ESSuggestService esSuggestService;

    @RequestMapping(value = "/suggest")
    @Allowed
    @ResponseBody
    public BaseResult Suggest(@RequestBody MyParam param) {

        String cmd = param.getCmd();
        if ("firstPage".equals(cmd)){
            return suggestHomePage(param);
        }
        if ("getAll".equals(cmd)){
            return findAllByType(param);
        }
        if ("searchAll".equals(cmd)){
            return searchAllByType(param);
        }
        if ("duden".equals(cmd)) {
            return dudenIntro(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }


    /**
     * 推荐首页
     * @return
     */
    public BaseResult suggestHomePage(MyParam param){

        return suggestService.suggestHomePage(param);
    }

    /**
     * 基于类型查看全部
     * @param param
     * @return
     */
    public BaseResult findAllByType(MyParam param){

        return suggestService.findAllByType(param);
//        return esSuggestService.getAll(param);
    }


    /**
     * 基于类型搜索全部
     * @param param
     * @return
     */
    public BaseResult searchAllByType(MyParam param) {


        LogBean bean = new LogBean();
        bean.setGroup(GwsLoggerTypeEnum.SUGGEST.getType());
        bean.setContent(param.getFilterStr());
        GwsLogger.info("推荐首页搜索",bean);

//        return suggestService.searchAllByType(param);

        return esSuggestService.searchAll(param);
    }


    /**
     * 大词典详情
     *
     * @param param
     * @return
     */
    public BaseResult dudenIntro(MyParam param) {

        return suggestService.dudenIntro(param);
    }


}
