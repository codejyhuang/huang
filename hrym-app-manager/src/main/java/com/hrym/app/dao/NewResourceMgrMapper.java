package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.NewResourceContentVO;
import com.hrym.rpc.app.dao.model.VO.TaskMusicVO;
import com.hrym.rpc.app.dao.model.task.*;
import com.hrym.rpc.app.dao.model.task.meditation.MeditationContent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mj on 2018/5/22.
 */
@Repository
public interface NewResourceMgrMapper {

    /**
     * 查询所有功课（排除禅修和经书）
     * @return
     */
    @DataSource("slave")
    @Select("select " +
            "i.item_id," +
            "i.is_support," +
            "i.item_name," +
            "i.item_intro," +
            "i.order_num," +
            "i.alias_name," +
            "i.title_desc," +
            "i.item_pic," +
            "i.catalogue_id," +
            "c.catalogue_name " +
            "FROM t_resource_item i " +
            "LEFT JOIN t_resource_catalogue c ON i.catalogue_id = c.catalogue_id " +
            "where i.catalogue_id != 10001 or i.catalogue_id != 10002")
    List<TaskItem> findAllItem();

    /**
     * 查询所有经书
     * @return
     */
    @DataSource("slave")
    @Select("select " +
            "i.item_id," +
            "i.is_support," +
            "i.item_name," +
            "i.item_intro," +
            "i.order_num," +
            "i.alias_name," +
            "i.title_desc," +
            "i.item_pic," +
            "i.catalogue_id," +
            "c.catalogue_name " +
            "FROM t_resource_item_book i " +
            "LEFT JOIN t_resource_catalogue c ON i.catalogue_id = c.catalogue_id")
    List<TaskItem> findAllBook();

    /**
     * 查询所有禅修
     * @return
     */
    @DataSource("slave")
    @Select("select " +
            "i.item_id," +
            "i.is_support," +
            "i.item_name," +
            "i.item_intro," +
            "i.order_num," +
            "i.alias_name," +
            "i.title_desc," +
            "i.item_pic," +
            "i.catalogue_id," +
            "c.catalogue_name " +
            "FROM t_resource_item_meditation i " +
            "LEFT JOIN t_resource_catalogue c ON i.catalogue_id = c.catalogue_id")
    List<TaskItem> findAllMeditation();


    /**
     * 通过功课名称查询功课（排除禅修和经书）
     */
    @DataSource("slave")
    @Select("select " +
            "i.item_id," +
            "i.is_support," +
            "i.item_name," +
            "i.item_intro," +
            "i.order_num," +
            "i.alias_name," +
            "i.title_desc," +
            "i.item_pic," +
            "i.catalogue_id," +
            "c.catalogue_name " +
            "FROM t_resource_item i " +
            "LEFT JOIN t_resource_catalogue c ON i.catalogue_id = c.catalogue_id " +
            "WHERE (i.catalogue_id != 10001 or i.catalogue_id != 10002) " +
            "and i.item_name LIKE '%${itemName}%'")
    List<TaskItem> findItemByName(@Param("itemName") String itemName);

    /**
     * 通过经书功课名称查询功课
     */
    @DataSource("slave")
    @Select("select " +
            "i.item_id," +
            "i.is_support," +
            "i.item_name," +
            "i.item_intro," +
            "i.order_num," +
            "i.alias_name," +
            "i.title_desc," +
            "i.item_pic," +
            "i.catalogue_id," +
            "c.catalogue_name " +
            "FROM t_resource_item_book i " +
            "LEFT JOIN t_resource_catalogue c ON i.catalogue_id = c.catalogue_id " +
            "WHERE i.item_name LIKE '%${itemName}%'")
    List<TaskItem> findBookByName(@Param("itemName") String itemName);

