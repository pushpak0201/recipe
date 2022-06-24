package com.recipes.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedException extends ResponseEntityExceptionHandler  {

	
	    @ExceptionHandler(InstanceCreationException.class)
	    public ResponseEntity<String> handleObjectExceptions(InstanceCreationException exception) {
	        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	        return entity;
	    }


		 @ExceptionHandler(RecordNotFoundException.class)
		 public ResponseEntity<String> handleRecordExceptions(RecordNotFoundException exception) {
		        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
		        return entity;
		    }	 

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleExceptions(Exception exception) {
	        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	        return entity;
	    }

	    @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
	            details.add(error.getDefaultMessage());
	        }
	        ErrorResponses error = new ErrorResponses("Validation Failed", details);

	        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	    }
}
