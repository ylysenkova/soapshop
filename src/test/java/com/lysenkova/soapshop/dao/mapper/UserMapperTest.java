package com.lysenkova.soapshop.dao.mapper;

import com.lysenkova.soapshop.entity.User;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserMapperTest {

    @Test
    public void mapRow() throws SQLException {
        UserMapper userMapper = new UserMapper();

        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getLong("id")).thenReturn(1L);
        when(mockResultSet.getString("login")).thenReturn("login");
        when(mockResultSet.getString("password")).thenReturn("password");
        when(mockResultSet.getString("salt")).thenReturn("salt");
        User actual = userMapper.mapRow(mockResultSet);

        assertNotNull(userMapper);
        assertEquals(1L, actual.getId());
        assertEquals("login", actual.getLogin());
        assertEquals("password", actual.getPassword());
        assertEquals("salt", actual.getSalt());
    }
}