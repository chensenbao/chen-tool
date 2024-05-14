package chen.chentool.config;

import chen.chentool.job.JobFactory;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Properties;

/**
 * @description: 定时器配置类
 * @author: sen
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private JobFactory jobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
//        Quartz参数配置
        Properties props = new Properties();
//        线程池配置
        props.setProperty("org.quartz.threadPool.threadCount", "20");
        props.setProperty("org.quartz.threadPool.threadPriority", "5");
        props.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");

        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setJobFactory(jobFactory);
        bean.setQuartzProperties(props);
        bean.setSchedulerName("Scheduler");
        bean.setStartupDelay(10);

        return bean;
    }

    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }
}
