package com.online.movie.ticket.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import com.online.movie.ticket.enumeration.MovieType;
import com.online.movie.ticket.util.JsonDateDeserializer;
import com.online.movie.ticket.util.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Data
@Table(
		   name = "Show_Master", 
		   uniqueConstraints = {
				   @UniqueConstraint(columnNames = {"Show_name","screen_Id"}, name="UK_SHOWMASTER_THEATER")
		   }
	  )
public class ShowMaster {
private static final long serialVersionUID = 1L;
	
	/*Auto Generation Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	/*Create/Last User and Data Info*/
	@Embedded
	@JsonIgnore
	SystemTrack systemTrack;

	/*ScreenMaster  id*/
	@ManyToOne
	@JoinColumn(name="screen_Id", nullable = false ,foreignKey = @ForeignKey(name = "FK_SHOWMASTER_THEATER"))
	ScreenMaster screenMaster;
	
	/*show Name */
	@NotEmpty(message = "Please enter show name")
	@Size(max = 100 ,message = "Show name length should not exceed 100")
	@Column(name="Show_name", nullable=false, length=100)
	String showName;
	
	@NotEmpty(message = "Show Start time is mandatory")
	@Size(max = 5 ,message = "Show Start Time length should not exceed 5")
	@Column(name = "Show_start_Time", nullable = false,length=5)
	String showStartTime;
	
	@NotEmpty(message = "Show End Date is mandatory")
	@Size(max = 5 ,message = "Show End Time length should not exceed 5")
	@Column(name = "Show_End_Time", nullable = false, length=5)
	String showEndTime; 
	
}
