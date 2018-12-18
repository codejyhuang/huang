package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.hrym.app.dao.SplashScreemMgrMapper;
import com.hrym.app.service.SplashScreemMgrService;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.dao.model.VO.SplashScreemVO;
import com.hrym.rpc.app.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hrym.common.util.DateUtil.TIME_PATTON_DEFAULT;

/**
 * Created by hrym13 on 2018/4/13.
 */
@Service
public class SplashScreemMgrServiceImpl implements SplashScreemMgrService {

    @Autowired
    private SplashScreemMgrMapper splashScreemMgrMapper;

    /**
     * 闪屏页列表
     * @return
     */
    @Override
    public Result findALLSScreem(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        PageHelper.startPage(page,rows);
        List<SplashScreemVO> screemVOS =splashScreemMgrMapper.findALLSScreem();
        for (SplashScreemVO screemVO :screemVOS){

            if (null != screemVO.getSt()) {

                String start = DateUtil.timestampToDates(screemVO.getSt(), TIME_PATTON_DEFAULT);
                screemVO.setSts(start);
            }
            if (null != screemVO.getEt()) {

                String end = DateUtil.timestampToDates(screemVO.getEt(), TIME_PATTON_DEFAULT);
                screemVO.setEts(end);
            }

        }
        PageInfo pageInfo = new PageInfo(screemVOS);
        long total = pageInfo.getTotal();

        return new Result(code,message,total,screemVOS);
    }

    /**
     * 闪屏页录入
     * @param screemVO
     * @return
     */
    @Override
    public Result insertSScreem(SplashScreemVO screemVO) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (StringUtils.isNotBlank(screemVO.getEts())){
            int end = DateUtil.getDateToLinuxTime(screemVO.getEts(),DateUtil.TIME_PATTON_DEFAULT);
            screemVO.setEt(end);
        }
        if (StringUtils.isNotBlank(screemVO.getSts())){
            int start = DateUtil.getDateToLinuxTime(screemVO.getSts(),DateUtil.TIME_PATTON_DEFAULT);
            screemVO.setSt(start);
        }
        splashScreemMgrMapper.insertSScreem(screemVO);
        return new Result(code,message,null);
    }

    /**
     * 闪屏页更新
     * @param screemVO
     * @return
     */
    @Override
    public Result updateSScreem(SplashScreemVO screemVO) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (StringUtils.isNotBlank(screemVO.getEts())){
            int end = DateUtil.getDateToLinuxTime(screemVO.getEts(),DateUtil.TIME_PATTON_DEFAULT);
            screemVO.setEt(end);
        }
        if (StringUtils.isNotBlank(screemVO.getSts())){
            int start = DateUtil.getDateToLinuxTime(screemVO.getSts(),DateUtil.TIME_PATTON_DEFAULT);
            screemVO.setSt(start);
        }
        splashScreemMgrMapper.updateSScreem(screemVO);
        return new Result(code,message,null);
    }

    /**
     * 闪屏页删除
     * @param id
     * @return
     */
    @Override
    public Result deleteSScreem(Integer id) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        splashScreemMgrMapper.deleteSScreem(id);
        return new Result(code,message,null);
    }

    /**
     * 闪屏页编辑
     * @param id
     * @return
     */
    @Override
    public SplashScreemVO findSScreemById(Integer id) {

        SplashScreemVO screemVO = splashScreemMgrMapper.findSScreemById(id);
        if (null != screemVO.getSt()) {

            String start = DateUtil.timestampToDates(screemVO.getSt(), TIME_PATTON_DEFAULT);
            screemVO.setSts(start);
        }
        if (null != screemVO.getEt()) {

            String end = DateUtil.timestampToDates(screemVO.getEt(), TIME_PATTON_DEFAULT);
            screemVO.setEts(end);
        }

        return screemVO;
    }
}
