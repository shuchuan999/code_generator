package com.codegenerator.core.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext context;


    public static  <T> T getBean(Class<T> tClass){
        return context.getBean(tClass);
    }


    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

    public static <T> T getProperty(String key,Class<T> tClass){
        return context.getEnvironment().getProperty(key,tClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }
}
