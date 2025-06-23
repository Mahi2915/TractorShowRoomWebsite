package com.web.ashoktractors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface TractorRepository extends JpaRepository<TractorModel,Integer>{
	
	
	
	@Query(value = "select*from tractors",nativeQuery=true)
	   public List<TractorModel>getAllTractors();
	
	@Query(value = "SELECT SUM(current_count) AS total_tractors FROM tractors;\r\n"
			+ "",nativeQuery=true)
	   public int getCount();
	
	@Modifying
    @Transactional
    @Query(value = "EXEC usp_insert_tractor:modelname,:brand,:color ,:enginepower,:currentcount,:price", nativeQuery = true)
    public void addTractor(@Param("modelname") String modelname, @Param("brand") String brand ,@Param("color") String color ,@Param("enginepower") String enginepower,
    		@Param("currentcount") int currentcount,@Param("price") double price);
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC  usp_get_tractor_by_id:tractor_id",nativeQuery=true)
   public List<TractorModel>fetchOneTractor(@Param("tractor_id")int tractor_id);
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC   sp_update_tractor:tractor_id,:modelname,:brand,:color ,:enginepower,:currentcount,:price", nativeQuery = true)
    public void editTractor(@Param("tractor_id") int tractor_id,@Param("modelname") String modelname, @Param("brand") String brand ,@Param("color") String color ,@Param("enginepower") String enginepower,
    		@Param("currentcount") int currentcount,@Param("price") double price);
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC  sp_delete_tractor:tractor_id",nativeQuery = true)
	public void deleteTractor(@Param("tractor_id") int tractor_id);
	
	
	
	
	
	
	
	

}
