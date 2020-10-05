package com.online.movie.ticket.repository;



import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.online.movie.ticket.model.admin.UserMaster;

public interface UserMasterRepository extends JpaRepository<UserMaster, Long> {
	UserMaster findByUserID(String userID);
	
	@Query("Select c from UserMaster c ORDER BY c.userID, c.userType ASC")
	@Override
	List<UserMaster> findAll();	
	
	
}

