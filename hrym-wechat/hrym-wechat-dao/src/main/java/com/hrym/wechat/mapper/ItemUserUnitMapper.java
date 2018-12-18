package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.ItemUserUnit;
import com.hrym.wechat.smallProgram.ItemParam;
import org.apache.ibatis.annotations.Param;

public interface ItemUserUnitMapper {

    /**
     * 查询用户功课量词
     * @param uUid
     * @param itemId
     * @param itemContentId
     * @param type
     * @return
     */
    @DataSource("slave")
    String queryUnitByUuidAndItemIdAndItemContentIdAndType(@Param("uuid") Integer uUid,
                                                                 @Param("itemId") Integer itemId,
                                                                 @Param("itemContentId") Integer itemContentId,
                                                                 @Param("type") Integer type);

    /**
     * 添加用户 功课 量词 信息
     * @param itemUserUnit
     */
    void insert(ItemUserUnit itemUserUnit);
    
    @DataSource("slave")
    ItemUserUnit queryItemUserUnit(@Param("uuid") Integer uUid,
                           @Param("itemId") Integer itemId,
                           @Param("itemContentId") Integer itemContentId,
                           @Param("type") Integer type);
    
    void updateItemUserUnitById(ItemUserUnit itemUserUnit);
}
