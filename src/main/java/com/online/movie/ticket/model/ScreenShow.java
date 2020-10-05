package com.online.movie.ticket.model;



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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.online.movie.ticket.model.SystemTrack;
import com.online.movie.ticket.enumeration.MovieShowStatus;

import com.online.movie.ticket.util.JsonDateDeserializer;
import com.online.movie.ticket.util.JsonDateSerializer;
import com.online.movie.ticket.model.MovieMaster;
import com.online.movie.ticket.model.ShowMaster;
import com.online.movie.ticket.model.ScreenMaster;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Entity
@Data
@Table(
		   name = "Screen_Show", 
		   uniqueConstraints = {
				   @UniqueConstraint(columnNames = {"screen_Id","show_Id","movie_id","start_date"}, name="UK_SCREEN_SHOW")
		   }
	  )
public class ScreenShow {

	
	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	/*Create/Last User and Data Info*/
	@Embedded
	@JsonIgnore
	SystemTrack systemTrack;

	/*ScreenMaster  id*/
	@ManyToOne
	@JoinColumn(name="screen_Id", nullable = false ,foreignKey = @ForeignKey(name = "FK_SCREEN_SHOW_THEATER"))
	ScreenMaster screenMaster;
	
	/*ShowMaster  id*/
	@ManyToOne
	@JoinColumn(name="show_Id", nullable = false ,foreignKey = @ForeignKey(name = "FK_SCREEN_SHOW_SHOW"))
	ShowMaster showMaster;
	
	/*MovieMaster  id*/
	@ManyToOne
	@JoinColumn(name="movie_id", nullable = false ,foreignKey = @ForeignKey(name = "FK_SCREEN_SHOW_MOVIE"))
	MovieMaster movieMaster;
	
	/* MovieShowStatus Status */
	@Column(name = "screenStatus", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	MovieShowStatus screenStatus;



	/* Start Date*/ 
	@NotNull(message = "Start date Code is mandatory")
	@Column(name = "start_date", nullable = false)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	Date startDate;	
	
	/* End Date*/ 
	@NotNull(message = "End date  is mandatory")
	@Column(name = "end_date", nullable = false)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	Date endDate;	

	
	/*Description*/
	@Column(name="remark", length=1000)
	String remark;
}