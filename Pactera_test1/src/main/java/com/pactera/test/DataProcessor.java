package com.pactera.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.pactera.test.model.Fridge;
import com.pactera.test.model.Ingredient;
import com.pactera.test.model.Recipe;
import com.pactera.test.model.Unit;

public class DataProcessor implements FridgeDataProcessor, RecipeDataProcessor {
	boolean valid = false;
	private DataParser parser;
	public DataProcessor(){
	}
	
	public DataProcessor(DataParser parser){
		this.parser = parser;
	}

	@Override
	public boolean processFridgeItems(Fridge fridge) throws DataProcessException{
		String item = fridge.getItem(); 
		int amount = fridge.getAmount(); 
		String unitString = fridge.getUnitString(); 
		String useByString = fridge.getUseByDateString();
		
		if(item == null){
			throw new DataProcessException("item should not be null");
		}
		if(amount <=0 ){
			throw new DataProcessException("no "+item+" available");
		}
		if(unitString!=null){
			if(Unit.getValue(unitString)==null){
				throw new DataProcessException("Not a valid Unit ");
			}
			fridge.setUnit(Unit.getValue(unitString));
		}
		if(useByString!=null){
			try{
				
				Date useByDate = formatDate(useByString);
				fridge.setUseBy(useByDate);
				Date now = new Date();
				if(useByDate.before(now)){
					throw new DataProcessException("useByDate shows expiry date of item "+item+" is over");
				}
			}catch(Exception ex){
				throw new DataProcessException(ex.getMessage());
			}
		}
		valid = true;
		return valid;
	}
	
	private Date formatDate(String dateString) throws DataProcessException{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
	 
			date = formatter.parse(dateString);
	 
		} catch (ParseException e) {
			throw new DataProcessException("useBy Date not a valid date");
		}
		return date;
	}
	
	@Override
	public boolean processRecipeItems(Ingredient ingredient) throws DataProcessException{
		String item=ingredient.getItem(); 
		int amount = ingredient.getAmount();
		Unit unit = ingredient.getUnit();
		if(item == null){
			throw new DataProcessException("item should not be null");
		}
		if(amount <=0 ){
			throw new DataProcessException("no "+item+" available");
		}
		if(unit!=null){
			if(Unit.getValue(unit.toString())==null){
				throw new DataProcessException("Not a valid Unit ");
			}
		}
		valid = true;
		return valid;
	}
	public Map<Integer, Recipe> recipeFinder(DataParser jsonparser, DataParser csvparser){
		Map<Integer,Recipe> map = new TreeMap<Integer,Recipe>();
		List<Recipe> recipes = jsonparser.getRecipes();
		List<Recipe> todaysRecipes = new ArrayList<Recipe>();
		List<Fridge> edibleItems = edibleFridgeItems(csvparser);
		Collections.sort(edibleItems, new Comparator<Fridge>() {
			public int compare(Fridge o1, Fridge o2) {
				return o1.getUseBy().compareTo(o2.getUseBy());
			}
		});
		if(edibleItems ==null || recipes ==null || (edibleItems!=null && edibleItems.size() <=0) || (recipes!=null && recipes.size() <=0)){
			System.out.println("Order Takeout");
			map = null;
			todaysRecipes = null;
		}
		boolean haveIngredients = false;
		int weight = 0;
		int i=0;
		
		for(Recipe recipe: recipes){
			weight = 0;
			for(Ingredient ingredient: recipe.getIngredients()){
				i = 0;
				for(Fridge item: edibleItems){
					i++;
					if(ingredient.getItem().equalsIgnoreCase(item.getItem())){
						if(ingredient.getUnit().toString().equalsIgnoreCase(item.getUnit().toString())){
							if(ingredient.getAmount() <= item.getAmount()){
 								haveIngredients = true;
 								break;
							}else{
								haveIngredients = false;
								continue;
							}
						}else{
							haveIngredients = false;
							continue;
						}
					}else{
						haveIngredients = false;
						continue;
					}
				}
				weight += i;
			}
			if(haveIngredients){
				todaysRecipes.add(recipe);
				map.put(weight, recipe);
			}
		}
		if(todaysRecipes!=null && todaysRecipes.size()<=0){
			System.out.println("Order Takeout");
			map = null;
		}
		return map;
		
	}
	
	private List<Fridge> edibleFridgeItems(DataParser parser){
		List<Fridge> fridge = parser.getFridge();
		for(Fridge item : fridge){
			Date useByDate = item.getUseBy();
			Date now = new Date();
			if(useByDate.after(now)){
				parser.getEdibleItemsInFridge().add(item);
				//System.out.println(fridge);
			}
		}
		return parser.getEdibleItemsInFridge();
	}

}
