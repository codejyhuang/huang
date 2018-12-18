package com.hrym.app.controller;

import com.hrym.app.service.TcalendarMgrService;
import com.hrym.common.base.BaseController;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.dao.model.VO.DateVO;
import com.hrym.rpc.app.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hrym13 on 2017/12/27.
 */
@Controller
@RequestMapping("/admin")
public class TcalendarMgrController extends BaseController {

    @Autowired
    private TcalendarMgrService tcalendarMgrService;
    /**
     * 日历显示
     * @return
     */
    @RequestMapping("/findAllTcalend")
    @ResponseBody
    public Result findAllTcalend(ManagerParam param) {

        return tcalendarMgrService.findAllTcalend(param.getPageNumber(),param.getPageSize());
    }

    /**
     * 日历修改
     * @param dateVO
     * @return
     */
    @RequestMapping("/updateAllTcalend")
    @ResponseBody
    public Result updateAllTcalend(DateVO dateVO) {

        return tcalendarMgrService.updateAllTcalend(dateVO);
    }

    /**
     * 日历添加
     * @param dateVO
     * @return
     */
    @RequestMapping("/insertAllTcalend")
    @ResponseBody
    public Result insertAllTcalend(DateVO dateVO) {

        return tcalendarMgrService.insertAllTcalend(dateVO);
    }

    /**
     * 删除
     * @param Id
     * @return
     */
    @RequestMapping("/deleteAllTcalend")
    @ResponseBody
    public Result deleteAllTcalend(Integer Id) {

        return tcalendarMgrService.deleteAllTcalend(Id);
    }

    /**
     * 根据ID查找内容
     * @param Id
     * @return
     */
    @RequestMapping("/FindTcalendById")
    @ResponseBody
    public DateVO FindTcalendById(Integer Id) {

        return tcalendarMgrService.FindTcalendById(Id);
    }
}
