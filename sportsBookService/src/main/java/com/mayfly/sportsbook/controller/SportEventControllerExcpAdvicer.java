package com.mayfly.sportsbook.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mayfly.sportsbook.exception.SportbookResourceNotFound;

@ControllerAdvice
public class SportEventControllerExcpAdvicer extends ResponseEntityExceptionHandler {

	public static final String EVNT_RESRC_NOT_FOUND = "Requested event resource not found, Please check the event id";

	@ExceptionHandler(SportbookResourceNotFound.class)
	public ResponseEntity<Object> handleSportbookResourceNotFound(SportbookResourceNotFound ex) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", EVNT_RESRC_NOT_FOUND);

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	/*
	 * can add for all the exception we encounter and throw exact status code to the
	 * client.
	 */

}