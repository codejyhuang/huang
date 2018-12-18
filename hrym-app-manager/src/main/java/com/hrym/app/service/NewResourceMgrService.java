package com.hrym.app.service;

import com.hrym.rpc.app.dao.model.VO.NewResourceContentVO;
import com.hrym.rpc.app.dao.model.VO.TaskMusicVO;
import com.hrym.rpc.app.dao.model.task.ManageLog;
import com.hrym.rpc.app.dao.model.task.TaskContent;
import com.hrym.rpc.app.dao.model.task.TaskItem;
import com.hrym.rpc.app.util.Result;

import java.util.Map;

/**
 * 新资源录入
 * Created by mj on 2018/5/22.
 */
public interface NewResourceMgrService {

    /**
     * 按类型查询所有功课资源
     */
    Result findAllByType(Integer typeId,Integer page, Integer rows);

    /**
     * 按类型和名称模糊查询
     * @param taskItem
     * @param page
     * @param rows
     * @return
     */
    Result searchResource(TaskItem taskItem, Integer page, Integer rows);

    /**
     * 保存功课资源
     * @param taskItem
     * @param typeId
     * @param userId
     * @return
     */
    Result saveTaskItem(TaskItem taskItem,String typeId,Integer userId);

    /**
     * 根据功课id查询功课
     * @param taskItem
     * @return
     */
    Map<String,Object> findItemById(TaskItem taskItem, Integer userId);

    /**
     * 修改功课
     * @param taskItem
     * @return
     */
    Result updateTaskItem(TaskItem taskItem,String typeId,Integer userId);

    /**
     * 功课删除
     * @return
     */
    Result deleteTaskItem(ManageLog manageLog,Integer typeId);

    /**
     * 功课内容添加
     * @param taskContent
     * @return
     */
    Result insertTaskContent(NewResourceContentVO taskContent, Integer userId, Integer typeId);

    /**
     * 音频添加
     * @param
     * @return
     */
    Result insertTaskMusic(TaskMusicVO taskMusicVO, Integer userId,Integer typeId);

    /**
     * 编辑功课内容
     * @param taskContent
     * @return
     */
    NewResourceContentVO editTaskContent(NewResourceContentVO taskContent);

    /**
     * 修改功课内容
     * @param taskContent
     * @return
     */
    Result updateTaskContent(NewResourceContentVO taskContent,Integer userId);

    /**
     * 音频修改
     */
    Result updateTaskMusic(TaskMusicVO taskMusicVO,Integer userId,Integer typeId);

    /**
     * 删除music
     * @param taskMusicVO
     * @return
     */
    Result deleteTaskMusic(TaskMusicVO taskMusicVO,Integer userId,Integer typeId);

    /**
     * 删除功课内容
     * @param manageLog
     * @return
     */
    Result deleteTaskContent(ManageLog manageLog,Integer typeId);

    /**
     * 根据功课ID查找功课内容
     * @param taskContent
     * @return
     */
    Result selectContentById(TaskContent taskContent);

    /**
     * 查询所有功课
     * @return
     */
    Result findAllItemLesson();

    /**
     * 查询所有功课内容（条件功课id）
     * @param itemId
     * @return
     */
    Result findContentLessonById(Integer itemId);
}
