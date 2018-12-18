package com.hrym.rpc.app.config;

import java.util.LinkedHashSet;

import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import com.hrym.common.util.PropertiesFileUtil;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;


/**
 * Created by xiaohan on 2017/11/9.
 */

@Configuration
@ComponentScan(basePackages = {"com.hrym"},
        excludeFilters = {@ComponentScan.Filter(type= FilterType.ANNOTATION,value = Controller.class)})
@EnableTransactionManagement
public class SpringConfiguration {

    //bean的id为transportClient
    @Bean
    public TransportClientFactory transportClient(){
        //获取资源文件
        PropertiesFileUtil util = PropertiesFileUtil.getInstance("es");
        String clusterName = util.get("es.cluster.name");
        String host = util.get("es.cluster.host");
        int port = util.getInt("es.cluster.port");

    	TransportClientFactory transportClientFactory = new TransportClientFactory();
        transportClientFactory.setClusterName(clusterName);
        transportClientFactory.setHost(host); //外网ip地址
//        transportClientFactory.setHost("192.168.0.35");//内网ip地址，自动发现集群其他ip
        transportClientFactory.setPort(port);
        return transportClientFactory;
    }




}
