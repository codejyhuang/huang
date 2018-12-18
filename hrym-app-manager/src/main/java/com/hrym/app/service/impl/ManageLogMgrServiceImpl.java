package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.app.dao.ManageLogMgrMapper;
import com.hrym.app.service.ManageLogMgrService;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.dao.model.VO.ManageLogVO;
import com.hrym.rpc.app.util.Result;
import com.sun.prism.impl.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.hrym.common.util.DateUtil.TIME_PATTON_DEFAULT;

/**
 * Created by hrym13 on 2018/2/2.
 */
@Service
public class ManageLogMgrServiceImpl implements ManageLogMgrService {

    @Autowired
    private ManageLogMgrMapper manageLogMgrMapper;

    /**
     * 日志打印
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllManageLog(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(page, rows,"create_time");
        List<ManageLogVO> manageLogVOS = manageLogMgrMapper.findAllManageLog();

        PageInfo pageInfo = new PageInfo(manageLogVOS);
        List<Map<String,Object>> list = new ArrayList<>();

        for (ManageLogVO m: manageLogVOS){
            Map<String, Object> map = Maps.newHashMap();
            map.put("itemContentId",m.getItemContentId());
            map.put("versionName",m.getVersionName());
            map.put("itemId",m.getItemId());
            map.put("itemName",m.getItemName());
            map.put("logId",m.getLogId());
            map.put("userId",m.getUserId());
            map.put("username",m.getUsername());
            map.put("catalogueId",m.getCatalogueId());
            map.put("catalogueName",m.getCatalogueName());
            map.put("musicId",m.getMusicId());
            map.put("musicName",m.getMusicName());
            map.put("createTime", DateUtil.timestampToDates(m.getCreateTime(),TIME_PATTON_DEFAULT));
            list.add(map);
        }

        long total = pageInfo.getTotal();
        return new Result(code, message, total,list);
    }
}
