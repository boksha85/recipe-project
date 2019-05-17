package com.boksha.recipeproject.controllers;

import com.boksha.recipeproject.domain.Recipe;
import com.boksha.recipeproject.repositories.RecipeRepository;
import com.boksha.recipeproject.service.RecipeService;
import com.boksha.recipeproject.service.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;

import static org.junit.Assert.*;

public class IndexControllerTest {

  @Mock
  RecipeService recipeService;

  @Mock
  Model model;

  IndexController indexController;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    indexController = new IndexController(recipeService);
  }

  @Test
  public void getIndexPage() {
    String returnedString = "index";
    String modelAttributeName = "recipes";
    String viewName = indexController.getIndexPage(model);

    assertEquals(returnedString, viewName);
    Mockito.verify(recipeService, Mockito.times(1)).getRecipes();
    //two examples
    Mockito.verify(model, Mockito.times(1)).addAttribute(modelAttributeName, new HashSet<Recipe>());
    Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq(modelAttributeName), Mockito.anySet());
  }
}