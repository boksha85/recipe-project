package com.boksha.recipeproject.controllers;

import com.boksha.recipeproject.domain.Recipe;
import com.boksha.recipeproject.repositories.RecipeRepository;
import com.boksha.recipeproject.service.RecipeService;
import com.boksha.recipeproject.service.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

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
    //given
    Set<Recipe> recipeData= new HashSet<>();
    recipeData.add(new Recipe());

    Recipe recipe = new Recipe();
    recipe.setId(1L);
    recipeData.add(recipe);

    Mockito.when(recipeService.getRecipes()).thenReturn(recipeData);

    ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
    //when
    String returnedString = "index";
    String modelAttributeName = "recipes";
    String viewName = indexController.getIndexPage(model);

    //then
    assertEquals(returnedString, viewName);
    Mockito.verify(recipeService, Mockito.times(1)).getRecipes();

    Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq(modelAttributeName), Mockito.anySet());
          //use argument capture
    Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq(modelAttributeName), argumentCaptor.capture());
    Set<Recipe> setInController = argumentCaptor.getValue();
    assertEquals(2, setInController.size());
  }
}