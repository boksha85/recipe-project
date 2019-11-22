package com.boksha.recipeproject.controllers;

import com.boksha.recipeproject.service.IngredientService;
import com.boksha.recipeproject.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * todo: class comment
 *
 * @author bsutulovic
 */
@Slf4j
@Controller
public class IngredientController
{
  private RecipeService recipeService;
  private IngredientService ingredientService;

  public IngredientController(RecipeService recipeService, IngredientService ingredientService)
  {
    this.recipeService = recipeService;
    this.ingredientService = ingredientService;
  }

  @GetMapping(value = "/recipe/{id}/ingredients")
  public String listIngredients(@PathVariable String id, Model model)
  {
    model.addAttribute("recipe", recipeService.findCommandById(new Long(id)));

    return "/recipe/ingredient/list";
  }

  @GetMapping(value = "/recipe/{recipeId}/ingredient/{ingredientId}/show")
  public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
    model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(new Long(recipeId), new Long(ingredientId)));

    return "recipe/ingredient/show";
  }
}
