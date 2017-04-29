package com.getjavajob.kovarnevm.phonebook.ui.servlets.filters;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractAuthInterceptor implements HandlerInterceptor {

    private static final String EXCLUDED = ".css;.js;.ico;/doLogin;/doLogout;/login";

    private List<String> excludedList = Arrays.asList(EXCLUDED.split(";"));

    protected boolean isExcluded(String uri) {
        for (String s : excludedList) {
            if (uri.contains(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
