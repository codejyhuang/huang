package com.hrym.rpc.auth.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.SplashScreemVO;
import com.hrym.rpc.app.dao.model.task.LoginInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by mj on 2017/6/25.
 */
public interface LoginMapper {

    /**
     * 增加用户登录信息
     * @param loginInfo
     */
    void saveLoginInfo(LoginInfo loginInfo);

    /**
     * 根据用户ID查询登录信息
     * @param uuid
     * @return LoginInfo
     */
    @DataSource("slave")
    LoginInfo findLoginInfoByUuid(Integer uuid);

    /**
     * 更新登录信息
     * @param loginInfo
     */
    void updateLoginInfoByToken(@Param("lg") LoginInfo loginInfo);

    /**
     * 根据token查询登录信息
     * @param token
     * @return
     */
    @DataSource("slave")
    LoginInfo findLoginInfoByToken(String token);


    /**
     * 根据时间获取splash
     * @param time
     * @return
     */
    @DataSource("slave")
    @Select("select id,pic,xpic,url,time from t_splash_screem where st<=#{time} and et >=#{time}")
    SplashScreemVO getSplashInfo(long time);

}
