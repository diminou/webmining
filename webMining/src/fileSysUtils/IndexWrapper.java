/**
 * 
 */
package fileSysUtils;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;


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
		try {
			this.deserializeRoot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(String label, DataValue dv) {
		if (root == null) {
			root = new TreeRepresentation(label, dv);
		} else {
			root.insert(label, dv);
			root = root.root();
		}
	}
	
	public void delete(String label){
		if(root != null){
			root.delete(label);
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
