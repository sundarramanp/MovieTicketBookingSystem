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

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.online.movie.ticket.model.SystemTrack;
import com.online.movie.ticket.enumeration.VisibilityStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(
		   name = "Screen_Master", 
		   uniqueConstraints = {
				   @UniqueConstraint(columnNames = {"theater_Id","screen_name"}, name="UK_SCREEN_NAME")
		   }
	  )

public class ScreenMaster implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	/*Create/Last User and Data Info*/
	@Embedded
	@JsonIgnore
	SystemTrack systemTrack;
	
	/*TheaterMaster  id*/
	@ManyToOne
	@JoinColumn(name="theater_Id", nullable = false ,foreignKey = @ForeignKey(name = "FK_SCREEN_THEATER"))
	TheaterMaster theaterMaster;
	
	/*theater Name*/
	@NotEmpty(message = "Please enter screen name")
	@Size(max = 100 ,message = "Screen name length should not exceed 100")
	@Column(name="screen_name", nullable=false, length=100)
	String screenName;


	/* Visibility Status */
	@NotNull(message = "Please enter theater visibility status")
	@Column(name = "status", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	VisibilityStatus status;
	
	@NotNull(message = "Please enter the number of row for this screen")
	@Column(name = "no_of_row", nullable = false, length = 5)
	Integer numberOfRow;
	
	@NotNull(message = "Please enter the number of column for this screen")
	@Column(name = "no_of_column", nullable = false, length = 5)
	Integer numberOfColumn;
	/*Remark*/
	@Size(max = 1000 ,message = "Remark character length should not exceed 1000")
	@Column(name="remark", length=1000)
	String remark;	

	

}