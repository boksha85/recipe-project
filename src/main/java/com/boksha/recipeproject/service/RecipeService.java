package com.boksha.recipeproject.service;

import com.boksha.recipeproject.commands.RecipeCommand;
import com.boksha.recipeproject.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {
  Set<Recipe> getRecipes();
  Recipe findById(Long id);
  RecipeCommand findCommandById(Long l);
  RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
  void deleteById(Long l);

}
