package com.lysenkova.soapshop.dao;

import com.lysenkova.soapshop.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAll() ;
    void add(Product product);
}
