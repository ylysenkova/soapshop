package com.lysenkova.soapshop.service.impl;

import com.lysenkova.soapshop.dao.ProductDao;
import com.lysenkova.soapshop.entity.Product;
import com.lysenkova.soapshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public void add(Product product) {
        productDao.add(product);
    }
}