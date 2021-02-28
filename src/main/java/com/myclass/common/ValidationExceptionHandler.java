package com.myclass.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handle validation exception
 */
@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// Get BindingResult object which manages all the system validation errors
		BindingResult bindingResult = ex.getBindingResult();
		// Get error list
		List<FieldError> errors = bindingResult.getFieldErrors();
		List<String> listErrors = errors.stream()
				.map(error -> error.getDefaultMessage()) // Convert from fieldError to string
				.collect(Collectors.toList());
		
		// Create response body
		Map<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		body.put("errors", listErrors);
		
		return new ResponseEntity<Object>(body,HttpStatus.BAD_REQUEST);
	}

}
