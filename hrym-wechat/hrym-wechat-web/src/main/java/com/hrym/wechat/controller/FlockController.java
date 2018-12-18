package com.hrym.wechat.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.wechat.service.IFlockService;
import com.hrym.wechat.smallProgram.FlockRecordParam;
import com.hrym.wechat.smallProgram.ItemParam;
import com.hrym.wechat.smallProgram.WechatFlockParam;
import com.hrym.wechat.smallProgram.WechatFlockUserParam;
import com.hrym.wechat.smallProgram.WechatUserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hrym/wechat/flock")
public class FlockController extends BaseController {

    @Autowired
    private IFlockService flockService;

    /**
     * 共修群创建
     * @param param
     * @return
     */
    @RequestMapping("/createFlock")
    @Allowed
    public BaseResult createFlock(@RequestBody WechatFlockParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message, flockService.createFlock(param));
    }

    /**
     * 添加群功课
     * @param param
     * @return
     */
    @RequestMapping("addAssignment")
    @Allowed
    public BaseResult addItem(@RequestBody WechatFlockParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        flockService.addItem(param);
        return new BaseResult(code, message, null);
    }

    /**
     * 删除群功课
     * @param param
     * @return
     */
    @RequestMapping("removeFlockLesson")
    @Allowed
    public BaseResult removeFlockLesson(@RequestBody WechatFlockParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message, flockService.removeFlockLesson(param));
    }



    /**
     * 共修群详细信息页面
     * @param param
     * @return
     */
    @RequestMapping("flockMessage")
    @Allowed
    public BaseResult flockMessage(@RequestBody WechatFlockParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message, flockService.selectById(param));
    }


    /**
     * 邀请页面
     * @param param
     * @return
     */
    @RequestMapping("invitePage")
    @Allowed
    public BaseResult invitePage(@RequestBody WechatFlockUserParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        return new BaseResult(code, message,  flockService.invitePage(param));
    }

    /**
     * 群统计
     * @param param
     * @return
     */
    @RequestMapping("/flockStatistic")
    @Allowed
    public BaseResult flockStatistic (@RequestBody FlockRecordParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        return new BaseResult(code, message, flockService.flockStatistic(param));
    }

    /**
     * 单群功课查找
     * @param param
     * @return
     */
    @RequestMapping("/flockItemList")
    @Allowed
    public BaseResult flockItemList (@RequestBody FlockRecordParam param){
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message, flockService.flockItemList(param));
    }

    /**
     * 查看共修群简介 + 群隐私状态
     * @param param
     * @return
     */
    @RequestMapping("queryFlockIntroAndPrivacy")
    @Allowed
    public BaseResult queryFlock(@RequestBody WechatFlockParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message, flockService.queryFlockIntro(param));
    }
    
    /**
     * 编辑 / 更新 群简介  / 群隐私
     * @param param
     * @return
     */
    @RequestMapping("updateFlockIntroOrPrivacy")
    @Allowed
    public BaseResult updateFlock(@RequestBody WechatFlockParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        flockService.updateFlock(param);
        return new BaseResult(code, message, null);
    }
    
    /**
     * 查找共修群里所有功课《去重》
     * @param param
     * @return
     */
    @RequestMapping("queryFlockItemNameList")
    @Allowed
    public BaseResult queryFlockItemNameList(@RequestBody WechatFlockParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message, flockService.queryFlockItemNameList(param));
    }
    
    /**
     * 统计数据时间
     * @param param
     * @return
     */
    @RequestMapping("flockStatisticTimes")
    @Allowed
    public BaseResult flockStatisticTimes (@RequestBody WechatFlockParam param){
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message, flockService.flockStatisticTimes(param));
    }
    
    /**
     * 设置功课简介、隐私、量词
     * @param param
     * @return
     */
    
    @RequestMapping("/flockItemSetting")
    @Allowed
    public BaseResult flockItemSetting(@RequestBody ItemParam param){
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        
        flockService.flockItemSetting(param);
        
        return new BaseResult(code, message,null);
        
    }
    
    /**
     * 查询功课简介、隐私、量词
     * @param param
     * @return
     */
    
    @RequestMapping("/queryFlockItemSetting")
    @Allowed
    public BaseResult queryFlockItemSetting(@RequestBody ItemParam param){
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message,flockService.queryFlockItemSetting(param));
        
    }
    
    /**
     * 可加入共修群列表
     * @param param
     * @return
     */
    
    @RequestMapping("/queryJoinFlockList")
    @Allowed
    public BaseResult queryJoinFlockList(@RequestBody WechatFlockParam param){
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        
        return new BaseResult(code, message,flockService.queryJoinFlockList(param));
        
    }
    
    /**
     * 共修群列表排序
     * @param param
     * @return
     */
    
    @RequestMapping("/updateFlockUserOrder")
    @Allowed
    public BaseResult updateFlockUserOrder(@RequestBody WechatFlockParam param){
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        
        flockService.updateFlockUserOrder(param);
        return new BaseResult(code, message,null);
        
    }
    
}
