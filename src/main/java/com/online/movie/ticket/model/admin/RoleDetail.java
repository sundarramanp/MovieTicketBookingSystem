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
import com.online.movie.ticket.model.admin.RoleMaster;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;
@ToString
@Entity
@Data
@Table(
		   name = "role_detail", 
		   uniqueConstraints = {
				   @UniqueConstraint(columnNames = {"role_id","object_name"}, name="UK_ROLE_DETAIL_OBJECT")
		   }
		)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RoleDetail {

	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	/*Create/Last User and Data Info*/
	@Embedded
	SystemTrack systemTrack;
	

	
	@ManyToOne
	@JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "FK_USER_DETAIL_ROLE"))
	RoleMaster roleMaster;
	
	
	@Column(name = "object_name", nullable=false, length = 100)
	String objectName;
	

	/*Description*/
	@Column(name="description", length=1000)
	String description;
}

