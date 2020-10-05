
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.movie.ticket.enumeration.VisibilityStatus;

import lombok.Data;

@Entity
@Data
@Table(
		   name = "movie_snaps"
		)
public class MovieSnaps implements Serializable{
	private static final long serialVersionUID = 1L;

	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	/*Create/Last User and Data Info*/
	@Embedded
	@JsonIgnore
	SystemTrack systemTrack;

	/*State id*/
	@ManyToOne
	@JoinColumn(name="movie_id", foreignKey = @ForeignKey(name = "FK_MOVIE_SNAP"))
	MovieMaster movieMaster;

	/*File Name*/
	String fileName;
	
	String fileType;
	

    private byte[] image;	
    
	
}
