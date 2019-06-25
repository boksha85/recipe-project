package com.boksha.recipeproject.controllers;

import com.boksha.recipeproject.commands.RecipeCommand;
import com.boksha.recipeproject.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

  @RequestMapping("/recipe/{id}/show")
  public String showById(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findById(new Long(id)));
    return "recipe/show";
  }

  @RequestMapping("/recipe/new")
  public String newRecipe(Model model) {
    model.addAttribute("recipe", new RecipeCommand());
    return "recipe/recipeform";
  }

  @RequestMapping("/recipe/{id}/update")
  public String updateRecipe(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
    return "recipe/recipeform";
  }

  //@RequestMapping(name="recipe", method = RequestMethod.POST) old way
  @PostMapping
  @RequestMapping("recipe")
  public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
    RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);
    return "redirect:/recipe/" + savedRecipe.getId()+ "/show";
  }


}
