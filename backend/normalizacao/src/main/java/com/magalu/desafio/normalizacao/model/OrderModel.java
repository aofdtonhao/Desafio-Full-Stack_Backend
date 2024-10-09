package com.magalu.desafio.normalizacao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.magalu.desafio.normalizacao.record.ProductRecord;

import java.time.LocalDate;
import java.util.List;

public class OrderModel {

    public OrderModel(long id, float total, LocalDate date, List<ProductRecord> products) {
        setId(id);
        setTotal(total);
        setDate(date);
        setProducts(products);
    }

    @JsonProperty("order_id")
    long id;

    @JsonProperty("total")
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    float total;

    @JsonProperty("date")
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate date;

    @JsonProperty("products")
    List<ProductRecord> products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ProductRecord> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRecord> products) {
        this.products = products;
    }

}
