package com.hrym.rpc.app.dao.model.VO.bookVO;

import java.io.Serializable;

/**
 * Created by xiaohan on 2017/11/15.
 */
public class TaskItemVO implements Serializable {

    private Integer itemId;
    private String itemName;
    private String itemIntro;
    private String itemPic;
    private String titleDesc;
    private String fileTxt;
    private Integer isAdd;
    private String currentPeopleNum;
    private Integer indexId;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemIntro() {
        return itemIntro;
    }

    public void setItemIntro(String itemIntro) {
        this.itemIntro = itemIntro;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public String getCurrentPeopleNum() {
        return currentPeopleNum;
    }

    public void setCurrentPeopleNum(String currentPeopleNum) {
        this.currentPeopleNum = currentPeopleNum;
    }

    public String getFileTxt() {
        return fileTxt;
    }

    public void setFileTxt(String fileTxt) {
        this.fileTxt = fileTxt;
    }

    public Integer getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(Integer isAdd) {
        this.isAdd = isAdd;
    }

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }

    @Override
    public String toString() {
        return "TaskItemVO{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemIntro='" + itemIntro + '\'' +
                ", itemPic='" + itemPic + '\'' +
                ", titleDesc='" + titleDesc + '\'' +
                ", fileTxt='" + fileTxt + '\'' +
                ", isAdd=" + isAdd +
                ", currentPeopleNum='" + currentPeopleNum + '\'' +
                ", indexId=" + indexId +
                '}';
    }
}



