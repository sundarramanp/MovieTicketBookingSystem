package com.online.movie.ticket.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.online.movie.ticket.model.ScreenMaster;


public interface ScreenMasterRepository extends JpaRepository<ScreenMaster, Long> {

	
}

