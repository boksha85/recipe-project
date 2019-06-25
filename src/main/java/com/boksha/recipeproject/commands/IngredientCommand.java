package com.boksha.recipeproject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * todo: class comment
 *
 * @author bsutulovic
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand
{
  private Long id;
  private String description;
  private BigDecimal amount;
  private UnitOfMeasureCommand uom;
}
