package quentin;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	public List<String> requeteSplit(String requete){
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
	public String ListeToString(List<String> listeString){
		String result = "";	
		for(String s :listeString){
			result = result + " " + s;
		}
		return result;
	}
	
	
	/**
	 * 
	 * @param requeteSplit : Liste des mots de la requete de l'utilisateur
	 * @return la liste lématiseé de requeteSplit
	 */
	public List<String> requeteLemm(List<String> requeteSplit){
		List<String> listeLemm = new ArrayList<String>();
		//TODO

		
		TreeTaggerWrapper<String> tt = new TreeTaggerWrapper<String>();
//		String res = "";
		try {
			tt.setModel("fichierPartage/french-utf8.par");
//			tt.s
			tt.setHandler(new TokenHandler<String>() {
				String res;
				@Override
				public void token(String token, String pos, String lemma) {
					// TODO Auto-generated method stub
					res=lemma;
//					listeStem.add(lemma);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
//		
//		try {
//			tt.setModel("$HOME/ML/WebMining/TT/lib/french-utf8.par");
//			tt.setHandler(new TokenHandler<String>() {
//				String res;
//
//				@Override
//				public void token(String token, String pos, String lemma) {
//					res = lemma;
//				}
//			});
//			tt.process((String[]) words.toArray());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	
		
		
		for(String s : requeteSplit){
			
//			listeStem.add();
		}
		
		return listeLemm;	
	}
	
	
	
	/**
	 * 
	 * @param requete: Mots de la requete de l'utilisateur
	 * @return la liste stematisée de requeteSplit
	 */
	public List<String> requeteStem(String requete) throws IOException {
		
			// on créé un fichier temporaire pour le stematiser (pour pouvoir utiliser "new FrenchStemmer()).normalize(fichierTempo);")
	       String chemin = "result/tempo.txt";
	       File fichierTempo =new File(chemin); 
	       // listeStem la liste des mots de la requête stematisée
	       List<String> listeStem = new ArrayList<String>();
	       
	       try {
	    	   // création du fichier temporaire et d'un fileWriter dessus
	    	   fichierTempo.createNewFile();
	    	   FileWriter fw = new FileWriter(fichierTempo);
	    	   try {
	    		   // écriture de la requete de l'utilisateur dans le fichier temporaire
	    		   fw.write(requete);
	    		   listeStem = (new FrenchStemmer()).normalize(fichierTempo);
	    			
	    		   // on supprime le fichier temporaire après pour libérer la mémoire car  n'est plus utile.
	    		   fichierTempo.delete();
	    		   
	    		   
			} catch (Exception e) {
				System.out.println("Erreur écriture fichier : " + e.getMessage());
			}
	    	   
			
		} catch (Exception e) {	
			System.out.println("Erreur création fichier : " + e.getMessage());
		}
	       		//delete fichier tempo
		
		return listeStem;	
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
