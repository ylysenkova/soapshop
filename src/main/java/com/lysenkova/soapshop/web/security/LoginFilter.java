package com.lysenkova.soapshop.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class LoginFilter implements Filter {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        if ("/login".equals(servletRequest.getRequestURI()) || servletRequest.getRequestURI().startsWith("/assets/")) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            LOGGER.info("Login filter is starting...");
            boolean isAuth = false;
            Cookie[] cookies = servletRequest.getCookies();

            LOGGER.debug("Cookies {}", Arrays.toString(cookies));
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("user-token".equals(cookie.getName())) {
                        isAuth = true;
                        break;
                    }
                }
            }
            if (!isAuth) {
                servletResponse.sendRedirect("/login");
            } else {
                chain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
