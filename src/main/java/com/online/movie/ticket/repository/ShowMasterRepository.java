package com.online.movie.ticket.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.online.movie.ticket.model.ShowMaster;


public interface ShowMasterRepository extends JpaRepository<ShowMaster, Long> {

	
}
