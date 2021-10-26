package stepDefinitions;

import java.util.ArrayList;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import responseJsonPaths.ResponseJsonPaths;
import utilities.GenericUtility;

public class SearchUserStepDefinition implements ResponseJsonPaths {
String result = null;
    @When("^the user searches for the particular user as parameter \"([^\"]*)\"$")
    public void the_user_searches_for_the_particular_user_as_parameter(String arg1) throws Throwable {
    	BaseStepDefinition.objResponse = RestAssured.given().accept(ContentType.JSON).pathParam("user_id", arg1).get("/{user_id}");
    }
    
    @Then("^Validate that the response is valid and \"([^\"]*)\" and \"([^\"]*)\" is searched successfully$")
    public void validate_that_the_response_is_valid_and_and_is_searched_successfully(String arg1, String arg2) throws Throwable {
    		ArrayList<String> lstOfData = GenericUtility.setListOfDataFromFeatureDataTable(arg2);
    		try
    		{
    		 GenericUtility.assertResultisPositive(Integer.parseInt((String) GenericUtility.fetchTheResponseObjectviaJsonPath(BaseStepDefinition.objResponse, ResponseJsonPaths.createUserResponseData_id)));
    		}
    		catch(Exception e)
    		{
    			GenericUtility.assertResultisPositive(0);
    		}
			GenericUtility.assertListcontainsValues(lstOfData,GenericUtility.fetchTheResponseObjectviaJsonPath(BaseStepDefinition.objResponse, ResponseJsonPaths.createUserResponseData_email));
			GenericUtility.assertListcontainsValues(lstOfData,GenericUtility.fetchTheResponseObjectviaJsonPath(BaseStepDefinition.objResponse, ResponseJsonPaths.createUserResponseData_fn));
			GenericUtility.assertListcontainsValues(lstOfData,GenericUtility.fetchTheResponseObjectviaJsonPath(BaseStepDefinition.objResponse, ResponseJsonPaths.createUserResponseData_ln));
			GenericUtility.assertListcontainsValues(lstOfData,GenericUtility.fetchTheResponseObjectviaJsonPath(BaseStepDefinition.objResponse, ResponseJsonPaths.createUserResponseData_avatar));
			GenericUtility.assertListcontainsValues(lstOfData,GenericUtility.fetchTheResponseObjectviaJsonPath(BaseStepDefinition.objResponse, ResponseJsonPaths.createUserResponseSupport_url));
			GenericUtility.assertListcontainsValues(lstOfData,GenericUtility.fetchTheResponseObjectviaJsonPath(BaseStepDefinition.objResponse, ResponseJsonPaths.createUserResponseSupport_text));
    }
    
    

}
