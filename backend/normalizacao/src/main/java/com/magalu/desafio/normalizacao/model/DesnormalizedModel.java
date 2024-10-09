package com.magalu.desafio.normalizacao.model;

import com.magalu.desafio.normalizacao.record.DesnormalizedRecord;
import com.magalu.desafio.normalizacao.record.NormalizedRecord;
import com.magalu.desafio.normalizacao.record.OrderRecord;
import com.magalu.desafio.normalizacao.record.ProductRecord;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class DesnormalizedModel {

    private DesnormalizedRecord desnormalizedRecord;

    public DesnormalizedModel(DesnormalizedRecord desnormalizedRecord) {
        this.desnormalizedRecord = desnormalizedRecord;
    }

    public NormalizedRecord toNormalized() {
        List<OrderRecord> orderRecords = new ArrayList<>();

        desnormalizedRecord.lines().forEach(line -> {
            String pattern = "^(.{10})(.{45})(.{10})(.{10})(.{12})(.{8}).*";
            String[] values = line.replaceAll(pattern, "$1-$2-$3-$4-$5-$6").split("-");

            String idUser = values[0].trim();
            String nameUser = values[1].trim();
            String idOrder = values[2].trim();
            String idProduct = values[3].trim();
            String valueProduct = values[4].trim();
            String dateOrder = values[5].trim();

            long userId = Long.parseLong(idUser);
            long orderId = Long.parseLong(idOrder);
            long productId = Long.parseLong(idProduct);
            float productValue = Float.parseFloat(valueProduct);
            LocalDate orderDate = LocalDate.parse(dateOrder, DateTimeFormatter.ofPattern("yyyyMMdd"));

            orderRecords.add(new OrderRecord(userId, nameUser, orderId, productId, productValue, orderDate));
        });

        return toNormalizedRecord(orderRecords);
    }

    // TODO: Imagino que role de refatorar isso, "map" com "groupingBy" se pá, fora que dá pra quebrar em métodos menores, mas na pressa vai assim mesmo
    private NormalizedRecord toNormalizedRecord(List<OrderRecord> orderRecords) {
        List<UserModel> users = new ArrayList<>();

        HashSet<Long> userIds = orderRecords
                .stream()
                .map(OrderRecord::userId)
                .collect(Collectors.toCollection(HashSet::new));

        userIds.forEach(userId -> {
            List<OrderRecord> orderRecordsByUserId = orderRecords
                    .stream()
                    .filter(orderRecord -> orderRecord.userId() == userId)
                    .toList();

            List<OrderModel> orders = new ArrayList<>();

            HashSet<Long> orderIds = orderRecordsByUserId
                    .stream()
                    .map(OrderRecord::orderId)
                    .collect(Collectors.toCollection(HashSet::new));

            for (Long orderId : orderIds) {
                List<OrderRecord> orderRecordsByOrderId = orderRecordsByUserId
                        .stream()
                        .filter(orderRecord -> orderRecord.orderId() == orderId)
                        .toList();

                List<ProductRecord> products = new ArrayList<>();
                // TODO: se sobrar tempo, transformar em BigDecimal
                float total = 0f;
                for (OrderRecord orderRecordByOrderId : orderRecordsByOrderId) {
                    total += orderRecordByOrderId.productValue();
                    products.add(new ProductRecord(orderRecordByOrderId.productId(), orderRecordByOrderId.productValue()));
                }

                orders.add(new OrderModel(orderId, total, orderRecordsByOrderId.getFirst().orderDate(), products));
            };

            UserModel userModel = new UserModel(userId, orderRecordsByUserId.getFirst().userName(), orders);
            users.add(userModel);
        });

        return new NormalizedRecord(users);
    }

}
