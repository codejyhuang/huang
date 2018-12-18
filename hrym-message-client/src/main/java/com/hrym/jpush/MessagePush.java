package com.hrym.jpush;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.hrym.common.message.MessageBean;
import com.hrym.common.util.PropertiesFileUtil;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;

/**
 * Created by hong on 2017/10/10.
 */
public class MessagePush {

    private JPushClient jpushClient;
    private String msg;

    private static org.slf4j.Logger _log = LoggerFactory.getLogger(MessagePush.class);

    public MessagePush(String msg){
        this.msg = msg;
        PropertiesFileUtil util = PropertiesFileUtil.getInstance("kafka");
        String secret = util.get("master.secret");
        String key = util.get("app.key");
        int tryNum = util.getInt("max.retry.num");
        jpushClient = new JPushClient(secret, key,tryNum);
    }

    /**
     * 向所有人推送消息
     * @return 消息ID
     */
    public long send(){

        PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras();
        _log.info("send msg start:"+payload.toString());
        long msgId = 0;
        try{
            PushResult result = jpushClient.sendPush(payload);
            msgId = result.msg_id;
            _log.info("send msg end :"+result.toString());
        }catch(Exception e){

            _log.info("send msg error:"+e.getMessage());
        }

        return msgId;
    }

    public static void main(String[] args){

//        MessageBean bean = new MessageBean();
//        bean.setMsgId(100000);
//        bean.setContent("content test");
//        bean.setMsgType(1);
//        bean.setTitle("慧修行");
//
//        JSONObject object = JSONObject.fromObject(bean);
//        MessagePush push = new MessagePush(object.toString());
//        push.send();

        MessageBean bean = new MessageBean();
        bean.setMsgId(100000);
        bean.setContent("content test");
        bean.setMsgType(1);
        bean.setTitle("慧修行日志");

        JSONObject object = JSONObject.fromObject(bean);
        MessagePush push = new MessagePush(object.toString());
        push.send();
    }


    public PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {

        JSONObject obj= JSONObject.fromObject(msg);
        int sendType = obj.getInt("sendType");
        Audience audience = Audience.alias("test");
        if(sendType==1){
            //广播
            audience = Audience.all();
        }
        else if(sendType==2){

            // 标签
            audience = Audience.tag(obj.getString("sendTypeValue"));
        }
        else  if(sendType==3){

            // 点对点
            audience = Audience.alias(obj.getString("sendTypeValue"));
        }


        String content = obj.getString("content");
        String tmp = obj.getString("title");
        String title = "".equals(tmp)?"慧修行":tmp;

        PropertiesFileUtil util = PropertiesFileUtil.getInstance("kafka");
        boolean apnsProduction = util.getBool("apns.production");

        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(audience)
                .setOptions(Options.newBuilder().setApnsProduction(apnsProduction).build())
                .setNotification(
                        Notification.newBuilder()
                                .setAlert(content)
                                .addPlatformNotification(IosNotification.newBuilder().setSound("default").setBadge(1).build())
                                .addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).build())
                                .build()

                )
                .build();
    }

}
