package com.magalu.desafio.normalizacao.model;

import com.magalu.desafio.normalizacao.record.OrderRecord;
import com.magalu.desafio.normalizacao.record.ProductRecord;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderModel {

    public OrderModel(long id, float total, String date, List<ProductRecord> products) {
        setId(id);
        setTotal(total);
        setDate(date);
        setProducts(products);
    }

    long id;

    float total;

    LocalDate date;

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

    public String getFormattedDate() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public List<ProductRecord> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRecord> products) {
        this.products = products;
    }

}
