package com.hrym.app.controller;

import com.google.common.collect.Maps;
import com.hrym.app.service.FdfsService;
import com.hrym.common.base.BaseController;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.dao.model.task.Qrcode;
import com.hrym.rpc.app.util.Result;
import com.hrym.app.service.QrCodeMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

/**
 * Created by hrym13 on 2017/10/30.
 */
@Controller
@RequestMapping(value = "/admin")
public class QrCodeController extends BaseController {

    @Autowired
    private QrCodeMgrService qrCodeMgrService;
    @Autowired
    private FdfsService fdfsService;

    /**
     * 查找渠道URL及所有内容
     *
     * @param param
     * @return
     */
    @RequestMapping("/findAllQrCode")
    @ResponseBody
    public Result findAllQrCode(ManagerParam param) {

        return qrCodeMgrService.findAllQrCode(param.getPageNumber(), param.getPageSize());
    }

    /**
     * 根据ID查找渠道url
     *
     * @param qrcode
     * @return
     */
    @RequestMapping("/findByCannelId")
    public ModelAndView findByCannelId(Qrcode qrcode) {

        Qrcode qr = qrCodeMgrService.findByCannelId(qrcode);
        ModelAndView mav = new ModelAndView();
        mav.addObject("qrcode", qr);
        mav.setViewName("/qrCode/qrcode");
        return mav;
    }

    /**
     * 渠道URL生成
     *
     * @param Qrurl
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/inserAllqrurl")
    @ResponseBody
    public String inserAllqrurl(@RequestBody String Qrurl, HttpServletRequest request) throws Exception {


        FileInputStream file = new FileInputStream(new File(Qrurl));
        Map<String, String> ret = Maps.newHashMap();
        Qrcode qrcode = new Qrcode();
        if (!Qrurl.isEmpty()) {
            ret = fdfsService.uploadStream(file.available(), new File(Qrurl).getName(), file);
            if (!"0".equals(ret.get("code"))) {

                return "";
            }
            //服务器返回的URL地址

            String qrUrl = ret.get("fileStorePath");
            System.out.println(">>>>>>>>>>>>" + qrUrl);

            qrcode.setChannelUrl(qrUrl);

        }

        return qrCodeMgrService.inserAllqrurl(qrcode);
    }

    /**
     * 渠道URL及内容删除
     *
     * @param qrcode
     * @return
     */
    @RequestMapping("/deteleurl")
    @ResponseBody
    public Result deteleurl(Qrcode qrcode) {

        return qrCodeMgrService.deteleurl(qrcode);
    }

    /**
     * 渠道生成
     *
     * @param qrcode
     * @return
     */
    @RequestMapping("/inserqrurl")
    @ResponseBody
    public Result inserqrurl(Qrcode qrcode) {

        return qrCodeMgrService.inserqrurl(qrcode);
    }

    /**
     * 访问次数
     *
     * @param qrcode
     * @return
     */
    @RequestMapping("/redirectView")
    public ModelAndView redirectView(Qrcode qrcode) {

        //设置访问次数
        Qrcode qr = qrCodeMgrService.findByCannelId(qrcode);
        return new ModelAndView(new RedirectView(qrcode.getUrl()));
    }
}
