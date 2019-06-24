package com.boksha.recipeproject.converters;


import com.boksha.recipeproject.commands.CategoryCommand;
import com.boksha.recipeproject.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * todo: class comment
 *
 * @author bsutulovic
 */
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>
{
  @Synchronized
  @Nullable
  @Override
  public Category convert(CategoryCommand source)
  {
    if (source == null)
    {
      return null;
    }
    final Category category = new Category();
    category.setId(source.getId());
    category.setDescription(source.getDescription());
    return  category;
  }
}
