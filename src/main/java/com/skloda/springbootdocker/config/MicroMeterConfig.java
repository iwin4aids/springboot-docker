package com.skloda.springbootdocker.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: jiangkun
 * @Description:
 * @Date: Created in 2018-12-26 17:58
 */
@Configuration
public class MicroMeterConfig {

    @Bean
    MeterRegistryCustomizer<PrometheusMeterRegistry> metricsCommonTags() {
        // 注入应用的通用标签，会存在于所有的metric tag上
        return registry -> registry.config().commonTags("region", "cn-east-01");
    }

    // 自定义一个counter类型指标，只增不减
    @Bean
    Counter myCounter(MeterRegistry registry) {
        return Counter.builder(MetricConstants.REQUEST_HELLO_COUNTER_NAME)
                .tags("app", "springboot-docker", "foo", "bar", "score", "100")
                .description("path '/hello' request counts")
                .register(registry);
    }

    AtomicInteger temperatureGauge = new AtomicInteger(37);

    // 针对gauge其实只需要在内存中强引用一个AtomicInteger对象即可
    @Bean("temperatureGauge")
    AtomicInteger getTemperature() {
        return temperatureGauge;
    }

    // 自定义一个gauge类型指标，仪表类型可增减
    @Bean
    Gauge myGauge(MeterRegistry registry) {
        return Gauge.builder(MetricConstants.CPU_USAGE_GAUGE_NAME, getTemperature(), AtomicInteger::get)
                .description("temperature gauge")
                .strongReference(true)
                .register(registry);
    }

    // 自定义一个DistributionSummary类型指标(分布摘要)
    @Bean
    DistributionSummary myDistributionSummary(MeterRegistry registry) {
        DistributionSummary summary = DistributionSummary.builder("my distribution summary")
                .description("simple distribution summary")
                .minimumExpectedValue(1L)
                .maximumExpectedValue(10L)
                .publishPercentiles(0.5, 0.75, 0.9)
                .register(registry);
        summary.record(1);
        summary.record(1.3);
        summary.record(2.4);
        summary.record(3.5);
        summary.record(4.1);
        summary.record(9.1);
        summary.record(8.1);
        System.out.println(summary.takeSnapshot());
        return summary;
    }

}
