package com.example.startsstock.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "stock_price")
public class Stock_price {

    private Long id;

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getStock_exhange() {
        return stock_exhange;
    }

    public void setStock_exhange(String stock_exhange) {
        this.stock_exhange = stock_exhange;
    }

    public String getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(String current_price) {
        this.current_price = current_price;
    }

    public String getDatetimestamp() {
        return datetimestamp;
    }

    public void setDatetimestamp(String datetimestamp) {
        this.datetimestamp = datetimestamp;
    }

    private String company_code;

    private String stock_exhange;

    private String current_price;
    private String datetimestamp;
}
