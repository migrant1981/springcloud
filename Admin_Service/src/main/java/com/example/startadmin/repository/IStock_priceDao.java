package com.example.startadmin.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.startadmin.entity.Stock_price;

public interface IStock_priceDao extends CrudRepository<Stock_price, Long> {

	@Query("from Stock_price")
    List<Stock_price> findAll();
	
	@Query("select u from Stock_price u where u.id=:id")
    public Stock_price findPriceByid(@Param("id") Long id);

}
