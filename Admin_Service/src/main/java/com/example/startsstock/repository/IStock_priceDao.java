package com.example.startsstock.repository;
import org.springframework.data.repository.CrudRepository;

import com.example.startsstock.Service.IStock_priceService;
import com.example.startsstock.entity.Stock_price;

public interface IStock_priceDao extends CrudRepository<IStock_priceService, Long> {


    void save(Stock_price c);

}
