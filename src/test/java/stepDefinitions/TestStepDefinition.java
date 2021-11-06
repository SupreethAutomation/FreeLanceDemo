package stepDefinitions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;

public class TestStepDefinition {
    protected final String BASEURI = "https://reqres.in/";
    public static int reqCounter = 0;
    public static int resCounter = 0;
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
