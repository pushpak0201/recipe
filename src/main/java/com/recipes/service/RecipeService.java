package com.recipes.service;

import java.util.List;

import com.recipes.dto.RecipeDTO;
import com.recipes.exception.InstanceCreationException;
import com.recipes.exception.RecordNotFoundException;

public interface RecipeService {
		
		public RecipeDTO createRecipe(RecipeDTO recipeDto) throws InstanceCreationException;
	    public RecipeDTO updateRecipe(RecipeDTO recipeDto) throws RecordNotFoundException;
	    public List<RecipeDTO> getAllRecipes();
	    public RecipeDTO getRecipeById(Long id) throws RecordNotFoundException;
	    public void deleteRecipeById(Long id) throws RecordNotFoundException;
}
