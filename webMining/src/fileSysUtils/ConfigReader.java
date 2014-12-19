/**
 * 
 */
package fileSysUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author divanov
 *
 */
public class ConfigReader {
	private Integer maxTerms = 1000;

	private ArrayList<String> readConfig(String filePath) throws IOException {

		ArrayList<String> text = new ArrayList<String>();
		// lecture du fichier texte
		InputStream ips = new FileInputStream(filePath);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String line;
		while ((line = br.readLine()) != null) {
			text.add(line);
		}
		br.close();
		return text;

	}

	public ConfigReader(String configPath) throws IOException {
		super();
		ArrayList<String> listConfig = readConfig(configPath);

		this.maxTerms = Integer.parseInt(listConfig.get(0));
	}

	public ConfigReader() throws IOException {
		super();
		ArrayList<String> listConfig = readConfig("./config");

		this.maxTerms = Integer.parseInt(listConfig.get(0));
	}

}
