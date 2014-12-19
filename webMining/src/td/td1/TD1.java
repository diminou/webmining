/**
 * 
 */
package td.td1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerWrapper;

import tools.FrenchStemmer;

/**
 * @author divanov
 *
 */
public class TD1 {
	private static void tag(ArrayList<String> words, final String filename)
			throws UnsupportedEncodingException {
		TreeTaggerWrapper<String> tt = new TreeTaggerWrapper<String>();
		String res = "";
		try {
			tt.setModel("$HOME/ML/WebMining/TT/lib/french-utf8.par");
			tt.setHandler(new TokenHandler<String>() {
				String res;

				@Override
				public void token(String token, String pos, String lemma) {
					res = lemma;
				}
			});
			tt.process((String[]) words.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	public static List<String> readLemmFile(File file) throws IOException {
		List<String> result = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			String[] split = line.split("\\s+");
			try {
				if (split[2] != null) {
					result.add(split[2]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		br.close();

		return result;
	}

	public static void main(String[] args) {
		final File folder = new File("./corpus");
		final File lemmes = new File ("./lemmes");
		Charset utf8 = StandardCharsets.UTF_8;
		for (final File fileEntry : folder.listFiles()) {
			System.out.println(fileEntry.getName());
			try {
				ArrayList<String> words = (new FrenchStemmer())
						.normalize(fileEntry);

				FrequencyMap fm = new FrequencyMap(words);
				

				String path = "./results/" + fileEntry.getName();
				System.out.println(path);
				System.out.println(fm.toString());
				System.out.println("*************");
				ArrayList<String> content = new ArrayList<String>();
				content.add(fm.toString());
				(new File(path)).createNewFile();
				Files.write(Paths.get(path), content, utf8,
						StandardOpenOption.CREATE);

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
				lemmWords = readLemmFile(fileEntry);
				FrequencyMap lemmFm = new FrequencyMap(lemmWords);
				System.out.println(lemmFm.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}