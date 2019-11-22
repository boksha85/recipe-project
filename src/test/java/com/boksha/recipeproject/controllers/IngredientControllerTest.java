package com.boksha.recipeproject.controllers;

import com.boksha.recipeproject.commands.IngredientCommand;
import com.boksha.recipeproject.commands.RecipeCommand;
import com.boksha.recipeproject.service.IngredientService;
import com.boksha.recipeproject.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * todo: class comment
 *
 * @author bsutulovic
 */
public class IngredientControllerTest
{
  @Mock
  RecipeService recipeService;

  @Mock
  IngredientService ingredientService;

  private IngredientController ingredientController;

  private MockMvc mockMvc;

  @Before
  public void setUp(){
    MockitoAnnotations.initMocks(this);

    ingredientController = new IngredientController(recipeService, ingredientService);
    mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
  }

  @Test
  public void testListIngredients() throws Exception {
    //given
    RecipeCommand recipeCommand = new RecipeCommand();
    Mockito.when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

    //when
    mockMvc.perform(get("/recipe/1/ingredients"))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.view().name("/recipe/ingredient/list"))
      .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));

    //then
    verify(recipeService, times(1)).findCommandById(anyLong());
  }

  @Test
  public void testShowIngredient() throws Exception {
    //given
    IngredientCommand ingredientCommand = new IngredientCommand();

    //when
    Mockito.when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

    //then
    mockMvc.perform(get("/recipe/1/ingredient/2/show"))
      .andExpect(status().isOk())
      .andExpect(view().name("recipe/ingredient/show"))
      .andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"));
  }
}
