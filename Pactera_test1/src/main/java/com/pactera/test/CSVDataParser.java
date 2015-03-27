package com.pactera.test;

import java.io.File;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;

import com.pactera.test.model.Fridge;

public class CSVDataParser extends DataParser {
	DataProcessor dataProcessor;
	
	public CSVDataParser(){
	}
	
	public CSVDataParser(DataProcessor dataProcessor){
		this.dataProcessor = dataProcessor;
	}
	
	
	@Override
	void readData(ApplicationContext context) throws DataProcessException {
		//System.out.println("Reading data from csv file");
		FlatFileItemReader<Fridge> fileItems = (FlatFileItemReader<Fridge>) context.getBean("fridgeReader");
		try {
			fileItems.open(new ExecutionContext());
			Fridge fridge = fileItems.read();
			while(fridge!=null){
				//System.out.println(fridge);
				getFridge().add(fridge);
				fridge = fileItems.read();
			}
		} catch (UnexpectedInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	void readData(ApplicationContext context, File file) throws DataProcessException {
		//System.out.println("Reading data from csv file");
		FlatFileItemReader<Fridge> fileItems = (FlatFileItemReader<Fridge>) context.getBean("fridgeReader");
		fileItems.setResource(new FileSystemResource(file.getAbsolutePath()));
		try {
			ExecutionContext execContext = new ExecutionContext();
			fileItems.open(execContext);
			Fridge fridge = fileItems.read();
			while(fridge!=null){
				//System.out.println(fridge);
				getFridge().add(fridge);
				fridge = fileItems.read();
			}
		} catch (UnexpectedInputException e) {
			throw new DataProcessException("Unexpected Input Error");
		} catch (ParseException e) {
			throw new DataProcessException("Error in parsing");
		} catch (Exception e) {
			throw new DataProcessException("Error in reading file");
		}

	}

	@Override
	void processData() {
		System.out.println("Looping through loaded csv file");

	}
	@Override
	void processData(DataParser parser) {
		for (Fridge fridge : parser.getFridge()) {
			try {
				//process data from the fridge
				dataProcessor.processFridgeItems(fridge);
			} catch (DataProcessException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public DataProcessor getDataProcessor() {
		return dataProcessor;
	}

}
