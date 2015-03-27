package com.pactera.test;

import java.io.File;

import com.pactera.test.model.Recipe;

public interface RecipeService {
	public Recipe todaysRecipe(File csvfile, File jsonfile);
}
