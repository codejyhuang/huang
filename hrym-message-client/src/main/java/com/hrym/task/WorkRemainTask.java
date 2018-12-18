package com.hrym.task;

import com.hrym.common.message.MessageBean;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.StringUtil;
import com.hrym.jpush.MessagePush;
import com.hrym.mapper.MessageMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by hong on 2017/10/19.
 */
@Component
public class WorkRemainTask extends TimerTask {

    @Autowired
    private MessageMapper mapper;

    public void doRemain() {

        //获取当时时间往后推延60秒的提醒
        Date date = new Date();
        long t = date.getTime();
        String start = DateUtil.format5(date);
        date.setTime(t+60000);
        String end = DateUtil.format5(date);
        List<Map> allRemains = mapper.findWorkRemain(start, end);

        for(Map<String,String> map : allRemains) {


            String taskId = StringUtil.getString(map.get("task_id"),"");
            MessageBean bean = new MessageBean();
            bean.setMsgId(System.currentTimeMillis());
            bean.setContent(buildSendMsg(map));
            // 发送方式(1:广播，2：标签，3：点对点)
            bean.setSendType(3);
            bean.setSendTypeValue(StringUtil.getString(map.get("uuid"),""));

            bean.setMsgType(3);
            bean.setMsgTypeValue(StringUtil.getString(map.get("task_id"),""));

            JSONObject object = JSONObject.fromObject(bean);
            MessagePush push = new MessagePush(object.toString());
            push.send();
        }
    }

    private String buildSendMsg(Map<String,String> map) {

        StringBuilder sb = new StringBuilder();
        sb.append("功课时间到，开始「 ")
                .append(StringUtil.getString(map.get("type_name"),""))
                .append("」吧！");

        return sb.toString();
    }

    @Override
    public void run() {
        doRemain();
    }


}
