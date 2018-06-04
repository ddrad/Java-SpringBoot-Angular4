package com.azaroff.projects.craftsman.aspect;

import com.azaroff.projects.craftsman.token.service.TokenData;
import com.azaroff.projects.craftsman.token.service.TokenService;
import com.azaroff.projects.craftsman.webapp.model.permission.Request;
import exception.TokenIsExpiredException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Configuration
public class CheckTokenDataAspect {
    @Autowired
    private TokenService tokenService;

    @Before("@annotation(com.azaroff.projects.craftsman.annotation.AppRequaredToken) && @annotation(requestMapping)")
    public void logExecutionTime(JoinPoint joinPoint, RequestMapping requestMapping) throws Throwable {
        Request request = new Request();
        RequestMethod requestMethodType = requestMapping.method()[0];
//        if (requestMethodType == RequestMethod.GET) {
        if (joinPoint.getArgs()[0] instanceof HttpHeaders) {
            HttpHeaders headers = (HttpHeaders) joinPoint.getArgs()[0];
            String tokenAlias = headers.get("tokenalias").get(0);
            String tokenData = headers.get("tokendata").get(0);
            request.setTokenAlias(tokenAlias);
            request.setData(tokenData);
        } else {
            request.setTokenAlias((String) joinPoint.getArgs()[0]);
            request.setData((String) joinPoint.getArgs()[1]);
        }
//        }
        TokenData tokenData = tokenService.findByAlias(request.getTokenAlias());
        if (tokenData == null) {
            throw new TokenIsExpiredException("TokenDta was not found by Alias");
        }
        boolean isExpiredTokenData = tokenService.isExpiredTokenData(tokenData);
        if (isExpiredTokenData) {
            throw new TokenIsExpiredException("TokenAlias is expared");
        }
    }
}
