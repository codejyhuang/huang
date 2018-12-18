package com.hrym.rpc.association.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.Association;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 社群mapper
 * Created by mj on 2017/8/17.
 */
public interface AssociationMapper {

    /**
     * 通过id查询社群列表
     * @param userId
     * @return
     */
    @DataSource("slave")
    List<Association> findAllAssociationById(Integer userId);

    /**
     * 查询所有社群列表,过滤字段(社群名称或者创建者名称)
     * @return
     */
    @DataSource("slave")
    @Select("select (select count(1) from t_association_member m where m.idt_association = t.idt_association) as num," +
            "t.idt_association,t.intro,t.name,t.avatar_url,t.user_id from t_association t " +
            "where t.name like '%${filterStr}%' or t.user_name like '%${filterStr}%' order by num DESC,create_time DESC")
    List<Association> findAllAssociation(@Param("filterStr") String filterStr);

    /**
     * 根据社群ID查询社群信息
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_association where idt_association=#{id}")
    Association findAssociationById(Integer id);

    /**
     * 添加社群
     * @param association
     */
    @Insert("insert into t_association(name,intro,avatar_url,create_time,user_name,user_id,type,update_time) values(#{name},#{intro}," +
            "#{avatarUrl},#{createTime},#{userName},#{userId},#{type},#{updateTime})")
    void saveAssociation(Association association);

    //获取最后一个ID
    @DataSource("slave")
    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();

    /**
     * 根据社群ID更新社群信息
     * @param association
     */
    @Update("update t_association set intro=#{intro},avatar_url=#{avatarUrl},update_time=#{updateTime} where idt_association=#{idtAssociation}")
    void updateAssociationInfoById(Association association);
}
