package com.codegenerator.core.component;

import org.quartz.*;

import java.text.ParseException;
import java.util.Date;

public abstract class Refresher implements Job {

    public abstract void refresh(String key);

    public void doRefresh(String key,String cron){
        SeedConfig seedConfig = SeedContext.configReader.readOneAndLock(key);
        try {
            CronExpression expression=new CronExpression(cron);
            Date nextTime = expression.getTimeAfter(seedConfig.getLastUpdateTime());
            if(nextTime.before(new Date())){
                refresh(key);
            }
        }catch (ParseException e){
            throw new RuntimeException("cron表达式解析异常");
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String key = jobDataMap.getString("key");
        String cron = jobDataMap.getString("cron");
        doRefresh(key,cron);
    }


}
