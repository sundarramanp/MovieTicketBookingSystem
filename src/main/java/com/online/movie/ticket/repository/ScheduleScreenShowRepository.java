package com.online.movie.ticket.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.online.movie.ticket.model.ScheduleScreenShow;


public interface ScheduleScreenShowRepository extends JpaRepository<ScheduleScreenShow, Long> {

	
}

