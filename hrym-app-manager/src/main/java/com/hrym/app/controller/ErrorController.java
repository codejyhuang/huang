package com.hrym.app.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hong on 2017/6/29.
 */
@RequestMapping(value = "/hrym")
@Controller
public class ErrorController {
    @Allowed
    @RequestMapping(value = "/handle/{code}")
    @ResponseBody
    public BaseResult error(@PathVariable("code") String code) {

        return new BaseResult(BaseConstants.GWSCODE2003,BaseConstants.GWSMSG2003,null);
    }
}
