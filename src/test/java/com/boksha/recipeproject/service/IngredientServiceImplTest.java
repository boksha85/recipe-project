package com.boksha.recipeproject.service;

import com.boksha.recipeproject.commands.IngredientCommand;
import com.boksha.recipeproject.converters.IngredientToIngredientCommand;
import com.boksha.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.boksha.recipeproject.domain.Ingredient;
import com.boksha.recipeproject.domain.Recipe;
import com.boksha.recipeproject.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * todo: class comment
 *
 * @author bsutulovic
 */
public class IngredientServiceImplTest
{

  private final IngredientToIngredientCommand ingredientToIngredientCommand;

  @Mock
  RecipeRepository recipeRepository;

  IngredientService ingredientService;

  //init converters
  public IngredientServiceImplTest()
  {
    this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
  }

  @Before
  public void setUp() throws Exception
  {
    MockitoAnnotations.initMocks(this);

    ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand);
  }

  @Test
  public void findByRecipeIdAndIngredientIdHappyPath()
  {
    //given
    Recipe recipe = new Recipe();
    recipe.setId(1L);

    Ingredient ingredient1 = new Ingredient();
    ingredient1.setId(1L);

    Ingredient ingredient2 = new Ingredient();
    ingredient2.setId(1L);

    Ingredient ingredient3 = new Ingredient();
    ingredient3.setId(3L);

    recipe.addIngredient(ingredient1);
    recipe.addIngredient(ingredient2);
    recipe.addIngredient(ingredient3);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    //then
    IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

    //when
    assertEquals(Long.valueOf(3L), ingredientCommand.getId());
    assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
    verify(recipeRepository, times(1)).findById(anyLong());
  }
}