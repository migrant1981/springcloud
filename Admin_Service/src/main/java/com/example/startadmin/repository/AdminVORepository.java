package com.example.startadmin.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.startadmin.entity.AdminVO;

public interface AdminVORepository extends CrudRepository<AdminVO, Long> {
    @Query("select u from AdminVO u where u.companyname=:companyname")
    public AdminVO getCompanyDetails(@Param("companyname") String companyname);
    
    @Query("select u from AdminVO u where u.companyname=:companyname")
    public AdminVO getMatchingCompany(@Param("companyname") String companyname);
    
    @Query("select u from AdminVO u where u.companyname=:companyname")
    public AdminVO getCompanyStockPrice(@Param("companyname") String companyname);
    
    @Query("select u from AdminVO u where u.companyname=:companyname")
    public AdminVO getCompanyIPODetails(@Param("companyname") String companyname);
    
  
}

