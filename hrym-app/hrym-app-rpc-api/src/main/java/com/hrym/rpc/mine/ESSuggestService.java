package com.hrym.rpc.mine;

import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.MyParam;



/**
 * Created by xiaohan on 2017/11/13.
 */
public interface ESSuggestService {

    /**
     * 基于类型搜索全部
     * @param param
     * @return
     */
    BaseResult searchAll(MyParam param);

    /**
     * 删除ES中数据库已删除的数据
     * @return
     */


}
