package com.boksha.recipeproject.controllers;

import com.boksha.recipeproject.domain.Recipe;
import com.boksha.recipeproject.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * todo: class comment
 *
 * @author bsutulovic
 */
public class RecipeControllerTest
{

  @Mock
  RecipeService recipeService;

  RecipeController recipeController;

  @Before
  public void setUp() throws Exception
  {
    MockitoAnnotations.initMocks(this);
    recipeController = new RecipeController(recipeService);
  }

  @Test
  public void showById() throws Exception
  {
    Recipe recipe = new Recipe();
    recipe.setId(1L);
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    Mockito.when(recipeService.findById(Mockito.anyLong())).thenReturn(recipe);

    mockMvc.perform(get("/recipe/show/1"))
      .andExpect(status().isOk())
      .andExpect(view().name("recipe/show"))
      .andExpect(model().attributeExists("recipe"));
  }
}