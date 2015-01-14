package benoit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class IndexMot {

	private String label;
	private int nbFiles;
	private Set<String> setFileNames;
	private StatMot stats;
	
	/**
	 * Constructeur 4 param
	 * @param label
	 * @param nbFiles
	 * @param listFileNames
	 * @param stats
	 */
	public IndexMot(String label, int nbFiles, Set<String> setFileNames, StatMot stats){
		super();
		this.label=label;
		this.nbFiles=nbFiles;
		this.setFileNames=setFileNames;
		this.stats=stats;
	}
	
	
	public IndexMot(){
		super();
		this.label="";
		this.nbFiles=0;
		this.setFileNames=new HashSet<String>();
		this.stats=new StatMot();
	}
	
	
	
	
	public IndexMot(String string) {
		super();
		this.label=string;
		this.setFileNames=new HashSet<String>();
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
	public Set<String> getListFileNames() {
		return setFileNames;
	}
	public void setListFileNames(Set<String> setFileNames) {
		this.setFileNames = setFileNames;
	}


	@Override
	public String toString() {
		return "IndexMot [label=" + label + ", nbFiles=" + nbFiles
				+ ", listFileNames=" + setFileNames + ", stats=" + stats + "]";
	}

	
	
	
	
	
	
	
	
}
