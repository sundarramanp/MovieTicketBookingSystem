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
import javax.validation.constraints.Size;

import com.online.movie.ticket.enumeration.ScheduleStatus;
import com.online.movie.ticket.enumeration.TicketStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(
		   name = "screen_seat_booking",
				   uniqueConstraints = {
						   @UniqueConstraint(columnNames = {"schedule_Show_id","seat_no"}, name="UK_SCREEN_SEAT_SHOW")
				   }
	  )
public class ScreenSeatBooking {

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
	@JoinColumn(name="schedule_Show_id", nullable = false ,foreignKey = @ForeignKey(name = "FK_SCHEDULE_SCREENSEAT"))
	ScheduleScreenShow scheduleScreenShow;
	
	
	@NotNull(message = "Please enter the seat number  for this screen")
	@Column(name = "seat_no", length = 5)
	Integer seatNo;
	

	@Size(max = 50 ,message = "Booking person name length should not exceed 50")
	@Column(name="booking_Person_Name",  length=50)
	String bookingPersonName;
	
	/* TicketStatus Status */
	@Column(name = "status", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	TicketStatus status;
}
