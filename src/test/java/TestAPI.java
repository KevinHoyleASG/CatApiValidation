import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestAPI {
	static String URI = "https://cat-fact.herokuapp.com/facts";
	
	  @Test
	  public void f() {
	  }

		@Test
		public void testFacts() {

			Response response = RestAssured.get(URI);
			System.out.println(response.statusCode());
			System.out.println(response.asString());
			System.out.println(response.getBody().asString());
			System.out.println(response.statusLine());

			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200);

		}
		
		@Test
		public void testFactsId() {
			
			String fact_Id = "/58e008ad0aac31001185ed0c";
			Response response = RestAssured.get(URI+fact_Id);
			System.out.println(response.statusCode());
			System.out.println(response.asString());
			System.out.println(response.getBody().asString());
			System.out.println(response.statusLine());

			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200);

		}	
}
