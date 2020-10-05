package com.online.movie.ticket.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.online.movie.ticket.util.JsonDateDeserializer;
import com.online.movie.ticket.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Embeddable
@Data

public class SystemTrack implements Serializable {
	private static final long serialVersionUID = 1L;

	/*Created User Name */
	@Column(name="create_user", length=30, nullable=false,updatable=false)
	String createUser;

	/*Created Date */
	@Column(name="create_date", nullable=false,updatable=false)
	@JsonSerialize(using=JsonDateSerializer.class)
	@JsonDeserialize(using=JsonDateDeserializer.class)

	Date createDate;

	/*Last Updated User Name */
	@Column(name="last_updated_user", length=30, nullable=false)
	String lastUpdatedUser;

	/*Last Updated Date */
	@Column(name="last_updated_date", nullable=false)
	@JsonSerialize(using=JsonDateSerializer.class)
	@JsonDeserialize(using=JsonDateDeserializer.class)

	Date lastUpdatedDate;

}
