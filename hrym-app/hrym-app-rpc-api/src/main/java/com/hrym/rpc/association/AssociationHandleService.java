package com.hrym.rpc.association;

import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.AssociationParam;

/**
 * Created by mj on 2017/9/10.
 */
public interface AssociationHandleService {

    /**
     * 社群申请
     * @return
     */
    BaseResult associationApply(AssociationParam param);

    /**
     * 社群审批
     * @param param
     * @return
     */
    BaseResult associationApprove(AssociationParam param);

    /**
     * 社群退出
     * @param param
     * @return
     */
    BaseResult associationExit(AssociationParam param);

    /**
     * 社群成员列表
     * @param param
     * @return
     */
    BaseResult findMemberList(AssociationParam param);

    /**
     * 我加入的社群首页
     * @param param
     * @return
     */
    BaseResult myAssociationHomePage(AssociationParam param);

    /**
     * 社群后台信息
     * @param param
     * @return
     */
    BaseResult getBackstageInfo(AssociationParam param);

    /**
     * 社群信息修改
     * @param param
     * @return
     */
    BaseResult associationEdit(AssociationParam param);

    /**
     * 社群主题发表
     * @param param
     * @return
     */
    BaseResult publishTopic(AssociationParam param);

    /**
     * 消息列表
     * @param param
     * @return
     */
    BaseResult messageList(AssociationParam param);

    /**
     * 消息频道
     * @param param
     * @return
     */
    BaseResult isReadMessage(AssociationParam param);
}
