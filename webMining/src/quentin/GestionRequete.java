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
	 * 
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
	 * 
	 * @param s : un mot de la requete
	 * @param nomDoc : un document 
	 * @param mapTf : la map contenant document et occurence
	 * @return le nombre d'occurence de s dans nomDoc
	 */
	public static double calculOccurence(String s, String nomDoc, HashMap<String,Integer> mapTf){
		double occurence=0;
		
//		parcours String de hashmap, quand égale à s, on retient la value associé
		
		if(mapTf.containsKey(nomDoc)){
		
		Set cles = mapTf.keySet();
		Iterator<String> i = cles.iterator();
		while(i.hasNext()){
			String document = i.next();
			if(document.equals(nomDoc)){
				occurence = (double)mapTf.get(document); //occurence = di
				break;
			}
		}			
		
		}else {
			occurence=0;
		}
		return occurence;
	}
	
	
	
	/** 
	 * 
	 * @param req : requete de l'utilisateur
	 * @param nomDoc : nom du document sur lequel on calcul le score associé à la requete
	 * @param root
	 * @return Le score du document associé à la requete
	 */
	public static double calculScoreDoc(String req, String nomDoc,  TreeRepresentation root){
		double score=0;
		List<String> listeRequete= new ArrayList<String>();
		listeRequete = requeteSplit(req);;
	
		double num=0;
		double denom = 0;
		List<Double> listeOccurence = new ArrayList<Double>();
		
		for(String s :listeRequete){
			DataValue DV = root.lookupDv(s);
			Set<String> setDoc = new HashSet<String>();			//set des documents contenant le mot s
			setDoc = DV.getDocuments();
			
			
			HashMap<String,Integer> mapTf = new HashMap<String,Integer>();
			//TODO décommenter quand ben a fait le truc
//			mapTf = DV.getStats().getTF();
			
			Iterator<String> iter = setDoc.iterator();
			while(iter.hasNext()){
				String doc = iter.next();
				if(doc.equals(nomDoc)){
					double di =0;
					di= calculOccurence(s, doc, mapTf);
					listeOccurence.add(di);
				}
			}

		}
		
		for(Double d : listeOccurence){
			num = num + d;
			denom = denom + (d*d);
		}
		if(denom!=0.0){
			score=num/denom;
		}else {
			score=0;
		}
		
		return score;
	}
	

	/**
	 * 
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
			Set<String> listeDocReq = root.lookupDv(s).getDocuments();
			listeAllDocReq.addAll(listeDocReq);
		}
		
		HashMap<String, Double> HM = new HashMap<String, Double>();
		// On calcul le score associé à chaque document
		Iterator<String> i = listeAllDocReq.iterator();
		while(i.hasNext()){
			String nomDoc = i.next();
			double score =0;
			score =calculScoreDoc(req, nomDoc, root);
			HM.put(nomDoc, score);			
		}
		
		return HM;
	}

	/**
	 * 
	 * @param map : hashmap<nomDocument, score> à triée
	 * @return treeMap donnant la map triée par ordre de score décoissant
	 */
	public static TreeMap<String, Double> classerDocument(HashMap<String, Double> map){
		
		ValueComparator comparateur = new ValueComparator(map);
		TreeMap<String,Double> mapTriee = new TreeMap<String,Double>(comparateur);
		
		mapTriee.putAll(map);
		
		return mapTriee;
	}
	
	
	
}
