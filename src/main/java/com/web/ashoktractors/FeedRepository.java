package com.web.ashoktractors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FeedRepository extends JpaRepository<ServiceFeedBack,Integer>{
	
	
	
	@Modifying
    @Transactional
    @Query(value = "EXEC InsertFeedback:customer_id,:tractor_number,:rating,:comment", nativeQuery = true)
    public void addFeed(@Param("customer_id") int customer_id, @Param("tractor_number") String tractor_number ,@Param("rating") int rating ,@Param("comment") String comment);
	
	
	@Query(value = "select*from ServiceFeedback",nativeQuery=true)
	   public List<ServiceFeedBack>getAllFeeds();

}
