package com.getjavajob.kovarnevm.phonebook.ui.servlets.filters;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends AbstractAuthInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isExcluded(request.getRequestURI())) {
            return true;
        }
        HttpSession session = request.getSession();
        Object loginAttribute = session.getAttribute("login");
        if (loginAttribute != null) {
            return true;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().startsWith("login")) {
                    String login = cookie.getName().substring(5);
                    request.getSession().setAttribute("login", login);
                    return true;
                }
            }
        }
        return true;
    }
}
