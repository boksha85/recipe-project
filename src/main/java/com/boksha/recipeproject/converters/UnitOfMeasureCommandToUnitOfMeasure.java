package com.boksha.recipeproject.converters;

import com.boksha.recipeproject.commands.UnitOfMeasureCommand;
import com.boksha.recipeproject.domain.UnitOfMeasure;
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
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure>
{
  //spring doesn't garant thread safe
  @Synchronized
  @Nullable
  @Override
  public UnitOfMeasure convert(UnitOfMeasureCommand source)
  {
    if (source == null) {
      return null;
    }
    final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
    unitOfMeasure.setId(source.getId());
    unitOfMeasure.setDescription(source.getDescription());
    return unitOfMeasure;
  }
}
