package com.spring.property;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @author EinIce
 * @description: Environment读取springboot配置文件
 * @date 2022/9/28
 */
@Service
public class EnvironmentDemo implements InitializingBean {
    @Autowired
    private Environment env;

    @Override
    public void afterPropertiesSet(){
        System.out.println(env.getProperty("spring.profiles.active"));
        System.out.println(env.getProperty("test.value"));
    }
}
