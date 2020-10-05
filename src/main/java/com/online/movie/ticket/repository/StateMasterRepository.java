package com.online.movie.ticket.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.online.movie.ticket.model.StateMaster;

public interface StateMasterRepository extends JpaRepository<StateMaster, Long> {
	
}
