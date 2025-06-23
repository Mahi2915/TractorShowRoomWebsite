package com.web.ashoktractors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface SupplierRepository extends JpaRepository<SupplierModel,Integer>{
	
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC AddSuppliers:supplier_name,:phone,:address", nativeQuery = true)
    public void addSupplier(@Param("supplier_name") String supplier_name, @Param("phone") String phone ,@Param("address") String address);
	
	
	@Query(value = "select*from spare_suppliers",nativeQuery=true)
	   public List<SupplierModel>getAllSuppliers();
	
	
	
	
	
	
	

}
