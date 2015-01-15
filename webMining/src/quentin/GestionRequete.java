package quentin;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import tools.FrenchStemmer;
import fileSysUtils.DataValue;
import fileSysUtils.TreeRepresentation;



public class GestionRequete {

	
	
	/**
	 * 
	 * @param requete : requete de l'utilisateur
	 * @return la liste de String contenant chaque mot de la requete
	 */
	public static List<String> requeteSplit(String requete){
		List<String> retour = new ArrayList<String>();
		String[] split = requete.split("\\s+");
		for(String s: split){
				retour.add(s);
		}
		return (retour);
	}
	
	
	/**
	 * 
	 * @param listeString liste de string à concaténer
	 * @return Un String contenant la concaténation de ListeString
	 */
	public static String ListeToString(List<String> listeString){
		String result = "";	
		for(String s :listeString){
			result = result + " " + s;
		}
		return result;
	}
	
	
	//TODO essaie lemming infructueux
//	/**
//	 * 
//	 * @param requeteSplit : Liste des mots de la requete de l'utilisateur
//	 * @return la liste lématiseé de requeteSplit
//	 */
//	public static List<String> requeteLemm(List<String> requeteSplit){
//		final List<String> listeLemm = new ArrayList<String>();
//		System.setProperty("treetagger.home", "/home/quentin/WebMining/TreeTagger");
//		TreeTaggerWrapper<String> tt = new TreeTaggerWrapper<String>();
//	
//		try {
//			tt.setModel("fichierPartage/french-utf8.par");
//			tt.setHandler(new TokenHandler<String>() {
//				String res = "";
//				@Override
//				public void token(String token, String pos, String lemma) {
//					res=lemma;
//					System.out.println(lemma);
//					listeLemm.add(lemma);
//				}
//			});
//			List<String> liste = new ArrayList<String>();
//			liste.add("This");
//			liste.add("is");
//			liste.add("a");
//			liste.add("test");
//		    tt.process(liste);
//		} catch (Exception e) {
//			System.out.println("Erreur : " + e.getMessage());
//		}
//		
//		return listeLemm;	
//	}

	
	
	/**
	 * Retourne la List<String> stematisée de la requête
	 * @param requete: Mots de la requete de l'utilisateur
	 * @return la liste stematisée de requeteSplit
	 */
	public static List<String> requeteStem(String requete) throws IOException {
		
			// on créé un fichier temporaire pour le stematiser (pour pouvoir utiliser "new FrenchStemmer()).normalize(fichierTempo);")
	       String chemin = "results/tempo.txt";
	       File folder = new File("results/");
	       for(File f : folder.listFiles()){
	    	   if(f.getName().equals("tempo.txt") ){
	    		   f.delete();
	    	   }
	       } 
	       
	       File fichierTempo = new File(chemin); 
	       // listeStem la liste des mots de la requête stematisée
	       List<String> listeStem = new ArrayList<String>();
	       
	       try {
	    	   
	    	   // création du fichier temporaire et d'un fileWriter dessus
	    	   fichierTempo.createNewFile();
	    	   FileWriter fw = new FileWriter(fichierTempo,true);
	    	   try {
	    		   // écriture de la requete de l'utilisateur dans le fichier temporaire
	    		fw.write(requete);
	    		fw.flush();
		    	fw.close();
	    		listeStem = (new FrenchStemmer()).normalize(fichierTempo);
	    		  
	    		   // on supprime le fichier temporaire après pour libérer la mémoire car  n'est plus utile.
	    		fichierTempo.delete();
	    		return listeStem;
	    		   
			} catch (Exception e) {
				System.out.println("Erreur d'écriture de fichier : " + e.getMessage());
				return listeStem;	
			}
	    	   
			
		} catch (Exception e) {	
			System.out.println("Erreur de création de fichier : " + e.getMessage());
			return listeStem;	
		}
	
	}

	/**
	 * Retourne le nombre d'occurence d'un mot dans un document
	 * @param s : un mot de la requete
	 * @param nomDoc : un document 
	 * @param mapTf : la map contenant document et occurence
	 * @return le nombre d'occurence de s dans nomDoc
	 */
	public static double calculOccurence(String nomDoc, HashMap<String,Integer> mapTf){
		double occurence=0.0;
		
//		parcours String de hashmap, quand égale à s, on retient la value associé
		
		if(mapTf.containsKey(nomDoc)){
		
//		Set cles = mapTf.keySet();
//		Iterator<String> i = cles.iterator();
//		while(i.hasNext()){
//			String document = i.next();
//			if(document.equals(nomDoc)){
//				occurence = (double)mapTf.get(document); //occurence = di
//				break;
//			}
//		}	
			
			occurence= (double) mapTf.get(nomDoc);
		
		}else {
			occurence=0.0;
		}
		return occurence;
	}
	
	
	
