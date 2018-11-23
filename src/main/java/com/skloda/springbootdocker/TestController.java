package com.skloda.springbootdocker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: jiangkun
 * @Description:
 * @Date: Created in 2018/11/22 17:03
 */
@RestController
public class TestController {

    @GetMapping("/remote")
    public String remoteRequest() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://springboot-docker", String.class);
        return responseEntity.getBody();
    }
}
