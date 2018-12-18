package com.hrym.wechat.smallProgram;

import com.hrym.common.base.BaseRequestParam;
import com.hrym.wechat.vo.FlockRecordVo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
    
    /**
     * Created by hrym13 on 2018/11/9.
     */
    public class FlockRecordParam extends BaseRequestParam {
        
        private FlockRecordData data;
    
        public FlockRecordData getData() {
            return data;
        }
    
        public void setData(FlockRecordData data) {
            this.data = data;
        }
    
        public Integer getFlockId() {
            return data.getFlockId();
        }
        
        public Integer getItemContentId() {
            return data.getItemContentId();
        }
        
        public Integer getItemId() {
            return data.getItemId();
        }
        
        public Integer getType() {
            return data.getType();
        }
        
        public Integer getUuid() {
            return data.getUuid();
        }
        
        public Float getNum() {
            return data.getNum();
        }
        public Float getLat() {
            return data.getLat();
        }
        
        public Float getLon() {
            return data.getLon();
        }
    
        public List<FlockRecordVo> getList() {
            return data.getList();
        }
        public String getStartTimes() {
            return data.getStartTimes();
        }
    
        public String getEndTimes() {
            return data.getEndTimes();
        }
        public Integer getPageSize() {
            return data.getPageSize();
        }
    
        public Integer getPageNumber() {
            return data.getPageNumber();
        }
    }
    @Setter
    @Getter
    class FlockRecordData implements Serializable {
        
        private Integer flockId;
        private Integer itemContentId;
        private Integer itemId;
        private Integer type;
        private Integer uuid;
        private Integer taskId;
        private Float num;
        private Float lat;
        private Float lon;
        private List<Integer> itemIds;
        private List<Integer> itemContentIds;
        private List<Integer> types;
        private String startTimes;
        private String endTimes;
    
        private Integer startTime;
        private Integer endTime;
        private List<FlockRecordVo> list;
        private Integer pageSize;
        private Integer pageNumber;
        
    }
