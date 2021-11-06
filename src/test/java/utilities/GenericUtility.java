package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.testng.Assert;

import io.restassured.response.Response;

public class GenericUtility {
	static FileWriter fileWriter = null;
    public static String getConfigData(String key) {
        Properties objProperties = new Properties();
        FileReader objFileReader = null;
        
        try {
            objFileReader = new FileReader(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "config.properties"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            objProperties.load(objFileReader);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (String) objProperties.get(key);
    }

    public static String getRandomInputText() {
        String randownText = "Test" + (int) (Math.random() * 999999) + (int) (Math.random() * 999999) + "Test";
        return randownText;
    }
    
    public static void assertTheActualWithExpectedResponseValues(String actual, String expected)
    {
    	System.out.println("Actual: "+actual);
    	System.out.println("Expected: "+expected);
    	if(actual.equals(null))
		{
    		actual = "";
		}
    	Assert.assertTrue(actual.equals(expected),"Actual differs from expected");
    }

    public static String fetchTheResponseObjectviaJsonPath(Response objRes, String jsonPath)
    {
    	String result = null;
    	try
    	{
    		result = objRes.body().jsonPath().getString(jsonPath);
    	}
    	catch(Exception e)
    	{
    		result ="";
    	}
    	return result;
    }
    
    public static void assertResultisPositive(int actual)
    {
    	System.out.println("Actual: "+actual);
    	Assert.assertTrue(actual>0,"Value is invalid");
    }
    
    public static ArrayList<String> setListOfDataFromFeatureDataTable(String dataValue)
    {
    	ArrayList<String> lstOfData = new ArrayList<>();
    	String[] data = dataValue.split("\\$");
    	for(String s : data)
    	{
    		lstOfData.add(s);
    	}
    	//System.out.println("lstOfData: "+lstOfData);
		return lstOfData;
    }
    
    public static void assertListcontainsValues(ArrayList<String> lstOfData, String expected)
    {
    	System.out.println("Actual: "+lstOfData);
    	System.out.println("Expected: "+expected);
    	if(!lstOfData.isEmpty())
		{
    		Assert.assertTrue(lstOfData.contains(expected));
		}
    	else
    	{
    	Assert.assertTrue(lstOfData.isEmpty(),"Actual differs from expected");
    	}
    }
    
    public static void writeJsonFile(String fileName,String filepath, String jsonString) throws IOException
    {
    	try {
    	fileWriter = new FileWriter(filepath + File.separator + fileName+".json");
    	fileWriter.write(jsonString);
    	System.out.println("File copied");
    	} catch (IOException e) {
            e.printStackTrace();
 
        } finally {
 
            try {
            	fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
