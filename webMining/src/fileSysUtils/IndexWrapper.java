/**
 * 
 */
package fileSysUtils;


import java.io.File;
import java.io.IOException;
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
	
	public String toString(){
		return root.toString();
	}
	
	public void serializeRoot(){
		JsonNode node = mapper.valueToTree(IndexWrapper.root);
		try {
			this.mapper.writeTree(new JsonGenerator(){

				@Override
				public void close() throws IOException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void copyCurrentEvent(JsonParser arg0)
						throws IOException, JsonProcessingException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void copyCurrentStructure(JsonParser arg0)
						throws IOException, JsonProcessingException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public JsonGenerator disable(Feature arg0) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public JsonGenerator enable(Feature arg0) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void flush() throws IOException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public ObjectCodec getCodec() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public JsonStreamContext getOutputContext() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public boolean isClosed() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean isEnabled(Feature arg0) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public JsonGenerator setCodec(ObjectCodec arg0) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public JsonGenerator useDefaultPrettyPrinter() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void writeBinary(Base64Variant arg0, byte[] arg1,
						int arg2, int arg3) throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeBoolean(boolean arg0) throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeEndArray() throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeEndObject() throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeFieldName(String arg0) throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeNull() throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeNumber(int arg0) throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeNumber(long arg0) throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeNumber(BigInteger arg0) throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeNumber(double arg0) throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeNumber(float arg0) throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeNumber(BigDecimal arg0) throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeNumber(String arg0) throws IOException,
						JsonGenerationException, UnsupportedOperationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeObject(Object arg0) throws IOException,
						JsonProcessingException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeRaw(String arg0) throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeRaw(char arg0) throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeRaw(String arg0, int arg1, int arg2)
						throws IOException, JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeRaw(char[] arg0, int arg1, int arg2)
						throws IOException, JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeRawUTF8String(byte[] arg0, int arg1, int arg2)
						throws IOException, JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeRawValue(String arg0) throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeRawValue(String arg0, int arg1, int arg2)
						throws IOException, JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeRawValue(char[] arg0, int arg1, int arg2)
						throws IOException, JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeStartArray() throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeStartObject() throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeString(String arg0) throws IOException,
						JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeString(char[] arg0, int arg1, int arg2)
						throws IOException, JsonGenerationException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeTree(JsonNode arg0) throws IOException,
						JsonProcessingException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void writeUTF8String(byte[] arg0, int arg1, int arg2)
						throws IOException, JsonGenerationException {
					// TODO Auto-generated method stub
					
				}}, node);
			this.mapper.writeValue(new File("./index/index.json"), node);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
