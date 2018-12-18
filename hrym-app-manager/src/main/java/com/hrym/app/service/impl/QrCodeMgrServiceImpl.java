package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.dao.model.task.Qrcode;
import com.hrym.rpc.app.util.Result;
import com.hrym.app.service.QrCodeMgrService;
import com.hrym.app.dao.QrCodeMgrMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hrym.common.util.DateUtil.TIME_PATTON_DEFAULT;

/**
 * Created by hrym13 on 2017/10/30.
 */
@Service
public class QrCodeMgrServiceImpl implements QrCodeMgrService {

    @Autowired
    private QrCodeMgrMapper qrCodeMgrMapper;


    /**
     * 查找渠道URL及内容
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllQrCode(Integer page, Integer rows) {

        String code =BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(page,rows);
        List<Qrcode> qrCode =qrCodeMgrMapper.findAllQrCode();

        for (Qrcode q :qrCode){
            if (null !=q.getCreateTime()){
                String createTime = DateUtil.timestampToDates(q.getCreateTime(),TIME_PATTON_DEFAULT);
                q.setCreateTimes(createTime);
                    }
                }
        PageInfo pageInfo = new PageInfo(qrCode);
        long total = pageInfo.getTotal();

        return new Result(code,message,total,qrCode);
    }

    /**
     * 根据ID查找内容
     * @param qrcode
     * @return
     */
    @Override
    public Qrcode findByCannelId(Qrcode qrcode) {

//        判断是否访问次数要+1
        if (null!=qrcode.getSelect() && 0==qrcode.getSelect()){

            Qrcode qr = qrCodeMgrMapper.findByCannelId(qrcode.getChannelId());
            return qr;

        }else {

            Qrcode qr = qrCodeMgrMapper.findByCannelId(qrcode.getChannelId());
            if (null ==qr.getAccessTimes()){
                qr.setAccessTimes(0);
            }
            qr.setAccessTimes(qr.getAccessTimes()+1);
            qrCodeMgrMapper.updateAccessTimes(qr);

            return qr;
        }

    }

    /**
     * 删除渠道URL及内容
     * @param qrcode
     * @return
     */
    @Override
    public Result deteleurl(Qrcode qrcode ) {

        String code = BaseConstants.GWSCODE0000;
        String message=BaseConstants.GWSMSG0000;

        qrCodeMgrMapper.deteleurl(qrcode.getChannelId());

        return new Result(code,message,null);
    }

    /**
     * URL保存
     * @param qrcode
     * @return
     */
    @Override
    public String inserAllqrurl(Qrcode qrcode) {

        qrCodeMgrMapper.inserAllqrurl(qrcode);

        return null;
    }

    /**
     * 渠道生成
     * @param qrcode
     * @return
     */
    @Override
    public Result inserqrurl(Qrcode qrcode) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        qrcode.setCreateTime(DateUtil.currentSecond());
        if (StringUtils.isBlank(qrcode.getUrl())){
            return new Result("1","URL不能为空",null);
        } else {
            qrCodeMgrMapper.inserAllqrurl(qrcode);

            return new Result(code,message,null);
        }

    }
}
