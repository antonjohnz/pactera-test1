package com.pactera.test.model;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable{
	private String name;
	private List<Ingredient> ingredients;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	@Override
	public String toString() {
		return "Recipe [name=" + name + ", ingredients=" + ingredients + "]";
	}
	
}
