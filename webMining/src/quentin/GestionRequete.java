package quentin;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerWrapper;
import org.tartarus.snowball.SnowballStemmer;

import tools.FrenchStemmer;



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
		return retour;
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
	 * @param listeMot : Liste des mots 
	 * @return la liste des digramme provenant de listeMot (sous la forme [mot1Digramme mot2Digramme])
	 */
	
	public List<String> listeDigramme(List<String> listeMot){
		List<String> listeDigramme = new ArrayList<String>();
		for(int i = 0; i< listeMot.size()-1; i++){
			listeDigramme.add(listeMot.get(i) + " " +  listeMot.get(i+1));
		}
		return listeDigramme; 
	}
	

	/**
	 * 
	 * @param listeMot liste de mot
	 * @return la liste de mot découlant de listeMot privé des mots non discriminant
	 */
	public List<String> listeDisciminante(List<String> listeMot){
		List<String> listeDisc = new ArrayList<String>();
		//TODO
		
		for(String m : listeMot){

//			if( m.isutile){
//				listeDisc.add(m);
//			}
		}
		
		return(listeDisc);
	}
	
	
}
