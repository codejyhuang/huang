package com.hrym.controller;

import com.hrym.entity.User;
import com.hrym.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/movie/{id}")
    public User findUser(@PathVariable("id") Long id){
        return userFeignClient.findByID(id);
    }


    @GetMapping("/test")
    public String test(){
        ServiceInstance choose = loadBalancerClient.choose("microservice-provider-user");
        System.out.println(choose.getPort());
        return "1";
    }
}
