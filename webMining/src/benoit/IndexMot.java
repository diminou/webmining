package benoit;

import java.util.ArrayList;
import java.util.List;


public class IndexMot {

	private String label;
	private int nbFiles;
	private List<String> listFileNames;
	private StatMot stats;
	
	/**
	 * Constructeur 3 param
	 * @param label
	 * @param nbFiles
	 * @param listFileNames
	 */
	public IndexMot(String label, int nbFiles, List<String> listFileNames, StatMot stats){
		super();
		this.label=label;
		this.nbFiles=nbFiles;
		this.listFileNames=listFileNames;
		this.stats=stats;
	}
	
	
	public IndexMot(){
		super();
		this.label="";
		this.nbFiles=0;
		this.listFileNames=new ArrayList<String>();
		this.stats=new StatMot();
	}
	
	
	
	
	public IndexMot(String string) {
		super();
		this.label=string;
		this.listFileNames=new ArrayList<String>();
		this.stats=new StatMot();
	}


	public StatMot getStats() {
		return stats;
	}


	public void setStats(StatMot stats) {
		this.stats = stats;
	}


	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getNbFiles() {
		return nbFiles;
	}
	public void setNbFiles(int nbFiles) {
		this.nbFiles = nbFiles;
	}
	public List<String> getListFileNames() {
		return listFileNames;
	}
	public void setListFileNames(List<String> listFileNames) {
		this.listFileNames = listFileNames;
	}


	@Override
	public String toString() {
		return "IndexMot [label=" + label + ", nbFiles=" + nbFiles
				+ ", listFileNames=" + listFileNames + ", stats=" + stats + "]";
	}

	
	
	
	
	
	
	
	
}