package com.lysenkova.soapshop.web.servlet;

import com.google.common.hash.Hashing;
import com.lysenkova.soapshop.entity.User;
import com.lysenkova.soapshop.exception.UserNotFoundException;
import com.lysenkova.soapshop.service.UserService;
import com.lysenkova.soapshop.web.templater.ThymeleafConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

@Controller
public class LoginServlet extends HttpServlet {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private UserService userService;

    @Autowired
    public LoginServlet(UserService userService) {
        this.userService = userService;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Get request in LoginServlet");
        response.setStatus(HttpServletResponse.SC_OK);
        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale());
        ThymeleafConfig.process("login.html", context, response);
    }

    @Override
//    @RequestMapping(value = "/login")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Post request in LoginServlet");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        LOGGER.info("Checking if User with login {} authorized", login);
        Optional<String> token = getToken(login, password);
        if (token.isPresent()) {
            Cookie cookie = new Cookie("user-token", token.get());
            cookie.setMaxAge(1800);
            response.addCookie(cookie);
            response.sendRedirect("/products");

        } else {
            response.sendRedirect("/login");
        }
    }

    private Optional<String> getToken(String login, String password) {
         LOGGER.info("User with login {} trying to log in", login);
        try {
            User user = userService.getByLogin(login);
            String hashedPassword = hashPassword(password, user.getSalt());
            if (login.equals(user.getLogin()) && hashedPassword.equals(user.getPassword())) {
                LOGGER.info("User {} has logged in.", user.getLogin());
                String token = UUID.randomUUID().toString();
                return Optional.of(token);
            }
        } catch (UserNotFoundException ex) {
            LOGGER.info("Incorrect username: {} or password.", login);
        }
        return Optional.empty();
    }

    private static String hashPassword(String password, String salt) {
        checkNotNull(password);
        return Hashing.sha256().hashString(salt + password, StandardCharsets.UTF_8).toString();
    }
}
