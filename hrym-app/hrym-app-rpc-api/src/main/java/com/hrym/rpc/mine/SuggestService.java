package com.hrym.rpc.mine;

import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.MyParam;
import com.hrym.rpc.app.util.Result;

/**
 * 推荐业务类
 * Created by mj on 2017/9/4.
 */
public interface SuggestService {

    /**
     * 推荐首页
     * @return
     */
    BaseResult suggestHomePage(MyParam param);

    /**
     * 基于类型查看全部
     * @param param
     * @return
     */
    BaseResult findAllByType(MyParam param);

    /**
     * 基于类型搜索全部
     * @param param
     * @return
     */
    BaseResult searchAllByType(MyParam param);

    /**
     *
     * 大词典详情展示
     * @param param
     * @return
     */
    BaseResult dudenIntro(MyParam param);

}
