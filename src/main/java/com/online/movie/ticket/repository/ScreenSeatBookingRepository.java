package com.online.movie.ticket.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.online.movie.ticket.model.ScreenSeatBooking;;



public interface ScreenSeatBookingRepository extends JpaRepository<ScreenSeatBooking, Long> {

	@Query("Select e from ScreenSeatBooking e where schedule_Show_id = :scheduleShowID  ORDER BY e.id ASC")
	List<ScreenSeatBooking> findByScheduleScreenID(@Param("scheduleShowID") Long scheduleShowID);
	
	@Query("Select e from ScreenSeatBooking e where schedule_Show_id = :scheduleShowID  and e.seatNo IN  ( :seatCodeList ) ORDER BY e.id ASC")
	List<ScreenSeatBooking> findByScheduleScreenIDandSeatCode(@Param("scheduleShowID") Long scheduleShowID,
			@Param("seatCodeList") List<Integer> seatCodeList);
	
	@Modifying
	@Query("Update ScreenSeatBooking e set e.status='BOOKED' ,e.bookingPersonName = :bookingPersonName where   schedule_Show_id = :scheduleShowID  and e.seatNo IN  ( :seatCodeList )")
	void  updateByScheduleScreenIDandSeatCode(@Param("scheduleShowID") Long scheduleShowID,
												@Param("bookingPersonName") String bookingPersonName,
												@Param("seatCodeList") List<Integer> seatCodeList);
	
}
