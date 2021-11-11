package stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import responseJsonPaths.ResponseJsonPaths;
import utilities.GenericUtility;

public class BaseStepDefinition implements ResponseJsonPaths {
	public static Response objResponse = null;
	String result = null;
	@Given("^the api is successfully running$")
	public void the_api_is_successfully_running(DataTable data) throws Throwable {
		RestAssured.basePath = data.raw().get(0).get(0);
		try
		{
			Assert.assertTrue(GenericUtility.verifyTheStatusCodeOfAPIService(), "API not running");
		}
		catch (Exception e) {
			Assert.assertTrue(false,"API is down");
		}
	}

	@Then("^Validate that the response is valid and token \"([^\"]*)\" is generated succesfully$")
	public void validate_that_the_response_is_valid_and_token_is_generated_succesfully(String arg1) throws Throwable {
		//System.out.println(objResponse.asPrettyString());
		try
		{
			result =  GenericUtility.fetchTheResponseObjectviaJsonPath(objResponse, ResponseJsonPaths.loginResponse_token);
			GenericUtility.assertTheActualWithExpectedResponseValues(result, arg1);
		}
		catch (Exception e) {
			result = "";
			GenericUtility.assertTheActualWithExpectedResponseValues(result, arg1);
		}
	}

	@But("^Validate that the response is Invalid and proper error message \"([^\"]*)\" is generated succesfully$")
	public void validate_that_the_response_is_Invalid_and_proper_error_messageis_generated_succesfully(String arg1) throws Throwable {
		//System.out.println(objResponse.asPrettyString());
		try
		{
			result = GenericUtility.fetchTheResponseObjectviaJsonPath(objResponse, ResponseJsonPaths.loginResponse_errorMsg);
			GenericUtility.assertTheActualWithExpectedResponseValues(result, arg1);
		}
		catch (Exception e) 
		{
			result = "";
			GenericUtility.assertTheActualWithExpectedResponseValues(result, arg1);
		}
	}

}
