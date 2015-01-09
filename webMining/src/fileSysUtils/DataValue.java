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

	public void update(DataValue dv){
		// Anciennes valeurs
		String ancienLabel=this.label;
		int ancienNbFiles=this.nbFiles;
		Set<String> ancienSetFileNames=this.setFileNames;
		StatMot ancienStats=this.stats;
		
		// Nouvel ajout
		String nouvLabel=dv.getLabel();
		int nouvNbFiles=dv.getNbFiles();
		Set<String> nouvSetFileNames=dv.getSetFileNames();
		StatMot nouvStats=dv.getStats();
		
		// Valeurs à remplacer
		String finalLabel="";
		int finalNbFiles=0;
		Set<String> finalSetFileNames=new HashSet<String>();
		StatMot finalStats=new StatMot();
		
		
		// Vérification que le mot est bien le bon
		if(ancienLabel.equals(nouvLabel)){
			List<String> nouvListFileNames=new ArrayList<String>();
			nouvListFileNames.addAll(nouvSetFileNames);
			
			// Vérification que la nouvelle liste de fichier que la dv contient est bien de taille 1
			if(nouvListFileNames.size()==1){
				
				// Le fichier n'a pas été renseigné antérieurement
				if (!ancienSetFileNames.contains(nouvListFileNames.get(0))){
					
					// Ajout du fichier dans la liste des fichiers
					finalSetFileNames.addAll(ancienSetFileNames);
					finalSetFileNames.add(nouvListFileNames.get(0));
					
					// Incrémentation du nbFiles
					finalNbFiles=ancienNbFiles+1;
					
					// Création de la stat associée à ce fichier pour le mot
					HashMap<String,Integer> mapTf=ancienStats.getMapTf();
					mapTf.put(nouvListFileNames.get(0), 1);
					finalStats.setMapTf(mapTf);
					
				}
				
				// Le fichier a déjà été renseigné
				else{
					
					// Modification de la stat associée à ce fichier
					HashMap<String,Integer> mapTf=ancienStats.getMapTf();
					Integer tfMotCourant=mapTf.get(nouvListFileNames.get(0));
					mapTf.put(nouvListFileNames.get(0), tfMotCourant+1);
					finalStats.setMapTf(mapTf);
				}
			}
			else{
				System.out.println("Update failed : List of FileNames too big (>1)");
			}
		}
		else{
			System.out.println("Update failed : Labels don't match between old and new");
		}
		
		this.nbFiles=finalNbFiles;
		this.setFileNames=finalSetFileNames;
		this.stats=finalStats;
	}

}
