package com.magalu.desafio.normalizacao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NormalizacaoApplicationTests {

	@Autowired
	NormalizacaoApplication normalizacaoApplication;

	@Test
	void contextLoads() {
		assertThat(normalizacaoApplication).isNotNull();
	}

}
