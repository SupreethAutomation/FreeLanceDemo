package stepDefinitions;

import cucumber.Runner.TestRunner;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import responseJsonPaths.RequestJsonKeys;
import responseJsonPaths.ResponseJsonPaths;
import utilities.GenericUtility;
import utilities.JsonUtility;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class RegistrationStepDefinition {
String result = null;
    @When("^user registers with the email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void user_registers_with_the_email_and_password(String arg1, String arg2,DataTable data) throws Throwable {
    	JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "json" + File.separator + data.raw().get(0).get(0))));
    	jsonObject=JsonUtility.createJsonObject(jsonObject,RequestJsonKeys.RegisterReq_email, arg1);
    	System.out.println("Modified json String: "+jsonObject.toJSONString());
    	jsonObject=JsonUtility.createJsonObject(jsonObject,RequestJsonKeys.RegisterReq_password, arg2);
    	System.out.println("Modified json String: "+jsonObject.toJSONString());
    	GenericUtility.writeJsonFile("Request-"+data.raw().get(0).get(0)+(++TestStepDefinition.reqCounter), TestRunner.reqDirPath, jsonObject.toJSONString());
        BaseStepDefinition.objResponse = RestAssured.given().header("Content-Type", "application/json").
                body(jsonObject.toJSONString()).accept(ContentType.JSON).post();
        GenericUtility.writeJsonFile("Response-"+data.raw().get(0).get(0)+(++TestStepDefinition.resCounter), TestRunner.resDirPath, BaseStepDefinition.objResponse.asString());
    	
    }
    
    @And("^verify the employee id \"([^\"]*)\"$")
    public void verify_the_employee_id(String arg1) throws Throwable 
    {
    	try
		{
			result = GenericUtility.fetchTheResponseObjectviaJsonPath(BaseStepDefinition.objResponse, ResponseJsonPaths.registerResponse_id);
			GenericUtility.assertTheActualWithExpectedResponseValues(result, arg1);
		}
		catch (Exception e) 
		{
			result = "";
			GenericUtility.assertTheActualWithExpectedResponseValues(result, arg1);
		}
    }
    
}
