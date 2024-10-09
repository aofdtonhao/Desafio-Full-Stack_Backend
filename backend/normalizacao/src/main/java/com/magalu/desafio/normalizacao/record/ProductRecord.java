package com.magalu.desafio.normalizacao.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductRecord(
        @JsonProperty("product_id")
        long id,
        @JsonProperty("value")
        @JsonFormat(shape=JsonFormat.Shape.STRING)
        float value
) {

}
