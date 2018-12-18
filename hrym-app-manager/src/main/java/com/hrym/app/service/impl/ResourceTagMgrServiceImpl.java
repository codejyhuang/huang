package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrym.app.dao.ResourceTagMgrMapper;
import com.hrym.app.service.ResourceTagMgrService;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.dao.model.VO.lessonVO.ResourceTagVO;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceTag;
import com.hrym.rpc.app.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hrym13 on 2018/7/3.
 */
@Service
public class ResourceTagMgrServiceImpl implements ResourceTagMgrService {

    @Autowired
    private ResourceTagMgrMapper tagMgrMapper;

    /**
     * 获取功课标签列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllTags(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        PageHelper.startPage(page,rows);
        List<ResourceTagVO> list = tagMgrMapper.findAllTags();

        for (ResourceTagVO tag :list) {

            if (tag.getCreateTime() != null) {

                String createTimes = DateUtil.timestampToDates(tag.getCreateTime(),DateUtil.TIME_PATTON_DEFAULT);
                tag.setCreateTimes(createTimes);
            }
            if (tag.getUpdateTime() != null) {

                String updateTimes = DateUtil.timestampToDates(tag.getUpdateTime(),DateUtil.TIME_PATTON_DEFAULT);
                tag.setUpdateTimes(updateTimes);
            }

        }

        PageInfo pageInfo = new PageInfo(list);
        long total = pageInfo.getTotal();

        return new Result(code,message,total,list);
    }

    /**
     * 获取功课列表
     * @return
     */
    @Override
    public Result findTagsList() {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

      List<ResourceTagVO> list = tagMgrMapper.findAllTags();

        return new Result(code,message,list);
    }

    /**
     * 根据ID查找功课标签
     * @param id
     * @return
     */
    @Override
    public Result findResourceTagById(Integer id) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        ResourceTag resourceTag = tagMgrMapper.findResourceTagById(id);

        return new Result(code,message,resourceTag);

    }

    /**
     * 功课标签录入
     * @param resourceTag
     */
    @Override
    public Result insertResourceTag(ResourceTag resourceTag) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        resourceTag.setCreateTime(DateUtil.currentSecond());
        resourceTag.setUpdateTime(DateUtil.currentSecond());
        tagMgrMapper.insertResourceTag(resourceTag);

        return new Result(code,message,null);

    }

    /**
     * 功课标签更新
     * @param resourceTag
     */
    @Override
    public Result updateResourceTag(ResourceTag resourceTag) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        resourceTag.setUpdateTime(DateUtil.currentSecond());
        tagMgrMapper.updateResourceTag(resourceTag);

        return new Result(code,message,null);

    }

    /**
     * 功课标签删除
     * @param tagId
     */
    @Override
    public Result deleteResourceTagById(Integer tagId) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        tagMgrMapper.deleteResourceTagById(tagId);

        return new Result(code,message,null);

    }
}
