package com.magalu.desafio.normalizacao.model;

import com.magalu.desafio.normalizacao.record.DesnormalizedRecord;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DesnormalizedModelTest {

    @Test
    void toNormalizedTest() {
        List<String> lines = new ArrayList<>();
        // lines.add("|-userId--|--------------userName----------------------|-orderId-|-prodId--|---value---|-date--|");
        lines.add("0000000002                                     Medeiros00000123450000000111      256.2420201201");
        lines.add("0000000001                                      Zarelli00000001230000000111      512.2420211201");
        lines.add("0000000001                                      Zarelli00000001230000000122      512.2420211201");
        lines.add("0000000002                                     Medeiros00000123450000000122      256.2420201201");
        DesnormalizedRecord desnormalizedRecord = new DesnormalizedRecord(lines);

        DesnormalizedModel desnormalizedModel = new DesnormalizedModel(desnormalizedRecord);

        desnormalizedModel.toNormalized();

        assertEquals(desnormalizedModel.toNormalized(), desnormalizedModel.toNormalized());
    }

}
