package com.hrym.auth.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.BookParam;
import com.hrym.rpc.app.common.constant.MeditationParam;
import com.hrym.rpc.auth.api.MeditationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by mj on 2018/5/28.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class MeditationController extends BaseController {

    @Autowired
    private MeditationService meditationService;

    @RequestMapping(value = "/meditation")
    @ResponseBody
    public BaseResult redirect(@RequestBody MeditationParam param) {

        String cmd = param.getCmd();

        if ("meditationList".equals(cmd)){
            return getHomePage(param);
        }
        if ("editTime".equals(cmd)){
            return editMeditation(param);
        }
        if ("report".equals(cmd)){
            return meditationReport(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    @RequestMapping(value = "/meditationList")
    @Allowed
    @ResponseBody
    public BaseResult doRedirect(@RequestBody MeditationParam param) {

        String cmd = param.getCmd();

        if ("meditationList".equals(cmd)){
            return getHomePage(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    /**
     * 获取禅修主页
     * @param param
     * @return
     */
    public BaseResult getHomePage(MeditationParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String,Object> map = meditationService.getHomePage(param);

        return new BaseResult(code,message,map);
    }

    /**
     * 修改禅修时长
     * @param param
     */
    public BaseResult editMeditation(MeditationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        meditationService.editMeditation(param);

        return new BaseResult(code,message,null);
    }

    /**
     * 禅修上报
     * @param param
     */
    public BaseResult meditationReport(MeditationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        meditationService.meditationReport(param);

        return new BaseResult(code,message,null);
    }
}
