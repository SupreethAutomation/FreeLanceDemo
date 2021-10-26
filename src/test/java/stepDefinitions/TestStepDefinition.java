package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;

public class TestStepDefinition {
    protected final String BASEURI = "https://reqres.in/";

    @Before
    public void setUp() {
        System.out.println("Test Started");
        RestAssured.baseURI = BASEURI;
    }

    @After
    public void tearDown() {
        System.out.println("Test completed");
    }
}
