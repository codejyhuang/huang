package com.hrym.rpc.app.dao.model.VO.customVO;

import com.hrym.rpc.app.dao.model.task.custom.TaskPlanCustom;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/6/21.
 */
public class TaskPlanCustomVO extends TaskPlanCustom implements Serializable {

    private String itemPic;

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    @Override
    public String toString() {
        return "TaskPlanCustomVO{" +
                "itemPic='" + itemPic + '\'' +
                '}';
    }
}