    /**
     * 通过功课禅修名称查询功课
     */
    @DataSource("slave")
    @Select("select " +
            "i.item_id," +
            "i.is_support," +
            "i.item_name," +
            "i.item_intro," +
            "i.order_num," +
            "i.alias_name," +
            "i.title_desc," +
            "i.item_pic," +
            "i.catalogue_id," +
            "c.catalogue_name " +
            "FROM t_resource_item_meditation i " +
            "LEFT JOIN t_resource_catalogue c ON i.catalogue_id = c.catalogue_id " +
            "WHERE i.item_name LIKE '%${itemName}%'")
    List<TaskItem> findMeditationByName(@Param("itemName") String itemName);

    /**
     * 添加经书
     */
    @Insert("insert into t_resource_item_book(item_name,item_intro,order_num,alias_name,title_desc,item_pic,catalogue_id,create_time,update_time,is_support)" +
            "values (#{itemName},#{itemIntro},#{orderNum},#{aliasName},#{titleDesc},#{itemPic},#{catalogueId},#{createTime},#{updateTime},#{isSupport})")
    void saveBook(TaskItem taskItem );

    /**
     * 添加禅修
     */
    @Insert("insert into t_resource_item_meditation(item_name,item_intro,order_num,alias_name,title_desc,item_pic,catalogue_id,create_time,update_time,is_support)" +
            "values (#{itemName},#{itemIntro},#{orderNum},#{aliasName},#{titleDesc},#{itemPic},#{catalogueId},#{createTime},#{updateTime},#{isSupport})")
    void saveMeditation(TaskItem taskItem );

    /**
     * 通过功课ID查询经书功课
     */
    @DataSource("slave")
    @Select("select item_id,item_name,is_support,item_intro,order_num,alias_name,title_desc,item_pic,catalogue_id,create_time,update_time " +
            "from t_resource_item_book where item_id=#{itemId}")
    TaskItem findBookById(Integer itemId);

    /**
     * 通过功课ID查询禅修功课
     */
    @DataSource("slave")
    @Select("select item_id,item_name,is_support,item_intro,order_num,alias_name,title_desc,item_pic,catalogue_id,create_time,update_time " +
            "from t_resource_item_meditation where item_id=#{itemId}")
    TaskItem findMeditationById(Integer itemId);

    /**
     * 经书功课修改
     */
    @Update("update t_resource_item_book set item_name = #{t.itemName},item_intro = #{t.itemIntro}," +
            "alias_name = #{t.aliasName},title_desc = #{t.titleDesc},item_pic = #{t.itemPic},update_time = #{t.updateTime},catalogue_id = #{t.catalogueId},is_support = #{t.isSupport}" +
            " where item_id=#{t.itemId}")
    void updateBookItem(@Param("t") TaskItem taskItem);

    /**
     * 禅修功课修改
     */
    @Update("update t_resource_item_meditation set item_name = #{t.itemName},item_intro = #{t.itemIntro}," +
            "alias_name = #{t.aliasName},title_desc = #{t.titleDesc},item_pic = #{t.itemPic},update_time = #{t.updateTime},catalogue_id = #{t.catalogueId},is_support = #{t.isSupport}" +
            " where item_id=#{t.itemId}")
    void updateMeditationItem(@Param("t") TaskItem taskItem);

    /**
     * 经书功课删除
     */
    @Delete("delete from t_resource_item_book where item_id=#{itemId}")
    void deleteBookItem(Integer itemId);

    /**
     * 禅修功课删除
     */
    @Delete("delete from t_resource_item_meditation where item_id=#{itemId}")
    void deleteMeditationItem(Integer itemId);

    @DataSource("slave")
    @Select("SELECT item_id,item_name FROM t_resource_item_book WHERE item_id=#{itemId}")
    ManageLog findByBookId(Integer itemId);

    @DataSource("slave")
    @Select("SELECT item_id,item_name FROM  t_resource_item_meditation WHERE item_id=#{itemId}")
    ManageLog findByMeditationId(Integer itemId);

