package com.hrym.rpc.auth.api;

import com.hrym.rpc.app.common.constant.BookParam;
import com.hrym.rpc.app.dao.model.VO.bookVO.BookcaseVO;
import com.hrym.rpc.app.dao.model.VO.bookVO.TaskItemVO;

import java.util.Map;

/**
 * Created by mj on 2018/4/16.
 */
public interface BookService {

    /**
     * 书城列表
     * @param param
     * @return
     */
    Map<String,Object> findBookstoreList(BookParam param);

    /**
     * 经书详情
     * @param param
     * @return
     */
    TaskItemVO findBookDetails(BookParam param);

    /**
     * 书架列表
     * @param param
     * @return
     */
    Map<String,Object> findBookCaseList(BookParam param);

    /**
     * 加入书架
     * @param param
     */
    void bookCaseAdd(BookParam param);

    /**
     * 移除书架
     * @param param
     */
    void bookCaseRemove(BookParam param);

    /**
     * 经书上报
     * @param param
     */
    void bookReport(BookParam param);

    /**
     * 经书大辞典查询
     * @param param
     * @return
     */
    Map<String,Object> dudenQuery(BookParam param);
}
