package com.boksha.recipeproject.service;

import com.boksha.recipeproject.commands.IngredientCommand;

/**
 * todo: class comment
 *
 * @author bsutulovic
 */
public interface IngredientService
{
  IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
