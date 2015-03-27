package com.pactera.test;

import static org.junit.Assert.*;

import java.io.File;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class FileControllerTest {
	@Resource
	private WebApplicationContext webApplicationContext;
	
	@Mock
    private RecipeService Service;
	
	@InjectMocks
    private FileController controller;
 
    private MockMvc mockMvc;
    Mockito mockito;


	@Before
	public void setUp() throws Exception {
		//controller = new FileController();
		controller = mockito.mock(FileController.class);
		// Process mock annotations
        //MockitoAnnotations.initMocks(this);
 
        // Setup Spring test in standalone mode
        //this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
 
	}

	@Test
	public void testGetForm() throws Exception{
		assertNotNull(controller);
		String view = controller.getForm(null);
		assertEquals(null,view);
	}
	
	@Test
	public void testFileUploaded() throws Exception{
		/*this.mockMvc.perform(post("/")
	            .param("csvfile", new File("C:\test\fridge.csv"))
	            .param("jsonfile", new File("C:\test\recipe.json"))
	            .andExpect(status().isOk())
	            .andExpect(forwardedUrl(FileController.PAGE_INDEX))
	            .andExpect(model().attributeExists("page_error"));*/
	}

}
