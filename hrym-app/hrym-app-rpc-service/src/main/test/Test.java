import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.hrym.common.logUtil.GwsLogger;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.NumUtil;
import com.hrym.common.util.RedisUtil;
import com.hrym.rpc.app.common.constant.UcenterConstant;
import com.hrym.rpc.app.dao.model.task.ShareInfo;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.auth.dao.mapper.ShareMapper;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by mj on 2017/6/27.
 */
public class Test {

    public static void main(String[] args) throws ParseException {


//        GwsLogger.info("split："+list);
        //将int类型按指定格式转换为字符串类型
    }


    public static void getPhoneCode(String phoneStr){
        String redisKey = phoneStr+ UcenterConstant.VERIFY_CODE_SUFFIX;
        RedisUtil.remove(redisKey);
        RedisUtil.set(redisKey,"8888",RedisUtil.EXRP_HOUR);
        String str = RedisUtil.get(redisKey);
        GwsLogger.info("验证码是："+str);

    }


    //计算今日建议数量
    public static int getTargetNum(double totalNum,Integer totalTime,double doneNum,int startTime){

        //已过的天数
        int afterDay = 0;
        try {
            afterDay = DateUtil.daysBetween(startTime,DateUtil.currentSecond());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //今日建议
        double targetNum = 0;
        double newTotalNum = 0.6*totalNum;//总完成数的60%
        double newTotalTime = 0.5*totalTime;//总周期的50%

        if (afterDay >= 0 && totalTime <= 10){
            targetNum = (totalNum-doneNum)/(totalTime-afterDay);
            return (int)Math.ceil(targetNum);//向上取整
        }
        if (afterDay >= 0 && afterDay < newTotalTime && doneNum < newTotalNum){
            targetNum = (newTotalNum-doneNum)/(newTotalTime-afterDay);
            return (int)Math.ceil(targetNum);//向上取整
        }
        if (afterDay >= 0){
            targetNum = (totalNum-doneNum)/(totalTime-afterDay);
            return (int)Math.ceil(targetNum);//向上取整
        }

        return (int)targetNum;
    }
}
