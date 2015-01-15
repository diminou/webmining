package benoit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;




/**
 * Contient pour chaque mot les statistiques qui lui sont attribuées :
 * -"TF" : nb d'occurrence du mot dans chaque document dans lequel il est présent
 * -"IDF" : log10(NbDocsTotal/NbDocsContennantLeMot)
 * -"Wt,d" : TF*IDF
 * -"Score" : p15 présentation
 * 
 * @author benoit
 *
 */
public class StatMot {
	
	private HashMap<String,Integer> mapTf;
	
	
	
	public StatMot() {
		super();
		this.mapTf=new HashMap<String,Integer>();
	}
	

	
	
	public void setMapTf(HashMap<String, Integer> mapTf) {
		this.mapTf = mapTf;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((mapTf == null) ? 0 : mapTf.hashCode());

		return result;
	}


	public HashMap<String, Integer> getMapTf() {
		return mapTf;
	}



	public StatMot(HashMap<String, Integer> mapTf) {
		super();
		this.mapTf = mapTf;
	}
	
	//FIXME
	public void update(StatMot sm){
		HashMap<String, Integer> ancienneMapTf = this.getMapTf();
		HashMap<String, Integer> nouvMapTf = sm.getMapTf();
		
		List<String> listeFiles=new ArrayList<String>();
		
		// On récupère la liste des files de la nouvelle map
		for (Iterator<String> i = nouvMapTf.keySet().iterator() ; i.hasNext() ; ){
		    listeFiles.add(i.next()); 
		}

		// On parcourt les fileNames pour modifier les stats de la hashmap
		for(String fileName : listeFiles){
			
			// Si le fichier a déjà été ajouté comme présent dans ce document : on incrémente
			if(ancienneMapTf.containsKey(fileName)){
				Integer tfMotCourant = ancienneMapTf.get(fileName);
				ancienneMapTf.put(fileName,	tfMotCourant + 1);
			}
			// Si le fichier n'a jamais été ajouté comme présent dans ce document : on ajoute
			else{
				ancienneMapTf.put(fileName,	1);
			}
		}
		// Mise à jour de l'ancienne mapTf
		setMapTf(ancienneMapTf);
		
	}
	
	
	@Override
	public String toString() {
		Iterator<String> iterator = this.mapTf.keySet().iterator();
		String result = "";
		while (iterator.hasNext()) {
			String key = iterator.next();
			result = result + System.getProperty("line.separator") + key
					+ " : " + mapTf.get(key);
		}

		return result;
	}













}
