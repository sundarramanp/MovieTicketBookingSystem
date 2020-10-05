package com.online.movie.ticket.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.online.movie.ticket.model.MovieMaster;


public interface MovieMasterRepository extends JpaRepository<MovieMaster, Long> {

	
}

