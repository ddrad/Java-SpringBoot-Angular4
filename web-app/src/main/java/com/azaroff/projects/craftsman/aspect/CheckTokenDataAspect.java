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

@Aspect
@Configuration
public class CheckTokenDataAspect {
@Autowired
private TokenService tokenService;

    @Before("@annotation(com.azaroff.projects.craftsman.annotation.AppRequaredToken)")
    public void logExecutionTime(JoinPoint joinPoint) throws Throwable {
        Request request = (Request) joinPoint.getArgs()[0];
        TokenData tokenData = tokenService.findByAlias(request.getTokenAlias());
        if(tokenData == null ) {
            throw new TokenIsExpiredException("TokenDta was not found by Alias");
        }
        boolean isExpiredTokenData = tokenService.isExpiredTokenData(tokenData);
        if (isExpiredTokenData) {
            throw new TokenIsExpiredException("TokenAlias is expared");
        }
    }
}
