package com.skloda.springbootdocker;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author: jiangkun
 * @Description:
 * @Date: Created in 2018/11/21 15:48
 */
@RestController
public class AntdProVueMockController {

    /**
     * 直接读取mock数据文件返回
     *
     * @param path 路径
     * @return json mock数据
     * @throws IOException
     */
    private String getMockData(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return IOUtils.toString(resource.getInputStream(), "UTF-8");
    }

    @PostMapping("/auth/login")
    public String auth_login() throws IOException {
        return getMockData("mock-data/auth_login.json");
    }

    @PostMapping("/auth/logout")
    public String auth_logout() throws IOException {
        return getMockData("mock-data/auth_logout.json");
    }

    @GetMapping("/auth/2step-code")
    public String auth_2stepcode() throws IOException {
        return getMockData("mock-data/auth_2step-code.json");
    }

    @GetMapping("/user/info")
    public String user_info() throws IOException {
        return getMockData("mock-data/user_info.json");
    }

    @GetMapping("/account/sms")
    public String account_sms() throws IOException {
        return getMockData("mock-data/account_sms.json");
    }

    //========================================================================

    @GetMapping("/service")
    public String service() throws IOException {
        return getMockData("mock-data/service.json");
    }

    @GetMapping("/role")
    public String role() throws IOException {
        return getMockData("mock-data/role.json");
    }

    @GetMapping("/permission")
    public String permission() throws IOException {
        return getMockData("mock-data/permission.json");
    }

    @GetMapping("/workplace/radar")
    public String workplace_radar() throws IOException {
        return getMockData("mock-data/workplace_radar.json");
    }

    @GetMapping("/workplace/activity")
    public String workplace_activity() throws IOException {
        return getMockData("mock-data/workplace_activity.json");
    }

    @GetMapping("/workplace/teams")
    public String workplace_teams() throws IOException {
        return getMockData("mock-data/workplace_teams.json");
    }

    @GetMapping("/list/search/projects")
    public String list_search_projects() throws IOException {
        return getMockData("mock-data/list_search_projects.json");
    }
}
