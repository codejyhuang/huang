package com.hrym.wechat.smallProgram;

import com.hrym.common.base.BaseRequestParam;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hrym13 on 2018/11/9.
 */
public class FlockCountReportParam extends BaseRequestParam {

    private FlockCountReportData data;
    
    public FlockCountReportData getData() {
        return data;
    }
    
    public void setData(FlockCountReportData data) {
        this.data = data;
    }
    public Integer getFlockId() {
        return data.getFlockId();
    }
    
    public Integer getItemContentId() {
        if(data.getItemContentId()==null)
            return 0;

        return data.getItemContentId();
    }
    
    public Integer getItemId() {
        if(data.getItemId()==null)
            return 0;
        return data.getItemId();
    }
    
    public Integer getType() {
        return data.getType();
    }
    
    public Integer getUuid() {
        return data.getUuid();
    }
    
    public Double getNum() {
        return data.getNum();
    }
    public Float getLat() {
        return data.getLat();
    }
    
    public Float getLon() {
        return data.getLon();
    }
    
    public Integer getRecordMethod() {
        return data.getRecordMethod();
    }
    public Integer getTaskId(){return data.getTaskId();}
}
@Setter
@Getter
class FlockCountReportData implements Serializable{
    
    private Integer flockId;
    private Integer itemContentId;
    private Integer itemId;
    private Integer type;
    private Integer uuid;
    private Integer taskId;
    private Double num;
    private Float lat;
    private Float lon;
    private List<Integer> itemIds;
    private List<Integer> itemContentIds;
    private List<Integer> types;
    private String startTimes;
    private String endTimes;
    private Integer recordMethod;   // 5:导入记录
    
    public Integer getRecordMethod() {
        return recordMethod;
    }
}