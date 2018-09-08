package com.lysenkova.soapshop.service.impl;

import com.lysenkova.soapshop.dao.UserDao;
import com.lysenkova.soapshop.entity.User;
import com.lysenkova.soapshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }
}
