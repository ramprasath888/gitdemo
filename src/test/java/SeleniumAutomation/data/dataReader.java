 package SeleniumAutomation.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class dataReader {

	
	public List<HashMap<String, String>> getJsonDataToHash() throws IOException
	{
		//read file json into string
	String json =FileUtils.readFileToString(new File(System.getProperty("User.dir")+"G:\\ram\\java\\SeleniumFramework\\src\\main\\java\\SeleniumAutomation\\resources\\GlobalData.properties"),StandardCharsets.UTF_8);
		
	//now convert string into HashMap using jackson databind
	ObjectMapper mapper =new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(json, new TypeReference<List<HashMap<String, String>>>(){});
	return data;
	
	}
}
