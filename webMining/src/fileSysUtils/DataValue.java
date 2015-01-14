package fileSysUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import benoit.StatMot;

public class DataValue {

	private String label;
	private int nbFiles;
	private Set<String> setFileNames;
	private StatMot stats;

	public DataValue() {
		super();
	}
	
	public DataValue(String label, int nbFiles, Set<String> setFileNames, StatMot stats){
		super();
		this.label = label;
		this.nbFiles = nbFiles;
		this.setFileNames = setFileNames;
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

	public Set<String> getSetFileNames() {
		return setFileNames;
	}

	public void setSetFileNames(Set<String> setFileNames) {
		this.setFileNames = setFileNames;
	}

	public StatMot getStats() {
		return stats;
	}

	public void setStats(StatMot stats) {
		this.stats = stats;
	}

	// FIXME
	private void updateLabel(String newLabel) {
		System.out.println("DataValue.updateLabel");

		System.out.println("updateLabel.newLabel : " + newLabel);
		if (newLabel != null) {

			System.out.println("this.label : " + this.label);
			this.label = newLabel;
			System.out.println("new label : " + newLabel);
			System.out.println("new this.label : " + this.label);
		} else {
			System.out.println("updateLabel : newLabel == null");
		}

		System.out.println("updateLevel finished" + System.lineSeparator());
	}

	// FIXME
	public void update(DataValue dv) {
		System.out.println("DataValue.update");
		if (dv != null) {
			System.out.println("update != null");
			System.out.println("DV.update label : " + dv.label);
			
			this.updateLabel(dv.label);
		} else {
			System.out.println("null DataValue as update data");
		}

		System.out
				.println("DataValue.update finished" + System.lineSeparator());
	}

	public String toString() {
		String result = "label = " + label + System.lineSeparator()
				+ "nbFiles = " + nbFiles;
		return result;
	}

	// FIXME

	/*
	 * public void update(DataValue dv) { // Anciennes valeurs String
	 * ancienLabel = this.label; int ancienNbFiles = this.nbFiles; Set<String>
	 * ancienSetFileNames = this.setFileNames; StatMot ancienStats = this.stats;
	 * 
	 * // Nouvel ajout String nouvLabel = dv.getLabel(); int nouvNbFiles =
	 * dv.getNbFiles(); Set<String> nouvSetFileNames = dv.getSetFileNames();
	 * StatMot nouvStats = dv.getStats();
	 * 
	 * // Valeurs à remplacer String finalLabel = ""; int finalNbFiles = 0;
	 * Set<String> finalSetFileNames = new HashSet<String>(); StatMot finalStats
	 * = new StatMot();
	 * 
	 * boolean testAncien = true;
	 * 
	 * if (ancienLabel == null) { this.label = dv.getLabel(); testAncien =
	 * false; System.out.println("Update failed : old label incorrect"); } if
	 * (ancienSetFileNames == null) { testAncien = false;
	 * System.out.println("Update failed : old filenames incorrect");
	 * 
	 * } if (ancienStats == null) { testAncien = false;
	 * System.out.println("Update failed : old stats incorrect");
	 * 
	 * }
	 * 
	 * if (testAncien) { // Vérification que le nouveau label n'est pas null if
	 * (nouvLabel != null) { // Vérification que le mot est bien le bon if
	 * (ancienLabel.equals(nouvLabel)) { List<String> nouvListFileNames = new
	 * ArrayList<String>(); nouvListFileNames.addAll(nouvSetFileNames);
	 * List<String> ancienListFileNames = new ArrayList<String>();
	 * ancienListFileNames.addAll(ancienSetFileNames);
	 * 
	 * // Vérification que la nouvelle liste de fichier que la dv // contient
	 * est bien de taille 1 if (nouvListFileNames.size() == 1) {
	 * 
	 * // Vérification que les stats ne sont pas vides if (nouvStats != null) {
	 * 
	 * // Le fichier n'a pas été renseigné antérieurement if
	 * (!ancienSetFileNames.contains(nouvListFileNames .get(0))) {
	 * 
	 * // Ajout du fichier dans la liste des fichiers
	 * finalSetFileNames.addAll(ancienSetFileNames);
	 * finalSetFileNames.add(nouvListFileNames.get(0));
	 * 
	 * // Incrémentation du nbFiles finalNbFiles = ancienNbFiles + 1;
	 * 
	 * // Création de la stat associée à ce fichier // pour le mot
	 * HashMap<String, Integer> mapTf = ancienStats .getMapTf();
	 * mapTf.put(nouvListFileNames.get(0), 1); finalStats.setMapTf(mapTf);
	 * 
	 * }
	 * 
	 * // Le fichier a déjà été renseigné else { //
	 * System.out.println("le fichier existe déjà"); // Modification de la stat
	 * associée à ce fichier HashMap<String, Integer> mapTf = ancienStats
	 * .getMapTf(); // System.out.println(mapTf.toString()); Integer
	 * tfMotCourant = mapTf .get(nouvListFileNames.get(0)); //
	 * System.out.println(tfMotCourant+1); //
	 * System.out.println(nouvListFileNames.get(0));
	 * mapTf.put(nouvListFileNames.get(0), tfMotCourant + 1); //
	 * System.out.println(mapTf.toString()); finalStats.setMapTf(mapTf); //
	 * System.out.println(finalStats.getMapTf().toString()); }
	 * setNbFiles(finalNbFiles); setSetFileNames(finalSetFileNames); this.stats
	 * = finalStats; } else { System.out.println("Update failed : Stats empty");
	 * } } else { System.out
	 * .println("Update failed : List of FileNames too big (>1)"); } } else {
	 * System.out
	 * .println("Update failed : Labels don't match between old and new"); } }
	 * else { System.out.println("Update failed : New label not implemented"); }
	 * }
	 */

	// }

}
