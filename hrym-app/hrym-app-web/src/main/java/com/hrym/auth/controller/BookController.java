package com.hrym.auth.controller;

import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.BookParam;
import com.hrym.rpc.app.dao.model.VO.bookVO.TaskItemVO;
import com.hrym.rpc.auth.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by mj on 2018/4/16.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class BookController extends BaseController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book")
    @ResponseBody
    public BaseResult getMyHome(@RequestBody BookParam param) {

        String cmd = param.getCmd();
        if ("bookList".equals(cmd)){
            return findBookstoreList(param);
        }
        if ("bookDetails".equals(cmd)){
            return findBookDetails(param);
        }
        if ("bookshelfAdd".equals(cmd)){
            return bookCaseAdd(param);
        }
        if ("bookshelfRemove".equals(cmd)){
            return bookCaseRemove(param);
        }
        if ("bookReport".equals(cmd)){
            return bookReport(param);
        }
        if ("bookcaseList".equals(cmd)){
            return findBookCaseList(param);
        }
        if ("dudenQuery".equals(cmd)) {
            return dudenQuery(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }


    /**
     * 书城列表
     * @param param
     * @return
     */
    public BaseResult findBookstoreList(BookParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String,Object> map = bookService.findBookstoreList(param);

        return new BaseResult(code,message,map);
    }

    /**
     * 经书详情
     * @param param
     * @return
     */
    public BaseResult findBookDetails(BookParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        TaskItemVO vo = bookService.findBookDetails(param);

        return new BaseResult(code,message,vo);
    }

    /**
     * 加入书架
     * @param param
     */
    public BaseResult bookCaseAdd(BookParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        bookService.bookCaseAdd(param);

        return new BaseResult(code,message,null);
    }

    /**
     * 书架列表
     * @param param
     * @return
     */
    public BaseResult findBookCaseList(BookParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String,Object> map = bookService.findBookCaseList(param);

        return new BaseResult(code,message,map);
    }

    /**
     * 移除书架
     * @param param
     */
    public BaseResult bookCaseRemove(BookParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        bookService.bookCaseRemove(param);

        return new BaseResult(code,message,null);
    }

    /**
     * 经书上报
     * @param param
     */
    public BaseResult bookReport(BookParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        bookService.bookReport(param);

        return new BaseResult(code,message,null);
    }


    /**
     * 经书大辞典查询
     * @param param
     * @return
     */
    public BaseResult dudenQuery(BookParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String, Object> map = bookService.dudenQuery(param);

        return new BaseResult(code,message,map);

    }
}
