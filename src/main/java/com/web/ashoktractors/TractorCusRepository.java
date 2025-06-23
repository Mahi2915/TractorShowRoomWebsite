package com.web.ashoktractors;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface TractorCusRepository extends JpaRepository<TractorCustomerModel,Integer> {
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC sp_signup_customer:name,:age,:gender,:email,:phone,:address,:password", nativeQuery = true)
    public void signUpCustomer(@Param("name") String name, @Param("age") int age,@Param("gender") String gender,@Param("email") String email,
    		@Param("phone") String phone,@Param("address") String address,@Param("password") String password);
	
	
	 @Query(value="select count(*) from tractor_customer",nativeQuery=true)
	 public int getCount();
	
	@Modifying
    @Transactional
    @Query(value = "EXEC sp_login_customers:email,:password", nativeQuery = true)
    public List<TractorCustomerModel> loginCustomer(@Param("email") String email,@Param("password") String password);
	
	@Query(value = "select*from tractor_customer",nativeQuery=true)
	   public List<TractorCustomerModel>getAllCustomers();
	
	@Modifying
    @Transactional
    @Query(value = "EXEC GetCustomerById:customer_id",nativeQuery=true)
   public List<TractorCustomerModel>fetchOneCustomer(@Param("customer_id")int customer_id);
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC  sp_update_customer:customer_id,:name,:age,:gender,:email,:phone,:address", nativeQuery = true)
    public void editCustomer(@Param("customer_id") int customer_id,@Param("name") String name,@Param("age") int age,@Param("gender") String gender,@Param("email") String email,
    		@Param("phone") String phone,@Param("address") String address);
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC sp_delete_customer:customer_id",nativeQuery = true)
	public void deleteCustomer(@Param("customer_id") int customer_id);
	
	
	
	
	
	
	
	
	
	
	
	
}
