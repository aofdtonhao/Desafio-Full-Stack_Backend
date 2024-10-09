package com.magalu.desafio.normalizacao.model;

import com.magalu.desafio.normalizacao.record.ProductRecord;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {

    @Test
    void userModelTest() {
        UserModel userModel = new UserModel(1, "Zarelli", orders1);

        ProductRecord productRecord1 = new ProductRecord(111, 512.24f);
        ProductRecord productRecord2 = new ProductRecord(122, 512.24f);

        List<ProductRecord> products1 = new ArrayList<>();
        products1.add(productRecord1);
        products1.add(productRecord2);

        OrderModel orderModel1 = new OrderModel(123, 1024.48f, "2021-12-01", products1);
        List<OrderModel> orders1 = new ArrayList<>();
        orders1.add(orderModel1);

        assertEquals(userModel, userModel);
    }

}
