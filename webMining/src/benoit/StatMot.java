package benoit;

import java.util.HashMap;
import java.util.Iterator;




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
