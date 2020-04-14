package com.skloda.springbootdocker.controller;

import com.skloda.springbootdocker.SpringbootDockerApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Author: jiangkun
 * @Date: Created in 2020/4/13 14:50
 * @Description:
 */
@RestController
public class IPController {

    private static final Logger logger = LoggerFactory.getLogger(SpringbootDockerApplication.class);

    @GetMapping("/ip")
    public String getIp() {
        return "server host ip -> " + getIpByEthNum();
    }

    private static String getIpByEthNum() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if ("eth0".equalsIgnoreCase(netInterface.getName())) {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
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

    public static void main(String[] args) {
        System.out.println(getIpByEthNum());
    }
}
