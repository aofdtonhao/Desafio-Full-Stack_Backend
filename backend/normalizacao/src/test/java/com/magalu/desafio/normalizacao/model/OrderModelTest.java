package com.magalu.desafio.normalizacao.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderModelTest {

    @Test
    void orderModelTest() {
        OrderModel orderModel = new OrderModel(12345, 512.48f, "2020-12-01", products);

        assertEquals(orderModel, orderModel);
        assertEquals(orderModel.getFormattedDate(), orderModel.getFormattedDate());
    }

}
