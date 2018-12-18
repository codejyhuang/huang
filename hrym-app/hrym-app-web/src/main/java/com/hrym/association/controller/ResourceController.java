package com.hrym.association.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.AssociationParam;
import com.hrym.rpc.association.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 资料控制层
 * Created by mj on 2017/9/14.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/resource")
    @ResponseBody
    public BaseResult doResource(@RequestBody AssociationParam param){

        String cmd = param.getCmd();
        if ("detail".equals(cmd)){
            return getArticleDetails(param);
        }
        if ("upload".equals(cmd)){
            return resourceUpload(param);
        }
        if ("list".equals(cmd)){
            return getResourceList(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }


    /**
     * 文章详情
     * @param param
     * @return
     */
    public BaseResult getArticleDetails(AssociationParam param){

        return resourceService.getArticleDetails(param);
    }

    /**
     * 资料上传
     * @param param
     * @return
     */
    public BaseResult resourceUpload(AssociationParam param){

        return resourceService.resourceUpload(param);
    }

    /**
     * 资料列表
     * @param param
     * @return
     */
    public BaseResult getResourceList(AssociationParam param) {

        return resourceService.resourceUpload(param);
    }
}
