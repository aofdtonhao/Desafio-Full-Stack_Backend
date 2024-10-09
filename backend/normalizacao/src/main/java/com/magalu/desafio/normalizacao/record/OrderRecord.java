package com.magalu.desafio.normalizacao.record;

import java.time.LocalDate;
import java.util.List;

public record OrderRecord(
        long userId,
        String userName,
        long orderId,
        long productId,
        float productValue,
        LocalDate orderDate
) {

}
