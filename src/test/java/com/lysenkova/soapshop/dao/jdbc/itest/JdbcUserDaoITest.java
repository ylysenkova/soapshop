package com.lysenkova.soapshop.dao.jdbc.itest;

import com.lysenkova.soapshop.dao.jdbc.JdbcUserDao;
import com.lysenkova.soapshop.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/spring/test-context.xml"})
public class JdbcUserDaoITest {

    @Autowired
    private JdbcUserDao userDao;

    @Test
    public void getAll() {
        List<User> users = userDao.getAll();
        for (User user : users) {
            assertNotNull(user);
        }
    }

    @Test
    public void getByLogin() {
        User userTest = new User();
        userTest.setLogin("main");
        User user = userDao.getByLogin("main");
        assertEquals(userTest.getLogin(), user.getLogin());
    }
}