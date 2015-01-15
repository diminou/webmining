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
import fileSysUtils.IndexWrapper;
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
     * Retourne la List<String> des mots setmmatisés d'un fichier
     * @param filename
     * @return
     * @throws IOException
     */
     public static List<String> listerMotsStem(String filename) throws IOException{
     	ArrayList<String> text = new ArrayList<String>();
 		//lecture du fichier texte	
 		InputStream ips=new FileInputStream(filename); 
 		InputStreamReader ipsr=new InputStreamReader(ips);
 		BufferedReader br=new BufferedReader(ipsr);
 		String line;
 		while ((line=br.readLine())!=null){
 			
 			String[] ligneTemp=line.split(" ");
 			for (int i=0 ; i<ligneTemp.length ; i++){
 			text.addAll((new FrenchStemmer()).normalize(ligneTemp[i]));
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
    
    public static HashMap<String,Integer> createIndexNumberDocs (String folderName){
    	HashMap<String, Integer> mapSortie=new HashMap<String, Integer>();
    
    	final File folder = new File(folderName);
        List<String> listeFiles=new ArrayList<String>();
        for(File fileEntry : folder.listFiles()){
        	listeFiles.add(folderName+fileEntry.getName());
        }
        
        for(int i=0 ; i<listeFiles.size() ; i++){
        	mapSortie.put(listeFiles.get(i), i);
        }
        
        
    	return mapSortie;
    }
    
    
    
  /**
   * Méthode finale de création d'index pour les fichiers bruts  
   * @param folderName
   * @throws IOException
   */
    public static void createIndex(String folderName, IndexWrapper index) throws IOException{
    	
    	// Liste des noms des fichiers du folder
        final File folder = new File(folderName);
        List<String> listeFiles=new ArrayList<String>();
        for(File fileEntry : folder.listFiles()){
        	listeFiles.add(folderName+fileEntry.getName());
        }
        HashMap<String, Integer> mapNumberDoc=createIndexNumberDocs(folderName);
        
        
        
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
//    			dataValueMotCourant.setLabel(labelMotCourant);
    			dataValueMotCourant.setNbFiles(1);
    			
    			Set<Integer> setFileName=new HashSet<Integer>();
    			setFileName.add(mapNumberDoc.get(listeFiles.get(i)));
    			dataValueMotCourant.setSetFileNames(setFileName);
    			
    			StatMot statMotCourant=new StatMot();
    			HashMap<Integer, Integer> mapTf=new HashMap<Integer,Integer>();
    			mapTf.put(mapNumberDoc.get(listeFiles.get(i)), 1);
    			statMotCourant.setMapTf(mapTf);
    			dataValueMotCourant.setStats(statMotCourant);
    			
    			// Ajout du mot dans la base de données (si déjà présent, la méthode update de la classe sera appelée)

    			index.insert(labelMotCourant, dataValueMotCourant);
    			
    			
    		}	
    	}
    }
        
    /**
    * Méthode finale de création d'index pour les fichiers avec stemmatisation en direct live  
     * @param folderName
     * @throws IOException
     */
    public static void createIndexStemming(String folderName, IndexWrapper index) throws IOException{
      	
	  	// Liste des noms des fichiers du folder
	      final File folder = new File(folderName);
	      List<String> listeFiles=new ArrayList<String>();
	      for(File fileEntry : folder.listFiles()){
	      	listeFiles.add(folderName+fileEntry.getName());
	      }
	      HashMap<String, Integer> mapNumberDoc=createIndexNumberDocs(folderName);
	      
	      // On parcourt tous les fichiers du folder
	  	for (int i=0 ; i<listeFiles.size() ; i++) {
	  		// Liste des "mots" du fichier courant
	  		List<String> listMotsFile=listerMotsStem(listeFiles.get(i));
	  		
	  		// Pour un fichier, on parcourt tous ses mots
	  		for(String s:listMotsFile){
	  			
	  			if(s.length()>1){
	  				
	  			// Initialisation des "label" et "DataValue" à envoyer ensuite
		  			String labelMotCourant=s;
		  			DataValue dataValueMotCourant=new DataValue();
		  			
		  			// Remplissage de la "DataValue"
//		  			dataValueMotCourant.setLabel(labelMotCourant);
		  			dataValueMotCourant.setNbFiles(1);
		  			
		  			Set<Integer> setFileName=new HashSet<Integer>();
		  			setFileName.add(mapNumberDoc.get(listeFiles.get(i)));
		  			dataValueMotCourant.setSetFileNames(setFileName);
		  			
		  			StatMot statMotCourant=new StatMot();
		  			HashMap<Integer, Integer> mapTf=new HashMap<Integer,Integer>();
		  			mapTf.put(mapNumberDoc.get(listeFiles.get(i)), 1);
		  			statMotCourant.setMapTf(mapTf);
		  			dataValueMotCourant.setStats(statMotCourant);
		  			
		  			// Ajout du mot dans la base de données (si déjà présent, la méthode update de la classe sera appelée)
		
		  			index.insert(labelMotCourant, dataValueMotCourant);
	  			}
	  			
	  			
	  			
	  		}	
	  	}
	  }
      
    /**
    * Méthode finale de création d'index pour les fichiers lemmatisés  
    * @param folderName
    * @throws IOException
    */
    public static void createIndexLemme(String folderName, IndexWrapper index) throws IOException{
    	
    	// Liste des noms des fichiers du folder
        final File folder = new File(folderName);
        List<String> listeFiles=new ArrayList<String>();
        for(File fileEntry : folder.listFiles()){
        	listeFiles.add(folderName+fileEntry.getName());
        }
	      HashMap<String, Integer> mapNumberDoc=createIndexNumberDocs(folderName);

        
        // On parcourt tous les fichiers du folder
    	for (int i=0 ; i<listeFiles.size() ; i++) {
    		// Liste des "mots" du fichier courant
    		List<String> listMotsFile=recupererIeMots(listeFiles.get(i),3);
    		
    		// Pour un fichier, on parcourt tous ses mots
    		for(int j=0 ; j<listMotsFile.size() ; j++){
    			
    			// Initialisation des "label" et "DataValue" à envoyer ensuite
    			String labelMotCourant=listMotsFile.get(j);
    			DataValue dataValueMotCourant=new DataValue();
    			
    			// Remplissage de la "DataValue"
//    			dataValueMotCourant.setLabel(labelMotCourant);
    			dataValueMotCourant.setNbFiles(1);
    			
    			Set<Integer> setFileName=new HashSet<Integer>();
    			setFileName.add(mapNumberDoc.get(listeFiles.get(i)));
    			dataValueMotCourant.setSetFileNames(setFileName);
    			
    			StatMot statMotCourant=new StatMot();
    			HashMap<Integer, Integer> mapTf=new HashMap<Integer,Integer>();
    			mapTf.put(mapNumberDoc.get(listeFiles.get(i)), 1);
    			statMotCourant.setMapTf(mapTf);
    			dataValueMotCourant.setStats(statMotCourant);
    			
    			// Ajout du mot dans la base de données (si déjà présent, la méthode update de la classe sera appelée)

    			index.insert(labelMotCourant, dataValueMotCourant);
    			
    			
    		}	
    	}
    }
	
    
    /**
     * Create map d'un doc (ses mots avec leurs occurrences)
     * @param filename
     * @return
     * @throws IOException
     */
    public static HashMap<String,Double> createMapDoc(String filename) throws IOException{
    	HashMap<String,Double> hashMapDoc=new HashMap<String, Double>();
    	
    	List<String> listeMotsClesFile=listerMots(filename);
    	
    	
    	for (String s:listeMotsClesFile){
    		if(hashMapDoc.containsKey(s)){
    			hashMapDoc.put(s, hashMapDoc.get(s)+1.0);
    		}
    		else{
    			hashMapDoc.put(s, 1.0);
    		}
    	}
    	return hashMapDoc;
    }
    
    /**
     * Calcul du score d'un document à partir de sa hashmap
     * @param hashMapDoc
     * @return
     */
    public static double calculScore(HashMap<String,Double> hashMapDoc){
    	double res=0.0;
    	
    	Iterator i=hashMapDoc.keySet().iterator();
    	while(i.hasNext()){
    		res=res+hashMapDoc.get(i.next())*hashMapDoc.get(i.next());
    	}
    	
    	return (Math.sqrt(res));
    }
    
    /** Méthode finale de création d'index pour les docs  
    * @param folderName
    * @throws IOException
    */
     public static IndexDoc createIndexDocs(String folderName) throws IOException{
     	IndexDoc indexSortie=new IndexDoc();
     	
     	
     	// Liste des noms des fichiers du folder
         final File folder = new File(folderName);
         List<String> listeFiles=new ArrayList<String>();
         for(File fileEntry : folder.listFiles()){
         	listeFiles.add(folderName+fileEntry.getName());
         }
         
         // On parcourt tous les fichiers du folder
     	for (int i=0 ; i<listeFiles.size() ; i++) {
     		
     		double scoreDoc=calculScore(createMapDoc(listeFiles.get(i)));
     		indexSortie.getMapDocs().put(listeFiles.get(i), scoreDoc);
     	}
     	
     	return indexSortie;
     	
     }

}
