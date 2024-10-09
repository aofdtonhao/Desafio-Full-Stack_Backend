package com.magalu.desafio.normalizacao.model;

import com.magalu.desafio.normalizacao.record.ProductRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {

    @Test
    void userModelTest() {
        ProductRecord productRecord1 = new ProductRecord(111, 512.24f);
        ProductRecord productRecord2 = new ProductRecord(122, 512.24f);
        List<ProductRecord> products = new ArrayList<>();
        products.add(productRecord1);
        products.add(productRecord2);

        LocalDate orderDate = LocalDate.parse("20211201", DateTimeFormatter.ofPattern("yyyyMMdd"));
        OrderModel orderModel = new OrderModel(123, 1024.48f, orderDate, products);
        List<OrderModel> orders = new ArrayList<>();
        orders.add(orderModel);

        UserModel userModel = new UserModel(1, "Zarelli", orders);

        assertEquals(1, userModel.id);
        assertEquals("Zarelli", userModel.name);
        assertEquals(1, userModel.getOrders().size());
        assertEquals(2, userModel.getOrders().getFirst().products.size());
    }

}