    /**
     * 根据功课ID查询禅修功课内容
     */
    @DataSource("slave")
    @Select("SELECT * FROM t_resource_content_meditation c " +
            "WHERE c.item_id = #{id}")
    List<MeditationContent> findAllMeditationContentById(Integer id);

    /**
     * 根据经书功课ID删除功课内容
     * @param id
     */
    @Delete("delete from t_resource_content_book where item_id = #{id}")
    void deleteBookTaskContentByItemId(Integer id);

    /**
     * 根据禅修功课ID删除功课内容
     * @param id
     */
    @Delete("delete from t_resource_content_meditation where item_id = #{id}")
    void deleteMeditationTaskContentByItemId(Integer id);

    /**
     * 音频删除
     */
    @Delete("delete from t_resource_music_meditation where item_id=#{itemId}")
    void deleteMeditationMusicByItemId( Integer itemId );

    /**
     * 根据禅修功课内容ID查找music
     */
    @DataSource("slave")
    @Select("SELECT item_content_id,music_name,music_file,music_subtitle,end_time,start_time,start_num,step," +
            "setting_time,singer,album_name,disk_number,composer,down_time,round_time,shou_end_time,need_set_time," +
            "version_id,music_id,orbital_number,style,need_set_time,shou_end_time,round_time,down_time,music_pic,voice_down," +
            "voice_start,push_time,music_file_android,music_version FROM t_resource_music_meditation WHERE item_content_id = #{id}")
    List<TaskMusic> findAllMeditationTaskMusicById (Integer id);

    /**
     * 经书功课内容添加
     */
    @Insert("insert into t_resource_content_book(item_id,version_name,source,author,origin,file_txt,file_pic,text,translator,years,video_file,content_version,version,create_time,voice_count,voice_name,voice_dic,voice_lm)" +
            "values(#{itemId},#{versionName},#{source},#{author},#{origin},#{fileTxt},#{filePic},#{text},#{translator},#{years},#{videoFile},#{contentVersion},#{version},#{createTime},#{voiceCount},#{voiceName},#{voiceDic},#{voiceLm})")
    void  insertBookContent(NewResourceContentVO taskContent);

    /**
     * 禅修功课内容添加
     */
    @Insert("insert into t_resource_content_meditation" +
            "(item_id,content_name,music_file,music_subtitle,video_file,setting_time," +
            "fixed_music_time,fixed_video_time,version_id,voice_down,voice_start," +
            "end_music_file,is_music_edit,create_time,update_time)" +
            "values(#{itemId},#{contentName},#{musicFile},#{musicSubtitle},#{videoFile},#{settingTime}," +
            "#{fixedMusicTime},#{fixedVideoTime},#{versionId},#{voiceDown},#{voiceStart},#{endMusicFile}," +
            "#{isMusicEdit},#{createTime},#{updateTime})")
    void  insertMeditationContent(NewResourceContentVO taskContent);

    /**
     * 音频添加
     */
    @Insert("insert into t_resource_music_meditation(item_content_id,item_id,music_name,music_file,music_subtitle,end_time," +
            "start_time,start_num,step,setting_time,singer,album_name,disk_number,composer,push_time,orbital_number,style,version_id," +
            "need_set_time,shou_end_time,round_time,down_time,music_pic,voice_down,voice_start,music_file_android,music_version)" +
            "values(#{itemContentId},#{itemId},#{musicName},#{musicFile},#{musicSubtitle},#{endTime},#{startTime},#{startNum},#{step},#{settingTime}," +
            "#{singer},#{albumName},#{diskNumber},#{composer},#{pushTime},#{orbitalNumber},#{style},#{versionId}," +
            "#{needSetTime},#{shouEndTime},#{roundTime},#{downTime},#{musicPic},#{voiceDown},#{voiceStart},#{musicFileAndroid},#{musicVersion})")
    void insertMeditationMusic(TaskMusicVO taskMusicVO);

