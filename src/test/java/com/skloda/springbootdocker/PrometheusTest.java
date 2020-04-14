package com.skloda.springbootdocker;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @Author: jiangkun
 * @Date: Created in 2020/4/13 16:06
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PrometheusTest {

    @Test
    public void testSummary() {

        DistributionSummary summary = DistributionSummary.builder("summary")
                .tag("summary", "summarySample")
                .description("summary sample test")
                .register(new SimpleMeterRegistry());

        summary.record(2D);
        summary.record(3D);
        summary.record(4D);

        System.out.println(summary.count());
        System.out.println(summary.measure());
        System.out.println(summary.max());
        System.out.println(summary.mean());
        System.out.println(summary.totalAmount());
    }

    @Test
    public void testTimerSample() {
        Timer timer = Timer.builder("timer")
                .tag("timer", "timersample")
                .description("timer test")
                .register(new SimpleMeterRegistry());

        for (int i = 0; i < 2; i++) {
            timer.record(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println(timer.count());
        System.out.println(timer.measure());
        System.out.println(timer.totalTime(TimeUnit.SECONDS));
        System.out.println(timer.mean(TimeUnit.SECONDS));
        System.out.println(timer.max(TimeUnit.SECONDS));
    }


}
