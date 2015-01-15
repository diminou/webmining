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

import benoit.TravailFichier;
/**
 * @author divanov
 *
 */
public class IndexWrapper {

	/**
	 * 
	 */
	private static TreeRepresentation root = null;
	


	public IndexWrapper() {
		super();
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
