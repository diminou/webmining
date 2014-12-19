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
import java.util.List;
import java.util.Map;


import td.td1.*;
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
    public static Map<String,Integer> occurrence(ArrayList<String> liste){
    	
    	return new FrequencyMap(liste).getFrequencies();
    }
    
    /**
     * Retourne la List<FrequencyMap> d'un folder de fichiers lemmatises
     * @param folderName
     */
    public static List<FrequencyMap> returnOccurencesFolderParFichier(String folderName){
    	
    	final File folder = new File("./corpus");
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
	
	
	
	



	
	
	
	
	
	

}
