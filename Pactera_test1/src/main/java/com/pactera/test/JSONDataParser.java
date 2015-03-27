package com.pactera.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;

import com.google.gson.Gson;
import com.pactera.test.model.Ingredient;
import com.pactera.test.model.Recipe;

public class JSONDataParser extends DataParser {
	Gson gson;
	DataProcessor dataProcessor;
	
	public JSONDataParser(){
	}
	
	public JSONDataParser(DataProcessor dataProcessor, Gson gson){
		this.dataProcessor = dataProcessor;
		this.gson = gson;
	}
	
	@Override
	void readData(ApplicationContext context) throws DataProcessException{
		try{
			//System.out.println("Reading data from json file");
			BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/recipe.json")));
			Recipe[] list = gson.fromJson(br, Recipe[].class);
			for (Recipe recipe : list)
			{
				getRecipes().add(recipe);
				//System.out.println(recipe);
			}
		}catch(Exception ex){
			throw new DataProcessException("Error in parsing");
		}
		

	}
	
	@Override
	void readData(ApplicationContext context, File file) throws DataProcessException{
		try{
			//System.out.println("Reading data from json file");
			//BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file.getName())));
			BufferedReader br = new BufferedReader(new FileReader(file));
			Recipe[] list = gson.fromJson(br, Recipe[].class);
			for (Recipe recipe : list)
			{
				getRecipes().add(recipe);
				//System.out.println(recipe);
			}
		}catch(Exception ex){
			throw new DataProcessException("Error in parsing");
		}
		

	}

	@Override
	void processData() {
		System.out.println("Looping through loaded csv file");

	}
	@Override
	void processData(DataParser parser) {
		for(Recipe recipe : parser.getRecipes()){
			for(Ingredient ingredients : recipe.getIngredients()){
				try {
					dataProcessor.processRecipeItems(ingredients);
					//System.out.println("Recipe Data Processed");
				} catch (DataProcessException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public DataProcessor getDataProcessor() {
		return dataProcessor;
	}

}
