package benoit;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class HashMapWrapDouble {
	
	
		private static HashMap<String, Double> hm = new HashMap<String, Double>();

		public HashMapWrapDouble() {
			super();
			try {
				this.deserializeHM();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		public void setHM(HashMap<String, Double> hashmap) {
			hm = hashmap;
		}

		public Double lookString(String s) {
			if (hm!= null) {
				return (Double)(hm.get(s));
			} else {
				return null;
			}
		}

//		public String lookInt(int integ) {
//			String S = "";
//			List<Integer> liste = new ArrayList<Integer>();
//			liste = (List<Integer>) hm.values();
//			for (int i = 0; i < liste.size(); i++) {
//				if (liste.get(i) == integ) {
//					List<String> listeString = new ArrayList<String>();
//					S = listeString.get(i);
//				}
//			}
//			return S;
//		}

		public String toString() {
			return hm.toString();
		}

		public boolean isEmpty() {
			return HashMapWrapDouble.hm == null;
		}

		public void serializeHM() throws IOException {
			OutputStream f = new FileOutputStream("./index/hmDouble.ser");
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(hm);
			out.close();
			f.close();
		}

		public void deserializeHM() throws IOException, ClassNotFoundException {
			FileInputStream fileIn = new FileInputStream("./index/hmDouble.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			HashMapWrapDouble.hm = (HashMap<String, Double>) in.readObject();
			in.close();
			fileIn.close();
		}

	
}
