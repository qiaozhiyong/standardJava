package com.qiaozhy.standardjava.config.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/25 6:00 PM
 */
@Configuration
// validate 配置学习
public class BeanConfigs {


    /*@Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(@Autowired LocalValidatorFactoryBean localValidatorFactoryBean) {
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidator(localValidatorFactoryBean);
        return methodValidationPostProcessor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver slr = new CookieLocaleResolver();
        slr.setDefaultLocale(Locale.CHINA);
        slr.setCookieMaxAge(3600);
        return slr;
    }*/

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setProviderClass(org.hibernate.validator.HibernateValidator.class);
        validator.setValidationMessageSource(getMessageSource());
        return validator;
    }

    private ResourceBundleMessageSource getMessageSource() {
        ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
        rbms.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        rbms.setUseCodeAsDefaultMessage(false);
        rbms.setCacheSeconds(60);
        rbms.setBasenames("classpath:org/hibernate/validator/ValidationMessages");
        return rbms;
    }

}
