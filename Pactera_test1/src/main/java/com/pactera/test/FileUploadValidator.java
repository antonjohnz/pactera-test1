package com.pactera.test;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pactera.test.model.FileUpload;

public class FileUploadValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return FileUpload.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FileUpload file = (FileUpload)target;
		 
		if(file.getCsvfile().getSize()==0){
			errors.rejectValue("csvfile", "required.fileUpload");
		}
		if(!file.getCsvfile().getOriginalFilename().endsWith(".csv")){
			errors.rejectValue("csvfile", "csvfile.required");
		}
		if(file.getJsonfile().getSize()==0){
			errors.rejectValue("jsonfile", "required.fileUpload");
		}
		if(!(file.getJsonfile().getOriginalFilename().endsWith(".json") || 
				file.getJsonfile().getOriginalFilename().endsWith(".txt"))){
			errors.rejectValue("jsonfile", "jsonfile.required");
		}
		
	}
}
