package fileSysUtils;

import interfaceGraphique.Fenetre;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import benoit.HashMapWrapDouble;
import benoit.HashMapWrapper;
import benoit.StatMot;
import benoit.TravailFichier;

public class MainForestier {

	static IndexWrapper index = new IndexWrapper();
	static HashMapWrapper hmw = new HashMapWrapper();
	static HashMapWrapDouble hmwd = new HashMapWrapDouble();
	static BackgroundUpdater bu1 = new BackgroundUpdater();

	public static void main(String[] args) throws IOException {
		
		
		
		
		
		index.insert("naturisme", null);
		
		index.insert("boloss", null);
		
		index.insert("xenophobe", null);
		
		index.insert("excellent", null);
		
		index.insert("ablation", null);
		
		index.insert("acne", null);
		
		index.insert("aperitif", null);
		
		index.insert("apx", null);
		
		index.insert("apx", null);
		
		
		
		/*private String label;
		private int nbFiles;
		private Set<String> setFileNames;
		private StatMot stats;
		

		DataValue dv0 = new DataValue("lab0", 0, new HashSet<String>(), new StatMot());
		
		index.insert("acne", dv0);
		
		
		DataValue dv = new DataValue("lab1", 0, new HashSet<String>(), new StatMot());

		
		System.err.println(index.lookup("acne") == null);
		
		
		
		index.insert("acne", dv);
		System.err.println(index.lookup("acne").toString());
		
		DataValue dv2 = new DataValue("lab2", 666, new HashSet<String>(), new StatMot());
		System.err.println("dv.getLabel : " + dv.getLabel() );
		
		index.insert("acne", dv2);
		
		
		DataValue dv3 = new DataValue("lab3", 777, new HashSet<String>(), new StatMot());
		System.err.println("dv.getLabel : " + dv.getLabel() );

		
		index.insert("acne", dv3);
		
		
		
		DataValue dv4 = new DataValue();
		dv4.setLabel("lab4");
		System.err.println("dv.getLabel : " + dv.getLabel() );

		
		index.insert("acne", dv4);
		
		
		DataValue testDv = new DataValue();
		System.out.println(testDv.getLabel());
		
		index.serializeRoot();
		System.out.println("serialize");*/
		
		
		
		Fenetre fenetre = new Fenetre();
		TravailFichier.createIndexStemming("./corpus/", index);
		MainForestier.hmw.setHM(TravailFichier.createIndexNumberDocs("./corpus/"));
		MainForestier.hmwd.setHM(TravailFichier.createIndexDocs("./corpus/").getMapDocs());
		
		index.serializeRoot();
		hmw.serializeHM();
		hmwd.serializeHM();
		
		
		try {
			index.deserializeRoot();
			System.out.println("deserialize");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(index.toString());
		System.out.println(index.lookup("lienne").getSetFileNames().toString());
		System.out.println(index.lookup("lienne").getStats().toString());
		

	}

}
