package com.online.movie.ticket.model.admin;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.online.movie.ticket.model.SystemTrack;
import com.online.movie.ticket.model.admin.UserMaster;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;
@ToString
@Entity
@Data
@Table(
		   name = "user_profile_mapping", 
		   uniqueConstraints = {
				   @UniqueConstraint(columnNames = {"user_id","profile_code"}, name="UK_USER_PROFILE_MAPPING")
		   }
		)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserProfileMapping {

	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	/*Create/Last User and Data Info*/
	@Embedded
	@JsonIgnore
	SystemTrack systemTrack;
	

	
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_USER_IDENTITY"))
	UserMaster userMaster;
	
	
	@Column(name = "profile_code", nullable=false, length = 30)
	String profileCode;
	
	@Column(name = "profile_type", nullable=false, length = 15)
	String profileType;
	
	/*Description*/
	@Column(name="description", length=1000)
	String description;
}
