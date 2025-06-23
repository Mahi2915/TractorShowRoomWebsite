package com.web.ashoktractors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface OrderRepository extends JpaRepository<OrderSparesModel,Integer> {
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC sp_add_spare_part_orders:part_id,:supplier_id,:quantity_ordered", nativeQuery = true)
    public void addOrder(@Param("part_id") int part_id, @Param("supplier_id") int supplier_id ,@Param("quantity_ordered") int quantity_ordered);
	
	
	@Query(value = "select*from spare_part_orders",nativeQuery=true)
	   public List<OrderSparesModel>getAllOrders();
	
	
	

}
