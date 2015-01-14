package fileSysUtils;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import benoit.TravailFichier;

public class MainForestier {

	public static void main(String[] args) throws IOException {
		
		IndexWrapper index = new IndexWrapper();
		index.insert("naturisme", null);
		System.out.println(index.toString());
		
		index.insert("boloss", null);
		System.out.println(index.toString());
		
		index.insert("xenophobe", null);
		System.out.println(index.toString());
		
		index.insert("excellent", null);
		System.out.println(index.toString());
		
		index.insert("ablation", null);
		System.out.println(index.toString());
		
		index.insert("acne", null);
		System.out.println(index.toString());
		
		index.insert("aperitif", null);
		System.out.println(index.toString());
		
		index.insert("apx", null);
		System.out.println(index.toString());
		
		index.insert("apx", null);
		System.out.println(index.toString());
		
		
		index.insert("acne", new DataValue());
		System.out.println(index.toString());
		
		System.err.println(index.lookup("acne").toString());
		
		index.insert("acne", new DataValue());
		System.out.println(index.toString());
		
		System.err.println(index.lookup("acne").toString());
		
		
		
		//TravailFichier.createIndex("./corpus/");
		
		
		
		
		
		/*ObjectMapper mapper = new ObjectMapper();
	 
		try {
	 
			JsonNode node = mapper.valueToTree(index);
			
			// convert user object to json string, and save to a file
			//mapper.writeTree(new File("./index/test.json"), node);
			//mapper.writeTree(new JsonGenerator, indexNode);
	 
			// display to console
			System.out.println(mapper.writeValueAsString(index));
	 
		} catch (JsonGenerationException e) {
	 
			e.printStackTrace();
	 
		} catch (JsonMappingException e) {
	 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}*/

	}

}
