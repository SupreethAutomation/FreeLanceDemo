package stepDefinitions;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import cucumber.Runner.TestRunner;
import cucumber.api.DataTable;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import responseJsonPaths.RequestJsonKeys;
import responseJsonPaths.ResponseJsonPaths;
import utilities.GenericUtility;
import utilities.JsonUtility;

public class PatchUserStepDefinition implements ResponseJsonPaths 
{
	@SuppressWarnings("unchecked")
	@When("^the admin patches the user \"([^\"]*)\" , \"([^\"]*)\" and \"([^\"]*)\" details$")
	public void the_admin_patches_the_user_and_details(String arg1, String arg2, String arg3,DataTable data) throws Throwable {
		
		JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "json" + File.separator + data.raw().get(0).get(0))));
    	jsonObject=JsonUtility.createJsonObject(jsonObject,RequestJsonKeys.PatchUserReq_name, arg2);
    	System.out.println("Modified json String: "+jsonObject.toJSONString());
    	jsonObject=JsonUtility.createJsonObject(jsonObject,RequestJsonKeys.PatchUserReq_job, arg3);
    	System.out.println("Modified json String: "+jsonObject.toJSONString());
    	GenericUtility.writeJsonFile("Request-"+data.raw().get(0).get(0)+(++TestStepDefinition.reqCounter), TestRunner.reqDirPath, jsonObject.toJSONString());
		 BaseStepDefinition.objResponse= RestAssured.given().header("Content-Type", "application/json").
		 body(jsonObject.toJSONString()).accept(ContentType.JSON).pathParam("user_id",arg1).patch("/{user_id}");
	}
	
	

}
