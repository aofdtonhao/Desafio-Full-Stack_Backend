package com.magalu.desafio.normalizacao.record;

import java.time.LocalDate;

public record OrderRecord(
        long userId,
        String userName,
        long orderId,
        long productId,
        float productValue,
        LocalDate orderDate
) {

}
