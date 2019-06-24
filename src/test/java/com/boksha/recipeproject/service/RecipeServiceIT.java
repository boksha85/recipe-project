package com.boksha.recipeproject.service;

import com.boksha.recipeproject.commands.RecipeCommand;
import com.boksha.recipeproject.converters.RecipeCommandToRecipe;
import com.boksha.recipeproject.converters.RecipeToRecipeCommand;
import com.boksha.recipeproject.domain.Recipe;
import com.boksha.recipeproject.repositories.RecipeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by jt on 6/21/17.
 * use SpringBootTest to bring entire context (@Autowired)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT
{

    private static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        Assert.assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        Assert.assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        Assert.assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        Assert.assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }
}
