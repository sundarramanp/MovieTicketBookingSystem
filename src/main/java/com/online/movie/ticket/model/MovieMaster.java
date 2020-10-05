package com.online.movie.ticket.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.online.movie.ticket.model.SystemTrack;
import com.online.movie.ticket.enumeration.VisibilityStatus;
import com.online.movie.ticket.enumeration.MovieType;
import com.online.movie.ticket.util.JsonDateDeserializer;
import com.online.movie.ticket.util.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Entity
@Data
@Table(
		   name = "Movie_Master", 
		   uniqueConstraints = {
				   @UniqueConstraint(columnNames = {"movie_name","movie_Director"}, name="UK_MOVIE_MASTER")
		   }
	  )

public class MovieMaster {
	
	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	/*Create/Last User and Data Info*/
	@Embedded
	@JsonIgnore
	SystemTrack systemTrack;
	
	/*movie Name*/
	@NotEmpty(message = "Please enter movie name")
	@Size(max = 100 ,message = "Movie name length should not exceed 100")
	@Column(name="movie_Name", nullable=false, length=100)
	String movieName;

	/* MovieType Status */
	@NotNull(message = "Please enter movie type")
	@Column(name = "movie_Type", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	MovieType movieType;
	
	@NotEmpty(message = "Please enter movie director name")
	@Column(name="movie_Director", length=50, nullable = false)
	@Size(max = 50 ,message = "Movie Director name length should not exceed 50")
	 String movieDirector;
	
	@NotEmpty(message = "Please enter movie duration")
	@Column(name="movie_duration", length=10, nullable = false)
	@Size(max = 10 ,message = "Movie duration length should not exceed 10")
	 String movieDuration;
	
	@NotEmpty(message = "Please enter movie language")
	@Column(name="movie_language", nullable = false,length=15)
	@Size(max = 15 ,message = "Movie language length should not exceed 15")
	String movieLanguage;

	

	/*Remark*/
	@Size(max = 1000 ,message = "Remark character length should not exceed 1000")
	@Column(name="remark", length=1000)
	String remark;	


}
