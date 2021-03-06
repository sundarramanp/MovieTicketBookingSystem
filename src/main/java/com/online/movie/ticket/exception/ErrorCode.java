package com.online.movie.ticket.exception;


import lombok.Getter;
/**
 * 
 * @author Sundar
 *
 */
public enum ErrorCode {
	
	SUCCESS("ERR0000", "Request Completed Successfully..."),
	FAILED("ERR1",""),
	UNAUTHORIZED_USER("ERR2","UnAuthorized User "),
	LOGIN_BAD_CREDENTIAL_ERROR("ERR0003","Incorrect username or password."),
	NOT_READABLE_API_INPUT_PARAMETER("ERR0004","Request parameter is not readable"),
	INVALID_REQUEST_ARUGUMENT_TO_API("ERR0005","Invalid/Missing Parameter(s) at API Request"),
	INVALID_REQUEST_METHOD("ERR0006","Invalid API Request Method"),
	ERROR_GENERIC("ERR5",""),
	ERROR_FK_CONSTRAINT("ERR6",""),
	INVALID_PARAMETER("ERR7",""),
	INVALID_PARAMETER_FORMAT("ERR8",""),
	DB_ERROR("ERR9",""),
	MISSING_PARAMETER("ERR10","Invalid Request Parameter"),
	SEARCH_KEYWORD_MISSING("ERR11",""),
	SEARCH_RECORD_PER_PAGE_MISSING("ERR12",""),
	SEARCH_SELECTED_PAGE_NUMBER_MISSING("ERR13",""),
	SEQUENCE_TYPE_ALREADY_EXIST("ERR14",""),
	SEQUENCE_PREFIX_ALREADY_EXIST("ERR15",""),
	SEQUENCE_TYPE_NOT_NULL("ERR16",""),
	SEQUENCE_FORMAT_NOT_NULL("ERR17",""),
	SEQUENCE_PREFIX_NOT_NULL("ERR18",""),
	SEQUENCE_PREFIX_LENGTH("ERR19",""),
	CANNOT_UPDATE_LOCKED_RECORD( "ERR20","Record has been locked by another user"),
	CANNOT_UPDATE_DELETED_RECORD("ERR21","Record has been deleted"),
	SAAS_USER_EMPTY("ERR25",""),
	SAAS_INVAILD_USER("ERR26",""),


	STATE_CODE_ALREADY_EXIST("ERR0302", "State Code has already exist"),
	STATE_NAME_ALREADY_EXIST("ERR0303", "State Name has already exist"),
	STATE_CODE_INVALID("ERR0304", "Enter the valid State Code"),
	STATE_NAME_INVALID("ERR0305", "Enter the valid State Name"),
	STATE_ID_NOT_FOUND("ERR0306", "State does not exists"),
	STATE_CODE_NOT_NULL("ERR0307", "Enter the State Code"),
	STATE_NAME_NOT_NULL("ERR0308", "Enter the State Name"),
	STATE_VISIBILITY_STATUS_NOTNULL("ERR0312", "Enter the State Visibility Status"),
	STATE_CODE_LENGTH_EXCEED("ERR0313", "State Code length should not exceed 10 characters"),
	STATE_NAME_LENGTH_EXCEED("ERR0314", "State Name length should not exceed 100 characters"),
	STATE_NOT_FOUND("ERR0315", "State not found"),
	STATE_CODE_NOTFOUND("ERR0316", "State Code does not exists"),
	STATE_NAME_NOTFOUND("ERR0317", "State Name does not exists"),
	STATE_NOT_NULL("ERR0318", "Enter State Mandatory Fileds"),
	STATE_ID_NOT_NULL("ERR0319","State Unique Identity is missing"),

	CITY_CODE_ALREADY_EXIST("ERR0402", "City Code has already exist"),
	CITY_NAME_ALREADY_EXIST("ERR0403", "City Name has already exist"),
	CITY_CODE_INVALID("ERR0404", "Enter the valid City Code"),
	CITY_NAME_INVALID("ERR0405", "Enter the valid City Name"),
	CITY_ID_NOT_FOUND("ERR0406", "City does not exists"),
	CITY_CODE_NOT_NULL("ERR0407", "Enter the City Code"),
	CITY_NAME_NOT_NULL("ERR0408", "Enter the City Name"),
	CITY_VISIBILITY_STATUS_NOTNULL("ERR0412", "Enter the City Visibility Status"),
	CITY_CODE_LENGTH_EXCEED("ERR0413", "City Code length should not exceed 10 characters"),
	CITY_NAME_LENGTH_EXCEED("ERR0414", "City Name length should not exceed 100 characters"),
	CITY_NOT_FOUND("ERR0415", "City not found"),
	CITY_CODE_NOTFOUND("ERR0416", "City Code does not exists"),
	CITY_NAME_NOTFOUND("ERR0417", "City Name does not exists"),
	CITY_NOT_NULL("ERR0418", "Enter City Mandatory Fileds"),
	CITY_ID_NOT_NULL("ERR0419","City Unique Identity is missing"),

	

