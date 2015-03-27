package com.pactera.test;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.context.WebApplicationContext;

import com.pactera.test.model.Recipe;

public class RecipeServiceImpl extends BaseService implements RecipeService {

	@Resource
	private WebApplicationContext context1;

	@Override
	public Recipe todaysRecipe(File csvfile, File jsonfile){
		Recipe finalRecipe = null;
		UserSession.currentUserId = "ajohn8";
		DataParser jsonparser = (JSONDataParser) context.getBean("jsonparser");
		DataParser parser = (CSVDataParser)context.getBean("csvparser");
		boolean errorParsing = false;
		try {
			jsonparser.readData(context,jsonfile);
			//parser.readData(context);
			parser.readData(context,csvfile);
		} catch (DataProcessException e) {
			System.out.println(e.getMessage());
			errorParsing = true;
		}
			
		if(UserSession.isValidUser()){
			if(!errorParsing){
				jsonparser.processData(jsonparser);
				parser.processData(parser);
				DataProcessor dataProcessor = parser.getDataProcessor();
				Map<Integer, Recipe> todaysRecipes = dataProcessor.recipeFinder(jsonparser, parser);
				if(todaysRecipes!=null){
					
					for(Integer integ : todaysRecipes.keySet()){
						finalRecipe = todaysRecipes.get(integ);
						System.out.println(integ+" "+finalRecipe);
						break;
					}
				}
			}
		}else{
			System.out.println("Invalid user");
		}
		return finalRecipe;
	}


}
