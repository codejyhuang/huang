package com.hrym.feign;


import com.hrym.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("microservice-provider-user")
public interface UserFeignClient {


//    @RequestMapping(value = "simple/{id}",method = RequestMethod.GET)
    @GetMapping(value = "simple/{id}")
    public User findByID(@PathVariable("id") Long id);
}
