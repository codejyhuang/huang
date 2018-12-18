package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.ManageLog;
import com.hrym.rpc.app.dao.model.task.TaskContent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功课内容mappper
 * Created by hrym13 on 2017/8/1.
 */
@Repository
public interface TaskContentMgrMapper {

    /**
     * 所有功课内容
     */
    @DataSource("slave")
    List<TaskContent> findAllTaskContent();

    /**
     * 模糊查询
     */
    @DataSource("slave")
    List<TaskContent> likefindTaskContent();

    /**
     * 根据功课ID查询功课内容
     */
    @DataSource("slave")
    @Select("SELECT c.item_content_id, c.years, c.source, c.author, c.translator, c.file_txt,c.video_file,c.content_version,c.file_pic, c.text,c.version,c.pic_version_name," +
            "c.version_name, c.item_id,c.origin FROM t_resource_content c " +
            "WHERE c.item_id = #{id}")
    List<TaskContent> findAllContentById(Integer id);

    /**
     * 根据内容ID查询功课内容
     */
    @DataSource("slave")
    @Select("SELECT c.item_content_id, c.years, c.source, c.author, c.translator, c.file_txt,c.video_file,c.content_version,c.version,c.text,c.file_pic, c.pic_version_name,c.origin," +
            "c.version_name,voice_count,voice_name,voice_dic,voice_lm,c.item_id FROM t_resource_content c " +
            "WHERE c.item_content_id = #{id}")
    TaskContent findContentById(Integer id);


    /**
     * 根据功课内容查找功课ID和功课内容ID
     * @param itemContentId
     * @return
     */
    @DataSource("slave")
    @Select("SELECT t.item_content_id,t.item_id,t.version_name,m.item_name " +
            "FROM " +
            " t_resource_content t " +
            "LEFT JOIN t_resource_item m " +
            "ON m.item_id=t.item_id " +
            "WHERE item_content_id=#{itemContentId}")
    ManageLog findById(Integer itemContentId);


    /**
     * 根据ID删除功课内容
     * @param id
     */
    @Delete("delete from t_resource_content where item_content_id = #{id}")
    void deleteTaskContent(Integer id);

    /**
     * 根据功课ID删除功课内容
     * @param id
     */
    @Delete("delete from t_resource_content where item_id = #{id}")
    void deleteTaskContentByItemId(Integer id);

    /**
     *功课内容修改
     */
    @Update("update t_resource_content set version_name = #{versionName},text = #{text},source = #{source},pic_version_name = #{picVersionName}," +
            "file_txt = #{fileTxt},file_pic = #{filePic},author = #{author},origin = #{origin},translator = #{translator},version = #{version},content_version = #{contentVersion}," +
            "video_file = #{videoFile},voice_count = #{voiceCount},voice_name = #{voiceName},voice_dic = #{voiceDic},voice_lm = #{voiceLm}" +
            " where item_content_id = #{itemContentId}")
    void updateTaskContent(TaskContent taskContent);

    /**
     * 功课内容添加
     */
    @Insert("insert into t_resource_content(item_id,version_name,source,author,origin,file_txt,file_pic,text,translator,years,video_file,content_version,version,create_time,voice_count,voice_name,voice_dic,voice_lm)" +
            "values(#{itemId},#{versionName},#{source},#{author},#{origin},#{fileTxt},#{filePic},#{text},#{translator},#{years},#{videoFile},#{contentVersion},#{version},#{createTime},#{voiceCount},#{voiceName},#{voiceDic},#{voiceLm})")
    void  insertTaskContent(TaskContent taskContent);


    /**
     * 查询最大id
     * @return
     */
    @Select("select max(item_content_id) from t_resource_content")
   int findMax();

    /**
     * 查找最新插入的ID
     * @return
     */
    @DataSource("master")
    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();
}
