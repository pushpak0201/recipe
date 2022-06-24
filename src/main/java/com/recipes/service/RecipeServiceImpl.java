package com.recipes.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;
import com.recipes.dto.RecipeDTO;
import com.recipes.exception.InstanceCreationException;
import com.recipes.exception.RecordNotFoundException;
import com.recipes.model.Recipe;
import com.recipes.repository.RecipeDao;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	
	 @Autowired
	 RecipeDao recipeDao;
	 
	 @Autowired
	 private ModelMapper modelMapper;
	 
	private static final Logger logger = LogManager.getLogger(RecipeServiceImpl.class);

	/**
	 * @author knatavar
	 * This Method Saving Data using DTO to map the class and store in Entity.
	 * @param recipeDto
	 * @return recipeDto
	 */
	@Transactional
	@Override
	public RecipeDTO createRecipe(RecipeDTO recipeDto) throws InstanceCreationException {
		 Recipe recipe = modelMapper.map(recipeDto, Recipe.class);
	        if (Objects.isNull(recipe)) {
	            logger.error("Recipe Instance creation exception");
	            throw new InstanceCreationException("Recipe Object creation exception");
	        }
	        logger.debug("Request mapped to Entity: {}", Boolean.TRUE);
	        recipeDto = modelMapper.map(recipeDao.save(recipe), RecipeDTO.class);
	        logger.debug("Entity mapped to Response: {}", Boolean.TRUE);
	        return recipeDto;
	}

	/**
	 * 
	 * @author knatavar
	 * This Method Updating Data using DTO to map the class.
	 * 
	 * @param recipeDto
	 * @return recipeDto
	 * 
	 */
	@Transactional
	@Override
	public RecipeDTO updateRecipe(RecipeDTO recipeDto) throws RecordNotFoundException {
		Optional<Recipe> recipe = recipeDao.findById(recipeDto.getId());
        logger.debug("Recipe found for update {}", recipe.isPresent());
        if (!recipe.isPresent()) {
            throw new RecordNotFoundException("No Recipe record exist for given id");
        }
        Recipe updatedEntity = modelMapper.map(recipeDto, Recipe.class);
        logger.debug("Request mapped for Entity: {}", Boolean.TRUE);
        recipeDto = modelMapper.map(recipeDao.save(updatedEntity), RecipeDTO.class);
        logger.debug("Entity mapped for Response: {}", Boolean.TRUE);
        return recipeDto;
	}

	/**
	 * @author knatavar
	 * 
	 * This Method featching all Data of Recipe
	 * 
	 * @return all Recipe records
	 * 
	 */
	@Transactional
	@Override
	public List<RecipeDTO> getAllRecipes() {
		  List<Recipe> recipe = recipeDao.findAll();
	        logger.debug("{} Recipe(s) found.", recipe.size());
	        Type  type = new TypeToken<List<RecipeDTO>>() {
	        }.getType();
	        return modelMapper.map(recipe, type);
	}

	/**
	 * @author knatavar
	 * This Method get the Recipe details from the particular ID.
	 * 
	 * @param id
	 * @return the Recipe details
	 */
	@Transactional
	@Override
	public RecipeDTO getRecipeById(Long id) throws RecordNotFoundException {
		   Optional<Recipe> recipe = recipeDao.findById(id);
	        logger.debug("Recipe found {}", recipe.isPresent());
	        return modelMapper.map(
	        		recipe.orElseThrow(() -> new RecordNotFoundException("Recipe not found with id: " + id))
	                , RecipeDTO.class);
	}

	/**
	 * @author knatavar
	 * 
	 * This Method delete the record for provided Id
	 * 
	 * @param id
	 */
	@Transactional
	@Override
	public void deleteRecipeById(Long id) throws RecordNotFoundException {
		  Optional<Recipe> recipe = recipeDao.findById(id);
	        logger.debug("Recipe found to delete {}", recipe.isPresent());
	        recipeDao.deleteById(recipe.orElseThrow(
	                () -> new RecordNotFoundException("No Recipe record exist for given id " + id)
	        ).getId());
		
	}

}
