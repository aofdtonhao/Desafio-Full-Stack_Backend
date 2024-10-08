package com.magalu.desafio.normalizacao.controller;

import com.magalu.desafio.normalizacao.model.OrderModel;
import com.magalu.desafio.normalizacao.model.UserModel;
import com.magalu.desafio.normalizacao.record.*;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class NormalizacaoController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/normalize")
    public NormalizedRecord normalize(@RequestParam(value = "name", defaultValue = "World") String name) {
        ProductRecord productRecord1 = new ProductRecord(111, 512.24f);
        ProductRecord productRecord2 = new ProductRecord(122, 512.24f);

        List<ProductRecord> products1 = new ArrayList<>();
        products1.add(productRecord1);
        products1.add(productRecord2);

        ProductRecord productRecord3 = new ProductRecord(111, 256.24f);
        ProductRecord productRecord4 = new ProductRecord(222, 256.24f);

        List<ProductRecord> products2 = new ArrayList<>();
        products2.add(productRecord3);
        products2.add(productRecord4);

        List<String> lines = new ArrayList<>();
        lines.add("|-userId--|--------------userName----------------------|-orderId-|-prodId--|---value---|-date--|");
        lines.add("0000000002                                     Medeiros00000123450000000111      256.2420201201");
        lines.add("0000000001                                      Zarelli00000001230000000111      512.2420211201");
        lines.add("0000000001                                      Zarelli00000001230000000122      512.2420211201");
        lines.add("0000000002                                     Medeiros00000123450000000122      256.2420201201");
        DesnormalizedRecord desnormalizedRecord = new DesnormalizedRecord(lines);

        OrderModel orderModel1 = new OrderModel(123, 1024.48f, "2021-12-01", products1);
        List<OrderModel> orders1 = new ArrayList<>();
        orders1.add(orderModel1);

        OrderModel orderModel2 = new OrderModel(12345, 512.48f, "2020-12-01", products2);
        List<OrderModel> orders2 = new ArrayList<>();
        orders2.add(orderModel2);

        UserModel userModel1 = new UserModel(1, "Zarelli", orders1);
        UserModel userModel2 = new UserModel(2, "Medeiros", orders2);

        List<UserModel> userModels = new ArrayList<>();
        userModels.add(userModel1);
        userModels.add(userModel2);

        NormalizedRecord normalizedRecord = new NormalizedRecord(userModels);
        return normalizedRecord;
    }

}
