package com.hrym.rpc.app.common.constant;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mj on 2017/8/31.
 */
public class AssociationParam extends BaseRequestParam implements Serializable {

    // 参数data
    private AssociationData data;

    public AssociationData getData() {
        return data;
    }

    public void setData(AssociationData data) {
        this.data = data;
    }

    public Integer getUserId() {
        return data.getUserId();
    }

    public Integer getPageNo() {
        return data.getPageNo();
    }

    public String getFilterStr() {
        return data.getFilterStr();
    }

    public Integer getAssociationId() {
        return data.getAssociationId();
    }

    public String getAssociationIntro() {
        return data.getAssociationIntro();
    }

    public String getAssociationName() {
        return data.getAssociationName();
    }

    public String getAvatarUrl() {
        return data.getAvatarUrl();
    }

    public String getToken() {
        return data.getToken();
    }

    public String getMsg() {
        return data.getMsg();
    }

    public Integer getApplyValue() {
        return data.getApplyValue();
    }

    public Integer getMsgId() {
        return data.getMsgId();
    }

    public Integer getFromUserId() {
        return data.getFromUserId();
    }

    public Integer getToUserId() {
        return data.getToUserId();
    }

    public Integer getArticleId() {
        return data.getArticleId();
    }

    public Integer getInfoType() {
        return data.getInfoType();
    }

    public String getResourceDesc() {
        return data.getResourceDesc();
    }

    public String getResourceName() {
        return data.getResourceName();
    }

    public Integer getResourceType() {
        return data.getResourceType();
    }

    public String getResourceUrl() {
        return data.getResourceUrl();
    }

    public String getUserName() {
        return data.getUserName();
    }

    public String getResourceDisplayName() {
        return data.getResourceDisplayName();
    }

    public Integer getUuid() {
        return data.getUuid();
    }

    public String getTopicDesc() {
        return data.getTopicDesc();
    }

    public Integer getTopicType() {
        return data.getTopicType();
    }

    public String getUrl1() {
        return data.getUrl1();
    }

    public String getUrl2() {
        return data.getUrl2();
    }

    public String getUrl3() {
        return data.getUrl3();
    }

    public String getUrl4() {
        return data.getUrl4();
    }

    public String getUrl5() {
        return data.getUrl5();
    }

    public String getUrl6() {
        return data.getUrl6();
    }

    public String getUrl7() {
        return data.getUrl7();
    }

    public String getUrl8() {
        return data.getUrl8();
    }

    public String getUrl9() {
        return data.getUrl9();
    }

    public Integer getType() {
        return data.getType();
    }

    public Integer getStatus() {
        return data.getStatus();
    }
}

class AssociationData implements Serializable {

    private Integer userId;         //用户ID
    private Integer pageNo;          //查询页码，从1开始
    private String filterStr;       //社群过滤字段(社群名称或者创建者名称)
    private Integer associationId;  // 社群ID
    private String associationIntro;//社群简介
    private String associationName; //社群名称
    private String avatarUrl;       //社群头像
    private String token;           //登录唯一凭证
    private String msg;             //申请理由
    private Integer applyValue;     //0:同意，1:拒绝
    private Integer msgId;          //用户申请时的消息id
    private Integer fromUserId;     //消息发送方ID
    private Integer toUserId;       //消息接受方ID
    private Integer articleId;      //文章ID
    private Integer infoType;       //消息类型
    private String resourceDesc;    //资源描述
    private String resourceName;    //资源名称
    private Integer resourceType;   //资源类型0:共享资源 1:社群资源 2:私有资源
    private String resourceUrl;     //资源url
    private String userName;        //用户名称
    private String resourceDisplayName;//资源显示名称，包括后缀
    private Integer uuid;
    private String topicDesc;       //主题内容描述
    private Integer topicType;      //0:功课相关\1:社群相关
    private String url1;
    private String url2;
    private String url3;
    private String url4;
    private String url5;
    private String url6;
    private String url7;
    private String url8;
    private String url9;
    private List<String> urlList;
    private Integer type;
    private Integer status;

    /**
     * 转换发表图片的URL，改为缩略图格式
     * @param str
     * @param type
     * @return
     */
    private  String getConvertVal(String str, int type) {

        int index = str.lastIndexOf(".");
        if(index<0)
            return str;
        if(type==0) {
            return str.substring(0,index)+"_200x200x0"+str.substring(index);
        }
        else if(type==1) {
            return str.substring(0,index)+"_200x200x1"+str.substring(index);
        }

        return "";
    }

    public void setUrlList(List<String> urlList) {

        if(urlList.size()==1) {
            //当只上传一个文件时，录入_200x200x0的缩率图,等比缩率
            String url = urlList.get(0);
            url1 =  getConvertVal(url,0);
            return;
        }
        this.urlList = urlList;
        for(int i= 0 ;i<urlList.size();i++){
            String val = urlList.get(i);
            if(i==0)
                url1 = getConvertVal(val,1);
            else if(i==1)
                url2= getConvertVal(val,1);
            else if(i==2)
                url3= getConvertVal(val,1);
            else if(i==3)
                url4= getConvertVal(val,1);
            else if(i==4)
                url5= getConvertVal(val,1);
            else if(i==5)
                url6= getConvertVal(val,1);
            else if(i==6)
                url7= getConvertVal(val,1);
            else if(i==7)
                url8= getConvertVal(val,1);
            else if(i==8)
                url9= getConvertVal(val,1);
            else {
                break;
            }
        }

    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getFilterStr() {
        return filterStr;
    }

    public void setFilterStr(String filterStr) {
        this.filterStr = filterStr;
    }

    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    public String getAssociationIntro() {
        return associationIntro;
    }

    public void setAssociationIntro(String associationIntro) {
        this.associationIntro = associationIntro;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getApplyValue() {
        return applyValue;
    }

    public void setApplyValue(Integer applyValue) {
        this.applyValue = applyValue;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResourceDisplayName() {
        return resourceDisplayName;
    }

    public void setResourceDisplayName(String resourceDisplayName) {
        this.resourceDisplayName = resourceDisplayName;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getUrl4() {
        return url4;
    }

    public void setUrl4(String url4) {
        this.url4 = url4;
    }

    public String getUrl5() {
        return url5;
    }

    public void setUrl5(String url5) {
        this.url5 = url5;
    }

    public String getUrl6() {
        return url6;
    }

    public void setUrl6(String url6) {
        this.url6 = url6;
    }

    public String getUrl7() {
        return url7;
    }

    public void setUrl7(String url7) {
        this.url7 = url7;
    }

    public String getUrl8() {
        return url8;
    }

    public void setUrl8(String url8) {
        this.url8 = url8;
    }

    public String getUrl9() {
        return url9;
    }

    public void setUrl9(String url9) {
        this.url9 = url9;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
