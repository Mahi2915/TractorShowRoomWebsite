package com.web.ashoktractors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TractorserviceRepository extends JpaRepository<TractorServiceBook,Integer> {
	
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC  CompleteTractorService:service_id,:cost",nativeQuery=true)
   public void CompleteService(@Param("service_id")int service_id,@Param("cost") double cost);
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC InsertTractorService:customer_name,:tractor_number,:service_type,:service_details,:mechanic_name", nativeQuery = true)
    public void addService(@Param("customer_name") String customer_name, @Param("tractor_number") String tractor_number ,@Param("service_type") String service_type,@Param("service_details") String service_details,
    		@Param("mechanic_name") String mechanic_name);
	
	@Query(value = "EXEC GetAll",nativeQuery=true)
	   public List<TractorServiceBook>getAllService();
	
	@Query(value = "select*from service_types",nativeQuery=true)
	   public List<Object[]>getAllTypes();
	
	@Modifying
    @Transactional
    @Query(value = "EXEC  GetTractorServiceById:service_id",nativeQuery=true)
   public List<TractorServiceBook>fetchOneService(@Param("service_id")int service_id);
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC   UpdateTractorService:service_id,:customer_name,:tractor_number,:service_type,:service_details,:mechanic_name", nativeQuery = true)
    public void editService(@Param("service_id") int service_id,@Param("customer_name") String customer_name, @Param("tractor_number") String tractor_number ,@Param("service_type") String service_type,@Param("service_details") String service_details,
    		@Param("mechanic_name") String mechanic_name);
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC  DeleteTractorService:service_id",nativeQuery = true)
	public void deleteService(@Param("service_id") int service_id);
	
	
	
	
	
	
	

}
