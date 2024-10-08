package com.magalu.desafio.normalizacao.model;

import com.magalu.desafio.normalizacao.record.DesnormalizedRecord;
import com.magalu.desafio.normalizacao.record.NormalizedRecord;
import com.magalu.desafio.normalizacao.record.OrderRecord;
import com.magalu.desafio.normalizacao.record.ProductRecord;

import java.util.ArrayList;
import java.util.List;

public class DesnormalizedModel {

    private DesnormalizedRecord desnormalizedRecord;

    public DesnormalizedModel(DesnormalizedRecord desnormalizedRecord) {
        this.desnormalizedRecord = desnormalizedRecord;
    }

    public NormalizedRecord toNormalized() {
        List<OrderRecord> orderRecords = new ArrayList<>();

        String firstLine = desnormalizedRecord.lines().removeFirst();
        desnormalizedRecord.lines().forEach(line -> {
            String pattern = "^(.{10})(.{45})(.{10})(.{10})(.{12})(.{8}).*";
            String[] values = line.replaceAll(pattern, "$1-$2-$3-$4-$5-$6").split("-");

            String idUser = values[0].trim();
            String nameUser = values[1].trim();
            String idOrder = values[2].trim();
            String idProduct = values[3].trim();
            String valueProduct = values[4].trim();
            String dateOrder = values[5].trim();

            orderRecords.add(new OrderRecord(idUser, nameUser, idOrder, idProduct, valueProduct, dateOrder));
        });

        return toNormalizedRecord(orderRecords);
    }

    private NormalizedRecord toNormalizedRecord(List<OrderRecord> orderRecords) {
        List<UserModel> users = new ArrayList<>();
        List<OrderModel> orders = new ArrayList<>();
        List<ProductRecord> productRecords = new ArrayList<>();

        float total = 0f;

        long productId = Long.parseLong(idProduct);
        float productValue = Float.parseFloat(valueProduct);
        ProductRecord productRecord = new ProductRecord(productId, productValue);
        total += productValue;
        productRecords.add(productRecord);

        long orderId = Long.parseLong(idOrder);

        OrderRecord orderRecord = new OrderRecord(orderId, total, date, productRecords);

        OrderModel orderModel = new OrderModel(orderRecord);

        orders.add(orderModel);

        long userId = Long.parseLong(idUser);

        UserModel userModel = new UserModel(userId, userName);
        userModel.setOrders(orders);
        users.add(userModel);

        return new NormalizedRecord(users);
    }

}
