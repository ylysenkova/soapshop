package com.lysenkova.soapshop.web.servlet;

import com.google.common.io.ByteStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class AssetsServlet extends HttpServlet {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
//    @RequestMapping(value = "/assets/*")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        InputStream imageURL = getFilePath(request);
        try (BufferedInputStream inputStream = new BufferedInputStream(imageURL);
             BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {

            LOGGER.info("Copying bytes from BufferedInput stream to BufferedOutputStream is started.");
            ByteStreams.copy(inputStream, outputStream);

        } catch (IOException e) {
            LOGGER.error("Error during read or write stream.", e);
            throw new RuntimeException(e);
        }
    }

    private InputStream getFilePath(HttpServletRequest request) {
        String path = request.getRequestURI().substring(1);
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream pathUrl = classLoader.getResourceAsStream(path);
        if (pathUrl == null) {
            throw new RuntimeException("Can not find path.");
        }
        return pathUrl;
    }
}
