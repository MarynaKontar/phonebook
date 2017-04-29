package com.getjavajob.kovarnevm.phonebook.ui.servlets.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccessInterceptor extends AbstractAuthInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isExcluded(request.getRequestURI())) {
            return true;
        }
        HttpSession session = request.getSession();
        Object loginAttribute = session.getAttribute("login");
        if (loginAttribute != null) {
            return true;
        } else {
            request.setAttribute("css", "danger");
            request.setAttribute("msg", "You must be logged in, to gain access to editing");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return false;
        }
    }

}
