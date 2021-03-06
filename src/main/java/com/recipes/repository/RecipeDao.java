package com.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recipes.model.Recipe;

@Repository
public interface RecipeDao extends JpaRepository<Recipe, Long>{

}
