package benoit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fileSysUtils.DataValue;
import fileSysUtils.TreeRepresentation;
import td.td1.FrequencyMap;
import tools.FrenchStemmer;

public class TravailFichier {
	
	
	/**
	 * Retourne la List<String> des mots "STEMISES" d'un fichier
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String> stemming(String filename) throws IOException {
        
    	ArrayList<String> words=(new FrenchStemmer()).normalize(new File(filename));
    	return words;
    }
	
	/**
	 * Retourne la Map<String,Integer> d'occurences d'une ArrayList
	 * @param liste
	 * @return
	 */
    public static Map<String,Integer> occurrence(List<String> liste){
    	
    	return new FrequencyMap(liste).getFrequencies();
    }
    
    /**
     * Retourne la List<FrequencyMap> d'un folder de fichiers lemmatises
     * @param folderName
     */
    public static List<FrequencyMap> returnOccurencesFolderParFichier(String folderName){
    	
    	final File folder = new File(folderName);
		final File lemmes = new File ("./lemmes");
		Charset utf8 = StandardCharsets.UTF_8;
		
		List<FrequencyMap> listeFreqMap=new ArrayList<FrequencyMap>();
		
		for (final File fileEntry : folder.listFiles()) {
			try {
				ArrayList<String> words = (new FrenchStemmer()).normalize(fileEntry);

				FrequencyMap fm = new FrequencyMap(words);
				

				String path = "./results/" + fileEntry.getName();
		
				ArrayList<String> content = new ArrayList<String>();
				content.add(fm.toString());
				(new File(path)).createNewFile();
				Files.write(Paths.get(path), content, utf8, StandardOpenOption.CREATE);

				PrintWriter writer = new PrintWriter(new File(path), "UTF-8");
				writer.println(fm.toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (final File fileEntry : lemmes.listFiles()) {
			List<String> lemmWords;
			try {
				lemmWords = recupererIeMots(fileEntry.getName(),3);
				FrequencyMap lemmFm = new FrequencyMap(lemmWords);
				listeFreqMap.add(lemmFm);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return listeFreqMap;
    }
    
   /**
    * Retourne la List<String> des mots d'un fichier
    * @param filename
    * @return
    * @throws IOException
    */
    public static List<String> listerMots(String filename) throws IOException{
    	ArrayList<String> text = new ArrayList<String>();
		//lecture du fichier texte	
		InputStream ips=new FileInputStream(filename); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		String line;
		while ((line=br.readLine())!=null){
			
			String[] ligneTemp=line.split(" ");
			for (int i=0 ; i<ligneTemp.length ; i++){
			text.add(ligneTemp[i]);
			}
		}
		br.close(); 
		return text;
    }


    
    
    /**
     * Retourne la List<String> des mots d'un folder
     * @param folderName
     * @return
     * @throws IOException
     */
    public static List<String> listerMotsFolder(String folderName) throws IOException{
    	
    	final File folder = new File(folderName);
    	
    	List<String> listeMotsFolder=new ArrayList<String>();
    	
    	
    	for (final File fileEntry : folder.listFiles()) {

			String filename=folderName+fileEntry.getName();
			List<String> words = listerMots(filename);
			
			for (int i=0 ; i<words.size() ; i++){
				listeMotsFolder.add(words.get(i));
				
			}	
    	}
    	return listeMotsFolder;
    }
    	
   /**
    * Retourne la List<String> des mots clés d'une List<String>
    * @param listeMots
    * @return
    */
    public static Set<String> listeMotsCles (List<String> listeMots){
    	
    	Set<String> setSortie=new HashSet<String>();
    	
    	for (int i=0 ; i<listeMots.size() ; i++){
    		setSortie.add(listeMots.get(i));
    		
    	}

    	return setSortie;
    }
    
    /** Retourne la List<String> des mots cles d'un folder
    * @param folderName
    * @return
    * @throws IOException
    */
   public static Set<String> listeMotsClesFolder(String folderName) throws IOException{
   	
   	final File folder = new File(folderName);
   	

   	Set<String> setMotsFolder=new HashSet<String>();
   	
   	
   	for (final File fileEntry : folder.listFiles()) {

			String filename=folderName+fileEntry.getName();
			List<String> words = listerMots(filename);
			
			for (int i=0 ; i<words.size() ; i++){
				setMotsFolder.add(words.get(i));
				
			}	
   	}
   	return setMotsFolder;
   }
    
	/**
	 * Retourne la List<String> des ie mots de chaque lignes d'un fichier lemmatise
	 * @param file
	 * @return
	 * @throws IOException
	 */
    public static ArrayList<String> recupererIeMots(String file, int i) throws IOException{
    	
    	ArrayList<String> text = new ArrayList<String>();
		//lecture du fichier texte	
		InputStream ips=new FileInputStream(file); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		String line;
		while ((line=br.readLine())!=null){
			if (line.split("\t").length>2){
			String temp=line.split("\t")[i-1];
			text.add(temp);
			}
		}
		br.close(); 
		return text;
		
    }
        
	
    
    /**
//     * Méthode final de création de l'index d'un folder
//     * @param folderName
//     * @return
//     * @throws IOException
//     */
//    public static Set<IndexMot> createIndexMot(String folderName) throws IOException{
//    	Set<IndexMot> setIndexSortie=new HashSet<IndexMot>();
//    	
//    	// Index des IndexMots
//    	HashMap<String,IndexMot> mapIndexSortie=new HashMap<String,IndexMot>();
//    	
//    	// Liste des mots clés du folder
//    	Set<String> setMotsClesFolder=listeMotsClesFolder(folderName);
//    	
//    	// Liste des noms des fichiers du folder
//        final File folder = new File(folderName);
//        List<String> listeFiles=new ArrayList<String>();
//        for(final File fileEntry : folder.listFiles()){
//        	listeFiles.add(folderName+fileEntry.getName());
//        }
//        
//    	
//        // Initialisation de l'index de sortie et de la HashMap<String,IndexMot>
//        Iterator<String> iterator = setMotsClesFolder.iterator();
//        while(iterator.hasNext()) {
//        	String setElement = iterator.next();
//        	IndexMot indexMotLocal=new IndexMot(setElement);
//        	setIndexSortie.add(indexMotLocal);
//        	mapIndexSortie.put(setElement, indexMotLocal);
//        }
//        
//        // On parcourt tous les fichiers du folder
//    	for (int i=0 ; i<listeFiles.size() ; i++) {
//    		// Liste des "mots" du fichier courant
//    		List<String> listMotsFile=listerMots(listeFiles.get(i));
//    		
//    		// Pour un fichier, on parcourt tous ses mots
//    		for(int j=0 ; j<listMotsFile.size() ; j++){
//    			
//    			// On récupère l'IndexMot correspondant au mot courant
//    			IndexMot indexMotCourant=mapIndexSortie.get(listMotsFile.get(j));
//    			
//    			
//    			int k=0;
//    			// On parcourt toute la List<IndexMot> pour trouver le mot clé du fileCourant
//    			while (!listeIndexSortie.get(k).getLabel().equals(indexMotCourant.getLabel())) {
//    				k++;
//    			}
//    			
//    			// On récupère la liste des FileNames du mot courant
//    			List<String>listeFileNamesMotCourant=new ArrayList<String>();
//				listeFileNamesMotCourant.addAll(listeIndexSortie.get(k).getListFileNames());
//				
//				// On récupère le nb de files qui contiennent le mot courant
//				int nbFilesMotCourant=listeIndexSortie.get(k).getNbFiles();
//				
//				// On récupère les stats du mot courant
//				StatMot statsMotCourant=listeIndexSortie.get(k).getStats();
//				
//				
//				// On vérifie que le fichier n'appartient pas à la liste des fichiers déjà rentrés avant de l'ajouter
//				boolean fileDejaRentre=false;
//				for(int l=0 ; l<listeFileNamesMotCourant.size() ; l++){
//					if(listeFileNamesMotCourant.get(l).equals(listeFiles.get(i))){
//						fileDejaRentre=true;
//					}
//				}
//				
//				// Si on n'a pas trouvé le fichier dans la liste des fichiers déjà présents
//				if(!fileDejaRentre){
//					// On ajoute le fichier à la liste et on incrémente le nb de fichier d'appartenance
//					listeFileNamesMotCourant.add(listeFiles.get(i));
//					nbFilesMotCourant++;
//					
//					// On modifie les stats associées au mot courant
//					HashMap<String,Integer> mapTfMotCourant=statsMotCourant.getMapTf();
//					mapTfMotCourant.put(listeFiles.get(i), 1);
//					statsMotCourant.setMapTf(mapTfMotCourant);
//				}
//				
//				// Si on a trouvé le fichier dans la liste des fichiers déjà présents
//				if(fileDejaRentre){
//					// On modifie les stats associées au mot courant
//					HashMap<String,Integer> mapTfMotCourant=statsMotCourant.getMapTf();
//					Integer tfMotCourant=mapTfMotCourant.get(listeFiles.get(i));
//					mapTfMotCourant.put(listeFiles.get(i), tfMotCourant+1);
//					statsMotCourant.setMapTf(mapTfMotCourant);
//				}
//
//				
//				listeIndexSortie.get(k).setListFileNames(listeFileNamesMotCourant);
//				listeIndexSortie.get(k).setNbFiles(nbFilesMotCourant);
//				listeIndexSortie.get(k).setStats(statsMotCourant);
//    			
//    		}
//    	}
//    	return listeIndexSortie;
//    }
    
    
    public static List<IndexMot> createIndexMot(String folderName) throws IOException{
    	List<IndexMot> listeIndexSortie=new ArrayList<IndexMot>();
    	
    	// Liste des noms des fichiers du folder
        final File folder = new File(folderName);
        List<String> listeFiles=new ArrayList<String>();
        for(final File fileEntry : folder.listFiles()){
        	listeFiles.add(folderName+fileEntry.getName());
        }
        
        // On parcourt tous les fichiers du folder
    	for (int i=0 ; i<listeFiles.size() ; i++) {
    		// Liste des "mots" du fichier courant
    		List<String> listMotsFile=listerMots(listeFiles.get(i));
    		
    		// Pour un fichier, on parcourt tous ses mots
    		for(int j=0 ; j<listMotsFile.size() ; j++){
    			
    			int k=0;
    			// On parcourt toute la List<IndexMot> pour trouver le mot clé du fileCourant    			
    			while (!listeIndexSortie.get(k).getLabel().equals(listMotsFile.get(j))&& k<listMotsFile.size()) {
    				k++;
    			}
    			
    			// Si on a parcouru l'ensemble de la liste sans trouver le mot
    			if(k==listMotsFile.size()+1){
    				// On ajoute le nom du fichier à la liste des fichiers
    				Set<String> setFileNameMotLocal=new HashSet<String>();
    				setFileNameMotLocal.add(listeFiles.get(i));
    				
    				// On initialise les stats du mot
    				StatMot statMotLocal=new StatMot();
    				HashMap<String,Integer> mapTf=new HashMap<String,Integer>();
    				mapTf.put(listeFiles.get(i), 1);
    				statMotLocal.setMapTf(mapTf);
    				
    				IndexMot indexMotLocal=new IndexMot(listMotsFile.get(j), 1, setFileNameMotLocal, statMotLocal);
    				
    				listeIndexSortie.add(indexMotLocal);
    			}
    			
    			// Si on a trouvé le mot à la position k
    			else{
    				
    				// Le fichier n'a pas été renseigné antérieurement
    				if (!listeIndexSortie.get(k).getListFileNames().contains(listeFiles.get(i))){
    					Set<String> setFileNames=new HashSet<String>();
        				setFileNames.add(listeFiles.get(i));
        				listeIndexSortie.get(k).setListFileNames(setFileNames);
        				
        				Integer nbFiles = listeIndexSortie.get(k).getNbFiles();
        				listeIndexSortie.get(k).setNbFiles(nbFiles+1);
        				
        				StatMot statMotLocal=listeIndexSortie.get(k).getStats();
        				HashMap<String,Integer> mapTf=statMotLocal.getMapTf();
        				mapTf.put(listeFiles.get(i), 1);
        				statMotLocal.setMapTf(mapTf);
        				listeIndexSortie.get(k).setStats(statMotLocal);
    					
    				}
    				// Le fichier a déjà été renseigné
    				else{
    					StatMot statMotLocal = new StatMot();
        				HashMap<String,Integer> mapTfMotCourant=statMotLocal.getMapTf();
    					Integer tfMotCourant=mapTfMotCourant.get(listeFiles.get(i));
    					mapTfMotCourant.put(listeFiles.get(i), tfMotCourant+1);
    					statMotLocal.setMapTf(mapTfMotCourant);
        				listeIndexSortie.get(k).setStats(statMotLocal);

    				}
    			}
    		}
    	}
    	return listeIndexSortie;
    }
    
    
    public static void createIndex(String folderName) throws IOException{
    	
    	// Liste des noms des fichiers du folder
        final File folder = new File(folderName);
        List<String> listeFiles=new ArrayList<String>();
        for(final File fileEntry : folder.listFiles()){
        	listeFiles.add(folderName+fileEntry.getName());
        }
        
        // On parcourt tous les fichiers du folder
    	for (int i=0 ; i<listeFiles.size() ; i++) {
    		// Liste des "mots" du fichier courant
    		List<String> listMotsFile=listerMots(listeFiles.get(i));
    		
    		// Pour un fichier, on parcourt tous ses mots
    		for(int j=0 ; j<listMotsFile.size() ; j++){
    			
    			// Initialisation des "label" et "DataValue" à envoyer ensuite
    			String labelMotCourant=listMotsFile.get(j);
    			DataValue dataValueMotCourant=new DataValue();
    			
    			// Remplissage de la "DataValue"
    			dataValueMotCourant.setLabel(labelMotCourant);
    			dataValueMotCourant.setNbFiles(1);
    			
    			Set<String> setFileName=new HashSet<String>();
    			setFileName.add(listeFiles.get(i));
    			dataValueMotCourant.setSetFileNames(setFileName);
    			
    			StatMot statMotCourant=new StatMot();
    			HashMap<String, Integer> mapTf=new HashMap<String,Integer>();
    			mapTf.put(listeFiles.get(i), 1);
    			statMotCourant.setMapTf(mapTf);
    			dataValueMotCourant.setStats(statMotCourant);
    			
    			// Ajout du mot dans la base de données (si déjà présent, la méthode update de la classe sera appelée)
    			///////////////////++++++++++++++++++++++++++++++++++//////////////////////////////////
    			
    			TreeRepresentation root = new TreeRepresentation("propre", null);
    			root.insert(labelMotCourant, dataValueMotCourant);
    			root = root.root();
    		}	
    	}
    }
    
    
	

}
