package com.hrym.rpc.app.dao.model.VO;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 日期实体类
 *
 * Created by mj on 2017/12/26.
 */
@Table(name = "t_date")
public class DateVO implements Serializable {

    /**
     * 日历id
     */
    @Id
    private Integer id;

    /**
     * 日历图片
     */
    private String datePic;

    /**
     * 日历开始时间
     */
    private Integer startTime;

    /**
     * 日历结束时间
     */
    private Integer endTime;

    @Transient
    private String startTimes;
    @Transient
    private String endTimes;

    public String getStartTimes() {
        return startTimes;
    }

    public void setStartTimes(String startTimes) {
        this.startTimes = startTimes;
    }

    public String getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(String endTimes) {
        this.endTimes = endTimes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatePic() {
        return datePic;
    }

    public void setDatePic(String datePic) {
        this.datePic = datePic;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "DateVO{" +
                "id=" + id +
                ", datePic='" + datePic + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", startTimes='" + startTimes + '\'' +
                ", endTimes='" + endTimes + '\'' +
                '}';
    }
}
