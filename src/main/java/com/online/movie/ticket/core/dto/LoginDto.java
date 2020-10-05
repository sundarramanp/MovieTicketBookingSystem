package com.online.movie.ticket.core.dto;


import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginDto {
	@NotEmpty(message = "Please provide a name")
	String userID;
	@NotEmpty(message = "Please provide a password")
	String password;
}

