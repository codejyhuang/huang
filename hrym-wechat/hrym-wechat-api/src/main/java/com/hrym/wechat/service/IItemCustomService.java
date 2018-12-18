package com.hrym.wechat.service;

import com.hrym.wechat.smallProgram.ItemParam;

import java.util.Map;

public interface IItemCustomService {

    Map<String,Object> buildCustom(ItemParam param);
}
