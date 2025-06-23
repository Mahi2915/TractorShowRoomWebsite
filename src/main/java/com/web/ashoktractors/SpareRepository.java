package com.web.ashoktractors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface SpareRepository extends JpaRepository<SparesModel,Integer> {
	/*part_id INT PRIMARY KEY IDENTITY(1,1),
    part_name VARCHAR(100) NOT NULL,
    brand VARCHAR(100),
    
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    availability VARCHAR(20) DEFAULT 'Available',
    updated_at DATETIME DEFAULT GETDATE()*/
	
	@Modifying
    @Transactional
    @Query(value = "EXEC  sp_insert_spare_part:part_name,:brand,:price,:quantity,:availability", nativeQuery = true)
    public void addSpares(@Param("part_name") String part_name, @Param("brand") String brand ,@Param("price") double price,@Param("quantity") int quantity,
    		@Param("availability") String availability);
	
	@Query(value = "select*from spare_parts",nativeQuery=true)
	   public List<SparesModel>getSpares();
	

	@Modifying
    @Transactional
    @Query(value = "EXEC  sp_get_spare_part_by_id:part_id",nativeQuery=true)
   public List<SparesModel>fetchOnePart(@Param("part_id")int part_id);
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC sp_update_spare_part:part_id,:part_name,:brand,:price,:quantity,:availability", nativeQuery = true)
    public void editSpare(@Param("part_id") int part_id,@Param("part_name") String part_name, @Param("brand") String brand ,@Param("price") double price,@Param("quantity") int quantity,
    		@Param("availability") String availability);
	
	@Modifying
    @Transactional
    @Query(value = "EXEC sp_delete_spare_part:part_id",nativeQuery = true)
	public void deleteSpare(@Param("part_id") int part_id);
	
	

}
