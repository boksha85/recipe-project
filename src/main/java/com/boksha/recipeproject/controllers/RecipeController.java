package com.boksha.recipeproject.controllers;

import com.boksha.recipeproject.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * todo: class comment
 *
 * @author bsutulovic
 */
@Slf4j
@Controller
public class RecipeController {
  private RecipeService recipeService;

  public RecipeController(RecipeService recipeService)
  {
    this.recipeService = recipeService;
  }

  @RequestMapping("/recipe/show/{id}")
  public String showById(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findById(new Long(id)));
    return "recipe/show";
  }
}
