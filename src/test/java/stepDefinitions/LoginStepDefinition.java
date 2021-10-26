package stepDefinitions;

import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import responseJsonPaths.RequestJsonKeys;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class LoginStepDefinition {

    @SuppressWarnings("unchecked")
	@When("^the user authenticates with the credentials \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_user_authenticates_with_the_credentials_and(String email, String password) throws Throwable {
    	JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "json" + File.separator + "login.json")));
        jsonObject.put(RequestJsonKeys.loginReq_email, email);
        jsonObject.put(RequestJsonKeys.loginReq_password, password);
       // System.out.println(jsonObject.toJSONString());
        BaseStepDefinition.objResponse = RestAssured.given().header("Content-Type", "application/json").
                body(jsonObject.toJSONString()).accept(ContentType.JSON).post();
    }
}
