package stepDefinitions;

import cucumber.Runner.TestRunner;
import cucumber.api.DataTable;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import responseJsonPaths.RequestJsonKeys;
import utilities.GenericUtility;
import utilities.JsonUtility;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class LoginStepDefinition {

	@When("^the user authenticates with the credentials \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_user_authenticates_with_the_credentials_and(String email, String password,DataTable data) throws Throwable {
    	
    	JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "json" + File.separator + data.raw().get(0).get(0))));
    	jsonObject=JsonUtility.createJsonObject(jsonObject,RequestJsonKeys.loginReq_email, email);
    	System.out.println("Modified json String: "+jsonObject.toJSONString());
    	jsonObject=JsonUtility.createJsonObject(jsonObject,RequestJsonKeys.loginReq_password, password);
    	System.out.println("Modified json String: "+jsonObject.toJSONString());
    	GenericUtility.writeJsonFile("Request-"+data.raw().get(0).get(0)+(++TestStepDefinition.reqCounter), TestRunner.reqDirPath, jsonObject.toJSONString());
        BaseStepDefinition.objResponse = RestAssured.given().header("Content-Type", "application/json").
                body(jsonObject.toJSONString()).accept(ContentType.JSON).post();
        GenericUtility.writeJsonFile("Response-"+data.raw().get(0).get(0)+(++TestStepDefinition.resCounter), TestRunner.resDirPath, BaseStepDefinition.objResponse.asString());
    }
}
