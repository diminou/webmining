package fileSysUtils;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class MainForestier {

	public static void main(String[] args) {
		TreeRepresentation root = new TreeRepresentation("naturisme", null);
		System.out.println(root.toString());
		
		root.insert("boloss", null);
		root = root.root();
		System.out.println(root.toString());
		
		root.insert("xenophobe", null);
		root = root.root();
		System.out.println(root.toString());
		
		root.insert("excellent", null);
		root = root.root();
		System.out.println(root.toString());
		
		root.insert("ablation", null);
		root = root.root();
		System.out.println(root.toString());
		
		root.insert("acne", null);
		root = root.root();
		System.out.println(root.toString());
		
		root.insert("aperitif", null);
		root = root.root();
		System.out.println(root.toString());
		
		root.insert("apx", null);
		root = root.root();
		System.out.println(root.toString());
		
		root.insert("apx", null);
		root = root.root();
		System.out.println(root.toString());
		
		
		/*ObjectMapper mapper = new ObjectMapper();
	 
		try {
	 
			// convert user object to json string, and save to a file
			mapper.writeValue(new File("./index/test.json"), root);
			//mapper.writeTree(new JsonGenerator, rootNode);
	 
			// display to console
			System.out.println(mapper.writeValueAsString(root));
	 
		} catch (JsonGenerationException e) {
	 
			e.printStackTrace();
	 
		} catch (JsonMappingException e) {
	 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}*/

	}

}
