package quentin;


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
	public List<String> requeteStem(String requete){
//		List<String> listeStem = new ArrayList<String>();
		//TODO
	
		ArrayList<String> words = (new FrenchStemmer()).normalize(requete);
		
		return words;	
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
