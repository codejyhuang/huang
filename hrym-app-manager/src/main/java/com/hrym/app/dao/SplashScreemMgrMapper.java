package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.SplashScreemVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 闪屏页
 * Created by hrym13 on 2018/4/13.
 */
@Repository
public interface SplashScreemMgrMapper {

    /**
     * 闪屏页列表
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_splash_screem ")
    List<SplashScreemVO> findALLSScreem();

    /**
     * 闪屏页面编辑
     * @param id
     * @return
     */
    @DataSource("/slave")
    @Select(" select * from t_splash_screem where id = #{id} ")
    SplashScreemVO findSScreemById(Integer id);

    /**
     * 闪屏页录入
     * @param screemVO
     */
    @Insert("insert into t_splash_screem (id,pic,url,st,et,time,xpic)values(#{id},#{pic},#{url},#{st},#{et},#{time},#{xpic})")
    void insertSScreem(SplashScreemVO screemVO);

    /**
     * 闪屏更新
     * @param screemVO
     */
    @Update(" update t_splash_screem set pic= #{pic},url=#{url},st=#{st},et= #{et},time=#{time},xpic=#{xpic} where id = #{id}")
    void updateSScreem(SplashScreemVO screemVO);

    /**
     * 删除
     * @param id
     */
    @Delete("delete from t_splash_screem where id = #{id}")
    void deleteSScreem(Integer id);

}
