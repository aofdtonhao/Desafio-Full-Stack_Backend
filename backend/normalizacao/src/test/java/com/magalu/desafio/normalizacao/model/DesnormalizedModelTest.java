package com.magalu.desafio.normalizacao.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.magalu.desafio.normalizacao.record.DesnormalizedRecord;
import com.magalu.desafio.normalizacao.record.NormalizedRecord;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DesnormalizedModelTest {

    // https://github.com/misaku/Desafio-Full-Stack/blob/main/BACK-END.md#sa%C3%ADda-de-dados
    private static final String EXPECTED_JSON =
            "{\n" +
            "  \"users\": [\n" +
            "    {\n" +
            "      \"user_id\": 1,\n" +
            "      \"name\": \"Zarelli\",\n" +
            "      \"orders\": [\n" +
            "        {\n" +
            "          \"order_id\": 123,\n" +
            "          \"total\": \"1024.48\",\n" +
            "          \"date\": \"2021-12-01\",\n" +
            "          \"products\": [\n" +
            "            {\n" +
            "              \"product_id\": 111,\n" +
            "              \"value\": \"512.24\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"product_id\": 122,\n" +
            "              \"value\": \"512.24\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"user_id\": 2,\n" +
            "      \"name\": \"Medeiros\",\n" +
            "      \"orders\": [\n" +
            "        {\n" +
            "          \"order_id\": 12345,\n" +
            "          \"total\": \"512.48\",\n" +
            "          \"date\": \"2020-12-01\",\n" +
            "          \"products\": [\n" +
            "            {\n" +
            "              \"product_id\": 111,\n" +
            "              \"value\": \"256.24\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"product_id\": 122,\n" +
            "              \"value\": \"256.24\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";

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

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String normalizedJson = objectWriter.writeValueAsString(normalizedRecord);

        assertEquals(objectMapper.readTree(EXPECTED_JSON), objectMapper.readTree(normalizedJson));
    }

}
