package com.hrym.wechat.entity;

import com.hrym.wechat.vo.FlockRecordVo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hrym13 on 2018/11/28.
 */
@Setter
@Getter
public class FlockUserBack implements Serializable {
    
    private Integer id;
    private Integer flockId;
    private Integer itemId;
    private Integer contentId;             // '版本id',
    private Integer itemType;            //'功课类型\n0:平台\n1:自建\n2:经书',
    private Integer type;                 //'0:群回向\n1:特别回向',
    private Integer time;                  // '回向时间',
    private Integer flag;              //'是否可修改\n0:可以修改\n1:不可以',
    private String info;                  //'回向文',
    private Integer uuid;                 //'0:系统统一回向\n其他：uuid',
    private String name;                 // 群名称
    private String avatarUrl;           //群头像
    private String nickname;            //昵称
    private String avatar;              // 群主头像
    private String versionName;         // 功课名称
    private String timeStr;     // 2018年12月04日
    private String weeks;           //周
    private String itemJson;        // json数据\:n群头像\n回向时间\n截止回向时间的群用户数\n  功课名称\n      功课数
    
    
    private Integer recordId;
    private String descJson;            // 群功课数组
    private List<FlockItem> list;
    private String ymd;
    private Integer itemJoinNum;
    private FlockUserBackJson flockUserBackJson;
//    private List<FlockUserBack> list;
    
    
}
