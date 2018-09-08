package com.lysenkova.soapshop.web.servlet;

import com.lysenkova.soapshop.entity.Product;
import com.lysenkova.soapshop.service.ProductService;
import com.lysenkova.soapshop.web.templater.ThymeleafConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@Controller
public class AddProductServlet extends HttpServlet {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private ProductService productService;

    @Autowired
    public AddProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
//    @RequestMapping(value = "/product/add")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Get request in AddProductServlet");
        response.setStatus(HttpServletResponse.SC_OK);
        WebContext context = new WebContext(request, response, request.getServletContext());
        ThymeleafConfig.process("add.html", context, response);
    }

    @Override
//    @RequestMapping(value = "/product/add")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Post request in AddProductServlet");
        addProduct(request);
        response.sendRedirect("/products");
    }

    private void addProduct(HttpServletRequest request) {
        Product product = new Product();

        product.setName(request.getParameter("name"));
        String price = request.getParameter("price").replace(",", ".");
        product.setPrice(Double.parseDouble(price));
        product.setImgRef(request.getParameter("imgRef"));
        Timestamp timestamp = Timestamp.valueOf(request.getParameter("localDateTime"));
        product.setLocalDateTime(timestamp.toLocalDateTime());
        productService.add(product);
    }
}
