package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.FlockRecordBook;
import com.hrym.wechat.entity.ParseRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/11/9.
 */
@Repository
public interface ParseRecordMapper {


    /**
     * 查询点赞用户
     * @param relationId
     * @return
     */
    @DataSource("slave")
    List<ParseRecord> queryLikeMember(Integer relationId);


    /**
     *
     * @param relationId
     * @param from
     * @return
     */
    @DataSource("slave")
    ParseRecord queryByRelationIdAndRelationTypeAndFrom(@Param("relationId") Integer relationId,
                                                        @Param("from") Integer from);

    /**
     * 保存点赞数据
     * @param ps
     */
    void insert(ParseRecord ps);

    /**
     * 删除点赞数据
     * @param relationId
     * @param from
     */
    @DataSource("slave")
    int removeByRelationIdAndUuid(@Param("relationId") Integer relationId,
                                   @Param("from") Integer from);
}
