package com.pactera.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.pactera.test.model.Fridge;
import com.pactera.test.model.Ingredient;
import com.pactera.test.model.Recipe;
import com.pactera.test.model.Unit;

public class DataProcessorTest extends ApplicationContextTest{

	DataParser csvparser;
	DataParser jsonparser;
	DataProcessor dataProcessor;
	private MockMvc mockMvc;
	Mockito mockito;
	
	//CSVDataParser csvparser;
	@Resource
	private WebApplicationContext context;
	
	Fridge fridge;
	Ingredient ingredient;
	Recipe recipe;
	
	File csvfile;
	File jsonfile;

	@Before
	public void setUp() throws Exception {
		csvparser = mockito.mock(CSVDataParser.class);
		jsonparser = mockito.mock(JSONDataParser.class);
		//dataProcessor = mockito.mock(DataProcessor.class);
		dataProcessor = new DataProcessor();
		csvfile = new File("C:\test\fridge.csv");
		jsonfile = new File("C:\test\recipe.json");
		
		fridge = new Fridge();
		fridge.setItem("curd");
		fridge.setAmount(10);
		fridge.setUnitString(Unit.GRAMS.toString());
		fridge.setUseByDateString("25/12/2015");
		
		ingredient = new Ingredient();
		ingredient.setItem("curd");
		ingredient.setAmount(5);
		ingredient.setUnit(Unit.GRAMS);
		        
	}

	@Test
	public void testProcessFridgeItems() throws DataProcessException {
		boolean valid = dataProcessor.processFridgeItems(fridge);
		assertTrue(valid);
	}

	@Test
	public void testProcessRecipeItems() throws DataProcessException {
		boolean valid = dataProcessor.processRecipeItems(ingredient);
		assertTrue(valid);
	}

	@Test
	public void testRecipeFinder() throws DataProcessException {
		jsonparser.readData(context, jsonfile);
		csvparser.readData(context, csvfile);
		assertNotNull(csvparser.getFridge());
		csvparser.setFridge(null);
		Map<Integer, Recipe> map = dataProcessor.recipeFinder(jsonparser, csvparser);
		assertNull(map);
	}

}
