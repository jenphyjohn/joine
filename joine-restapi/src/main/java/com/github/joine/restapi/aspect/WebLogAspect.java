package com.github.joine.restapi.aspect;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: JenphyJohn
 * @Date: 2019/4/17 9:34 PM
 */
@Aspect
@Component
public class WebLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void delMapping() {
    }

    @Pointcut("execution(public * com.github.joine.restapi.controller..*.*(..))")
    private void webLog() {
    }

    @Before("(requestMapping() || getMapping() || postMapping() || putMapping() || delMapping()) && webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attributes.getRequest();

        logger.info("============Request Start==============");
        logger.info("URL: " + req.getRequestURL().toString());
        logger.info("HTTP_METHOD: " + req.getMethod());
        logger.info("IP: " + req.getRemoteAddr());
        logger.info("CLASS_METHOD: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS: " + JSON.toJSONString(convert(joinPoint.getArgs())));
        logger.info("============Request End==============");
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("============Response Start==============");
        if (null != ret) {
            logger.info("RESPONSE: " + JSON.toJSONString(ret));
        }
        logger.info("============Response End==============");
    }

    private List<Object> convert(Object[] args) {
        Stream<?> stream = ArrayUtils.isEmpty(args) ? Stream.empty() : Arrays.asList(args).stream();
        List<Object> logArgs = stream
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                .collect(Collectors.toList());
        return logArgs;
    }
}
