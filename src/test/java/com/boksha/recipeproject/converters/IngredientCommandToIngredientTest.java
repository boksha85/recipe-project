package com.boksha.recipeproject.converters;

import com.boksha.recipeproject.commands.IngredientCommand;
import com.boksha.recipeproject.commands.UnitOfMeasureCommand;
import com.boksha.recipeproject.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * todo: class comment
 *
 * @author bsutulovic
 */
public class IngredientCommandToIngredientTest
{
  private static final Long ID = new Long(1L);
  private static final String DESCRIPTION = "Cheeseburger";
  private static final BigDecimal AMOUNT = new BigDecimal(4);

  private static final Long ID_UOM = new Long(1L);

  IngredientCommandToIngredient converter;

  @Before
  public void setUp() throws Exception
  {
    converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
  }

  @Test
  public void testNullObject() throws Exception
  {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() throws Exception
  {
    assertNotNull(converter.convert(new IngredientCommand()));
  }

  @Test
  public void convert()
  {
    //given
    IngredientCommand source = new IngredientCommand();
    source.setId(ID);
    source.setDescription(DESCRIPTION);
    source.setAmount(AMOUNT);

    UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
    unitOfMeasureCommand.setId(ID_UOM);

    source.setUom(unitOfMeasureCommand);

    //when
    Ingredient ingredient = converter.convert(source);

    //then
    assertNotNull(ingredient);
    assertNotNull(ingredient.getUom());
    assertEquals(ID, ingredient.getId());
    assertEquals(DESCRIPTION, ingredient.getDescription());
    assertEquals(AMOUNT, ingredient.getAmount());
    assertEquals(ID_UOM, ingredient.getUom().getId());
  }

  @Test
  public void convertWithNullUOM() throws Exception
  {
    //then
    IngredientCommand source = new IngredientCommand();
    source.setId(ID);
    source.setDescription(DESCRIPTION);
    source.setAmount(AMOUNT);

    //when
    Ingredient ingredient = converter.convert(source);

    //then
    assertNotNull(ingredient);
    assertNull(ingredient.getUom());
    assertEquals(ID, ingredient.getId());
    assertEquals(AMOUNT, ingredient.getAmount());
    assertEquals(DESCRIPTION, ingredient.getDescription());
  }
}