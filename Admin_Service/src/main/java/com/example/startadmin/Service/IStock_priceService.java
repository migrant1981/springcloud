package com.example.startadmin.Service;

import java.util.List;

import com.example.startadmin.entity.Stock_price;

public interface IStock_priceService {

    void save(Stock_price c);

    Stock_price findById(Long id);
    
    List<Stock_price> findAll();

}
