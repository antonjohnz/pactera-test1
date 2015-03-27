package com.pactera.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.pactera.test.model.FileUpload;
import com.pactera.test.model.Ingredient;
import com.pactera.test.model.Recipe;

@Controller
public class FileController {
	public static final String PAGE_INDEX = "todaysRecipe";
    public static final String PAGE_SHOW = "home";
	
	@Autowired
	FileUploadValidator validator;
	
	//@Autowired
    RecipeService recipeService;
		
	@InitBinder
	private void initBinder(WebDataBinder binder) {
	        binder.setValidator(validator);
	        recipeService = new RecipeServiceImpl();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getForm(Model model) {
		FileUpload fileModel = new FileUpload();
		model.addAttribute("csvfile", fileModel);
		model.addAttribute("jsonfile", fileModel);
		return PAGE_SHOW;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String fileUploaded(Model model, @Validated FileUpload file,
			BindingResult result) {

		String returnVal = PAGE_INDEX;
		if (result.hasErrors()) {
			returnVal = PAGE_SHOW;
		} else {
			MultipartFile multipartCsvFile = file.getCsvfile();
			MultipartFile multipartJsonFile = file.getJsonfile();
			File convCsvFile = new File( multipartCsvFile.getOriginalFilename());
			File convJsonFile = new File( multipartJsonFile.getOriginalFilename());
			try {
				multipartCsvFile.transferTo(convCsvFile);
				multipartJsonFile.transferTo(convJsonFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Recipe todaysRecipe = recipeService.todaysRecipe(convCsvFile, convJsonFile);
			if(todaysRecipe!=null){
				model.addAttribute("ingredientSize", todaysRecipe.getIngredients().size());
				model.addAttribute("recipename", todaysRecipe.getName());
				model.addAttribute("ingredients", todaysRecipe.getIngredients().toArray());
				String fullingredients = "<p><ul>";
				for(Ingredient ingredient :todaysRecipe.getIngredients()){
					fullingredients += "<li>"+ingredient.getItem()+" "+ingredient.getAmount()+" "+ingredient.getUnit().toString()+"";
					fullingredients += "</li><br/>";
					
				}
				model.addAttribute("fullingredients" , fullingredients+"</ul></p>");
				model.addAttribute("orderout", null);
			}else{
				System.out.println("Order Takeout");
				model.addAttribute("orderout", "Order Takeout");
				//return PAGE_SHOW;
			}
		}
		return returnVal;
	}
	
	
}
