package com.getjavajob.kovarnevm.phonebook.ui.servlets.listeners;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.Properties;

public class AppStartListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("login.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        servletContextEvent.getServletContext().setAttribute("login.properties", properties);
//        String excluded = ".css;.js;.ico;/doLogin;/doLogout;/login";
//        List<String> excludedList = Arrays.asList(excluded.split(";"));
//        servletContextEvent.getServletContext().setAttribute("excludedList", excludedList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
