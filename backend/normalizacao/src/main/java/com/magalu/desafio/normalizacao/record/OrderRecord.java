package com.magalu.desafio.normalizacao.record;

import java.util.List;

public record OrderRecord(
        String idUser,
        String nameUser,
        String idOrder,
        String idProduct,
        String valueProduct,
        String dateOrder
) {

}
