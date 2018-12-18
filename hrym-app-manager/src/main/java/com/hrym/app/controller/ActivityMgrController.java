package com.hrym.app.controller;

import com.hrym.app.service.ActivityMgrService;
import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.util.WXAuthUtil;
import com.hrym.rpc.app.dao.model.VO.activityVO.ActivityPrayVO;
import com.hrym.rpc.app.dao.model.activity.ActivityHelp;
import com.hrym.rpc.app.dao.model.activity.ActivityPray;
import com.hrym.rpc.app.dao.model.activity.WXUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mj on 2018/4/19.
 */
@Controller
@RequestMapping(value = "/admin", produces = "text/html;charset=UTF-8")
public class ActivityMgrController extends BaseController {

    @Autowired
    private ActivityMgrService activityMgrService;

    /**
     * 祈福活动首页
     *
     * @return
     */
    @RequestMapping(value = "/prayHomePage", produces = "text/html;charset=UTF-8")
    @ResponseBody
    @Allowed
    public String prayHomePage(String id, String callback) {

        ActivityPrayVO vo = activityMgrService.prayHomePage(id);
        String result = callback + "(" + JSONObject.fromObject(vo).toString() + ");";//拼接可执行的js
        return result;
    }


    /**
     * 插入祈福信息
     *
     * @param pray
     */
    @RequestMapping(value = "/savePrayInfo", produces = "text/html;charset=UTF-8")
    @ResponseBody
    @Allowed
    public String savePrayInfo(ActivityPray pray, String callback) {

        activityMgrService.savePrayInfo(pray);

        Map<String, Object> map = new HashMap<>();
        map.put("code", BaseConstants.GWSCODE0000);
        map.put("message", BaseConstants.GWSMSG0000);

        String result = callback + "(" + JSONObject.fromObject(map).toString() + ");";//拼接可执行的js
        return result;

    }

    /**
     * 查找所有信息按助力值倒序
     *
     * @return
     */
    @RequestMapping(value = "/findTop10", produces = "text/html;charset=UTF-8")
    @ResponseBody
    @Allowed
    public String findTop10(String callback) {

        List<ActivityPrayVO> voList = activityMgrService.findTop10();
        String result = callback + "(" + JSONArray.fromObject(voList).toString() + ");";//拼接可执行的js
        return result;
    }

    /**
     * 随机获取库里一条祈福记录
     *
     * @return
     */
    @RequestMapping(value = "/findOne", produces = "text/html;charset=UTF-8")
    @ResponseBody
    @Allowed
    public String findOne(Integer pageNo, String callback) {

        ActivityPrayVO vo = activityMgrService.findOne(pageNo);
        String result = callback + "(" + JSONObject.fromObject(vo).toString() + ");";//拼接可执行的js
        return result;
    }

    /**
     * 助力主页
     *
     * @param fromId
     * @return
     */
    @RequestMapping(value = "/helpPage", produces = "text/html;charset=UTF-8")
    @ResponseBody
    @Allowed
    public String getHelpHomePage(String fromId, String toId, String callback) {

        ActivityPrayVO vo = activityMgrService.getHelpHomePage(fromId, toId);
        String result = callback + "(" + JSONObject.fromObject(vo).toString() + ");";//拼接可执行的js
        return result;
    }

    /**
     * 为别人助力
     *
     * @param help
     */
    @RequestMapping(value = "/help", produces = "text/html;charset=UTF-8")
    @ResponseBody
    @Allowed
    public String helpOthers(ActivityHelp help, String callback) {

        activityMgrService.helpOthers(help);

        Map<String, Object> map = new HashMap<>();
        map.put("code", BaseConstants.GWSCODE0000);
        map.put("message", BaseConstants.GWSMSG0000);

        String result = callback + "(" + JSONObject.fromObject(map).toString() + ");";//拼接可执行的js

        return result;
    }

    /**
     * 保存微信授权用户信息
     *
     * @param user
     */
    @RequestMapping(value = "/saveWXUser", produces = "text/html;charset=UTF-8")
    @ResponseBody
    @Allowed
    public String saveWXUser(WXUser user, String callback) {

        activityMgrService.saveWXUser(user);

        Map<String, Object> map = new HashMap<>();
        map.put("code", BaseConstants.GWSCODE0000);
        map.put("message", BaseConstants.GWSMSG0000);

        String result = callback + "(" + JSONObject.fromObject(map).toString() + ");";//拼接可执行的js
        return result;
    }

    /**
     * 分页获取祈福信息
     *
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/findAllByPageNo", produces = "text/html;charset=UTF-8")
    @ResponseBody
    @Allowed
    public String findAllByPageNo(Integer pageNo, String callback) {

        Map<String, Object> map = activityMgrService.findAllByPageNo(pageNo);

        String result = callback + "(" + JSONObject.fromObject(map).toString() + ");";//拼接可执行的js
        return result;
    }

    /**
     * 微信授权登录
     *
     * @return
     */
    @RequestMapping(value = "/wxLogin", produces = "text/html;charset=UTF-8")
    @ResponseBody
    @Allowed
    public String wxLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uuid = req.getParameter("uuid");
        String backUrl = "http://www.everglamming.com/prayFor/index.html?uuid=" + uuid;
        if (null == uuid) {
            backUrl = "http://www.everglamming.com/pray/index.html";
        } else {
            boolean flag = activityMgrService.isPray(uuid);
            if (flag == false) {
                backUrl = "http://www.everglamming.com/pray/index.html";
            }
        }
        WXAuthUtil.wxLogin(req, resp, backUrl);

        return null;
    }

    /**
     * 微信授权登录回调函数
     *
     * @return
     */
    @RequestMapping(value = "/callBack", produces = "text/html;charset=UTF-8")
    @ResponseBody
    @Allowed
    public String callBack(String code, String callback) throws ServletException, IOException {

        String userInfo = WXAuthUtil.callBack(code);
        String result = callback + "(" + JSONObject.fromObject(userInfo).toString() + ");";//拼接可执行的js
        return result;
    }

}
