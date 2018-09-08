package com.lysenkova.soapshop.dao.mapper;

import com.lysenkova.soapshop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper  {
    public User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setSalt(resultSet.getString("salt"));
        return user;
    }
}
