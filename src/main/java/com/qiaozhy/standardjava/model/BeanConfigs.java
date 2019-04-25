package com.qiaozhy.standardjava.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 6:00 PM
 */
@Configuration
public class BeanConfigs {


    @Bean
    public Validator getValidator(){

        return new LocalValidatorFactoryBean();

    }

}