	SHOW_NAME_ALREADY_EXIST("ERR1303", "Show Name has already exist"),
	SHOW_NAME_INVALID("ERR1305", "Enter the valid Show Name"),
	SHOW_ID_NOT_FOUND("ERR1306", "Show does not exists"),
	SHOW_NAME_NOT_NULL("ERR1308", "Enter the Show Name"),
	SHOW_NAME_LENGTH_EXCEED("ERR1314", "Show Name length should not exceed 100 characters"),
	SHOW_NOT_FOUND("ERR1315", "Show not found"),
	SHOW_NAME_NOTFOUND("ERR1317", "Show Name does not exists"),
	SHOW_NOT_NULL("ERR1318", "Enter Show Mandatory Fileds"),
	SHOW_ID_NOT_NULL("ERR1319","Show Unique Identity is missing"),
	SHOW_START_TIME_NOT_NULL("ERR1320", "Enter show start time"),
	SHOW_END_TIME_NOT_NULL("ERR1321", "Enter show end time"),
	
	THEATER_CODE_ALREADY_EXIST("ERR1402", "Theater Code has already exist"),
	THEATER_NAME_ALREADY_EXIST("ERR1403", "Theater Name has already exist"),
	THEATER_CODE_INVALID("ERR1404", "Enter the valid Theater Code"),
	THEATER_NAME_INVALID("ERR1405", "Enter the valid Theater Name"),
	THEATER_ID_NOT_FOUND("ERR1406", "Theater ID does not exists"),
	THEATER_CODE_NOT_NULL("ERR1407", "Enter the Theater Code"),
	THEATER_NAME_NOT_NULL("ERR1408", "Enter the Theater Name"),
	THEATER_VISIBILITY_STATUS_NOTNULL("ERR1412", "Enter the Theater Visibility Status"),
	THEATER_CODE_LENGTH_EXCEED("ERR1413", "Theater Code length should not exceed 10 characters"),
	THEATER_NAME_LENGTH_EXCEED("ERR1414", "Theater Name length should not exceed 100 characters"),
	THEATER_NOT_FOUND("ERR1415", "Theater not found"),
	THEATER_CODE_NOTFOUND("ERR1416", "Theater Code does not exists"),
	THEATER_NAME_NOTFOUND("ERR1417", "Theater Name does not exists"),
	THEATER_NOT_NULL("ERR1418", "Enter Theater Mandatory Fileds"),
	THEATER_ID_NOT_NULL("ERR1419","Theater Unique Identity is missing"),
	THEATER_ID_FOREIGN_KEY("ERR1422","Theater Details not available in Master, So Cannot add or update a child row"),

	SCREEN_NAME_ALREADY_EXIST("ERR1503", "ScreenName has already exist"),
	SCREEN_NAME_INVALID("ERR1505", "Enter the valid ScreenName"),
	SCREEN_NAME_NOT_NULL("ERR1508", "Enter the Screen Name"),
	SCREEN_VISIBILITY_STATUS_NOTNULL("ERR1512", "Enter the Screen Visibility Status"),
	SCREEN_NAME_LENGTH_EXCEED("ERR1514", "Screen Name length should not exceed 100 characters"),
	SCREEN_NOT_FOUND("ERR1515", "Screen not found"),
	SCREEN_NAME_NOTFOUND("ERR1517", "Screen Name does not exists"),
	SCREEN_NOT_NULL("ERR1518", "Enter Screen Mandatory Fileds"),
	SCREEN_ID_NOT_NULL("ERR1519","Screen Unique Identity is missing"),
	SCREEN_ROW_NOT_NULL("ERR1530","Enter the number of rows for this screen"),
	SCREEN_COLUMN_NOT_NULL("ERR1531","Enter the number of columns for this screen"),

