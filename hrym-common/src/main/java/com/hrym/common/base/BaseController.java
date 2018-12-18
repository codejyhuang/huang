package com.hrym.common.base;

import com.hrym.common.exception.LogicException;
import com.hrym.common.util.PropertiesFileUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.InvalidSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器基类
 * Created by qhzhang on 2017/2/4.
 */
public abstract class BaseController {

    private final static Logger _log = LoggerFactory.getLogger(BaseController.class);

    /**
     * 统一异常处理
     *
     * @param request
     * @param response
     * @param exception
     */
    @ExceptionHandler
    @ResponseBody
    public BaseResult exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        _log.error("统一异常处理 start：", exception);

        String code = BaseConstants.GWSCODE3001;
        String msg = BaseConstants.GWSMSG3001;
        if (exception instanceof LogicException){
            code = "8888";
            System.out.println(exception.getMessage());
            System.out.println(exception.getStackTrace());
            System.out.println(exception.getLocalizedMessage());
            msg = exception.getLocalizedMessage();
        }
        _log.error("统一异常处理 end：", exception);
        return new BaseResult(code,msg,null);
    }

    /**
     * 返回jsp视图
     *
     * @param path
     * @return
     */
    public static String jsp(String path) {
        return path.concat(".jsp");
    }

    /**
     * 返回thymeleaf视图
     *
     * @param path
     * @return
     */
    public static String thymeleaf(String path) {
        String folder = PropertiesFileUtil.getInstance().get("app.name");
        return "/".concat(folder).concat(path).concat(".html");
    }

}
