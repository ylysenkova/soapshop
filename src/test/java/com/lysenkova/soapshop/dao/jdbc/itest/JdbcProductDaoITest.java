package com.lysenkova.soapshop.dao.jdbc.itest;

import com.lysenkova.soapshop.dao.jdbc.JdbcProductDao;
import com.lysenkova.soapshop.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/spring/test-context.xml"})
public class JdbcProductDaoITest {
    @Autowired
    private JdbcProductDao productDao;

    @Test
    public void getAll() {
        List<Product> products = productDao.getAll();
        for (Product product : products) {
            assertNotNull(product.getName());
            assertNotNull(product.getImgRef());
            assertNotNull(product.getLocalDateTime());
        }
    }

    @Test
    public void add() {
        Product product = new Product();
        product.setName("Flower");
        product.setPrice(40.00);
        product.setImgRef("c:/image.jpg");
        product.setLocalDateTime(LocalDateTime.now());
        int expected = productDao.getAll().size() + 1;
        productDao.add(product);
        int actual = productDao.getAll().size();
        assertEquals(expected, actual);
    }
}