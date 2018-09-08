package com.lysenkova.soapshop.dao;

import com.lysenkova.soapshop.entity.User;

import java.util.List;

public interface UserDao  {
    List<User> getAll();

    User getByLogin(String login) ;
}
