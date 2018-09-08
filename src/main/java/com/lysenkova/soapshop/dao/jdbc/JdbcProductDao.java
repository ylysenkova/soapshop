package com.lysenkova.soapshop.dao.jdbc;

import com.lysenkova.soapshop.dao.ProductDao;
import com.lysenkova.soapshop.dao.mapper.ProductMapper;
import com.lysenkova.soapshop.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcProductDao implements ProductDao {
    private final static String GET_ALL_SQL = "select id, name, price, image_ref, date from products";
    private final static String ADD_PRODUCT_SQL = "insert into products (name, price, image_ref) values (?, ?, ?)";

    private final ProductMapper PRODUCT_MAPPER = new ProductMapper();
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    private DataSource dataSource;

    @Autowired
    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getAll() {
        LOGGER.info("Getting all products");
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_SQL)) {
            while (resultSet.next()) {
                Product product = PRODUCT_MAPPER.mapRow(resultSet);
                products.add(product);
            }
            LOGGER.trace("Got products {}", products);
        } catch (SQLException e) {
            LOGGER.error("SQL error during getting all products.");
            throw new RuntimeException("SQL error during getting all products. ", e);
        }
        return products;
    }

    public void add(Product product) {
        LOGGER.info("Adding product {}", product);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getImgRef());
            preparedStatement.executeUpdate();
            LOGGER.info("Added product {}", product);
        } catch (SQLException e) {
            LOGGER.error("SQL error during add product.", e);
            throw new RuntimeException("SQL error during add product.", e);
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
