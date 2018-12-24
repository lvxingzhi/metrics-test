package metrics.model;

import com.codahale.metrics.Counter;
import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;

import java.io.File;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xingzhi.lv
 * @description
 * @since 2018/12/19 20:21
 */
public class MetersMain {

    private static final MetricRegistry metricRegistry = new MetricRegistry();

    public static void main(String[] args) throws InterruptedException {
//        testMeter1();
//        testGauges();
//        testCounter();
//        testHistogram();
        testTimer();
//        testHealthCheck();
//        ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
//        reporter.start(1, TimeUnit.SECONDS);
        CsvReporter csvReporter = CsvReporter.forRegistry(metricRegistry).formatFor(Locale.US).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build(new File("D://"));
        csvReporter.start(1,TimeUnit.SECONDS);
        Thread.sleep(2000 * 1000);
    }

    public static void testMeter1() throws InterruptedException {
        Meter meter = metricRegistry.meter("111");
        meter.mark();
    }

    public static void testGauges() throws InterruptedException {
        int num = 100;
        metricRegistry.register("222", new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return num;
            }
        });
    }

    public static void testCounter() throws InterruptedException {
        Counter counter = new Counter();
        metricRegistry.register("333", counter);
        counter.dec();
        counter.dec();
        counter.inc();
    }

    public static void testHistogram() {
        Histogram histogram = metricRegistry.histogram("444");
        histogram.update(1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    histogram.update(i);
                    try {
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        thread.start();
    }

    public static void testTimer(){
        Timer timer = metricRegistry.timer("555");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Timer.Context context = timer.time();
                    try {
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                    }
                    context.stop();
                }
            }
        });
        thread.start();
    }

    public static void testHealthCheck(){

        class TestHealthCheck extends HealthCheck{
           private int tag = 0;

            @Override
            protected Result check() throws Exception {
                if(tag==0){
                    return HealthCheck.Result.healthy();
                }else{
                    return HealthCheck.Result.healthy();
                }
            }
        }

        HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();
        healthCheckRegistry.register("666",new TestHealthCheck());

        Map<String, HealthCheck.Result> result = healthCheckRegistry.runHealthChecks();
        for (Map.Entry<String, HealthCheck.Result> stringResultEntry : result.entrySet()) {
            System.out.println("key:"+stringResultEntry.getKey()+"    value:"+stringResultEntry.getValue());
        }
    }

}
