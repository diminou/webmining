package benoit;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

	public Integer lookString(String s) {
		if(hm != null){
			return (Integer) hm.get(s);
		} else {
			return null;
		}
	}

	public String lookInt(int integ) {
		String S = "";
		
		Iterator i= hm.keySet().iterator();
		while(i.hasNext()){
			String str = (String) i.next();
			if(hm.get(str)==integ){
				S= str;
			}
		}
		
		
		return S;
	}

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
