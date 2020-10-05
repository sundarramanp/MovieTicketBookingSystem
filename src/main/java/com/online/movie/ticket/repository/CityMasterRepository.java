package com.online.movie.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.movie.ticket.model.CityMaster;


public interface CityMasterRepository extends JpaRepository<CityMaster, Long> {
	
}
