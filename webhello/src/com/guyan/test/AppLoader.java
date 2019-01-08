package com.guyan.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppLoader implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("contextInitialized");
        servletContextEvent.getServletContext().setAttribute("name", "global");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("contextDestroyed");
    }
}
