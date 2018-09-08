package com.lysenkova.soapshop.service;

import com.lysenkova.soapshop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    void add(Product product);
}
