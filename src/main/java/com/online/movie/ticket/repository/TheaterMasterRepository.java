package com.online.movie.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.online.movie.ticket.model.TheaterMaster;


public interface TheaterMasterRepository extends JpaRepository<TheaterMaster, Long> {

	
}
