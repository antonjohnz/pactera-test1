package com.pactera.test;

import java.util.Map;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.JsonLineMapper;

import com.pactera.test.model.Recipe;

public class RecipeLineMapper implements LineMapper<Recipe> {
    private JsonLineMapper delegate;
    public void setDelegate(JsonLineMapper delegate) {
        this.delegate = delegate;
    }
	@Override
	public Recipe mapLine(String line, int lineNumber) throws Exception {
		Map<String, Object> recipeAsMap = delegate.mapLine(line, lineNumber);
		Recipe recipe = new Recipe();
        // map fields

		recipeAsMap.put(recipe.getName(), recipe);
        return recipe ;
	}
}
