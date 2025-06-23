package com.web.ashoktractors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseModel, Integer> {
	/*
	 * sp_purchase_spare_part
	 * 
	 * @part_id INT,
	 * 
	 * @supplier_id INT,
	 * 
	 * @quantity INT, -- Received quantity (entered directly)
	 * 
	 * @purchase_price DECIMAL(10, 2)
	 */

	@Modifying
	@Transactional
	@Query(value = "EXEC sp_purchase_spare_part:part_id,:supplier_id,:quantity,:purchase_price", nativeQuery = true)
	public void addpurchase(@Param("part_id") int part_id, @Param("supplier_id") int supplier_id,
			@Param("quantity") int quantity, @Param("purchase_price") double purchase_price);

	@Query(value = "select*from purchases", nativeQuery = true)
	public List<PurchaseModel> getAllPurchase();

	@Modifying
	@Transactional
	@Query(value = "EXEC  sp_get_purchase_by_id:purchase_id", nativeQuery = true)
	public List<PurchaseModel> fetchOne(@Param("purchase_id") int purchase_id);

	@Modifying
	@Transactional
	@Query(value = "EXEC sp_update_purchases :purchase_id,:part_id,:supplier_id,:quantity,:purchase_price", nativeQuery = true)
	public void editPurchase(@Param("purchase_id") int purchase_id, @Param("part_id") int part_id,
			@Param("supplier_id") int supplier_id, @Param("quantity") int quantity,
			@Param("purchase_price") double purchase_price);

	@Modifying
	@Transactional
	@Query(value = "EXEC sp_delete_purchase:purchase_id", nativeQuery = true)
	public void deletePurchase(@Param("purchase_id") int purchase_id);

}
