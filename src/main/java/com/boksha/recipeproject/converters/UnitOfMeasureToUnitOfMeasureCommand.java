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
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand>
{
  @Synchronized
  @Nullable
  @Override
  public UnitOfMeasureCommand convert(UnitOfMeasure source)
  {
    if (source == null) {
      return null;
    }
    final UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
    unitOfMeasureCommand.setId(source.getId());
    unitOfMeasureCommand.setDescription(source.getDescription());
    return unitOfMeasureCommand;
  }
}
