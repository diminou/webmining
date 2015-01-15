package quentin;	

import interfaceGraphique.Fenetre;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import benoit.HashMapWrapDouble;
import benoit.HashMapWrapper;
import benoit.TravailFichier;

public class MainQuentin {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		System.out.println(GestionRequete.requeteSplit("petit test séparation requete"));
//		List<String> listeString = GestionRequete.requeteSplit("petit test séparation requete");
//		System.out.println(GestionRequete.ListeToString(listeString) );
//		
//		System.out.println(GestionRequete.requeteStem("petit test stemmatisation requete"));
	
		
//		HashMap<String, Double> hm = new HashMap<String, Double>();
//
//		hm.put("aaa", 0.20);
//		hm.put("z", 1154.0);
//		hm.put("a", 2569.0);
//		hm.put("bbb", 0.30);
//		hm.put("c", 36.0);	
//		hm.put("b", 12.0);
//		System.out.println(GestionRequete.classerDocument(hm));
		
//		Fenetre f = new Fenetre();
			
//		HashMap<String, Integer> hm = TravailFichier.createIndexNumberDocs("./corpus/");
//		HashMapWrapper hmw = new HashMapWrapper();
//		
//		
//		hmw.setHM(hm);
//		
//		hmw.serializeHM();
//		try {
//			hmw.deserializeHM();
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(hmw.toString());
		
		
		HashMap<String, Double> hm = TravailFichier.createIndexDocs("./corpus/").getMapDocs();

		HashMapWrapDouble hmw = new HashMapWrapDouble();
		
		
	//		hmw.setHM(hm);
	//		
	//		hmw.serializeHM();
		try {
			hmw.deserializeHM();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(hmw.toString());
	}

}	
