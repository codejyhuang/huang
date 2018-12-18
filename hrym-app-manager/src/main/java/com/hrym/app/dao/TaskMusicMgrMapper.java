package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.TaskMusicVO;
import com.hrym.rpc.app.dao.model.task.ManageLog;
import com.hrym.rpc.app.dao.model.task.SubResource;
import com.hrym.rpc.app.dao.model.task.TaskMusic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 音频Mapper
 * Created by hrym13 on 2017/8/10.
 */
@Repository
public interface TaskMusicMgrMapper {


    /**
     * 根据功课内容ID查找music
     */
    @DataSource("slave")
    @Select("SELECT item_content_id,music_name,music_file,music_subtitle,end_time,start_time,start_num,step," +
            "setting_time,singer,album_name,disk_number,composer,down_time,round_time,shou_end_time,need_set_time," +
            "version_id,music_id,orbital_number,style,need_set_time,shou_end_time,round_time,down_time,music_pic,voice_down," +
            "voice_start,push_time,music_file_android,music_version FROM t_resource_music WHERE item_content_id = #{id}")
    List<TaskMusic> findAllTaskMusicById (Integer id);

    @DataSource("slave")
    @Select("SELECT m.music_id,m.item_content_id,m.item_id,m.music_name,i.item_name,c.version_name " +
            "FROM t_resource_music m " +
            "left JOIN t_resource_item i ON m.item_id=i.item_id " +
            "LEFT JOIN t_resource_content c on c.item_content_id=m.item_content_id " +
            "WHERE m.music_id = #{musicId}")
    TaskMusicVO findByMusicId(Integer musicId);

    /**
     * 字表查询
     * @return
     */
    @DataSource("slave")
    @Select("SELECT idt_sub_resource,type,value,des,resource_id FROM t_sub_resource WHERE resource_id = #{resourceId} ")
    SubResource findAllSubResource(Integer resourceId);


    /**
     * 音频修改
     */
    @Update("update t_resource_music set music_name = #{musicName},music_file = #{musicFile},music_subtitle = #{musicSubtitle},end_time = #{endTime}," +
            "start_time = #{startTime},start_num = #{startNum},step = #{step},setting_time = #{settingTime},singer = #{singer},album_name = #{albumName}," +
            "disk_number = #{diskNumber},composer = #{composer},push_time = #{pushTime},orbital_number = #{orbitalNumber},style = #{style}," +
            "need_set_time = #{needSetTime},shou_end_time = #{shouEndTime},round_time = #{roundTime},version_id = #{versionId},down_time = #{downTime}," +
            "music_pic = #{musicPic},voice_down = #{voiceDown},voice_start = #{voiceStart},music_file_android = #{musicFileAndroid},music_version = #{musicVersion} where music_id = #{musicId} ")
    void updateTaskMusic(TaskMusicVO taskMusicVO);

    /**
     * 子音频修改
     * @param taskMusicVO
     */
    @Update("update t_sub_resource set type = #{type},value = #{value},des = #{des} where idt_sub_resource = #{idtSubResource}")
    void updateSubResource(TaskMusicVO taskMusicVO);



    /**
     * 音频添加
     */
    @Insert("insert into t_resource_music(item_content_id,item_id,music_name,music_file,music_subtitle,end_time," +
            "start_time,start_num,step,setting_time,singer,album_name,disk_number,composer,push_time,orbital_number,style,version_id," +
            "need_set_time,shou_end_time,round_time,down_time,music_pic,voice_down,voice_start,music_file_android,music_version)" +
            "values(#{itemContentId},#{itemId},#{musicName},#{musicFile},#{musicSubtitle},#{endTime},#{startTime},#{startNum},#{step},#{settingTime}," +
            "#{singer},#{albumName},#{diskNumber},#{composer},#{pushTime},#{orbitalNumber},#{style},#{versionId}," +
            "#{needSetTime},#{shouEndTime},#{roundTime},#{downTime},#{musicPic},#{voiceDown},#{voiceStart},#{musicFileAndroid},#{musicVersion})")
    void insertTaskMusic(TaskMusicVO taskMusicVO);

    /**
     * 子音频添加
     * @param taskMusicVO
     */
    @Insert("insert into t_sub_resource(type,value,des,resource_id)values(#{type},#{value},#{des},#{resourceId})")
    void insertSubResource(TaskMusicVO taskMusicVO);


    /**
     * 音频删除
     */
    @Delete("delete from t_resource_music where music_id=#{musicId}")
    void deleteTaskmusic( Integer musicId );

    /**
     * 音频删除
     */
    @Delete("delete from t_resource_music where item_content_id=#{contentId}")
    void deleteTaskMusicByContentId( Integer contentId );

    /**
     * 子音频删除
     * @param
     */
    @Delete("delete from t_sub_resource where resource_id = #{musicId}")
    void deleteSubResource(Integer musicId);

    /**
     * 音频删除
     */
    @Delete("delete from t_resource_music where item_id=#{itemId}")
    void deleteTaskmusicByItemId( Integer itemId );

    @DataSource("master")
    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();

}
