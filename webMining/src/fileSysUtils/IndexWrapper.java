/**
 * 
 */
package fileSysUtils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
/**
 * @author divanov
 *
 */
public class IndexWrapper {

	/**
	 * 
	 */
	private static TreeRepresentation root = null;
	
	
	private ObjectMapper mapper = new ObjectMapper(){
	 

		//JsonNode node = mapper.valueToTree(IndexWrapper.root);
		
		// convert user object to json string, and save to a file
		//mapper.writeTree(new File("./index/test.json"), node);
		//mapper.writeTree(new JsonGenerator, indexNode);
 
		// display to console
		//System.out.println(mapper.writeValueAsString(index));
 
		
	};


	public IndexWrapper() {
		// TODO Auto-generated constructor stub
	}

	public void insert(String label, DataValue dv) {
		if (root == null) {
			root = new TreeRepresentation(label, dv);
		} else {
			root.insert(label, dv);
			root = root.root();
		}
	}

	public DataValue lookup(String label) {
		if (root != null) {
			return root.lookupDv(label);
		} else {
			return null;
		}
	}
	
	public boolean isEmpty(){
		return IndexWrapper.root == null;
	}
	
	public String toString(){
		return root.toString();
	}
	
	
	/*ObjectMapper m = new ObjectMapper();
	// can either use mapper.readTree(source), or mapper.readValue(source, JsonNode.class);
	JsonNode rootNode = m.readTree(new File("user.json"));
	// ensure that "last name" isn't "Xmler"; if is, change to "Jsoner"
	JsonNode nameNode = rootNode.path("name");
	String lastName = nameNode.path("last").getTextValue().
	if ("xmler".equalsIgnoreCase(lastName)) {
	  ((ObjectNode)nameNode).put("last", "Jsoner");
	}
	// and write it out:
	m.writeValue(new File("user-modified.json"), rootNode);*/
	
	/*TreeMapper treeMapper = new TreeMapper();
	ObjectNode userOb = treeMapper.objectNode();
	Object nameOb = userRoot.putObject("name");
	nameOb.put("first", "Joe");
	nameOb.put("last", "Sixpack");
	userOb.put("gender", User.Gender.MALE.toString());
	userOb.put("verified", false);
	byte[] imageData = getImageData(); // or wherever it comes from
	userOb.put("userImage", imageData);*/

	
	public void serializeRoot() throws IOException{
		
		OutputStream f = new FileOutputStream("./index/index.ser");
		ObjectOutputStream out = new ObjectOutputStream(f);
		out.writeObject(root);
		out.close();
		f.close();
	}
	
	public void deserializeRoot() throws IOException, ClassNotFoundException{
		 FileInputStream fileIn = new FileInputStream("./index/index.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         IndexWrapper.root = (TreeRepresentation) in.readObject();
         in.close();
         fileIn.close();
	}

}
