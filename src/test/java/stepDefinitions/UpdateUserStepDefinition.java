package stepDefinitions;

import cucumber.Runner.TestRunner;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import responseJsonPaths.RequestJsonKeys;
import responseJsonPaths.ResponseJsonPaths;
import utilities.GenericUtility;
import utilities.JsonUtility;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateUserStepDefinition implements ResponseJsonPaths {
    String result = null;

	@When("^the admin updates the user \"([^\"]*)\" , \"([^\"]*)\" and \"([^\"]*)\" details$")
    public void the_admin_updates_the_user_and_details(String arg1, String arg2, String arg3,DataTable data) throws Throwable {
    	JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "json" + File.separator + data.raw().get(0).get(0))));
    	jsonObject=JsonUtility.createJsonObject(jsonObject,RequestJsonKeys.UpdateUserReq_name, arg2);
    	System.out.println("Modified json String: "+jsonObject.toJSONString());
    	jsonObject=JsonUtility.createJsonObject(jsonObject,RequestJsonKeys.UpdateUserReq_job, arg3);
    	System.out.println("Modified json String: "+jsonObject.toJSONString());
    	GenericUtility.writeJsonFile("Request-"+data.raw().get(0).get(0)+(++TestStepDefinition.reqCounter), TestRunner.reqDirPath, jsonObject.toJSONString());
    	 BaseStepDefinition.objResponse = RestAssured.given().header("Content-Type", "application/json").
                 body(jsonObject.toJSONString()).accept(ContentType.JSON).pathParam("user_id", arg1).put("/{user_id}");
        GenericUtility.writeJsonFile("Response-"+data.raw().get(0).get(0)+(++TestStepDefinition.resCounter), TestRunner.resDirPath, BaseStepDefinition.objResponse.asString());
    }

    @Then("^Validate that the response is valid and \"([^\"]*)\" and \"([^\"]*)\" details are updated succesfully$")
    public void validate_that_the_response_is_valid_and_and_details_are_updated_succesfully(String arg1, String arg2) throws Throwable {
    	result = GenericUtility.fetchTheResponseObjectviaJsonPath(BaseStepDefinition.objResponse, ResponseJsonPaths.createUserResponse_name);
    	GenericUtility.assertTheActualWithExpectedResponseValues(result, arg1);
    	result = GenericUtility.fetchTheResponseObjectviaJsonPath(BaseStepDefinition.objResponse, ResponseJsonPaths.createUserResponse_job);
    	GenericUtility.assertTheActualWithExpectedResponseValues(result, arg2);
    	String updatedDay = BaseStepDefinition.objResponse.body().jsonPath().get(ResponseJsonPaths.createUserResponse_updatedAt).toString().substring(0, 10);
        Date objDate = new Date();
        SimpleDateFormat objDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String expectedDate = objDateFormat.format(objDate);
        System.out.println(expectedDate);
        System.out.println(updatedDay);
        GenericUtility.assertTheActualWithExpectedResponseValues(updatedDay, expectedDate);
    }
    
    
}
