package com.hrym.wechat.smallProgram;

import com.hrym.common.base.BaseRequestParam;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/11/8.
 */
public class WechatFlockUserParam extends BaseRequestParam {
    
    private WechatFlockUserData data;
    
    public WechatFlockUserData getData() {
        return data;
    }
    
    public void setData(WechatFlockUserData data) {
        this.data = data;
    }
    
    public Integer getShareUuid() {
        return data.getShareUuid();
    }
    
    public Integer getUuid() {
        return data.getUuid();
    }
    
    public Integer getId() {
        return data.getId();
    }
}

@Setter
@Getter
class WechatFlockUserData implements Serializable {
    
    private Integer shareUuid;
    private Integer uuid;
    private Integer id;
    private String userName;
    
}
