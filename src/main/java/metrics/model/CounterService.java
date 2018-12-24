package metrics.model;

import com.codahale.metrics.MetricRegistry;

import java.util.Queue;

/**
 * @author xingzhi.lv
 * @description
 * @since 2018/12/19 19:59
 */
public class CounterService {

    public void init(){
        MetricRegistry.name(Queue.class,"requests","size");
        MetricRegistry.name(Queue.class,"responses","size");
    }

    public static void main(String[] args) {

    }

}
