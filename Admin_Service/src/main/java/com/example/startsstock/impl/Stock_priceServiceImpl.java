package com.example.startsstock.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.startsstock.Service.IStock_priceService;
import com.example.startsstock.entity.Stock_price;
import com.example.startsstock.repository.IStock_priceDao;

import java.util.List;

@Service
@Transactional
public class Stock_priceServiceImpl implements IStock_priceService {

    @Autowired
    private IStock_priceDao Stock_priceDao;

    @Override
    public void save(Stock_price c) {
        Stock_priceDao.save(c);
    }

	@Override
	public Stock_price findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stock_price> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
