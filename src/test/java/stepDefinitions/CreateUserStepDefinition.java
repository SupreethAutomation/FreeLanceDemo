package stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import responseJsonPaths.RequestJsonKeys;
import responseJsonPaths.ResponseJsonPaths;
import utilities.GenericUtility;

import java.io.File;
import java.io.FileReader;

public class CreateUserStepDefinition implements ResponseJsonPaths {
String result = null;
    
    @SuppressWarnings("unchecked")
	@When("^the user creates new Users with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_user_creates_new_Users_with_and(String arg1, String arg2) throws Throwable {
    	JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "json" + File.separator + "createUser.json")));
        jsonObject.put(RequestJsonKeys.CreateUserReq_name, arg1);
        jsonObject.put(RequestJsonKeys.CreateUserReq_job, arg2);
        System.out.println(jsonObject.toJSONString());
        BaseStepDefinition.objResponse = RestAssured.given().header("Content-Type", "application/json").
                body(jsonObject.toJSONString()).accept(ContentType.JSON).post();
    }

    @Then("^Validate that the response is valid and new user with \"([^\"]*)\" and \"([^\"]*)\" is created succesfully$")
    public void validate_that_the_response_is_valid_and_new_user_with_and_is_created_succesfully(String arg1, String arg2) throws Throwable {
    	try
		{
			result =  GenericUtility.fetchTheResponseObjectviaJsonPath(BaseStepDefinition.objResponse, ResponseJsonPaths.createUserResponse_name);
			GenericUtility.assertTheActualWithExpectedResponseValues(result, arg1);
		}
		catch (Exception e) {
			result = "";
			GenericUtility.assertTheActualWithExpectedResponseValues(result, arg1);
		}
    	
    	try
		{
			result =  GenericUtility.fetchTheResponseObjectviaJsonPath(BaseStepDefinition.objResponse, ResponseJsonPaths.createUserResponse_job);
			GenericUtility.assertTheActualWithExpectedResponseValues(result, arg2);
		}
		catch (Exception e) {
			result = "";
			GenericUtility.assertTheActualWithExpectedResponseValues(result, arg2);
		}
    	GenericUtility.assertResultisPositive(Integer.parseInt((String) GenericUtility.fetchTheResponseObjectviaJsonPath(BaseStepDefinition.objResponse, ResponseJsonPaths.createUserResponse_id)));
    }
    
    
}
