package com.online.movie.ticket.core.dto;

import lombok.Data;

@Data
public class ReserveSeatDTO {
	Long id;
	String selectedSeat;
	String bookingPersonName;

}
