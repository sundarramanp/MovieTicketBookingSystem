package com.online.movie.ticket.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.online.movie.ticket.model.ScreenShow;


public interface ScreenShowRepository extends JpaRepository<ScreenShow, Long> {

	
}
