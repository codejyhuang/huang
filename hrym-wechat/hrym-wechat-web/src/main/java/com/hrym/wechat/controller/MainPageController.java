package com.hrym.wechat.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.wechat.service.IFlockService;
import com.hrym.wechat.smallProgram.WechatFlockParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

                                      ////////////////////////////////////////////////////////////////////
                                      //                          _ooOoo_                               //
                                      //                         o8888888o                              //
                                      //                         88" . "88                              //
                                      //                         (| ^_^ |)                              //
                                      //                         O\  =  /O                              //
                                      //                      ____/`---'\____                           //
                                      //                    .'  \\|     |//  `.                         //
                                      //                   /  \\|||  :  |||//  \                        //
                                      //                  /  _||||| -:- |||||-  \                       //
                                      //                  |   | \\\  -  /// |   |                       //
                                      //                  | \_|  ''\- -/''  |   |                       //
                                      //                  \  .-\__  `-`  ___/-. /                       //
                                      //                ___`. .'  /--.--\  `. . ___                     //
                                      //              ."" '<  `.___\_<|>_/___.'  >'"".                  //
                                      //            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
                                      //            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
                                      //      ========`-.____`-.___\_____/___.-`____.-'========         //
                                      //                           `=---='                              //
                                      //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
                                      //             佛祖保佑          永无BUG         永不修改            //
                                      ////////////////////////////////////////////////////////////////////
//                                    写字楼里写字间                                            写字间中程序员
//                                    程序人员写程序                                            又将程序换酒钱
//                                    酒醒只在屏前坐                                            酒醉还来屏下眠
//                                    酒醉酒醒日复日                                            屏前屏下年复年
//                                    但愿老死电脑间                                            不愿鞠躬老板前
//                                    奔驰宝马贵者趣                                            公交自行程序员
//                                    别人笑我太疯癫                                            我笑自己命太贱
//                                    但见满街漂亮妹                                            哪个归得程序员

@RestController
@RequestMapping("hrym/wechat")
public class MainPageController extends BaseController {

    @Autowired
    private IFlockService flockService;

    /**
     * 小程序首页
     * @param param
     * @return
     */
    @RequestMapping("mainPage")
    @Allowed
    public BaseResult mainPage(@RequestBody WechatFlockParam param){
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code,message,flockService.listFlockByUuid(param.getUUid()));
    }


}
