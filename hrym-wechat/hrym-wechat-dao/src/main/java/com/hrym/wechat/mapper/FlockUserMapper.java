package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.FlockItem;
import com.hrym.wechat.entity.FlockUser;
import com.hrym.wechat.entity.WechatUserAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlockUserMapper {

    /**
     * 插入 群 -- 用户 关系
     * @param flockUser
     */
    void insert(FlockUser flockUser);

    /**
     * 查询共修群用户
     * @param id
     * @return
     */
    @DataSource("slave")
    List<FlockUser> queryByFlockId(@Param("id") Integer id, @Param("type") Integer type,@Param("keywords")String keywords);


    /**
     * 批量删除 群成员
     * @param flockId
     * @param userIds
     */
    void batchRemoveFlockUser(@Param("flockId")Integer flockId,@Param("userIds") List<Integer> userIds);


    /**
     * 查询共修群用户
     * @param id
     * @return
     */
    @DataSource("slave")
    List<FlockUser> selectAllByFlockId(Integer id);

    /**
     * 查询群所有用户数量
     * @param flockId
     * @return
     */
    @DataSource("slave")
    Integer queryCountByFlockId(Integer flockId);

    /**
     *
     * @param flockId
     * @param uuid
     * @return
     */
    @DataSource("slave")
    FlockItem queryByFlockIdAndUuid(@Param("flockId") Integer flockId,@Param("uuid") Integer uuid);

    /**
     * 查询是否在群里
     * @param id
     * @param uUid
     * @return
     */
    @DataSource("slave")
    FlockUser queryCountByFlockIdAndUuid(@Param("flockId") Integer id, @Param("uuid") Integer uUid);

    /**
     * 更新群排序 order
     * @param flockId
     * @param order
     * @param uuid
     */
    void updateFlockUserOrder(@Param("flockId") Integer flockId, @Param("order") Integer order, @Param("uuid") Integer uuid);

    /**
     * 查询群所有用户 id 集合
     * @param id
     * @return
     */
    @DataSource("slave")
    List<Integer> queryUuidListByFlockId(Integer id);
}
