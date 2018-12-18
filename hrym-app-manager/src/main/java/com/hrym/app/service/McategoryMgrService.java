package com.hrym.app.service;
import com.hrym.rpc.app.dao.model.VO.ManageLogVO;
import com.hrym.rpc.app.dao.model.VO.TaskMusicVO;
import com.hrym.rpc.app.dao.model.system.BackUser;
import com.hrym.rpc.app.dao.model.task.*;
import com.hrym.rpc.app.util.Result;

import java.util.List;
import java.util.Map;

/**
 * 后台登录
 * Created by mj on 2017/7/21.
 */
public interface McategoryMgrService {

    /**
     * 登录验证接口
     * @return
     */
    BackUser login(String userName, String pwd);

    /**
     * 类目管理
     * @return
     */
    Result categoryManage(Integer page, Integer rows);

    /**
     * 目录检索
     * @return
     */
    Result searchCatalogueById(ResourceCatalogue rcl,Integer page, Integer rows);


    /**
     * 删除类目功课
     * @param
     * @return
     */
    Result deleteCatalogue(ManageLog manageLog);

    /**
     * 新建类目功课
     */
    Result insertCatalogue (ResourceCatalogue rc,Integer userId) ;

    /**
     * 修改目录
     */
    Result updateResourceCatalogue (ResourceCatalogue resourceCatalogue,Integer userId);

    /**
     * 查询所有功课内容
     */
    Result findAllTaskContent (Integer page, Integer rows);

    /**
     * 根据目录ID查找功课
     * @return
     */
    Result findTaskItemById(Integer Id);

    /**
     * 根据功课ID查询功课内容
     * @param taskContent
     * @return
     */
    Result selectContentById (TaskContent taskContent);

    /**
     * 编辑功课内容
     * @param taskContent
     * @return
     */
    TaskContent editTaskContent (TaskContent taskContent);

    /**
     * 编辑目录
     * @param resourceCatalogue
     * @return
     */
    ResourceCatalogue editCatalogue (ResourceCatalogue resourceCatalogue,Integer userId);

    /**
     * 删除功课内容
     * @return
     */
    Result deleteTaskContent (ManageLog manageLog);

    /**
     * 修改功课内容
     */
    Result updateTaskContent(TaskContent taskContent,Integer userId);

    /**
     * 修改功课
     */
    Result updateTaskItem(TaskItem taskItem,String typeId,Integer userId);

    /**
     * 添加功课内容
     */
    Result insertTaskContent(TaskContent taskContent,Integer userId);

    /**
     * 添加功课item
     */
    Result insertTaskItem(TaskItem taskItem,String typeId,Integer userId);

    /**
     * 查询功课item
     */
    Result findAllTaskItem(Integer page, Integer rows);

    /**
     * 功课检索
     * @return
     */
    Result searchItem(TaskItem taskItem,Integer page, Integer rows);

    /**
     * 根据ID查询功课
     */
    Map<String,Object> findItemById(TaskItem taskItem,Integer userId);


    /**
     * 功课删除
     */
    Result deleteTaskItem(ManageLog manageLog);


    /**
     * 音频修改
     */
    Result updateTaskMusic(TaskMusicVO taskMusicVO,Integer userId);

    /**
     * 添加音频
     */
    Result insertTaskMusic(TaskMusicVO taskMusicVO,Integer userId);

    /**
     * 音频删除
     */
    Result deleteTaskmusic(TaskMusicVO taskMusicVO,Integer userId);

    List<TaskType> getTree();

}
