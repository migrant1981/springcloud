package com.example.startadmin.Service;

import com.example.startadmin.entity.AdminVO;

public interface Admin {
	void save(AdminVO c);

	AdminVO getCompanyStockPrice(String name);
    
	AdminVO getMatchingCompany(String name);
    
	AdminVO getCompanyDetails(String name);
    
	AdminVO getCompanyIPODetails(String name);    

}
