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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Entity
@Data
@Table(
		   name = "Theater_master", 
		   uniqueConstraints = {
				   @UniqueConstraint(columnNames = {"Theater_name","city_id"}, name="UK_THEATER_NAME")
		   }
	  )

public class TheaterMaster implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	/*Create/Last User and Data Info*/
	@Embedded
	@JsonIgnore
	SystemTrack systemTrack;

	/*theater Name*/
	@NotEmpty(message = "Please enter theater name")
	@Size(max = 100 ,message = "Theater name length should not exceed 100")
	@Column(name="theater_Name", nullable=false, length=100)
	String theaterName;



	/*City id*/
	@ManyToOne
	@JoinColumn(name="city_id", foreignKey = @ForeignKey(name = "FK_THEATERCITYID"))
	CityMaster cityMaster;
	
	
	@Size(max = 50 ,message = "Manager name length should not exceed 50")
	@Column(name="manager_Name", nullable = false, length=50)
	private String managerName;
	
	@Size(max = 15 ,message = "Manager contact number length should not exceed 15")
	@Column(name="manager_Contact",nullable = false, length=15)
	private String managerContact;

	/* Visibility Status */
	@NotNull(message = "Please enter theater visibility status")
	@Column(name = "status", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	VisibilityStatus status;
	
	/*Remark*/
	@Size(max = 1000 ,message = "Remark character length should not exceed 1000")
	@Column(name="remark", length=1000)
	String remark;	

	

}
