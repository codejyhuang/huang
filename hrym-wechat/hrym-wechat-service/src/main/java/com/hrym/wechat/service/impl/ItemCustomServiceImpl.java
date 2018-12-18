package com.hrym.wechat.service.impl;

import com.hrym.common.util.DateUtil;
import com.hrym.common.util.StringUtil;
import com.hrym.wechat.entity.ItemCustom;
import com.hrym.wechat.entity.ItemUserUnit;
import com.hrym.wechat.mapper.ItemCustomMapper;
import com.hrym.wechat.mapper.ItemUserUnitMapper;
import com.hrym.wechat.service.IItemCustomService;
import com.hrym.wechat.smallProgram.ItemParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class ItemCustomServiceImpl implements IItemCustomService {

    @Autowired
    private ItemCustomMapper itemCustomMapper;
    @Autowired
    private ItemUserUnitMapper userUnitMapper;

    /**
     * 自建功课
     * @param param
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> buildCustom(ItemParam param) {
        Map<String,Object> map = new HashMap<>();
        int count = itemCustomMapper.queryCountByCustomName(param.getLessonName(),param.getUserId());
        if (count==0){
            ItemCustom custom = new ItemCustom();
            custom.setCreateTime(DateUtil.currentSecond());
            custom.setIntro(param.getIntro());
            custom.setUnit(param.getUnit());
            custom.setUserId(param.getUserId());
            custom.setCustomName(param.getLessonName());
            custom.setInfo(param.getInfo());
            itemCustomMapper.buildCustom(custom);

            if (!"遍".equals(param.getUnit().trim())){
                ItemUserUnit itemUserUnit = new ItemUserUnit();
                itemUserUnit.setType(1);
                itemUserUnit.setUuid(param.getUserId());
                itemUserUnit.setItemId(custom.getItemId());
                itemUserUnit.setUnit(param.getUnit());
                userUnitMapper.insert(itemUserUnit);
            }
            map.put("state",1);
        }else {
            map.put("state",0);
            map.put("errorMsg","已经有您建立的同名功课,请修改功课名称");
        }
        return map;
    }
}
