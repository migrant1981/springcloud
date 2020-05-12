package com.example.startsstock.Service;

import com.example.startsstock.entity.Stock_price;

import java.util.List;

public interface IStock_priceService {

    void save(Stock_price c);

    Stock_price findById(Long id);

    List<Stock_price> findAll();

}
