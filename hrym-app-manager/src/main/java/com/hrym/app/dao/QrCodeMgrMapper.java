package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.Qrcode;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2017/10/30.
 */
@Repository
public interface QrCodeMgrMapper {

    /**
     * 二维码URL查找
     * @return
     */
    @DataSource("slave")
    @Select("select channel_id,channel_name,channel_url,url,access_times,create_time from t_qr_code")
    List<Qrcode> findAllQrCode();

    /**
     * URL保存
     * @param qrcode
     */
    @Insert("insert into t_qr_code (channel_name,url,access_times,create_time)" +
            "values (#{channelName},#{url},#{accessTimes},#{createTime})")
    void inserAllqrurl(Qrcode qrcode);
    /**
     * 渠道点击次数
     * @param
     */
    @Update("update t_qr_code set access_times = #{accessTimes} where channel_id = #{channelId}")
    void updateAccessTimes(Qrcode qrcode);

    /**
     * url删除
     * @param channelId
     */
    @Delete("delete from t_qr_code where channel_id = #{channelId}")
    void deteleurl(Integer channelId);

    /**
     * 根据ID查找渠道内容
     * @param id
     * @return
     */
    @DataSource("/slave")
    @Select("select channel_id,channel_name,channel_url,url,access_times,create_time from t_qr_code where channel_id = #{channelId}")
    Qrcode findByCannelId(Integer id);
}
