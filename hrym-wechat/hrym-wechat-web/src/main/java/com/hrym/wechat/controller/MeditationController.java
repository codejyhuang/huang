package com.hrym.wechat.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.wechat.smallProgram.MeditationParam;
import com.hrym.wechat.smallProgram.MeditationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hrym13 on 2018/4/22.
 */
@Controller
@RequestMapping("/hrym/program")
public class MeditationController extends BaseController {

    @Autowired
    private MeditationService meditationService;

    @RequestMapping("/MeditationController")
    @Allowed
    @ResponseBody
    public BaseResult MeditationController( @RequestBody MeditationParam param){

        String cmd = param.getCmd();
        if ("insertMeditation".equals(cmd)){
            return insertMeditation(param);
        }
            return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    /**
     * 微信群与人关系录入
     * @param param
     * @return
     */
    public BaseResult insertMeditation(MeditationParam param){
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        meditationService.insertMeditation(param);
        return new BaseResult(code,message,null);
    }
}
