package com.magalu.desafio.normalizacao.controller;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class NormalizacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NormalizacaoController normalizacaoController;

    @Test
    void contextLoads() throws Exception {
        assertThat(normalizacaoController).isNotNull();
    }

    @Test
    void normalizeShouldReturnDefault() throws Exception {
        this.mockMvc.perform(get("/normalizacao/normalize?file=../../arquivos/data_2.txt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("users")));
    }

}
