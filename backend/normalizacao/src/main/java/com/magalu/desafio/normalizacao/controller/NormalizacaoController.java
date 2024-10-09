package com.magalu.desafio.normalizacao.controller;

import com.magalu.desafio.normalizacao.model.DesnormalizedModel;
import com.magalu.desafio.normalizacao.record.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/normalizacao")
public class NormalizacaoController {

    @GetMapping("/normalize")
    public NormalizedRecord normalize(@RequestParam(value = "file", defaultValue = "../arquivos/data_1.txt") String file) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(file), StandardCharsets.UTF_8);

        DesnormalizedRecord desnormalizedRecord = new DesnormalizedRecord(lines);

        DesnormalizedModel desnormalizedModel = new DesnormalizedModel(desnormalizedRecord);

        return desnormalizedModel.toNormalized();
    }

}
