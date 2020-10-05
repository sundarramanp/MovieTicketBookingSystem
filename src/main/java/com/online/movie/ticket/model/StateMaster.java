package com.online.movie.ticket.model;


import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
		   name = "state_master", 
		   uniqueConstraints = {@UniqueConstraint(columnNames = {"state_code"}, name="UK_STATE_MASTER_CODE"),
				   @UniqueConstraint(columnNames = {"state_name"}, name="UK_STATE_MASTER_NAME")}
		)
public class StateMaster implements Serializable{
	private static final long serialVersionUID = 1L;

	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	/*Create/Last User and Data Info*/
	@Embedded
	@JsonIgnore
	SystemTrack systemTrack;
	
	/*State Code*/
	@NotEmpty(message = "Please enter state code")
	@Size(max = 10 ,message = "State code length should not exceed 10")
	@Column(name="state_code", nullable=false, length=10)
	String stateCode;
	
	/*State Name*/
	@NotEmpty(message = "Please enter state name")
	@Size(max = 100 ,message = "State name length should not exceed 100")
	@Column(name="state_name", nullable=false, length=100)
	String stateName;
	

	/* Visibility Status */
	@NotNull(message = "Please enter state visibility status")
	@Column(name = "status", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	VisibilityStatus status;
	
	/*Remark*/
	@Size(max = 1000 ,message = "Remark character length should not exceed 1000")
	@Column(name="remark", length=1000)
	String remark;	
}
