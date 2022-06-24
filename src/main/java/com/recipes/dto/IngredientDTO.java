package com.recipes.dto;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class IngredientDTO {
	
	 private Long id;

	    @Length(min = 2, message = "The field must be having minimum 2 characters")
	    @Length(max = 60, message = "The field having maximum 60 characters")
	    private String name;

	    @Length(max = 10, message = "The field maximum 10 characters")
	    private String quantity;
}
