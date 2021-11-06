package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

public class JsonUtility 
{
	@SuppressWarnings("unchecked")
	public static JSONObject createJsonObject(JSONObject jsonObject,String key, String value) throws FileNotFoundException, IOException, ParseException
	{
		HashMap<String, Object> map = new Gson().fromJson(jsonObject.toString(), HashMap.class);
		System.out.println("Original json String: "+jsonObject.toJSONString());
		if(map.containsKey(key))
		{
			jsonObject.put(key,value);
		}
		return jsonObject;

	}
}
