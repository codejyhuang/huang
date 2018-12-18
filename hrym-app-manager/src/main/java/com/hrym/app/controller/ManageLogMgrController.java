package com.hrym.app.controller;

import com.hrym.app.service.ManageLogMgrService;
import com.hrym.common.base.BaseController;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hrym13 on 2018/2/2.
 * 日志实体类
 */
@RequestMapping("/admin")
@Controller
public class ManageLogMgrController extends BaseController {

    @Autowired
    private ManageLogMgrService manageLogMgrService;

    /**
     * 查找所有的日志
     *
     * @return
     */
    @RequestMapping("/findAllManageLog")
    @ResponseBody
    public Result findAllManageLog(ManagerParam param) {

        return manageLogMgrService.findAllManageLog(param.getPageNumber(), param.getPageSize());
    }
}
