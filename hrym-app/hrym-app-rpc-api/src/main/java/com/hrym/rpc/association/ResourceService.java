package com.hrym.rpc.association;

import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.AssociationParam;

/**
 * 资料业务接口
 * Created by mj on 2017/9/14.
 */
public interface ResourceService {

    /**
     * 文章详情
     * @param param
     * @return
     */
    BaseResult getArticleDetails(AssociationParam param);

    /**
     * 资料上传
     * @param param
     * @return
     */
    BaseResult resourceUpload(AssociationParam param);

    /**
     * 资料列表
     * @param param
     * @return
     */
    BaseResult resourceList(AssociationParam param);
}
