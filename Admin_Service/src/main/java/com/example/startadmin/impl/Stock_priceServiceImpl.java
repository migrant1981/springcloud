package com.example.startadmin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.startadmin.Service.IStock_priceService;
import com.example.startadmin.entity.Stock_price;
import com.example.startadmin.repository.IStock_priceDao;

import java.util.List;

@Service
@Transactional
public class Stock_priceServiceImpl implements IStock_priceService {

    @Autowired
    private IStock_priceDao Stock_priceDao;

    @Override
    public void save(Stock_price c) {
    	this.Stock_priceDao.save(c);
    }

	@Override
	public Stock_price findById(Long id) {
		Stock_price sprice = this.Stock_priceDao.findPriceByid(id);
		return sprice;
	}

	@Override
	public List<Stock_price> findAll() {
		List<Stock_price> sprice = this.Stock_priceDao.findAll();
		return sprice;
	}
}
