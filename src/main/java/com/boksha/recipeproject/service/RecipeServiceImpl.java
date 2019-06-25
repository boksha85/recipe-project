package com.boksha.recipeproject.service;

import com.boksha.recipeproject.commands.RecipeCommand;
import com.boksha.recipeproject.converters.RecipeCommandToRecipe;
import com.boksha.recipeproject.converters.RecipeToRecipeCommand;
import com.boksha.recipeproject.domain.Recipe;
import com.boksha.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
  private final RecipeRepository recipeRepository;
  private final RecipeCommandToRecipe recipeCommandToRecipe;
  private final RecipeToRecipeCommand recipeToRecipeCommand;

  public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand)
  {
    this.recipeRepository = recipeRepository;
    this.recipeCommandToRecipe = recipeCommandToRecipe;
    this.recipeToRecipeCommand = recipeToRecipeCommand;
  }

  @Override
  public Set<Recipe> getRecipes() {
    log.debug("I'm in the service");
    Set<Recipe> recipeSet = new HashSet<>();
    recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
    return recipeSet;
  }

  @Override
  public Recipe findById(Long id)
  {
    return recipeRepository.findById(id).orElse(null);
  }

  @Override
  public RecipeCommand findCommandById(Long l)
  {
    return recipeToRecipeCommand.convert(findById(l));
  }

  @Override
  @Transactional
  public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand)
  {
    Recipe savedRecipe = recipeRepository.save(recipeCommandToRecipe.convert(recipeCommand));

    log.debug("Saved Recipe ID: " + savedRecipe.getId());

    return recipeToRecipeCommand.convert(savedRecipe);
  }
}
