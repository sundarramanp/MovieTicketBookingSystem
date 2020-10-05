package com.online.movie.ticket.exception;


import lombok.Data;

@Data
public class ExceptionHelper {

	String tableName; // Table Which Causes the problem.
	String columnName; // Column Which Causes the problem.
	String fkReference; // Foreign key reference In Case of Delete
	String ukReference; // Unique Key
	String message;
	String duplicateValue;
	String help;

	public ExceptionHelper(Exception exception) {

		this.message = exception.getCause().getCause().getMessage();

		if (message.startsWith("Duplicate")) {
			// For DataIntegrityViolationException - UK Problem
			String duplicateInputValue = message.split("'")[1];

			if (duplicateInputValue.contains("-")) {
				duplicateInputValue = duplicateInputValue.split("-")[1];
			}

			setDuplicateValue(duplicateInputValue);
			setUkReference(message.split("'")[2]);
		} else if (message.startsWith("Cannot delete")) {
			// For DataIntegrityViolationException - FK Problem
			String[] d = message.split("`");

			setTableName(d[3]);
			setFkReference(d[5]);
			setColumnName(d[7]);
			setHelp(help);
		}

	}

}
