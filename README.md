"# metrics-test" 
https://blog.csdn.net/scutshuxue/article/details/8350135
https://blog.csdn.net/scutshuxue/article/details/8351810
https://metrics.dropwizard.io/4.0.0/getting-started.html



度量监控类库,统计信息
Metrics: 获取JVM和应用程序指标, 让人知道发生了什么
是一个Java工具包,里面有各种监控的工具,用于监控代码的运行状态,可以通过集成Jetty,Logback,Log4j,Apache HttpClient, Ehcache, JDBI等工具集成,可以自定义.

图形化展示平台
Graphite:
Ganglia:

Metrics组成部分:
输入组成:Gauges,Counters,Histograms,Meters,Timers,Health Checks
输出组成:控制台,CSV文件,Logback等日志,JMX,监控工具(Ganglia,Graphite)

应用场景:
Meters: 统计吞吐量(TPS),打印每秒,每分,五分,十五分的吞吐量
Gauges: 不累计,每次重新计算返回
Counters: 从0开始的计数器, dec减1, inc加1
Histograms: 直方分布图,最大,最小,平均,百分比数据小于,中位值,方差等等
Timers:时间统计, 开始,结束之间的时间,有次数,有平均时间,有百分比时间
Health Checks:

















