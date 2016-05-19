package org.revo.Event

import groovy.util.logging.Log
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.revo.Domain.User
import org.revo.Service.SecurityService
import org.revo.Util.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.metrics.CounterService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

import java.util.logging.Level

/**
 * Created by revo on 5/10/16.
 */
@Component
@Aspect
@Log
public class ServiceAspect {
    @Autowired
    CounterService counterService;
@Autowired SecurityService securityService
    @AfterReturning("execution(* org.revo.Service.*.*(..))")

    public void UserService(JoinPoint point) {
        counterService.increment(Util.SplitCut(point.toString()))
    }

    @AfterThrowing(pointcut = "execution(* org.revo.Service.*.*(..))", throwing = "ex")
    public void UserServiceEx(JoinPoint point, Exception ex) {
        String s = point.toString()
        counterService.increment(Util.SplitCut(s) + ".ex(" + ex.getMessage() + ")")
        LinkedHashMap<String, Serializable> message = ["user": securityService.current(), "method": s, "ex": ex.message]
        log.log(Level.WARNING, message.entrySet().collect { it.key + ":" + it.value }.join("\n"))
    }

}
