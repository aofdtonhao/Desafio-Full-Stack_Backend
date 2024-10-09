package com.magalu.desafio.normalizacao.controller;

import com.magalu.desafio.normalizacao.model.OrderModel;
import com.magalu.desafio.normalizacao.model.UserModel;
import com.magalu.desafio.normalizacao.record.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class NormalizacaoController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/normalize")
    public NormalizedRecord normalize(@RequestParam(value = "name", defaultValue = "World") String name) {
        // TODO: parte mais importante, depois do upload

        return null;
    }

}
