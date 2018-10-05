package com.oppo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by JieChen on 2018/7/30.
 */
public class MyListenner implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //System.out.println("server 啟動...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //System.out.println("server 關閉...");
    }
}
