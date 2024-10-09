package com.magalu.desafio.normalizacao.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.magalu.desafio.normalizacao.record.DesnormalizedRecord;
import com.magalu.desafio.normalizacao.record.NormalizedRecord;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DesnormalizedModelTest {

    @Test
    void toNormalizedTest() throws JsonProcessingException {
        List<String> lines = new ArrayList<>();
        // lines.add("|-userId--|--------------userName----------------------|-orderId-|-prodId--|---value---|-date--|");
        lines.add("0000000002                                     Medeiros00000123450000000111      256.2420201201");
        lines.add("0000000001                                      Zarelli00000001230000000111      512.2420211201");
        lines.add("0000000001                                      Zarelli00000001230000000122      512.2420211201");
        lines.add("0000000002                                     Medeiros00000123450000000122      256.2420201201");
        DesnormalizedRecord desnormalizedRecord = new DesnormalizedRecord(lines);

        DesnormalizedModel desnormalizedModel = new DesnormalizedModel(desnormalizedRecord);

        NormalizedRecord normalizedRecord = desnormalizedModel.toNormalized();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(normalizedRecord);

        assertEquals(desnormalizedModel.toNormalized().toString(), json);
    }

}
