package com.hrym.rpc.auth.api;

import com.hrym.rpc.app.common.constant.MeditationParam;

import java.util.Map;

/**
 *
 * 禅修
 * Created by mj on 2018/5/28.
 */
public interface MeditationService {

    Map<String,Object> getHomePage(MeditationParam param);

    void editMeditation(MeditationParam param);

    void meditationReport(MeditationParam param);
}
