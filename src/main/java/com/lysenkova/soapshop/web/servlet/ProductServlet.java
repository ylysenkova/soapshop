package com.lysenkova.soapshop.web.servlet;

import com.lysenkova.soapshop.entity.Product;
import com.lysenkova.soapshop.service.ProductService;
import com.lysenkova.soapshop.web.templater.ThymeleafConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductServlet extends HttpServlet {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private ProductService productService;

    @Autowired
    public ProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Get request in ProductServlet");

        response.setStatus(HttpServletResponse.SC_OK);
        List<Product> products = productService.getAll();
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("products", products);
        ThymeleafConfig.process("products.html", context, response);

    }

}
