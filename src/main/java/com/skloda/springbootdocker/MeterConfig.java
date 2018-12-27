package com.skloda.springbootdocker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.ImmutableTag;
import io.micrometer.core.instrument.Tag;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: jiangkun
 * @Description:
 * @Date: Created in 2018-12-26 17:58
 */
@Configuration
public class MeterConfig {

    @Bean
    MeterRegistryCustomizer<PrometheusMeterRegistry> metricsCommonTags() {
        //注入应用的通用标签
        return registry -> registry.config().commonTags("region", "my-data-center");
    }

    // 自定义一个counter类型指标
    @Bean
    Counter myCounter(PrometheusMeterRegistry meterRegistry) {
        Set<Tag> tags = new HashSet<>();
        ImmutableTag tag1 = new ImmutableTag("app", "springboot-docker");
        ImmutableTag tag2 = new ImmutableTag("foo", "bar");
        tags.add(tag1);
        tags.add(tag2);
        return meterRegistry.counter("hello_call_count", tags);
    }
}
