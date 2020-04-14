package com.skloda.springbootdocker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: jiangkun
 * @Description: 测试k8s集群内的服务发现能力
 * @Date: Created in 2018/11/22 17:03
 */
@RestController
public class TestController {

    @GetMapping("/remote")
    public String remoteRequest() {
        RestTemplate restTemplate = new RestTemplate();
        //服务端域名具有实时的服务发现能力
        //如果使用Nginx的upstream中配置dns则会有解析缓存的问题存在，必须手动reload才能生效
        //如果基于k8s的service则会自动服务发现
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://springboot-docker:8080", String.class);
        return responseEntity.getBody();
    }
}
