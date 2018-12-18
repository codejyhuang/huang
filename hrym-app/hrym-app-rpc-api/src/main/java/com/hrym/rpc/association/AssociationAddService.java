package com.hrym.rpc.association;

import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.AssociationParam;

/**
 * 社群添加service
 * Created by mj on 2017/8/17.
 */
public interface AssociationAddService {

    /**
     * 社群主页接口-文章
     * @param param
     * @return
     */
    BaseResult getAssociationHomepage(AssociationParam param);

    /**
     * 社群主页接口-我加入的社群
     * @param param
     * @return
     */
    BaseResult getMyAssociation(AssociationParam param);

    /**
     * 社群主页接口-banner活动
     * @return
     */
    BaseResult getBanner();

    /**
     * 社群列表（可根据社群名称或者创建者名称过滤)
     * @param param
     * @return
     */
    BaseResult getAssociationList(AssociationParam param);

    /**
     * 社群详情
     * @param param
     * @return
     */
    BaseResult associationDetails(AssociationParam param);

    /**
     * 创建新社群
     * @param param
     * @return
     */
    BaseResult createAssociation(AssociationParam param);

}
