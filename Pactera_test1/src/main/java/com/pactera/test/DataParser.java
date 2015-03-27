package com.pactera.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.pactera.test.model.Fridge;
import com.pactera.test.model.Recipe;

public abstract class DataParser {
	
	private List<Fridge> fridge = new ArrayList<Fridge>();
	
	private List<Recipe> recipes = new ArrayList<Recipe>();

	private List<Fridge> edibleItemsInFridge = new ArrayList<Fridge>();
	
	private List<Recipe> todaysRecipes = new ArrayList<Recipe>();
	
	public List<Fridge> getEdibleItemsInFridge() {
		return edibleItemsInFridge;
	}

	public void setEdibleItemsInFridge(List<Fridge> edibleItemsInFridge) {
		this.edibleItemsInFridge = edibleItemsInFridge;
	}
	public List<Fridge> getFridge() {
		return fridge;
	}

	public void setFridge(List<Fridge> fridge) {
		this.fridge = fridge;
	}
		
	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	// Template method
	// This method defines a generic structure for parsing data
	public void parseDataAndGenerateOutput(ApplicationContext context) throws DataProcessException{
		readData(context);
		processData();
		writeData();
	}

	// This methods will be implemented by its subclass
	abstract void readData(ApplicationContext context) throws DataProcessException;
	abstract void readData(ApplicationContext context, File file) throws DataProcessException;
	abstract void processData();
	abstract void processData(DataParser parser);
	abstract DataProcessor getDataProcessor();

	// We have to write output in a CSV file so this step will be same for all
	// subclasses
	public void writeData() {
		System.out.println("Output generated,writing to CSV");
	}

	public List<Recipe> getTodaysRecipes() {
		return todaysRecipes;
	}

	public void setTodaysRecipes(List<Recipe> todaysRecipes) {
		this.todaysRecipes = todaysRecipes;
	}
}