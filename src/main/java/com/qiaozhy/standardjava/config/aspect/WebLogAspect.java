package com.qiaozhy.standardjava.config.aspect;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/29 10:37 AM
 */
@Aspect
@Slf4j
@Component
public class WebLogAspect {
    /**
     * 切点的定义
     */
    @Pointcut("@annotation(com.qiaozhy.standardjava.config.aspect.WebLog)")
    public void webLog() {
    }

    /**
     * 切点之前的切入
     *
     * @param joinpoint
     * @throws ClassNotFoundException
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinpoint) throws ClassNotFoundException {

    }

    /**
     * 在切点之后织入
     *
     * @throws Throwable
     */
    @After("webLog()")
    public void doAfter() {

    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 获取请求reque
        final ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        final HttpServletRequest request = requestAttributes.getRequest();
        // 获取具体注解的描述
        final String des = getAspectAnnotationDes(proceedingJoinPoint);
        //请求url
        final String url = request.getRequestURL().toString();
        // 请求方式
        final String requestMethod = request.getMethod();
        // 请求ip
        final String ip = getClientIp(request);
        // 请求参数
        final Object[] args = proceedingJoinPoint.getArgs();
        //调用路径
        final String methodPath = proceedingJoinPoint.getSignature().getDeclaringTypeName() + proceedingJoinPoint.getSignature().getName();
        log.info("请求Url为[{}]请求方式为[{}]请求IP为[{}]调用路径为[{}]描述信息为[{}]请求参数为[{}]", url, requestMethod, ip, methodPath, des,JSON.toJSONString(args));

        Stopwatch execTimeWatch = Stopwatch.createStarted();

        Object result = proceedingJoinPoint.proceed();
        final long time = execTimeWatch.stop().elapsed(TimeUnit.SECONDS);

        log.info("请求Url为[{}]请求方式为[{}]用时为[{}]结果为[{}]", url, requestMethod, time, JSON.toJSONString(result));
        return result;

    }

    /**
     * 获得切面上方法具体注解的描述
     *
     * @return
     */
    public String getAspectAnnotationDes(JoinPoint joinPoint) throws ClassNotFoundException {
        final String targetName = joinPoint.getTarget().getClass().getName();
        final String MethodName = joinPoint.getSignature().getName();
        final Object[] arguments = joinPoint.getArgs();
        final Class<?> targetClass = Class.forName(targetName);
        final Method[] methods = targetClass.getMethods();
        StringBuilder desc = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(MethodName)) {
                final Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == arguments.length) {
                    desc.append(method.getAnnotation(WebLog.class).desc());
                    break;
                }

            }
        }
        return desc.toString();

    }

    /**
     * https://stackoverflow.com/questions/14180416/cant-get-ip-from-httpservletrequest-if-using-localhost-url
     * 获取真实ip
     * @param request
     * @return
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}
