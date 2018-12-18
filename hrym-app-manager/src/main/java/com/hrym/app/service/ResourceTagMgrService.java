package com.hrym.app.service;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceTag;
import com.hrym.rpc.app.util.Result;
import org.apache.ibatis.annotations.Select;

/**
 * Created by hrym13 on 2018/7/3.
 */
public interface ResourceTagMgrService {

    /**
     * 功课标签列表
     * @param
     * @return
     */
    Result findAllTags(Integer page,Integer rows);

    /**
     * 获取功课标签
     * @return
     */
    Result findTagsList();

    /**
     * 根据ID查找功课标签
     * @param id
     * @return
     */
   Result findResourceTagById(Integer id);

    /**
     * 功课标签录入
     * @param resourceTag
     */
    Result insertResourceTag(ResourceTag resourceTag);

    /**
     * 功课标签更新
     * @param resourceTag
     */
    Result updateResourceTag(ResourceTag resourceTag);

    /**
     * 功课标签删除
     * @param tagId
     */
    Result deleteResourceTagById(Integer tagId);
}
