package com.magalu.desafio.normalizacao;

import com.magalu.desafio.normalizacao.properties.StorageProperties;
import com.magalu.desafio.normalizacao.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class NormalizacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NormalizacaoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}
