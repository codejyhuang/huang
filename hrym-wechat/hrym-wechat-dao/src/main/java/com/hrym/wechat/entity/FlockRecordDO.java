package com.hrym.wechat.entity;

import com.hrym.wechat.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlockRecordDO extends BaseEntity {

    public static final Integer NORMAL_STATE = 1;


    private Integer parentId;  //功课上报中的recordId

    private Integer createTime; //上报时间

    private Integer uuid; //上报人id

    private Double num; //上报数

    private String ymd;

    private Integer year;

    private Integer month;

    private Float lon; //经度

    private Float lat; //纬度

    private Integer state = NORMAL_STATE; //是否删除  0 : 删除 1 : 正常

    private Integer itemId; //功课id

    private Integer itemContentId; // 内容id

    private Integer type;

    private String unit; //量词 查询出来

    private Integer flockId;

    private String reportStr;

    private Integer order;

    private Integer isImport; //导入数据   0:否  1:是

    private String lessonName;
    private String tableName ="t_flock_record_2018";
    private String avatar;
    private String nickName;
    private String timeStr;




    private Integer likeNum; //点赞数量
    private Integer hasLike; //null  未点赞    有值 已点赞

    public Integer getHasLike() {
        return hasLike==null?0:1;
    }

    public Integer getLikeNum() {
        return likeNum==null?0:likeNum;
    }
}
