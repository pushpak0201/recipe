package com.recipes.com;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipes.dto.RecipeDTO;
import com.recipes.service.RecipeService;


@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerMockMvcTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    RecipeService recipeService;
    
    @Autowired
    private ObjectMapper objectMapper;


    String getRequestJson() throws IOException {
        return Files.readString(Path.of("./src/test/resources/request.json"));
    }

    RecipeDTO getRecipeDTO() throws IOException {
        return objectMapper.readValue(ResourceUtils.getFile("./src/test/resources/request.json"), RecipeDTO.class);
    }
    
    
    
    @Test
    void createRecipeTest() throws Exception {
        //Given
        RecipeDTO recipeDTO = getRecipeDTO();
        //When
        Mockito.when(recipeService.createRecipe(recipeDTO)).thenReturn(getRecipeDTO());
        ResultActions resultActions = mockMvc.perform(post("/recipe/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getRequestJson())
        );
        resultActions.andExpect(status().isCreated());
    }
    
    @Test
    void updateRecipeTest() throws Exception {
        //Given
        RecipeDTO recipeDTO = getRecipeDTO();
        //When
        Mockito.when(recipeService.updateRecipe(recipeDTO)).thenReturn(getRecipeDTO());
        ResultActions resultActions = mockMvc.perform(put("/recipe/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getRequestJson())
        );
        //Then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void getAllRecipesTest() throws Exception {
        //When
        Mockito.when(recipeService.getAllRecipes()).thenReturn(Stream.of(getRecipeDTO()).collect(Collectors.toList()));
        ResultActions resultActions = mockMvc.perform(get("/recipe/getAll"));
        //Then
        resultActions.andExpect(status().isOk());
    }
   
    @Test
    void getRecipeByIdTest() throws Exception {
        //Given
        Long recipeId = 1L;
        //When
        Mockito.when(recipeService.getRecipeById(recipeId)).thenReturn(getRecipeDTO());
        ResultActions resultActions = mockMvc.perform(get("/recipe/getById/{id}",1l));
        //Then
        resultActions.andExpect(status().isOk());
    }
    
    @Test
    void deleteRecipeTest() throws Exception {
        //Given
        Long recipeId = 1L;
        //When
        ResultActions resultActions = mockMvc.perform(delete("/recipe/delete/{id}", recipeId)
        );
        //Then
        resultActions.andExpect(status().isOk());
    }
    
}
