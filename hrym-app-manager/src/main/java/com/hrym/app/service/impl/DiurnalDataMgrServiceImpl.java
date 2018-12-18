package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.hrym.app.dao.DiurnalDataMapper;
import com.hrym.app.service.DiurnalDataMgrService;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.dao.model.system.DiurnalData;
import com.hrym.rpc.app.dao.model.system.UserData;
import com.hrym.rpc.app.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hrym.common.util.DateUtil.TIME_PATTON_DEFAULT;

/**
 * Created by hrym13 on 2018/3/9.
 */
@Service
public class DiurnalDataMgrServiceImpl implements DiurnalDataMgrService {

    @Autowired
    private DiurnalDataMapper diurnalDataMapper;


    /**
     * 根据表名查找对应的内容
     * @return
     */
    @Override
    public List findUserData( String tableName) {


        List<DiurnalData> dataList = diurnalDataMapper.findAllUserData(tableName);

        System.out.println("dataList的值"+dataList.size());
//        List<DiurnalData> diurnalData = new ArrayList<>();
            for (DiurnalData data2 :dataList){
                data2.setId(data2.getId());
                data2.setUuid(data2.getUuid());
                data2.setNickName(data2.getNickName());
                data2.setRealName(data2.getRealName());
                if (null != data2.getRecentTime()){
                    String recentTimeStr = DateUtil.timestampToDates(data2.getRecentTime(),TIME_PATTON_DEFAULT);
                    data2.setRecentTimes(recentTimeStr);
                }
                if (null != data2.getCreatedTime()){
                    String createTimeStr = DateUtil.timestampToDates(data2.getCreatedTime(),TIME_PATTON_DEFAULT);
                    data2.setCreatedTimes(createTimeStr);
                }
            }

        return dataList;
    }

    /**
     * 今日登录人数
     * @param tableName
     * @return
     */
    @Override
    public DiurnalData findUserCount(String tableName) {

        DiurnalData number = diurnalDataMapper.findUserCount(tableName);
        number.setRecount(number.getRecount());

        return number;
    }

    /**
     * 今日注册数
     * @param tableName
     * @return
     */
    @Override
    public DiurnalData findAllCountCreatedTime(String tableName) {

        DiurnalData data = diurnalDataMapper.findUserCountByCreateTime(tableName);
        if (data!=null){
        data.setCount(data.getCount());
        }
        return data;
    }


    /**
     * 查找所有的表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findUserDataTable(Integer page,Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(page,rows,"table_name desc");
        List<DiurnalData> data = diurnalDataMapper.findAllUserDataTable();

        PageInfo pageInfo = new PageInfo(data);
        List<Map<String,Object>> list = new ArrayList<>();
        for (DiurnalData data1 :data){
            DiurnalData Tcount = diurnalDataMapper.findUserCountByCreateTime(data1.getTableName());
            if (Tcount!=null) {
                Map<String, Object> map = new HashMap<>();
                map.put("tableName", data1.getTableName());
                DiurnalData count = diurnalDataMapper.findUserCount(data1.getTableName());
                map.put("count", count.getRecount());

                if (Tcount != null) {
                    map.put("Tcount", Tcount.getCount());
                } else {
                    map.put("Tcount", 0);
                }
                list.add(map);
            }
        }

        long ptotal = pageInfo.getTotal();
        long total =ptotal-1;
        return new Result(code,message,total,list);
    }
}
