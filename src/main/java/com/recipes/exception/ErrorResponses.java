package com.recipes.exception;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement(name = "error")
@Data
public class ErrorResponses {

	    //General error message 
	    private String message;

	    //Specific errors in API while processing
	    private List<String> details;

	    public ErrorResponses(String message, List<String> details) {
	        super();
	        this.message = message;
	        this.details = details;
	    }
}
