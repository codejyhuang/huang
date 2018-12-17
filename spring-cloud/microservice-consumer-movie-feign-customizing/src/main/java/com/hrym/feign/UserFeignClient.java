package com.hrym.feign;


import com.conf.FeignConfiguration;
import com.hrym.entity.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-provider-user",configuration = FeignConfiguration.class)
public interface UserFeignClient {



//    @GetMapping(value = "simple/{id}")
    @RequestLine("GET /simple/{id}")
    public User findByID(@Param("id") Long id);

}
