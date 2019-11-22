package com.boksha.recipeproject.service;

import com.boksha.recipeproject.commands.IngredientCommand;
import com.boksha.recipeproject.converters.IngredientToIngredientCommand;
import com.boksha.recipeproject.domain.Recipe;
import com.boksha.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * todo: class comment
 *
 * @author bsutulovic
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService
{

  private final RecipeRepository recipeRepository;
  private final IngredientToIngredientCommand ingredientToIngredientCommand;

  public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand)
  {
    this.recipeRepository = recipeRepository;
    this.ingredientToIngredientCommand = ingredientToIngredientCommand;
  }

  @Override
  public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId)
  {
    Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

    if(!recipeOptional.isPresent()) {
      //todo implement error handling
      log.error("recipe id not found. Id: " + recipeId);
    }

    Recipe recipe = recipeOptional.get();

    Optional<IngredientCommand> ingredientCommandOptional =
      recipe.getIngredients()
        .stream()
      .filter(ingredient -> ingredient.getId().equals(ingredientId))
      .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
        .findFirst();

    if(!ingredientCommandOptional.isPresent()) {
      //todo impl error handling
      log.error("Ingredient id not found. Id: " + ingredientId);
    }
    return ingredientCommandOptional.get();
  }
}
