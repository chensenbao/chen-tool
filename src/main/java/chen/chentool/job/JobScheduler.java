package chen.chentool.job;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 定时器调度
 * @author: sen
 */
@Component
public class JobScheduler {

    @Autowired
    private Scheduler scheduler;



}
