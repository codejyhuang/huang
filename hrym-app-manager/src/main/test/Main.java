import com.hrym.common.util.DateUtil;
import com.hrym.common.util.MD5Util;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mj on 2017/6/27.
 */
public class Main {

    public static void main(String[] args) throws Exception{

        String pwd = "admin";
        String s = MD5Util.MD5(pwd);


        String time = DateUtil.getNowDate();
        System.out.println(">>>>>>>>>>>>"+s);
    }

    public String getFormatNowDate() {
        Date nowTime = new Date(DateUtil.currentSecond());
        SimpleDateFormat sdFormatter = new SimpleDateFormat(DateUtil.DATA_PATTON_YYYYMMDDHHMMSS);
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        return retStrFormatNowDate;
    }
}
