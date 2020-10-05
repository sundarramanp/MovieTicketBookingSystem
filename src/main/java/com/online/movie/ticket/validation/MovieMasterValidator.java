package com.online.movie.ticket.validation;

import org.springframework.stereotype.Service;
import com.online.movie.ticket.enumeration.VisibilityStatus;
import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.model.MovieMaster;
import com.online.movie.ticket.util.ValidateUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MovieMasterValidator {
	
	public void validate(MovieMaster movieMaster) {

		
		log.debug("Movie Master Validation Begins .... ");
		
		ValidateUtil.notNull(movieMaster,ErrorCode.MOVIE_NOT_NULL);
	
		// Validating Movie Name (Mandatory)
		ValidateUtil.notNullOrEmpty(movieMaster.getMovieName(),ErrorCode.MOVIE_NAME_NOT_NULL);
		movieMaster.setMovieName(movieMaster.getMovieName().trim().toUpperCase());
		ValidateUtil.checkPattern(movieMaster.getMovieName(), "MovieName", true);
		
		// Validating movieDirector (Mandatory)
		ValidateUtil.notNullOrEmpty(movieMaster.getMovieDirector(),ErrorCode.MOVIE_NAME_NOT_NULL);
		movieMaster.setMovieDirector(movieMaster.getMovieDirector().trim().toUpperCase());
		ValidateUtil.checkPattern(movieMaster.getMovieDirector(), "MovieDirector", true);
		
		// Validating movieDuration (Mandatory)
		ValidateUtil.notNullOrEmpty(movieMaster.getMovieDuration(),ErrorCode.MOVIE_NAME_NOT_NULL);
		movieMaster.setMovieDuration(movieMaster.getMovieDuration().trim().toUpperCase());
		ValidateUtil.checkPattern(movieMaster.getMovieDuration(), "MovieDuration", true);
		
		
		// Validating movieLanguage (Mandatory)
		ValidateUtil.notNullOrEmpty(movieMaster.getMovieLanguage(),ErrorCode.MOVIE_NAME_NOT_NULL);
		movieMaster.setMovieLanguage(movieMaster.getMovieLanguage().trim().toUpperCase());
		ValidateUtil.checkPattern(movieMaster.getMovieLanguage(), "MmovieLanguage", true);
		
		
		// Validating Description (Not Mandatory)
		
	   ValidateUtil.checkPattern(movieMaster.getRemark(), "MovieDescription", true);

		// Validating Status (Mandatory)
		
		log.debug("Movie Master Validation Ends .... ");
		
		
	}
	

}