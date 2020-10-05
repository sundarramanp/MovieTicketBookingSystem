package com.online.movie.ticket.model.admin;




import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.online.movie.ticket.model.SystemTrack;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(
		   name = "role_master", 
		   uniqueConstraints = {
				   @UniqueConstraint(columnNames = {"role_code"}, name="UK_ROLE_CODE"),
				   
		   }
		)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RoleMaster implements Serializable{
	private static final long serialVersionUID = 1L;

	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	/*Create/Last User and Data Info*/
	@Embedded
	@JsonIgnore
	SystemTrack systemTrack;
	

	@Column(name="role_code", nullable=false, length=10)
	String roleCode;
	
	
	@Column(name="role_name", nullable=false, length=20)
	String roleName;
	
	/*Description*/
	@Column(name="description", length=1000)
	String description;
	
}
	