package quentin;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import tools.FrenchStemmer;
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
	
	
	
//	/**
//	 * 
//	 * @param requeteSplit : Liste des mots de la requete de l'utilisateur
//	 * @return la liste lématiseé de requeteSplit
//	 */
//	public static List<String> requeteLemm(List<String> requeteSplit){
//		final List<String> listeLemm = new ArrayList<String>();
//		//TODO
//		System.setProperty("treetagger.home", "/home/quentin/WebMining/TreeTagger");
//		TreeTaggerWrapper<String> tt = new TreeTaggerWrapper<String>();
//	
//		try {
//			tt.setModel("fichierPartage/french-utf8.par");
//			tt.setHandler(new TokenHandler<String>() {
//				String res = "";
//				@Override
//				public void token(String token, String pos, String lemma) {
//					// TODO Auto-generated method stub
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
//			// TODO: handle exception
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
 * @param A : mot 1 du digramme
 * @param B : mot 2 du digramme
 * @param root 
 * @return set des titres des documents contenant A et B
 */
	public static Set<String> GestionDigramme(String A, String B, TreeRepresentation root){
		// vérif arbre non nul
		TreeRepresentation treeA = root.lookup(A);
		Set<String> setDocA = new HashSet<String>();
		 setDocA = treeA.getData().getDocuments();
		
		TreeRepresentation treeB = root.lookup(B);
		Set<String> setDocB = new HashSet<String>();
		setDocB = treeB.getData().getDocuments();
		
		Set<String> setDocAB = new HashSet<String>();
		setDocAB= setDocA;
		
		setDocAB.retainAll(setDocB);
		
		return setDocAB;
		
	}
	
	// listemotd'un doc 
	
	public static void tempo(List<String> listeMotDoc, String motA, String MotB){
		//trouver la position du mot A, du mot B, calculer leur distance, si distance < seuil ==> stocke digramme
		
		
	}
	
	
//TODO supprimer
//	/**
//	 * 
//	 * @param listeMot liste de mot
//	 * @return la liste de mot découlant de listeMot privé des mots non discriminant
//	 */
//	public List<String> listeDisciminante(List<String> listeMot){
//		List<String> listeDisc = new ArrayList<String>();
//		//TODO
//		
//		for(String m : listeMot){
//
////			if( m.isutile){
////				listeDisc.add(m);
////			}
//		}
//		
//		return(listeDisc);
//	}
	
	
}
