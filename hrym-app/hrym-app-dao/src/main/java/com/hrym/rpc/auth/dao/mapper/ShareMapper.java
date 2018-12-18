package com.hrym.rpc.auth.dao.mapper;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.ShareInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * Created by mj on 2017/10/30.
 */
public interface ShareMapper extends Mapper<ShareInfo> {

    /**
     * 保存分享信息s
     * @param share
     */
    @Insert("insert t_share(share_type,share_type_value,task_id) value(#{shareType},#{shareTypeValue},#{taskId})")
    void saveShareInfo(ShareInfo share);

    /**
     * 通过ID查询分享信息
     * @param id
     * @return
     */
    @Select("select * from t_share where share_id=#{id}")
    ShareInfo findShareInfoById(Integer id);

    @DataSource("slave")
    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();

}
