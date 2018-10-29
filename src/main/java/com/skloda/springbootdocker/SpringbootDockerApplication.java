package com.skloda.springbootdocker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

@SpringBootApplication
@RestController
public class SpringbootDockerApplication {

    private static Logger logger = LoggerFactory.getLogger(SpringbootDockerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDockerApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "Spring Boot Hello World!!!!! Current Docker Container IP is : " + getIpByEthNum();
    }

    private String getIpByEthNum() {
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if ("eth0".equalsIgnoreCase(netInterface.getName())) {
                    Enumeration addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = (InetAddress) addresses.nextElement();
                        if (ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            logger.error(e.getMessage(), e);
        }
        return "获取服务器IP错误";
    }
}