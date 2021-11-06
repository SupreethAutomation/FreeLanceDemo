package cucumber.Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = "src/test/java/features/"
        , glue = "stepDefinitions"
        , plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/TestReport.html"}//,tags="@patchUser"
)
public class TestRunner extends AbstractTestNGCucumberTests {
	/*
	 * mvn test -Dcucumber.options="--plugin com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/TestReport.html" -Dcucumber.options="--tags @TAGNAME" 
	 */
	public static String timeStamp = null;
    public File objExecutionFolder = null;
    public static String reqDirPath = null;
    public static String resDirPath = null;
	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("running before suite");
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date objDate = new Date();
        timeStamp = sdf.format(objDate);
        //create a execution directory
        objExecutionFolder = new File(System.getProperty("user.dir")+"/Req_Res"+"/Execution_" + timeStamp);
        if (!objExecutionFolder.exists()) {
        objExecutionFolder.mkdirs();
        }
        reqDirPath = objExecutionFolder.getAbsolutePath() + "\\Requests\\";
        resDirPath = objExecutionFolder.getAbsolutePath() + "\\Responses\\";
        
        File reqDirectory = new File(reqDirPath);
        if (!reqDirectory.exists()) {
        	reqDirectory.mkdirs();
        }
        File resDirectory = new File(resDirPath);
        if (!resDirectory.exists()) {
        	resDirectory.mkdirs();
        }
	}
}