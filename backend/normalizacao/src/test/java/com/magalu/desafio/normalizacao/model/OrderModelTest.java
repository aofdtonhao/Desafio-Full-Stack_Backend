package com.magalu.desafio.normalizacao.model;

import com.magalu.desafio.normalizacao.record.ProductRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderModelTest {

    @Test
    void orderModelTest() {
        ProductRecord productRecord3 = new ProductRecord(111, 256.24f);
        ProductRecord productRecord4 = new ProductRecord(222, 256.24f);
        List<ProductRecord> products = new ArrayList<>();
        products.add(productRecord3);
        products.add(productRecord4);

        LocalDate orderDate = LocalDate.parse("20201201", DateTimeFormatter.ofPattern("yyyyMMdd"));

        OrderModel orderModel = new OrderModel(12345, 512.48f, orderDate, products);

        assertEquals(12345, orderModel.id);
        assertEquals(512.48f, orderModel.total);
        assertEquals(2, orderModel.products.size());
        assertEquals("2020-12-01", orderModel.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

}
