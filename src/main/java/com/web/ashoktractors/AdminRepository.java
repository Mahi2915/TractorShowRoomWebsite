package com.web.ashoktractors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface AdminRepository extends JpaRepository<AdminModel,Integer> {
	
	@Modifying
    @Transactional
    @Query(value = "EXEC usp_AddAdmin:name,:username,:password", nativeQuery = true)
    public void signUpAdmin(@Param("name") String name, @Param("username") String username,@Param("password") String password);
	
	@Modifying
    @Transactional
    @Query(value = "EXEC GetAdminById:admin_id",nativeQuery=true)
   public List<AdminModel>fetchOne(@Param("admin_id")int admin_id);
	
	@Modifying
    @Transactional
    @Query(value = "EXEC sp_login_admin:username,:password", nativeQuery = true)
    public List<AdminModel> loginAdmin(@Param("username") String username,@Param("password") String password);
	
	@Modifying
    @Transactional
    @Query(value = "EXEC  UpdateAdmin:admin_id,:name,:username,:password", nativeQuery = true)
    public void editAdmin(@Param("admin_id") int admin_id,@Param("name") String name, @Param("username") String username,@Param("password") String password);
	
	@Modifying
    @Transactional
    @Query(value = "EXEC  usp_DeleteAdmin:admin_id",nativeQuery = true)
	public void deleteAdmin(@Param("admin_id") int admin_id);
	
	@Query(value = "select*from admintractor",nativeQuery=true)
	   public List<AdminModel>getAllAdmins();
	
	

}
