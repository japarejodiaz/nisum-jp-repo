package com.springboot.project.nisumprojectrestapp.utils;


import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class InterceptorUtils {

    public static final String NISUM_AUTH_HEADER = "Authorization";

    public String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(NISUM_AUTH_HEADER);
    }

}

