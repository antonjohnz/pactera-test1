package com.pactera.test;

import static org.junit.Assert.fail;

import java.io.File;

import javax.annotation.Resource;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

public class CSVDataParserTest extends ApplicationContextTest{
	
	DataParser parser;
	private MockMvc mockMvc;
	Mockito mockito;
	
	CSVDataParser csvparser;
	@Resource
	private WebApplicationContext context;
	
	File file;

	@Before
	public void setUp() throws Exception {
		parser = mockito.mock(CSVDataParser.class);
		file = new File("C:\test\fridge.csv");
		        
	}

	@Test
	public void testReadDataApplicationContextFile() throws DataProcessException {
		parser.readData(context, file);
		assertNotNull(parser.getFridge());
	}

	@Test
	public void testProcessDataDataParser() {
		parser.processData(csvparser);
		assertNotNull(parser.getFridge());
	}

}
