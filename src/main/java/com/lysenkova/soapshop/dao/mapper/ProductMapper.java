package com.lysenkova.soapshop.dao.mapper;

import com.lysenkova.soapshop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ProductMapper {
    public Product mapRow(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getDouble("price"));
        product.setImgRef(resultSet.getString("image_ref"));
        product.setLocalDateTime(resultSet.getObject("date", LocalDateTime.class));
        return product;
    }
}
