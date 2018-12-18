package com.hrym.rpc.app.dao.model.VO.bookVO;

import java.io.Serializable;

/**
 * Created by mj on 2018/4/16.
 */
public class BookcaseVO implements Serializable {

    private Integer indexId;
    private Integer doneNum;
    private String fileTxt;
    private Integer itemId;
    private String itemName;
    private String itemPic;
    private String percent;
    private Integer typeId;
    private String unitDesc;
    private String titleDesc;

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }

    public Integer getDoneNum() {
        return doneNum;
    }

    public void setDoneNum(Integer doneNum) {
        this.doneNum = doneNum;
    }

    public String getFileTxt() {
        return fileTxt;
    }

    public void setFileTxt(String fileTxt) {
        this.fileTxt = fileTxt;
    }

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

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getUnitDesc() {
        return unitDesc;
    }

    public void setUnitDesc(String unitDesc) {
        this.unitDesc = unitDesc;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    @Override
    public String toString() {
        return "BookcaseVO{" +
                "indexId=" + indexId +
                ", doneNum=" + doneNum +
                ", fileTxt='" + fileTxt + '\'' +
                ", itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemPic='" + itemPic + '\'' +
                ", percent='" + percent + '\'' +
                ", typeId=" + typeId +
                ", unitDesc='" + unitDesc + '\'' +
                ", titleDesc='" + titleDesc + '\'' +
                '}';
    }
}
