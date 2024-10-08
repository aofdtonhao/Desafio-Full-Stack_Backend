package com.magalu.desafio.normalizacao.record;

import com.magalu.desafio.normalizacao.model.UserModel;

import java.util.List;

public record NormalizedRecord(
        List<UserModel> users
) {

}
