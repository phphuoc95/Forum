package com.nobj.forum.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@ControllerAdvice
public class GlobalApiExceptionHandler {
	
	@ExceptionHandler(value = {CustomApiRequestException.class})
	public ResponseEntity<?> apiRequestExceptionHandling(CustomApiRequestException e, WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(400);
		errorDetails.setError("Bad Request");
		errorDetails.setMessage(e.getMessage());
		errorDetails.setTimestamp(new Date());
		errorDetails.setDescription(webRequest.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<?> methodArgumentNotValidExceptionHandling(MethodArgumentNotValidException e, WebRequest webRequest){
		
		final List<String> errors = new ArrayList<String>();
		
        for (final FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        
        for (final ObjectError error : e.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        
        String errorStr = errors.toString()
        		.replaceAll("\\[|\\]|", "")
        		.replace(",", ";");
		
		final ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(400);
		errorDetails.setError("Validation Error");
		errorDetails.setMessage(errorStr);
		errorDetails.setDescription(webRequest.getDescription(true));
		errorDetails.setTimestamp(new Date());
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value = {TypeMismatchException.class, MethodArgumentTypeMismatchException.class})
	public ResponseEntity<?> methodArgumentNotValidExceptionHandling(TypeMismatchException e, WebRequest webRequest){
		String requiredType = e.getRequiredType().
				toString().substring(
						e.getRequiredType().
						toString().lastIndexOf(".") + 1);
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(400);
		errorDetails.setError("Bad Value");
		errorDetails.setMessage("Value " + "'" + e.getValue() + "'" + " should be of type: " + requiredType);
		errorDetails.setDescription(webRequest.getDescription(true));
		errorDetails.setTimestamp(new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<?> globalExceptionHandling(Exception e, WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(500);
		errorDetails.setError("Internal Server");
		errorDetails.setMessage("Unknown error");
		errorDetails.setDescription(webRequest.getDescription(true));
		errorDetails.setTimestamp(new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {AccessDeniedException.class})
	public ResponseEntity<?> AccessDeniedExceptionHandling(AccessDeniedException e, WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(403);
		errorDetails.setError("Access Denied");
		errorDetails.setMessage(e.getLocalizedMessage());
		errorDetails.setDescription(webRequest.getDescription(true));
		errorDetails.setTimestamp(new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = {BadCredentialsException.class})
	public ResponseEntity<?> badCredentialsExceptionHandling(BadCredentialsException e, WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(401);
		errorDetails.setError("Unauthorize");
		errorDetails.setMessage(e.getLocalizedMessage());
		errorDetails.setDescription(webRequest.getDescription(true));
		errorDetails.setTimestamp(new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}
	
	
	@ExceptionHandler(value = {NoHandlerFoundException.class})
	public ResponseEntity<?> noHandlerFoundExceptionHadling(NoHandlerFoundException e, WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(404);
		errorDetails.setError("No Handler Found");
		errorDetails.setMessage("The request handler was not found");
		errorDetails.setDescription(webRequest.getDescription(true));
		errorDetails.setTimestamp(new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {NonTransientDataAccessException.class})
	public ResponseEntity<?> nonTransientDataAccessExceptionHandling(NonTransientDataAccessException e, WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(400);
		errorDetails.setError("Failed action");
		errorDetails.setMessage("Non transient data");
		errorDetails.setDescription(webRequest.getDescription(true));
		errorDetails.setTimestamp(new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value = {EmptyResultDataAccessException.class})
	public ResponseEntity<?> emptyResultDataAccessExceptionHadling(EmptyResultDataAccessException e, WebRequest webRequest){
		
		String error = e.getLocalizedMessage();
		int lastIndexOfPoint = error.lastIndexOf(".");
		String message = error.substring(lastIndexOfPoint + 1);
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(400);
		errorDetails.setError("Empty data");
		errorDetails.setMessage("No " + message);
		errorDetails.setDescription(webRequest.getDescription(true));
		errorDetails.setTimestamp(new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {EntityNotFoundException.class})
	public ResponseEntity<?> entityNotFoundExceptionHandling(EntityNotFoundException e, WebRequest webRequest){
		int lastIndexPoint = e.getLocalizedMessage().lastIndexOf(".") + 1;
		String errorString = e.getLocalizedMessage().substring(lastIndexPoint);
				
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(404);
		errorDetails.setError("Not found");
		errorDetails.setMessage("Not found " + errorString);
		errorDetails.setDescription(webRequest.getDescription(true));
		errorDetails.setTimestamp(new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(value = {ConstraintViolationException.class, DataIntegrityViolationException.class})
	public ResponseEntity<?> dataIntegrityViolationExceptionHandling(ConstraintViolationException e, WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(400);
		errorDetails.setError("Constraint Violation");
		errorDetails.setMessage(e.getSQLException().getLocalizedMessage());
		errorDetails.setDescription(webRequest.getDescription(true));
		errorDetails.setTimestamp(new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
}
