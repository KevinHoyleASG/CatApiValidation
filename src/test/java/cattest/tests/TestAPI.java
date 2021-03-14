package cattest.tests;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.opencsv.bean.CsvToBeanBuilder;

import cattest.model.CatData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class TestAPI {
	
	static String URI = "https://cat-fact.herokuapp.com/facts";
	List<CatData> catData;
	
	  @BeforeClass
	  public void setup() throws Exception {
		  loadCatData();
	  }

	  /******************************************
	   * HTTP Tests
	   *******************************************/

		@Test (priority = 1)
		public void isValidFactsHTTPStatusCode() {
			Response response = RestAssured.get(URI);
			/*
			System.out.println(response.statusCode());
			System.out.println(response.asString());
			System.out.println(response.getBody().asString());
			System.out.println(response.statusLine());
			*/
			int statusCode = response.getStatusCode();			
			Assert.assertEquals(statusCode, 200);
		}
		
		@Test (priority = 1)
		public void isValidFactsIdHTTPStatusCode() {			
			String fact_Id = "/" + catData.get(0).get_id();
			Response response = RestAssured.get(URI+fact_Id);
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200);
		}	
		@Test (priority = 1)
		public void isInValidFactsHTTPStatusCode() {
			Response response = RestAssured.get(URI+"tyty");
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 404);
		}
		
		@Test (priority = 1)
		public void isInValidFactsIdHTTPStatusCode() {
			//Leaving out "/"
			String fact_Id = catData.get(0).get_id();
			Response response = RestAssured.get(URI+fact_Id);
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 404);
		}	
		
		
		/******************************************
		 * JSON Validation
		 * 
		 ******************************************/
		@Test (priority = 2)
		public void isJSOnStructureCorrect() {			
			Response response = RestAssured.get(URI);
			response.then()
			.assertThat()
			.statusCode(200)
			.body(matchesJsonSchemaInClasspath("catSchema.json"));			
		}
		
		/******************************************
		 *  Data Validation
		 * 
		 *  1. isFactsAllValid
		 *  This will generally fail as the 5 random
		 *  cat facts returned are unlikely to match 
		 *  the existing dataset.
		 * 
		 *  2. isAllFactsIdValid
		 *  This will fail as some facts in the dataset
		 *  no longer exist in the database.
		 *  
		 * *****************************************/
		
		@Test (priority = 3)
		public void isFactsAllValid() {
			boolean allFactsValid = true;
			Response response = RestAssured.get(URI);
						
			List<String> jsonResponse = response.jsonPath().getList("_id");
			
			for(String id: jsonResponse) {
				boolean found = catData.stream().anyMatch(x -> x.get_id().equals(id));
				if(!found) {
					System.out.println("isFactsAllValid Error: " + id + " not found");
					allFactsValid = false;						
				}
			}
			
			Assert.assertEquals(allFactsValid, true);
		}
		
		@Test (priority = 3)
		public void isAllFactsIdValid() {
			boolean allFactsValid = true;
			
			for(CatData cat: catData) {
				String fact_Id = "/" + cat.get_id();
				Response response = RestAssured.get(URI+fact_Id);
				int statusCode = response.getStatusCode();	
				if(statusCode != 200) {
					System.out.println("isAllFactsIdValid Error: " + cat.get_id() + " not found");
					allFactsValid = false;
				}
			}
			Assert.assertEquals(allFactsValid, true);
		}		
		
		/*******************************************
		 * Setup 
		 *******************************************/
		
	    void loadCatData() throws Exception {

	        String fileName = "Test Automation - Dataset.csv";

			try {	        
		        URL resource = getClass().getClassLoader().getResource(fileName);	        	        

		        File resourceFile = new File(resource.toURI());	        
		        catData = new CsvToBeanBuilder<CatData>(new FileReader(resourceFile))
			                .withType(CatData.class)
			                .withSkipLines(1)
			                .withIgnoreEmptyLine(true)
			                .build()
			                .parse();

		       // beans.forEach(b -> System.out.println(b.get_id()));
        
			} catch (Exception e) {
				// TODO Auto-generated catch block				
				e.printStackTrace();
				throw new Exception();
			}	        
	    }
	
}
