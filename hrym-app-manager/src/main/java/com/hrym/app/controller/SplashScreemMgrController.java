package com.hrym.app.controller;

import com.hrym.app.service.SplashScreemMgrService;
import com.hrym.common.base.BaseController;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.dao.model.VO.SplashScreemVO;
import com.hrym.rpc.app.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hrym13 on 2018/4/13.
 */
@Controller
@RequestMapping("/admin")
public class SplashScreemMgrController extends BaseController {

    @Autowired
    private SplashScreemMgrService splashScreemMgrService;

    /**
     * 闪屏页列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/findALLSScreem")
    @ResponseBody
    public Result findALLSScreem(ManagerParam param) {

        return splashScreemMgrService.findALLSScreem(param.getPageNumber(), param.getPageSize());
    }

    /**
     * 闪屏页录入
     *
     * @param screemVO
     * @return
     */
    @RequestMapping("/insertSScreem")
    @ResponseBody
    public Result insertSScreem(SplashScreemVO screemVO) {

        return splashScreemMgrService.insertSScreem(screemVO);
    }

    /**
     * 闪屏页更新
     *
     * @param screemVO
     * @return
     */
    @RequestMapping("/updateSScreem")
    @ResponseBody
    public Result updateSScreem(SplashScreemVO screemVO) {

        return splashScreemMgrService.updateSScreem(screemVO);
    }

    /**
     * 闪屏页编辑
     *
     * @param id
     * @return
     */
    @RequestMapping("/findSScreemById")
    @ResponseBody
    public SplashScreemVO findSScreemById(Integer id) {

        return splashScreemMgrService.findSScreemById(id);
    }

    /**
     * 闪屏页删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteSScreem")
    @ResponseBody
    public Result deleteSScreem(Integer id) {

        return splashScreemMgrService.deleteSScreem(id);
    }


}
