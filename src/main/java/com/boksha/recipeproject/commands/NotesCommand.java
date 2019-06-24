package com.boksha.recipeproject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * todo: class comment
 *
 * @author bsutulovic
 */
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand
{
  private Long id;
  private String recipeNotes;
}
