package com.boksha.recipeproject.service;

import com.boksha.recipeproject.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {
  Set<Recipe> getRecipes();
}