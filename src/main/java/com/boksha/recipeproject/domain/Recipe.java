package com.boksha.recipeproject.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;
  private Integer prepTime;
  private Integer cookTime;
  private Integer servings;
  private String source;
  private String url;
  private String directions;

  @OneToOne(fetch = FetchType.EAGER)
  private UnitOfMeasure uom;

  //recipe is target in Ingredient class
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
  private Set<Ingredient> ingredients;

  @Lob
  private Byte[] image;

  //ordinal will get as number 1,2,3, but string will use values
  @Enumerated(value = EnumType.STRING)
  private Difficulty difficulty;

  @OneToOne(cascade = CascadeType.ALL)
  private Notes notes;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getPrepTime() {
    return prepTime;
  }

  public void setPrepTime(Integer prepTime) {
    this.prepTime = prepTime;
  }

  public Integer getCookTime() {
    return cookTime;
  }

  public void setCookTime(Integer cookTime) {
    this.cookTime = cookTime;
  }

  public Integer getServings() {
    return servings;
  }

  public void setServings(Integer servings) {
    this.servings = servings;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDirections() {
    return directions;
  }

  public void setDirections(String directions) {
    this.directions = directions;
  }

  public UnitOfMeasure getUom() {
    return uom;
  }

  public void setUom(UnitOfMeasure uom) {
    this.uom = uom;
  }

  public Set<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(Set<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  public Byte[] getImage() {
    return image;
  }

  public void setImage(Byte[] image) {
    this.image = image;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  public Notes getNotes() {
    return notes;
  }

  public void setNotes(Notes notes) {
    this.notes = notes;
  }
}
