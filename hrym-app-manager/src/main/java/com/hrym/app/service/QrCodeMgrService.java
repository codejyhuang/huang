package com.hrym.app.service;

import com.hrym.rpc.app.dao.model.task.Qrcode;
import com.hrym.rpc.app.util.Result;


/**
 * Created by hrym13 on 2017/10/30.
 */
public interface QrCodeMgrService {

    /**
     * 查找所有的渠道URL及内容
     * @param page
     * @param rows
     * @return
     */
    Result findAllQrCode(Integer page, Integer rows);

    /**
     * 根据ID查找内容
     * @param qrcode
     * @return
     */
    Qrcode findByCannelId(Qrcode qrcode);

    /**
     * 删除的渠道URL及内容
     * @param qrcode
     * @return
     */
    Result deteleurl(Qrcode qrcode);

    /**
     * 渠道URL生成
     * @param qrcode
     * @return
     */
    String inserAllqrurl(Qrcode qrcode);

    /**
     * 渠道添加
     * @param qrcode
     * @return
     */
    Result inserqrurl(Qrcode qrcode);


}
