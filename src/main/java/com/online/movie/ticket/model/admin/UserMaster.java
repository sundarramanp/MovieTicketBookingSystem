package com.online.movie.ticket.model.admin;



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
import javax.persistence.Version;

import com.online.movie.ticket.enumeration.LoginUserType;
import com.online.movie.ticket.enumeration.UserStatus;
import com.online.movie.ticket.model.SystemTrack;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
/**
 * 
 * @author Sundar
 *
 */
@Entity
@Data
@Table(
		   name = "user_master", 
		   uniqueConstraints = {
				   @UniqueConstraint(columnNames = {"login_id"}, name="UK_USER_MASTER_LOGIN_ID")
		   }
		)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserMaster implements Serializable{
	private static final long serialVersionUID = 1L;

	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	/*Create/Last User and Data Info*/
	@Embedded
	@JsonIgnore
	SystemTrack systemTrack;
	
	
	@Column(name="login_id", nullable=false, length=30)
	String userID;
	
	
	@Column(name="password", nullable=false, length=60)
	String password;

	@Column(name="user_name", nullable=false, length=50)
	String userName;
	
	
	@Column(name="user_type", nullable=false, length=20)
	@Enumerated(EnumType.STRING)
	LoginUserType userType;
	
	/*Description*/
	@Column(name="description", length=1000)
	String description;
	
	/* Status*/
	@Column(name="status",nullable=false, length=10)
	@Enumerated(EnumType.STRING)
	UserStatus status;
	
}
	