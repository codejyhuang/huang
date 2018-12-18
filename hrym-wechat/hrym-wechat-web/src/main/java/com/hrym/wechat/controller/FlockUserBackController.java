package com.hrym.wechat.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.wechat.service.IFlockUserBackService;
import com.hrym.wechat.smallProgram.FlockRecordParam;
import com.hrym.wechat.smallProgram.FlockUserBackParam;
import com.hrym.wechat.smallProgram.WechatFlockParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by hrym13 on 2018/11/28.
 */
@RestController
@RequestMapping("hrym/wechat/flockBack")
public class FlockUserBackController extends BaseController {
    
    @Autowired
    private IFlockUserBackService flockUserBackService;
    
    /**
     * 上次特别回向文
     *
     * @param param
     * @return
     */
    @RequestMapping("lastSpecialBack")
    @Allowed
    public BaseResult lastSpecialBack(@RequestBody FlockUserBackParam param) {
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        
        return new BaseResult(code, message, flockUserBackService.lastSpecialBack(param));
    }
    
    /**
     * 功课特别回向文录入
     *
     * @param param
     * @return
     */
    @RequestMapping("insertSpecialBack")
    @Allowed
    public BaseResult insertSpecialBack(@RequestBody FlockUserBackParam param) {
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
    
        flockUserBackService.insertSpecialBack(param);
        
        return new BaseResult(code, message, null);
    }
    
    /**
     * 编辑特别回向文
     *
     * @param param
     * @return
     */
    @RequestMapping("updateSpecialBack")
    @Allowed
    public BaseResult updateSpecialBack(@RequestBody FlockUserBackParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
    
        flockUserBackService.updateSpecialBack(param);
        return new BaseResult(code, message, null);
    }
    
    /**
     * 特别回向详情
     * @param param
     * @return
     */
    @RequestMapping("querySpecialBackDetails")
    @Allowed
    public BaseResult querySpecialBackDetails(@RequestBody FlockUserBackParam param) {
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        
        Map<String,Object> map =flockUserBackService.querySpecialBackDetails(param);
        return new BaseResult(code, message, map);
    }
    /**
     * 报数的功课回向
     * @param param
     * @return
     */
    @RequestMapping("queryCountDirection")
    @Allowed
    public BaseResult queryCountDirection(@RequestBody FlockUserBackParam param) {
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        
        Map<String,Object> map =flockUserBackService.queryCountDirection(param);
        return new BaseResult(code, message, map);
    }
    /**
     * 特别回向删除
     * @param param
     * @return
     */
    @RequestMapping("deleteSpecialBack")
    @Allowed
    public BaseResult deleteSpecialBack(@RequestBody FlockUserBackParam param) {
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
    
        flockUserBackService.deleteSpecialBack(param);
        return new BaseResult(code, message, null);
    }
//    /**
//     * 群回向详情
//     * @param param
//     * @return
//     */
//    @RequestMapping("queryFlockBackDetails")
//    @Allowed
//    public BaseResult queryFlockBackDetails(@RequestBody FlockUserBackParam param) {
//
//        String code = BaseConstants.GWSCODE0000;
//        String message = BaseConstants.GWSMSG0000;
//
//        Map<String,Object> map =flockUserBackService.queryFlockBackDetails(param);
//        return new BaseResult(code, message, map);
//    }
    
    /**
     * 共修回向列表
     * @param param
     * @return
     */
    @RequestMapping("queryDedicationVersesList")
    @Allowed
    public BaseResult queryDedicationVersesList(@RequestBody FlockUserBackParam param) {
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        
        Map<String,Object> map =flockUserBackService.queryDedicationVersesList(param);
        return new BaseResult(code, message, map);
    }
    
    /**
     * 共修群回向详情dejson
     * @param param
     * @return
     */
    @RequestMapping("queryDedicationVerses")
    @Allowed
    public BaseResult queryDedicationVersesDetails(@RequestBody FlockUserBackParam param) {
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        
        Map<String,Object> map =flockUserBackService.queryDedicationVerses(param);
        return new BaseResult(code, message, map);
    }
    /**
     * 共修回向群今日特别回向记录
     * @param param
     * @return
     */
    @RequestMapping("queryDedicationVersesRecordList")
    @Allowed
    public BaseResult queryDedicationVersesRecordList(@RequestBody FlockUserBackParam param) {
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        
        Map<String,Object> map =flockUserBackService.queryDedicationVersesRecordList(param);
        return new BaseResult(code, message, map);
    }
    
//    @RequestMapping("testList")
//    @Allowed
//    public BaseResult testList(@RequestBody FlockRecordParam param){
//
//        String code = BaseConstants.GWSCODE0000;
//        String message = BaseConstants.GWSMSG0000;
//
//        Map<String,Object> map =flockUserBackService.testList(param);
//        return new BaseResult(code, message, map);
//    }
    
    
    
}
