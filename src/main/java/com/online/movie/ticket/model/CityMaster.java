package com.online.movie.ticket.model;


import java.io.Serializable;


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

import com.online.movie.ticket.enumeration.VisibilityStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(
		   name = "city_master", 
		   uniqueConstraints = {@UniqueConstraint(columnNames = {"city_code"}, name="UK_CITY_CITYCODE"),
				   @UniqueConstraint(columnNames = {"city_name"}, name="UK_CITY_CITYNAME")}
		)
public class CityMaster implements Serializable{
	private static final long serialVersionUID = 1L;

	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	/*Create/Last User and Data Info*/
	@Embedded
	@JsonIgnore
	SystemTrack systemTrack;
	
	/*City Code*/
	@NotEmpty(message = "Please enter city code")
	@Size(max = 10 ,message = "City code length should not exceed 10")
	@Column(name="city_code", nullable=false, length=10)
	String cityCode;
	
	/*City Name*/
	@NotEmpty(message = "Please enter city name")
	@Size(max = 100 ,message = "City name length should not exceed 100")
	@Column(name="city_name", nullable=false, length=100)
	String cityName;
	
	/*State id*/
	@ManyToOne
	@JoinColumn(name="state_id", foreignKey = @ForeignKey(name = "FK_BRANCHCITYID"))
	StateMaster stateMaster;

	/* Visibility Status */
	@NotNull(message = "Please enter city visibility status")
	@Column(name = "status", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	VisibilityStatus status;
	
	/*Remark*/
	@Size(max = 1000 ,message = "Remark character length should not exceed 1000")
	@Column(name="remark", length=1000)
	String remark;	
}