    /**
     * 根据经书内容ID查询功课内容
     */
    @DataSource("slave")
    @Select("SELECT c.item_content_id, c.years, c.source, c.author, c.translator, c.file_txt,c.video_file,c.content_version,c.version,c.text,c.file_pic, c.pic_version_name,c.origin," +
            "c.version_name,voice_count,voice_name,voice_dic,voice_lm,c.item_id FROM t_resource_content_book c " +
            "WHERE c.item_content_id = #{id}")
    NewResourceContentVO findBookContentById(Integer id);

    /**
     * 根据禅修内容ID查询功课内容
     */
    @DataSource("slave")
    @Select("SELECT * FROM t_resource_content_meditation " +
            "WHERE content_id = #{id}")
    NewResourceContentVO findMeditationContentById(Integer id);


    /**
     *功课经书内容修改
     */
    @Update("update t_resource_content_book set version_name = #{versionName},text = #{text},source = #{source},pic_version_name = #{picVersionName}," +
            "file_txt = #{fileTxt},file_pic = #{filePic},author = #{author},origin = #{origin},translator = #{translator},version = #{version},content_version = #{contentVersion}," +
            "video_file = #{videoFile},voice_count = #{voiceCount},voice_name = #{voiceName},voice_dic = #{voiceDic},voice_lm = #{voiceLm}" +
            " where item_content_id = #{itemContentId}")
    void updateBookContent(NewResourceContentVO taskContent);

    /**
     *功课禅修内容修改
     */
    @Update("update t_resource_content_meditation set content_name = #{contentName},music_file = #{musicFile},music_subtitle = #{musicSubtitle},video_file = #{videoFile}," +
            "setting_time = #{settingTime},fixed_music_time = #{fixedMusicTime},fixed_video_time = #{fixedVideoTime},version_id = #{versionId},voice_down = #{voiceDown},voice_start = #{voiceStart},end_music_file = #{endMusicFile}," +
            "update_time = #{updateTime},is_music_edit = #{isMusicEdit} " +
            "where content_id = #{contentId}")
    void updateMeditationContent(NewResourceContentVO taskContent);

    /**
     * 根据经书功课内容查找功课ID和功课内容ID
     * @param itemContentId
     * @return
     */
    @DataSource("slave")
    @Select("SELECT t.item_content_id,t.item_id,t.version_name,m.item_name " +
            "FROM " +
            " t_resource_content_book t " +
            "LEFT JOIN t_resource_item_book m " +
            "ON m.item_id=t.item_id " +
            "WHERE item_content_id=#{itemContentId}")
    ManageLog findBookByContentId(Integer itemContentId);

    /**
     * 根据禅修功课内容查找功课ID和功课内容ID
     * @param contentId
     * @return
     */
    @DataSource("slave")
    @Select("SELECT t.content_id,t.item_id,t.content_name,m.item_name " +
            "FROM " +
            " t_resource_content_meditation t " +
            "LEFT JOIN t_resource_item_meditation m " +
            "ON m.item_id=t.item_id " +
            "WHERE content_id=#{contentId}")
    ManageLog findMeditationByContentId(Integer contentId);

    /**
     * 根据ID删除经书功课内容
     * @param id
     */
    @Delete("delete from t_resource_content_book where item_content_id = #{id}")
    void deleteBookContent(Integer id);

    /**
     * 根据ID删除禅修功课内容
     * @param id
     */
    @Delete("delete from t_resource_content_meditation where content_id = #{id}")
    void deleteMeditationContent(Integer id);


    /**
     * 根据经书功课ID查询功课内容
     */
    @DataSource("slave")
    @Select("SELECT c.item_content_id, c.years, c.source, c.author, c.translator, c.file_txt,c.video_file,c.content_version,c.file_pic, c.text,c.version,c.pic_version_name," +
            "c.version_name, c.item_id,c.origin FROM t_resource_content_book c " +
            "WHERE c.item_id = #{id}")
    List<TaskContent> findAllBookContentById(Integer id);

}
