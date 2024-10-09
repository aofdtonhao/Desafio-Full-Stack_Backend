package com.magalu.desafio.normalizacao.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserModel {

    @JsonProperty("user_id")
    long id;

    @JsonProperty("name")
    String name;

    @JsonProperty("orders")
    List<OrderModel> orders;

    public UserModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserModel(long id, String name, List<OrderModel> orders) {
        this.id = id;
        this.name = name;
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }

}
