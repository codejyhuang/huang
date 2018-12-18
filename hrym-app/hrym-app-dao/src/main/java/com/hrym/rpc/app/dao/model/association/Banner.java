package com.hrym.rpc.app.dao.model.association;

import java.io.Serializable;

/**banner活动表
 * Created by hrym13 on 2017/9/21.
 */
public class Banner implements Serializable {

    private Integer bannerId;       //活动ID
    private String bannerTitle;     //活动主题
    private Integer startTime;      //开始时间
    private Integer endTime;        //结束时间
    private String bannerDesc;      //活动描述
    private String bannerUrl;       //活动url
    private Integer bannerType;
    private String startTimeis;
    private String endTimeis;
    private String bannerPic;
    private String needLogin;
    private Integer sort;           //自定义排序
    //分享url
    private String shareUrl;
    private Integer needShare;
    private String additionalParam;
    private Integer needResult;
    private String shareChannel;
    //分享渠道
    private String shareChannelA;  //微信朋友圈
    private String shareChannelB;  //微信好友
    private String shareChannelC;  //QQ空间
    private String shareChannelD;  //QQ好友
    private String shareChannelE;  //微博


    @Override
    public String toString() {
        return "Banner{" +
                "bannerId=" + bannerId +
                ", bannerTitle='" + bannerTitle + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", bannerDesc='" + bannerDesc + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", bannerType=" + bannerType +
                ", startTimeis='" + startTimeis + '\'' +
                ", endTimeis='" + endTimeis + '\'' +
                ", bannerPic='" + bannerPic + '\'' +
                ", needLogin='" + needLogin + '\'' +
                ", sort=" + sort +
                ", shareUrl='" + shareUrl + '\'' +
                ", needShare=" + needShare +
                ", additionalParam='" + additionalParam + '\'' +
                ", needResult=" + needResult +
                ", shareChannel='" + shareChannel + '\'' +
                ", shareChannelA='" + shareChannelA + '\'' +
                ", shareChannelB='" + shareChannelB + '\'' +
                ", shareChannelC='" + shareChannelC + '\'' +
                ", shareChannelD='" + shareChannelD + '\'' +
                ", shareChannelE='" + shareChannelE + '\'' +
                '}';
    }


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getShareChannelA() {
        return shareChannelA;
    }

    public void setShareChannelA(String shareChannelA) {
        this.shareChannelA = shareChannelA;
    }

    public String getShareChannelB() {
        return shareChannelB;
    }

    public void setShareChannelB(String shareChannelB) {
        this.shareChannelB = shareChannelB;
    }

    public String getShareChannelC() {
        return shareChannelC;
    }

    public void setShareChannelC(String shareChannelC) {
        this.shareChannelC = shareChannelC;
    }

    public String getShareChannelD() {
        return shareChannelD;
    }

    public void setShareChannelD(String shareChannelD) {
        this.shareChannelD = shareChannelD;
    }

    public String getShareChannelE() {
        return shareChannelE;
    }

    public void setShareChannelE(String shareChannelE) {
        this.shareChannelE = shareChannelE;
    }

    public String getNeedLogin() {
        return needLogin;
    }

    public void setNeedLogin(String needLogin) {
        this.needLogin = needLogin;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
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

    public String getBannerDesc() {
        return bannerDesc;
    }

    public void setBannerDesc(String bannerDesc) {
        this.bannerDesc = bannerDesc;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Integer getBannerType() {
        return bannerType;
    }

    public void setBannerType(Integer bannerType) {
        this.bannerType = bannerType;
    }

    public String getStartTimeis() {
        return startTimeis;
    }

    public void setStartTimeis(String startTimeis) {
        this.startTimeis = startTimeis;
    }

    public String getEndTimeis() {
        return endTimeis;
    }

    public void setEndTimeis(String endTimeis) {
        this.endTimeis = endTimeis;
    }

    public String getBannerPic() {
        return bannerPic;
    }

    public void setBannerPic(String bannerPic) {
        this.bannerPic = bannerPic;
    }

    public Integer getNeedShare() {
        return needShare;
    }

    public void setNeedShare(Integer needShare) {
        this.needShare = needShare;
    }

    public String getAdditionalParam() {
        return additionalParam;
    }

    public void setAdditionalParam(String additionalParam) {
        this.additionalParam = additionalParam;
    }

    public Integer getNeedResult() {
        return needResult;
    }

    public void setNeedResult(Integer needResult) {
        this.needResult = needResult;
    }

    public String getShareChannel() {
        return shareChannel;
    }

    public void setShareChannel(String shareChannel) {
        this.shareChannel = shareChannel;
    }

}
