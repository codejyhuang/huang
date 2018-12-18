package com.hrym.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信授权登录
 * Created by mj on 2018/4/25.
 */
public class WXAuthUtil {
    
    public static final String APPID = "wx01a274f765225716";
    public static final String APPSECRET = "7601a471da5a9b5e889c1a9fcf592698";
    
    public static final String APPID_WECHAT = "wxb2befeb1da6c0bde";
    public static final String APPSECRET_WECHAT = "eeedfa1f457ac91cc93229a160ca420e";
    
    public static void wxLogin(HttpServletRequest req, HttpServletResponse resp, String backUrl) throws ServletException, IOException {
        /**
         *这儿一定要注意！！首尾不能有多的空格（因为直接复制往往会多出空格），其次就是参数的顺序不能变动
         **/
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID +
                "&redirect_uri=" + URLEncoder.encode(backUrl, "UTF-8") +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        resp.sendRedirect(url);
        
    }
    
    /**
     * 公众号微信登录授权回调函数
     *
     * @param code
     * @throws ServletException
     * @throws IOException
     */
    public static String callBack(String code) throws ServletException, IOException {
        
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID +
                "&secret=" + APPSECRET +
                "&code=" + code +
                "&grant_type=authorization_code";
        
        JSONObject jsonObject = doGetJson(url);
        String openid = jsonObject.getString("openid");
        String access_token = jsonObject.getString("access_token");
        String refresh_token = jsonObject.getString("refresh_token");
        
        //验证access_token是否失效；
        String chickUrl = "https://api.weixin.qq.com/sns/auth?access_token=" + access_token + "&openid=" + openid;
        
        JSONObject chickuserInfo = WXAuthUtil.doGetJson(chickUrl);
        System.out.println(chickuserInfo.toString());
        if (!"0".equals(chickuserInfo.getString("errcode"))) {
            // 刷新access_token（如果需要）-----暂时没有使用,参考文档https://mp.weixin.qq.com/wiki，
            String refreshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + openid +
                    "&grant_type=refresh_token&refresh_token=" + refresh_token;
            
            JSONObject refreshInfo = WXAuthUtil.doGetJson(chickUrl);
            
            access_token = refreshInfo.getString("access_token");
        }
        //拉取用户信息(需scope为 snsapi_userinfo)
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token +
                "&openid=" + openid +
                "&lang=zh_CN";
        JSONObject userInfo = doGetJson(infoUrl);
        
        return userInfo.toString();
    }
    
    
    /**
     * 小程序获取Sessionkey和openid
     *
     * @param jscode
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public static Map<String, Object> getSessionkey(String encryptedData, String iv, String jscode) throws ServletException, IOException {
        
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID_WECHAT +
                "&secret=" + APPSECRET_WECHAT +
                "&js_code=" + jscode +
                "&grant_type=authorization_code";
        
        JSONObject jsonObject = doGetJson(url);
        String openid = jsonObject.getString("openid");
        String sessionkey = jsonObject.getString("session_key");
        String unionId = null;
        unionId = jsonObject.getString("unionid");
        
        Map<String, Object> map = new HashMap<>();
        map.put("openid", openid);
        map.put("sessionkey", sessionkey);
        int i = 0;
        byte[] result = null;
        
        ////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            result = AESUtil.decrypt(encryptedData, sessionkey, iv, "UTF-8");
            
            if (null != result && result.length > 0) {
                String userInfo = new String(result, "UTF-8");
                JSONObject json = JSON.parseObject(userInfo);
                unionId = (String) json.get("unionId");
                map.put("userInfo", userInfo);
                map.put("status", "1");
                map.put("msg", "解密成功");
                map.put("nickName", (String) json.get("nickName"));
                map.put("sex", (Integer) json.get("gender")==1?0:1);
                map.put("avatarUrl", (String) json.get("avatarUrl"));
                map.put("unionId", (String) json.get("unionId"));
                map.put("openId", (String) json.get("openId"));
                map.put("province", (String) json.get("province"));

//                map.put("userInfo", json);
            } else {
                map.put("status", "0");
                map.put("msg", "解密失败");
                map.put("unionId", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
        
    }
    
    /**
     * HttpClient服务器模拟浏览器发送请求
     *
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
        JSONObject jsonObject = null;
        
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        HttpGet httpGet = new HttpGet(url);
        
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(8000).setConnectTimeout(8000).build();//设置请求和传输超时时间
        httpGet.setConfig(requestConfig);
        
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //把返回的结果转换为JSON对象
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSON.parseObject(result);
        }
        
        return jsonObject;
    }
    
    
}