	/** 
	 * Retourne le score du document associé à la requête
	 * @param req : requete de l'utilisateur
	 * @param nomDoc : nom du document sur lequel on calcul le score associé à la requete
	 * @param root
	 * @return Le score du document associé à la requete
	 */
	public static double calculScoreDoc(String req, String nomDoc,  TreeRepresentation root){
		double score=0;
		List<String> listeRequete= new ArrayList<String>();
		listeRequete = requeteSplit(req);
	
		double num=0.0;
		double denom = 0.0;
		List<Double> listeOccurence = new ArrayList<Double>();
		
		// On parcourt l'ensemble des mots de la requête
		for(String s :listeRequete){
			DataValue DV = root.lookupDv(s);
			
			Set<String> setDoc = DV.getSetFileNames();					//set des documents contenant le mot s
			HashMap<String,Integer> mapTf = DV.getStats().getMapTf();   //map des TF
			
			// On parcourt l'ensemble des documents d'appartenance du mot de la requête à la recherche du doc d'entrée			
			if (setDoc.contains(nomDoc)){
				double di =0.0;
				di= calculOccurence(nomDoc, mapTf);
				listeOccurence.add(di);
				
			}
			
			//TODO à suppr
//			Iterator<String> iter = setDoc.iterator();
//			while(iter.hasNext()){
//				String doc = iter.next();
//				if(doc.equals(nomDoc)){
//					double di =0;
//					di= calculOccurence(doc, mapTf);
//					listeOccurence.add(di);
//				}
//			}

		}
		
		for(Double d : listeOccurence){
			num = num + d;
			denom = denom + (d*d);
		}
		if(denom!=0.0){
			score=num/Math.sqrt(denom);
		}else {
			score=0.0;
		}
		
		return score;
	}
	

	/**
	 * Retourne la map non triée contenant tous les documents associée à la requete et leur score associé
	 * @param req : requete complète de l'utilisateur
	 * @param root 
	 * @return : la map non triée contenant tous les documents associée à la requete et leur score associé.
	 */
	public static HashMap<String, Double> CalculAllScore(String req,  TreeRepresentation root){
		
		//contient tous les documents contenant au moins un mots de la requete
		Set<String> listeAllDocReq = new HashSet<String>();
		
		List<String> listeRequete= new ArrayList<String>();
		listeRequete = requeteSplit(req);
		for(String s : listeRequete){
			Set<String> listeDocReq = root.lookupDv(s).getSetFileNames();
			listeAllDocReq.addAll(listeDocReq);
		}
		
		HashMap<String, Double> HM = new HashMap<String, Double>();
		// On calcul le score associé à chaque document
		Iterator<String> i = listeAllDocReq.iterator();
		while(i.hasNext()){
			String nomDoc = i.next();
			double score =0.0;
			score =calculScoreDoc(req, nomDoc, root);
			HM.put(nomDoc, score);			
		}
		
		return HM;
	}

	/**
	 * Retourne la treeMap donnant la map triée par ordre de score décroissant
	 * @param map : hashmap<nomDocument, score> à triée
	 * @return treeMap donnant la map triée par ordre de score décoissant
	 */
	public static TreeMap<String, Double> classerDocument(HashMap<String, Double> map){
		
		ValueComparator comparateur = new ValueComparator(map);
		TreeMap<String,Double> mapTriee = new TreeMap<String,Double>(comparateur);
		
		mapTriee.putAll(map);
		
		return mapTriee;
	}
	
	/**
	 * Print la TreeMap
	 * @param treeMap
	 */
	public static void printMap(TreeMap<String, Double> treeMap) {
	    Iterator it = treeMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        System.out.println(pairs.getKey() + " = " + pairs.getValue());
	        it.remove();
	    }
	}
	
	
	/**
	 * 
	 * @param treeMap
	 * @return la liste contenant les clés de la treemap (utile pour l'interface)
	 */
	public static List<String> mapKeyToListe(TreeMap<String, Double> treeMap){
		List<String> listeString = new ArrayList<String>();
		
		Iterator it = treeMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        listeString.add((String) pairs.getKey());

	        it.remove();
	    }
	    return listeString;
	}
	
}
