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
		try {
			index.deserializeRoot();
			hmw.deserializeHM();
			hmwd.deserializeHM();
		} catch (Exception e) {
			System.err.println("pas d'index serialise ");
			e.printStackTrace();
			TravailFichier.createIndexStemming("./corpus/", index);
			MainForestier.hmw.setHM(TravailFichier.createIndexNumberDocs("./corpus/"));
			MainForestier.hmwd.setHM(TravailFichier.createIndexDocs("./corpus/").getMapDocs());
		}
		
		
		
		index.serializeRoot();
		hmw.serializeHM();
		hmwd.serializeHM();
		
		/*double beginTime = System.currentTimeMillis();
		for(int i = 0; i<10000; i++){
			index.lookup("argentine");
		}
		double endTime = System.currentTimeMillis();
		System.out.println(endTime - beginTime);*/
		
		
		

	}

}
