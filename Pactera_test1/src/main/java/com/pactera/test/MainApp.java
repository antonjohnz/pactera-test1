package com.pactera.test;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pactera.test.model.Ingredient;
import com.pactera.test.model.Recipe;





public class MainApp {
	
		
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
        System.out.println("Enter the csv file for the fridge items");
        //get user input for a
        String csvfile = reader.next();
        System.out.println("Enter the json / txt file for the Recipes");
        String jsonfile = reader.next();
		UserSession.currentUserId = "ajohn8";
		ApplicationContext context = new ClassPathXmlApplicationContext("/application-config.xml");
		DataParser jsonparser = (JSONDataParser) context.getBean("jsonparser");
		DataParser parser = (CSVDataParser)context.getBean("csvparser");
		File csvfileO = new File(csvfile);
		File jsonfileO = new File(jsonfile);
		try {
			jsonparser.readData(context, jsonfileO);
			//parser.readData(context);
			parser.readData(context,csvfileO);
		} catch (DataProcessException e) {
			System.out.println(e.getMessage());
		}
		
		if(UserSession.isValidUser()){
			jsonparser.processData(jsonparser);
			parser.processData(parser);
			DataProcessor dataProcessor = parser.getDataProcessor();
			Map<Integer, Recipe> todaysRecipes = dataProcessor.recipeFinder(jsonparser, parser);
			
			for(Integer integ : todaysRecipes.keySet()){
				Recipe finalRecipe = todaysRecipes.get(integ);
				System.out.println(integ+" "+finalRecipe);
				break;
			}
			
		}else{
			System.out.println("Invalid user");
		}
		
	}
	
}
