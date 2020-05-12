package com.example.startsstock.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.startsstock.Service.Admin;
import com.example.startsstock.entity.AdminVO;
import com.example.startsstock.repository.AdminVORepository;

@Service
public class AdminImpl implements Admin {
	
	@Autowired
	private AdminVORepository adminVORepository;
	
	
	@Override
	public AdminVO getCompanyDetails(String companyname) {
		AdminVO adminVO = this.adminVORepository.getCompanyDetails(companyname);
		return adminVO;
	}
	
	@Override
	public AdminVO getCompanyStockPrice(String companyname) {
		AdminVO adminVO = this.adminVORepository.getCompanyStockPrice(companyname);
		return adminVO;
	}
	
	@Override
	public AdminVO getMatchingCompany(String companyname) {
		AdminVO adminVO = this.adminVORepository.getMatchingCompany(companyname);
		return adminVO;
	}
	
	@Override
	public AdminVO getCompanyIPODetails(String companyname) {
		AdminVO adminVO = this.adminVORepository.getCompanyIPODetails(companyname);
		return adminVO;
	}
	
	
	@Override
	public void save(AdminVO c) {
		this.adminVORepository.save(c);
	}
	
}
