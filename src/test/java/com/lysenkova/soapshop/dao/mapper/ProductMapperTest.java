package com.lysenkova.soapshop.dao.mapper;

import com.lysenkova.soapshop.entity.Product;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductMapperTest {

    @Test
    public void mapRow() throws SQLException {
        ProductMapper productMapper = new ProductMapper();

        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getLong("id")).thenReturn(1L);
        when(mockResultSet.getString("name")).thenReturn("Flower");
        when(mockResultSet.getDouble("price")).thenReturn(40.00);
        when(mockResultSet.getString("image_ref")).thenReturn("D:\\Photos\\SoapImageStorage\\elf.jpg");
        LocalDateTime localDateTime = LocalDateTime.of(2018, Month.APRIL, 1, 11, 11, 11);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        when(mockResultSet.getTimestamp("date")).thenReturn(timestamp);

        Product actual = productMapper.mapRow(mockResultSet);
        assertNotNull(actual);
        assertEquals(1L, actual.getId());
        assertEquals("Flower", actual.getName());
        assertEquals(40.00, actual.getPrice(), 0);
        assertEquals("D:\\Photos\\SoapImageStorage\\elf.jpg", actual.getImgRef());
        LocalDateTime expectedDate = LocalDateTime.of(2018, Month.APRIL, 1, 11, 11, 11);
        assertEquals(expectedDate, actual.getLocalDateTime());
    }
}