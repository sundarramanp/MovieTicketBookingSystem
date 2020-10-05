
package com.online.movie.ticket.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.online.movie.ticket.model.MovieSnaps;


public interface MovieSnapsRepository extends JpaRepository<MovieSnaps, Long> {

	
	@Query("Select e from MovieSnaps e where movie_id = :moiveID  ORDER BY e.id ASC")
	List<MovieSnaps> findByMovieID(@Param("moiveID") Long moiveID);
	
	
}

