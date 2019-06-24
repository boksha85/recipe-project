package com.boksha.recipeproject.converters;

import com.boksha.recipeproject.commands.CategoryCommand;
import com.boksha.recipeproject.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * todo: class comment
 *
 * @author bsutulovic
 */
public class CategoryCommandToCategoryTest
{

  private final long ID_VALUE = new Long(1L);
  private final String DESCRIPTION = "description";

  CategoryCommandToCategory converter;

  @Before
  public void setUp() throws Exception
  {
    converter = new CategoryCommandToCategory();
  }

  @Test
  public void testNullObject() throws Exception
  {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() throws Exception
  {
    assertNotNull(converter.convert(new CategoryCommand()));
  }


  @Test
  public void convert()
  {
    //given
    CategoryCommand source = new CategoryCommand();
    source.setId(ID_VALUE);
    source.setDescription(DESCRIPTION);

    //when
    Category category = converter.convert(source);

    //then
    assertEquals(ID_VALUE, category.getId());
    assertEquals(DESCRIPTION, category.getDescription());


  }
}