package com.getjavajob.kovarnevm.phonebook.ui.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Controller
@SessionAttributes("login")
public class AuthorizationController {

    private static final int COOKIE_LIFETIME = (int) TimeUnit.DAYS.toSeconds(30);

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(@RequestParam("login") String login,
                          @RequestParam("password") String password,
                          @RequestParam(value = "checkbox", required = false) String checkbox,
                          final RedirectAttributes redirectAttributes,
                          Model model,
                          HttpServletResponse resp) {
        if (password == null) {
            password = "";
        }
        Properties properties = (Properties) servletContext.getAttribute("login.properties");
        String realPassword = properties.getProperty(login + ".password");
        if (realPassword != null && realPassword.equals(password)) {
            model.addAttribute("login", login);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Hello " + login + "!");
            if (checkbox != null) {
                Cookie cookie = new Cookie("login" + login, realPassword);
                cookie.setMaxAge(COOKIE_LIFETIME);
                resp.addCookie(cookie);
            }
            return "redirect:/showEmployees";
        } else {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "wrong login or password");
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/doLogout", method = RequestMethod.GET)
    public String doLogout(HttpServletResponse resp,
                           SessionStatus status,
                           @ModelAttribute("login") String login,
                           final RedirectAttributes redirectAttributes) {
        status.setComplete();
        Cookie cookie = new Cookie("login" + login, "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        redirectAttributes.addFlashAttribute("css", "info");
        redirectAttributes.addFlashAttribute("msg", "bye bye " + login + "!");
        return "redirect:/showEmployees";
    }

}

