package com.recipes.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipes.dto.RecipeDTO;
import com.recipes.exception.InstanceCreationException;
import com.recipes.exception.RecordNotFoundException;
import com.recipes.service.RecipeService;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
	
	private static final Logger logger = LogManager.getLogger(RecipeController.class);
	
	@Autowired
	RecipeService  recipeService;
	
	/**
	 * @author knatavar
	 * This Method is responsible for creating Recipe
	 * @param recipeDTO
	 * @return
	 * @throws InstanceCreationException
	 */
	@PostMapping("/create")
    public ResponseEntity<RecipeDTO> createRecipe(@Valid @RequestBody RecipeDTO recipeDTO,BindingResult bindingResult)throws InstanceCreationException {
        RecipeDTO addingRecipeDTO = recipeService.createRecipe(recipeDTO);
        logger.info("Recipe record created ");
        return new ResponseEntity<RecipeDTO>(addingRecipeDTO, new HttpHeaders(), HttpStatus.CREATED);
    }
	
	/**
	 * @author knatavar
	 * This Method is responsible for update Recipe
	 * @param recipe
	 * @return
	 * @throws RecordNotFoundException
	 */
	@PutMapping("/update")
	  public ResponseEntity<RecipeDTO> updateRecipe(@Valid @RequestBody RecipeDTO recipe, BindingResult bindingResult)throws RecordNotFoundException {
		 RecipeDTO updatedRecipeDTO = recipeService.updateRecipe(recipe);
	     logger.info("Recipe record Updated ");
	     return new ResponseEntity<RecipeDTO>(updatedRecipeDTO, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * @author knatavar
	 * This Method is get all list of recipe
	 * @return
	 */
	@GetMapping("/getAll")
	 public ResponseEntity<List<RecipeDTO>> getAllRecipe() {
		List<RecipeDTO> listRecipe = recipeService.getAllRecipes();
		logger.info("Recipe list total Size {}",listRecipe.size());
		return new ResponseEntity<List<RecipeDTO>>(listRecipe, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * @author knatavar
	 * This Method is responsible for getting recipe by Id
	 * @param id
	 * @return
	 * @throws RecordNotFoundException
	 */
	@GetMapping("/getById/{id}")
	public ResponseEntity<RecipeDTO> getRecipe(@PathVariable("id") Long id)throws RecordNotFoundException{
		 RecipeDTO recipeDTO = recipeService.getRecipeById(id);
		 return new ResponseEntity<RecipeDTO>(recipeDTO, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * @author knatavar
	 * This Method is responsible for deleteing recipe by Id
	 * @param id
	 * @return
	 * @throws RecordNotFoundException
	 */
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteRecipe(@PathVariable("id") Long id)throws RecordNotFoundException{
		recipeService.deleteRecipeById(id);
		logger.info("Recipe record deleted id{}" , id);
		return new ResponseEntity<String>("Record deleted Successfully.", new HttpHeaders(), HttpStatus.OK);
	}
}
