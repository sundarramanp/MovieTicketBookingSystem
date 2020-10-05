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
		   name = "user_role_mapping", 
		   uniqueConstraints = {
				   @UniqueConstraint(columnNames = {"user_id","role_id"}, name="UK_USER_ROLE_MAPPING")
		   }
		)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserRoleMapping {

	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	/*Create/Last User and Data Info*/
	@Embedded
	@JsonIgnore
	SystemTrack systemTrack;
	

	
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_USER_ROLE_IDENTITY"))
	UserMaster userMaster;
	
	@ManyToOne
	@JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "FK_USER_ROLE_CODE"))
	RoleMaster roleMaster;
	
	
	/*Description*/
	@Column(name="description", length=1000)
	String description;
}
