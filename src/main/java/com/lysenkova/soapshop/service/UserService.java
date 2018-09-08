package com.lysenkova.soapshop.service;

import com.lysenkova.soapshop.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getByLogin(String login) ;
}
