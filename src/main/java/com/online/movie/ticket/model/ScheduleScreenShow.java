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
import com.online.movie.ticket.enumeration.ScheduleStatus;

import com.online.movie.ticket.util.JsonDateDeserializer;
import com.online.movie.ticket.util.JsonDateSerializer;
import com.online.movie.ticket.model.MovieMaster;
import com.online.movie.ticket.model.ShowMaster;
import com.online.movie.ticket.model.TheaterMaster;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Entity
@Data
@Table(
		   name = "Schedule_screen_Show", 
		   uniqueConstraints = {
				   @UniqueConstraint(columnNames = {"Screen_Show_id","screen_Date","schedule_Status"}, name="UK_SCHEDULE_SCREEN_SHOW")
		   }
	  )
public class ScheduleScreenShow {

	
	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	/*Create/Last User and Data Info*/
	@Embedded
	@JsonIgnore
	SystemTrack systemTrack;

	/*Screen_Show_id  id*/
	@ManyToOne
	@JoinColumn(name="Screen_Show_id", nullable = false ,foreignKey = @ForeignKey(name = "FK_SCHEDULE_SCREENSHOW"))
	ScreenShow screenShow;
	

	/* Schedule Date*/ 
	@NotNull(message = "Screen date  is mandatory")
	@Column(name = "screen_Date", nullable = false)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	Date screenDate;	
	
	
	/* MovieShowStatus Status */
	@Column(name = "schedule_Status", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	ScheduleStatus scheduleStatus;



}