	MOVIE_NAME_ALREADY_EXIST("ERR1703", "Movie name has already exist"),
	MOVIE_NAME_NOT_NULL("ERR1705", "Enter the movie Name"),
	MOVIE_DIRECTOR_NAME_NOT_NULL("ERR1710", "Enter the movie director Name"),
	MOVIE_ID_NOT_FOUND("ERR1716", "Movie does not exists"),
	MOVIE_STATUS_NOTNULL("ERR1719", "Enter the Movie Status"),
	MOVIE_DURATION_NOTNULL("ERR1713", "Enter the Movie duration"),
	MOVIE_NAME_LENGTH_EXCEED("ERR1714", "Movie Name length should not exceed 50 characters"),
	MOVIE_NOT_FOUND("ERR1715", "Movie not found"),
	MOVIE_VISIBILITY_STATUS_NOTNULL("ERR1716", "Enter the Movie Visibility Status"),
	MOVIE_NAME_NOTFOUND("ERR1717", "Movie Name does not exists"),
	MOVIE_NOT_NULL("ERR1718", "Enter Movie Mandatory Fileds"),
	MOVIE_ID_NOT_NULL("ERR1719","Movie Unique Identity is missing"),
	MOVIE_LANGUAGE_NOT_NULL("ERR1722","Enter the movie language"),
	
	SCREEN_SHOW_ALREADY_EXIST("ERR1803", "Screen Show has already exist"),
	SCREEN_SHOW_SCREEN_ID_NOT_NULL("ERR1802", "Screen details not found"),
	SCREEN_SHOW_MOVIE_ID_NOT_NULL("ERR1804", "Movie details not found"),
	SCREEN_SHOW_SHOW_ID_NOT_NULL("ERR1804", "Show details not found"),
	SCREEN_SHOW_STATUS_NOT_NULL("ERR1805", "Enter screen show status"),
	SCREEN_SHOW_START_DATE_NOT_NULL("ERR1881", "Enter screen show start date"),
	SCREEN_SHOW_END_DATE_NOT_NULL("ERR1880", "Enter screen show end date"),
	SCREEN_SHOW_DATE_VALID("ERR1882", "Show end date should be greater than start date"),
	SCREEN_SHOW_NOT_FOUND("ERR1815", "Screen Show not found"),
	SCREEN_SHOW_NOT_NULL("ERR1818", "Enter Screen Show Mandatory Fileds"),
	
	SCHEDULE_SCREEN_ALREADY_EXIST("ERR1902", "This screen show already scheduled"),
	SCHEDULE_SCREEN_SHOW_NOT_NULL("ERR1901", "Screen show not be empty"),
	SCHEDULE_SCREEN_SHOW_STATUS_NOT_NULL("ERR1917", "Enter screen show scheduled status"),
	SCHEDULE_SCREEN_PERSONAL_MAIL_ID_NOT_NULL("ERR1919", "Enter employee personal mail id "),
	SCHEDULE_SCREEN_SHOW_NOT_FOUND("ERR1915", "Screen show not found"),
	SCHEDULE_SCREEN_SHOW_DATE_NOT_NULL("ERR1917", "Enter screen show scheduled date"),

	USER_MASTER_USER_ID_NOT_NULL("ERR2001", "Enter login user ID"),
	USER_MASTER_PASSWORD_NOT_NULL("ERR2002", "Enter login passoword"),
	USER_MASTER_USER_NAME_NOT_NULL("ERR2003", "Enter user name"),
	USER_MASTER_USER_TYPE_NOT_NULL("ERR2006", "Enter login user type "),
	USER_MASTER_USER_STATUS_NOT_NULL("ERR2007", "Enter login user status"),
	USER_MASTER_DETAIL_ALREADY_EXIST("ERR2011", "Login user detail has already exist"),	
	USER_MASTER_NOT_FOUND("ERR1912", "Login user details not found"),
	USER_MASTER_NOT_NULL("ERR1915", "Enter user master Mandatory Fileds"),
	
	SCREEN_SEAT_ALREADY_EXIST("ERR3002", "This screen show already scheduled"),
	SCREEN_SEAT_SHOW_NOT_NULL("ERR3001", "Screen show not be empty"),
	SCREEN_SEAT_SHOW_STATUS_NOT_NULL("ERR3017", "Enter screen seat status"),
	SCREEN_SEAT_BOOKING_PERSON_NAME_NOT_NULL("ERR3019", "Enter booking person name "),
	SCREEN_SEAT_SHOW_NOT_FOUND("ERR3015", "Screen seat not found"),
	SCREEN_SEAT_NUMBER_NOT_NULL("ERR3017", "Enter screen seat number"),
	
	EMPLOYEE_CERTIFICATE_NAME_NOT_NULL("ERR2050", "Enter employee certificate name"),
	EMPLOYEE_CERTIFICATE_NO_NOT_NULL("ERR2051", "Enter employee certificate number"),
	EMPLOYEE_CERTIFICATE_ALREADY_EXIST("ERR2055", "Employment certificate has already exist"),
	
