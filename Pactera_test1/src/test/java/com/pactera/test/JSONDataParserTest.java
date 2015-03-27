package com.pactera.test;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

public class JSONDataParserTest extends ApplicationContextTest{
	@Autowired
	ApplicationContext context;
	
	DataParser parser;
	JSONDataParser jsonparser;
	private MockMvc mockMvc;
	Mockito mockito;
	File file;

	@Before
	public void setUp() throws Exception {
		parser = mockito.mock(CSVDataParser.class);
		file = new File("C:\test\recipe.json");
	}

	@Test
	public void testReadDataApplicationContextFile() throws DataProcessException {
		parser.readData(context, file);
		Assert.assertNotNull(parser.getRecipes());
	}

	@Test
	public void testProcessDataDataParser() {
		parser.processData(jsonparser);
		Assert.assertNotNull(parser.getRecipes());
	}

}
