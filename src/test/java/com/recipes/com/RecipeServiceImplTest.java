package com.recipes.com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipes.dto.RecipeDTO;
import com.recipes.model.Ingredient;
import com.recipes.model.Recipe;
import com.recipes.repository.RecipeDao;
import com.recipes.service.RecipeService;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeServiceImplTest {

	
	 @MockBean
	 RecipeDao recipeDao;
	 
	 @Autowired
	 private RecipeService recipeService;
	 
	 @Autowired
	 private ObjectMapper objectMapper;
	 
	 
	 Recipe recipe;
	 Ingredient ingredient;
	 RecipeDTO recipeDTO;
	 
	 
	 	@BeforeEach
	    public void setUp() throws IOException {
	        recipeDTO = getRecipeDTO();
	        recipe = new Recipe();
	        recipe.setId(recipeDTO.getId());
	        recipe.setName(recipeDTO.getName());
	        ingredient = new Ingredient();
	        ingredient.setName("Oil");
	        ingredient.setId(recipeDTO.getId());
	        Set<Ingredient> ngredients = new HashSet<>();
	        ngredients.add(ingredient);
	    }
	 	
	 	 RecipeDTO getRecipeDTO() throws IOException {
	         return objectMapper.readValue(ResourceUtils.getFile("./src/test/resources/request.json"), RecipeDTO.class);
	     }
	 	 
	 	 @Test
	     void creatRecipeTest() throws Exception {
	         Mockito.when(recipeDao.save(Mockito.any())).thenReturn(recipe);
	         RecipeDTO responseDTO = recipeService.createRecipe(recipeDTO);
	         assertEquals("KajuKhoya", responseDTO.getName());
	     }
	 	 
	 	 @Test
	     void updateRecipeTest() throws Exception {
	         Mockito.when(recipeDao.findById(Mockito.anyLong())).thenReturn(Optional.of(recipe));
	         Mockito.when(recipeDao.save(Mockito.any())).thenReturn(recipe);
	         RecipeDTO responseDTO = recipeService.updateRecipe(recipeDTO);
	         assertEquals("KajuKhoya", responseDTO.getName());
	     }
	 	 
	 	 
	 	 @Test
	     void getAllRecipeTest() throws Exception {
	         List<Recipe> listRecipe = new ArrayList<>();
	         listRecipe.add(recipe);
	         Mockito.when(recipeDao.findAll()).thenReturn(listRecipe);
	         List<RecipeDTO> listRecipeDTO = recipeService.getAllRecipes();
	         assertTrue(listRecipeDTO.size() > 0);
	     }

	 	@Test
	    void getRecipeByIDTest() throws Exception {
	        Mockito.when(recipeDao.findById(Mockito.anyLong())).thenReturn(Optional.of(recipe));
	        RecipeDTO responseDTO = recipeService.getRecipeById(1l);
	        assertEquals("KajuKhoya", responseDTO.getName());
	    }
	 	
	 	 @Test
	     void deleteRecipeTest() throws Exception {
	         Mockito.when(recipeDao.findById(Mockito.anyLong())).thenReturn(Optional.of(recipe));
	         recipeDao.deleteById(1l);
	         recipeService.deleteRecipeById(1l);
	         assertTrue(true);
	     }
}
