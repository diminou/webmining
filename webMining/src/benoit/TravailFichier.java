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
				lemmWords = recuperer3eMots(fileEntry.getName());
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
    * Retourne le Set<String> des mots clés d'une List<String>
    * @param listeMots
    * @return
    */
    public static Set<String> setMotsCles (List<String> listeMots){
    	
    	Set<String> setSortie=new HashSet<String>();
    	
    	for (int i=0 ; i<listeMots.size() ; i++){
    		setSortie.add(listeMots.get(i));
    		
    	}
    	return setSortie;
    }
    
    
    
	/**
	 * Retourne la List<String> des 3e mots de chaque lignes d'un fichier lemmatise
	 * @param file
	 * @return
	 * @throws IOException
	 */
    public static ArrayList<String> recuperer3eMots(String file) throws IOException{
    	
    	ArrayList<String> text = new ArrayList<String>();
		//lecture du fichier texte	
		InputStream ips=new FileInputStream(file); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		String line;
		while ((line=br.readLine())!=null){
			if (line.split("\t").length>2){
			String temp=line.split("\t")[2];
			text.add(temp);
			}
		}
		br.close(); 
		return text;
		
    }
        
    /**
	 * Retourne la List<String> des 2e mots de chaque lignes d'un fichier lemmatise
	 * @param file
	 * @return
	 * @throws IOException
	 */
    public static ArrayList<String> recuperer2eMots(String file) throws IOException{
    	
    	ArrayList<String> text = new ArrayList<String>();
		//lecture du fichier texte	
		InputStream ips=new FileInputStream(file); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		String line;
		while ((line=br.readLine())!=null){
			if (line.split("\t").length>2){
			String temp=line.split("\t")[1];
			text.add(temp);
			}
		}
		br.close(); 
		return text;
		
    }
       
    /**
	 * Retourne la List<String> des 1er mots de chaque lignes d'un fichier lemmatise
	 * @param file
	 * @return
	 * @throws IOException
	 */
    public static ArrayList<String> recuperer1erMots(String file) throws IOException{
    	
    	ArrayList<String> text = new ArrayList<String>();
		//lecture du fichier texte	
		InputStream ips=new FileInputStream(file); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		String line;
		while ((line=br.readLine())!=null){
			if (line.split("\t").length>2){
			String temp=line.split("\t")[2];
			text.add(temp);
			}
		}
		br.close(); 
		return text;
		
    }
	
	
    
    /**
     * Méthode final de création de l'index d'un folder
     * @param folderName
     * @return
     * @throws IOException
     */
    public static List<IndexMot> createIndexMot(String folderName) throws IOException{
    	List<IndexMot> listeIndexSortie=new ArrayList<IndexMot>();
    	
    	// Index des IndexMots
    	HashMap<String,IndexMot> mapIndexSortie=new HashMap<String,IndexMot>();
    	
    	List<String> listeMotsFolder=listerMotsFolder(folderName);

    	Set<String> setFolder=setMotsCles(listeMotsFolder);
    	List<String> listeMotsClesFolder=new ArrayList<>();
    	listeMotsClesFolder.addAll(setFolder);
    	
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
    		// Liste des mots clés du file courant
    		List<String> listMotsClesFile=listerMots(listeFiles.get(i));
    		
    		// Pour un fichier, on parcourt tout ses mots clés
    		for(int j=0 ; j<listMotsClesFile.size() ; j++){
    			
    			IndexMot indexMotCourant=mapIndexSortie.get(listMotsClesFile.get(j));
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
