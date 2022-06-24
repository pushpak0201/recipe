package com.recipes.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recipes.model.Ingredient;

import lombok.Data;

@Data
public class RecipeDTO {
	 	
	 private Long id;


	    @Length(min = 5, message = "The field must be having minimum 5 characters")
	    @Length(max = 50, message = "The field having maximum 50 characters")
	    private String name;


	    private Float price;

	    private String type;

	    @NotNull(message = "Recipe Cooking instructions are required")
	    @NotBlank(message = "Recipe Cooking instructions  must be of length between 1 to 1500")
	    @Size(min = 1, max = 1500, message = "Recipe Cooking instructions must be length between 1 to 1500")
	    private String cookingInstructions;

	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd‐MM‐yyyy HH:mm")
	    private Date creationDate;

	    @NotNull(message = "Recipe Ingredients required")
	    @NotEmpty(message = "Recipe Ingredients Required")
	    private List<Ingredient> ingredients;
}
