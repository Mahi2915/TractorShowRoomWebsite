package com.web.ashoktractors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SalesInterface extends JpaRepository<SalesModel,Integer> {
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC sp_spare_part_sale:customer_id,:part_id,:sale_quantity", nativeQuery = true)
    public void saleEntry(@Param("customer_id") int customer_id, @Param("part_id") int part_id ,@Param("sale_quantity") int sale_quantity);
	
	
	@Query(value = "select*from spare_part_sales",nativeQuery=true)
	   public List<SalesModel>getAllSales();
	
	
	 @Query(value = "EXEC GetTop3SoldPartsToday", nativeQuery = true)
		    List<Object[]> getTop3SoldPartsToday(); 
		    
		    @Query(value = "EXEC GetSoldParts", nativeQuery = true)    
		    List<Object[]> getMonthlySparePartSales();

}
