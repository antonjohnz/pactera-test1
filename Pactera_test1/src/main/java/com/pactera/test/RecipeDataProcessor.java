package com.pactera.test;

import com.pactera.test.model.Ingredient;


public interface RecipeDataProcessor {
	boolean processRecipeItems(Ingredient ingredient) throws DataProcessException;
}
