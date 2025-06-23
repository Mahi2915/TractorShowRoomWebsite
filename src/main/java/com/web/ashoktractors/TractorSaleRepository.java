package com.web.ashoktractors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TractorSaleRepository extends JpaRepository<TractorSaleModel,Integer> {
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC RecordTractorSales:tractor_id,:customer_id,:sale_quantity,:sale_price", nativeQuery = true)
    public void addTractorSales(@Param("tractor_id") int tractor_id, @Param("customer_id") int customer_id,@Param("sale_quantity") int sale_quantity ,@Param("sale_price") double sale_price);

	@Modifying
    @Transactional
    @Query(value = "EXEC  getOneByTractorsales:sale_id",nativeQuery=true)
   public List<TractorSaleModel>fetchOneSale(@Param("sale_id")int sale_id);
	
	
	@Query(value = "EXEC GetCurrentMonthSales",nativeQuery=true)
	  public List<Object[]> fetchMonthlySales();
	
	@Modifying
    @Transactional
    @Query(value = "EXEC   updateTractorSale:sale_id,:tractor_id,:customer_id,:sale_quantity,:sale_price", nativeQuery = true)
    public void editTractorSale(@Param("sale_id")int sale_id,@Param("tractor_id") int tractor_id,@Param("customer_id") int customer_id, @Param("sale_quantity") int sale_quantity ,@Param("sale_price") double sale_price);
	
	@Modifying
    @Transactional
    @Query(value = "EXEC  deleteTractorSale:sale_id",nativeQuery = true)
	public void deleteTractorSale(@Param("sale_id") int sale_id);
	
	
	
	@Query(value = "select*from tractor_sales",nativeQuery=true)
	   public List<TractorSaleModel>getAllTractorSales();
	
	
	}
	
	
	
	
	
	
	

