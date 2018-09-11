package com.lysenkova.soapshop;

import com.lysenkova.soapshop.service.ProductService;
import com.lysenkova.soapshop.service.UserService;
import com.lysenkova.soapshop.web.security.LoginFilter;
import com.lysenkova.soapshop.web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Starter {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/root-context.xml");
        UserService userService = applicationContext.getBean(UserService.class);
        ProductService productService = applicationContext.getBean(ProductService.class);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new LoginServlet(userService)), "/login");
        context.addServlet(new ServletHolder(new LogoutServlet()), "/logout");
        context.addServlet(new ServletHolder(new ProductServlet(productService)), "/products");
        context.addServlet(new ServletHolder(new AddProductServlet(productService)), "/product/add");
        context.addServlet(new ServletHolder(new AssetsServlet()), "/assets/*");
        context.addFilter(new FilterHolder(new LoginFilter()), "/*", EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST));


        Server server = new Server(5000);
        server.setHandler(context);

        server.start();
    }
}
