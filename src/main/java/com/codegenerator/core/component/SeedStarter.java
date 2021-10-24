package com.codegenerator.core.component;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.InitializingBean;
import java.util.List;

public abstract class SeedStarter implements InitializingBean {

    public abstract void config();

    private void defaultConfig(){
        new ConfigBuilder()
                .setConfigReader(new JDBCConfigReader())
                .addRefresher(SetToOneRefresher.type,SetToOneRefresher::new)
                .setSeedGetter(new JDBCSeedGetter());

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //加载配置
        defaultConfig();
        config();

        List<SeedConfig> configs = SeedContext.configReader.read();
        for(SeedConfig config:configs){
            if(config.isAutoRefresh()){
                //首先执行一次刷新
                Refresher refresher = SeedContext.refresherMap.get(config.getRefreshType()).get();
                refresher.doRefresh(config.getKey(),config.getCron());

                //创建定时刷新任务
                SchedulerFactory schedulerFactory = new StdSchedulerFactory();
                Scheduler scheduler = schedulerFactory.getScheduler();
                JobDetail jobDetail = JobBuilder.newJob(SetToOneRefresher.class)
                        .usingJobData("key",config.getKey())//存放key数据
                        .usingJobData("cron",config.getCron())//存放cron数据
                        .withIdentity(config.getKey()+"_refresher_job", config.getKey()+"_refresher_job").build();


                CronTrigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(config.getKey()+"_refresher_trigger", config.getKey()+"_refresher_trigger")//组标识
                        .withSchedule(
                                CronScheduleBuilder.cronSchedule(config.getCron())//设置cron表达式
                        ).build();


                scheduler.scheduleJob(jobDetail, trigger);

                scheduler.start();
            }
        }
    }
}