	EMPLOYEE_EXP_HIST_PREVIOUS_NAME_NOT_NULL("ERR2080", "Enter employee previous company/institute name"),
	EMPLOYEE_EXP_HIST_START_DATE_NOT_NULL("ERR2081", "Enter employee previous company/institute start date"),
	EMPLOYEE_EXP_HIST_END_DATE_NOT_NULL("ERR2080", "Enter employee previous company/institute end date"),
	EMPLOYEE_EXP_HIST_DESIGNATION_NOT_NULL("ERR2081", "Enter employee previous company/institute designation"),
	EMPLOYEE_EXP_HIST_ALREADY_EXIST("ERR2085", "Employee Experiance history has already exist"),
	
	BRANCH_CODE_ALREADY_EXIST("ERR2102", "Branch Code has already exist"),
	BRANCH_NAME_ALREADY_EXIST("ERR2103", "Branch Name has already exist"),
	BRANCH_CODE_INVALID("ERR2104", "Enter the valid Branch Code"),
	BRANCH_NAME_INVALID("ERR2105", "Enter the valid Branch Name"),
	BRANCH_ID_NOT_FOUND("ERR2106", "Branch does not exists"),
	BRANCH_CODE_NOT_NULL("ERR2107", "Enter the Branch Code"),
	BRANCH_NAME_NOT_NULL("ERR2108", "Enter the Branch Name"),
	BRANCH_VISIBILITY_STATUS_NOTNULL("ERR2112", "Enter the Branch Visibility Status"),
	BRANCH_CODE_LENGTH_EXCEED("ERR2113", "Branch Code length should not exceed 10 characters"),
	BRANCH_NAME_LENGTH_EXCEED("ERR2114", "Branch Name length should not exceed 100 characters"),
	BRANCH_NOT_FOUND("ERR2115", "Branch not found"),
	BRANCH_CODE_NOTFOUND("ERR2116", "Branch Code does not exists"),
	BRANCH_NAME_NOTFOUND("ERR2117", "Branch Name does not exists"),
	BRANCH_NOT_NULL("ERR2118", "Enter Branch Mandatory Fileds"),
	BRANCH_ID_NOT_NULL("ERR2119","Branch Unique Identity is missing"),
	BRANCH_OPEN_DATE_NOT_NULL("ERR2121", "Enter the branch open date"),
	BRANCH_ADDRESS_NOT_NULL("ERR2122", "Enter the branch address"),
	BRANCH_PHONE_NO_NOT_NULL("ERR2123", "Enter the branch phone number"),
	BRANCH_EMAIL_NO_NOT_NULL("ERR2124", "Enter the branch email ID"),
	
	COURSE_CODE_ALREADY_EXIST("ERR2302", "Course Code has already exist"),
	COURSE_NAME_ALREADY_EXIST("ERR2303", "Course Name has already exist"),
	COURSE_CODE_INVALID("ERR2304", "Enter the valid Course Code"),
	COURSE_NAME_INVALID("ERR2305", "Enter the valid Course Name"),
	COURSE_ID_NOT_FOUND("ERR2306", "Course ID does not exists"),
	COURSE_CODE_NOT_NULL("ERR2307", "Enter the Course Code"),
	COURSE_NAME_NOT_NULL("ERR2308", "Enter the Course Name"),
	COURSE_VISIBILITY_STATUS_NOTNULL("ERR2312", "Enter the Course Visibility Status"),
	COURSE_CODE_LENGTH_EXCEED("ERR2313", "Course Code length should not exceed 10 characters"),
	COURSE_NAME_LENGTH_EXCEED("ERR2314", "Course Name length should not exceed 100 characters"),
	COURSE_NOT_FOUND("ERR2315", "Course not found"),
	COURSE_CODE_NOTFOUND("ERR2316", "Course Code does not exists"),
	COURSE_NAME_NOTFOUND("ERR2317", "Course Name does not exists"),
	COURSE_NOT_NULL("ERR2318", "Enter Course Mandatory Fileds"),
	COURSE_ID_NOT_NULL("ERR2319","Course Unique Identity is missing"),
	COURSE_ID_FOREIGN_KEY("ERR2322","Course Details not available in Master, So Cannot add or update a child row"),
	COURSE_DURATION_YEAR_NOT_NULL("ERR2324", "Enter the Course duration of year"),
	COURSE_DURATION_MONTH_NOT_NULL("ERR2325", "Enter the Course duration of month"),
	COURSE_DURATION_NOT_ZERO("ERR2326", "Course duration should not be zero"),
	VARIANT_ALSO_NEGOTIATES("Test",  "Variant Also Negotiates");

	@Getter
	private final String code;
	@Getter
	private final String description;

	ErrorCode(String code, String description) {
		this.code = code;
		this.description = description;
	}


	

}
