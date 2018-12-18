package com.hrym.wechat.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.common.exception.LogicException;
import com.hrym.wechat.service.IFlockUserService;
import com.hrym.wechat.smallProgram.WechatFlockParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("hrym/wechat/flockUser")
public class FlockUserController extends BaseController {

    @Autowired
    private IFlockUserService flockUserService;

    /**
     * 共修群群成员列表
     *
     * @param param
     * @return
     */
    @RequestMapping("flockMember")
    @Allowed
    public BaseResult flockMember(@RequestBody WechatFlockParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message, flockUserService.flockMember(param));
    }

    /**
     * 加入共修群
     *
     * @param param
     * @return
     */
    @RequestMapping("joinFlock")
    @Allowed
    public BaseResult joinFlock(@RequestBody WechatFlockParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message, flockUserService.joinFlock(param));
    }


    /**
     * 删除群成员
     *
     * @param param
     * @return
     */
    @RequestMapping("removeFlockUser")
    @Allowed
    public BaseResult removeFlockUser(@RequestBody WechatFlockParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        flockUserService.removeFlockUser(param);
        return new BaseResult(code, message, null);
    }


    /**
     * 退出共修群
     *
     * @param param
     * @return
     */
    @RequestMapping("dropFlock")
    @Allowed
    public BaseResult dropFlock(@RequestBody WechatFlockParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        flockUserService.dropFlock(param);
        return new BaseResult(code, message, null);
    }


    /**
     * 解散共修群
     *
     * @param param
     * @return
     */
    @RequestMapping("dissolveFlock")
    @Allowed
    public BaseResult dissolveFlock(@RequestBody WechatFlockParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        Map<String, Object> map = flockUserService.dissolveFlock(param);
        return new BaseResult(code, message, map);
    }


    /**
     * 解散共修群
     *
     * @param param
     * @return
     */
    @RequestMapping("checkFlockMember")
    @Allowed
    public BaseResult checkFlockMember(@RequestBody WechatFlockParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        Map<String, Object> map = flockUserService.checkFlockMember(param);
        return new BaseResult(code, message, map);
    }


}
