package benoit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Classe permettant de définir les méthodes calculant les stats pour les mots du corpus
 * @author benoit
 *
 */
public class TravailStats {

	
	
	
	
	
	
	
	 /**
     * Méthode final de création de l'ensemble des stats d'un folder
     * @param folderName
     * @return
     * @throws IOException
     */
    public static List<IndexMot> createIndexMot(String folderName) throws IOException{
    	List<IndexMot> listeIndexSortie=new ArrayList<IndexMot>();
    	
    	// Index des IndexMots
    	HashMap<String,IndexMot> mapIndexSortie=new HashMap<String,IndexMot>();
    	
    	// Liste des mots du folder
    	List<String> listeMotsFolder=TravailFichier.listerMotsFolder(folderName);

    	// Liste des mots clés du folder
    	List<String> listeMotsClesFolder=TravailFichier.listeMotsCles(listeMotsFolder);
    	
        final File folder = new File(folderName);
        List<String> listeFiles=new ArrayList<String>();
        
        for(final File fileEntry : folder.listFiles()){
        	listeFiles.add(folderName+fileEntry.getName());
        }
        
    	
        // Initialisation de l'index de sortie et de la HashMap<String,IndexMot>
        for (int i=0 ; i<listeMotsClesFolder.size() ; i++){
        	IndexMot indexMotLocal=new IndexMot(listeMotsClesFolder.get(i));
        	listeIndexSortie.add(indexMotLocal);
        	mapIndexSortie.put(listeMotsClesFolder.get(i), indexMotLocal);
        	
        }
        
        // On parcourt tous les fichiers du folder
    	for (int i=0 ; i<listeFiles.size() ; i++) {
    		// Liste des mots du file courant
    		List<String> listMotsFile=TravailFichier.listerMots(listeFiles.get(i));
    		
    		// Pour un fichier, on parcourt tout ses mots
    		for(int j=0 ; j<listMotsFile.size() ; j++){
    			
    			IndexMot indexMotCourant=mapIndexSortie.get(listMotsFile.get(j));
    			int k=0;
    			// On parcourt tout le Set<IndexMot> pour trouver le mot clé du fileCourant
    			while (!listeIndexSortie.get(k).getLabel().equals(indexMotCourant.getLabel())) {
    				k++;
    			}
    			List<String>listeFileNamesMotCourant=new ArrayList<String>();
    					
				listeFileNamesMotCourant.addAll(listeIndexSortie.get(k).getListFileNames());
				
				listeFileNamesMotCourant.add(listeFiles.get(i));
				int nbFilesMotCourant=listeIndexSortie.get(k).getNbFiles();
				nbFilesMotCourant++;
				
				listeIndexSortie.get(k).setListFileNames(listeFileNamesMotCourant);
				listeIndexSortie.get(k).setNbFiles(nbFilesMotCourant);
				
    			
    		}
    	}
    	return listeIndexSortie;
    }
	
}
