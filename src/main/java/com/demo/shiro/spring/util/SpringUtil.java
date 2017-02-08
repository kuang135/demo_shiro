package com.demo.shiro.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author yaowk
 * @Date 2017/1/4 11:52
 */
@Component
public final class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @Author yaowk
     * @Date 2017/1/4 11:52
     * @Desc 根据bean的名称获取实力
     */
    public  static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

}