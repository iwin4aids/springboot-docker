package com.skloda.springbootdocker.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: jiangkun
 * @Date: Created in 2020/4/13 14:43
 * @Description: 测试prometheus自定义指标集成
 */
@RestController
public class PrometheusController {

    @Qualifier("myCounter")
    private final Counter counter;

    @Qualifier("myGauge")
    private final Gauge gauge;

    @Qualifier("temperatureGauge")
    private final AtomicInteger temperatureGauge;

    @Qualifier("myDistributionSummary")
    private final DistributionSummary myDistributionSummary;

    public PrometheusController(Counter counter, Gauge gauge, AtomicInteger temperatureGauge, DistributionSummary myDistributionSummary) {
        this.counter = counter;
        this.gauge = gauge;
        this.temperatureGauge = temperatureGauge;
        this.myDistributionSummary = myDistributionSummary;
    }

    @GetMapping("/hello")
    public String hello() {
        // 自定义指标计数器
        this.counter.increment();
        return "OK";
    }

    @GetMapping("/temperature")
    public String temperature() {
        // 自定义指标计数器
        if (System.currentTimeMillis() % 2 == 0) {
            temperatureGauge.addAndGet(10);
            System.out.println("ADD + " + gauge.measure() + " : " + temperatureGauge);
        } else {
            int val = temperatureGauge.addAndGet(-5);
            if (val < 0) {
                temperatureGauge.set(1);
            }
            System.out.println("DECR - " + gauge.measure() + " : " + temperatureGauge);
        }
        return "OK";
    }

    @GetMapping("/summary")
    public String summary() {
        // 自定义指标计数器
        myDistributionSummary.record(Math.random());
        return "OK";
    }
}
