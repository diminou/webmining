package benoit;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fileSysUtils.IndexWrapper;
import fileSysUtils.TreeRepresentation;

public class HashMapWrapper {

	/**
		 * 
		 */
	private static HashMap<String, Integer> hm = new HashMap<String, Integer>();

	public HashMapWrapper() {
		super();
	}

	public void setHM(HashMap<String, Integer> hashmap) {
		hm = hashmap;
	}

	public int lookString(String s) {
		return hm.get(s);
	}

//	public String lookInt(int integ) {
//		String S = "";
//		List<Integer> liste = new ArrayList<Integer>();
//		liste = (List<Integer>) hm.values();
//		for (int i = 0; i < liste.size(); i++) {
//			if (liste.get(i) == integ) {
//				List<String> listeString = new ArrayList<String>();
//				S = listeString.get(i);
//			}
//		}
//		return S;
//	}

	public String toString() {
		return hm.toString();
	}

	public boolean isEmpty() {
		return HashMapWrapper.hm == null;
	}

	public void serializeHM() throws IOException {
		OutputStream f = new FileOutputStream("./index/hm.ser");
		ObjectOutputStream out = new ObjectOutputStream(f);
		out.writeObject(hm);
		out.close();
		f.close();
	}

	public void deserializeHM() throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream("./index/hm.ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		HashMapWrapper.hm = (HashMap<String, Integer>) in.readObject();
		in.close();
		fileIn.close();
	}

}
