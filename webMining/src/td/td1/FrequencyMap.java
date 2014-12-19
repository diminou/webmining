/**
 * 
 */
package td.td1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author divanov
 *
 */
public class FrequencyMap {

	/**
	 * 
	 */
	private Map<String, Integer> frequencies;

	public FrequencyMap(List<String> words) {
		this.frequencies = new HashMap<String, Integer>();
		Collections.sort(words);
		for (String string : words) {
			if (!frequencies.containsKey(string)) {
				this.frequencies.put(string, new Integer(1));
			} else {
				this.frequencies.put(string, frequencies.get(string) + 1);
			}

		}
		this.purify();
	}

	public Map<String, Integer> getFrequencies() {
		return this.frequencies;
	}

	@Override
	public String toString() {
		Iterator<String> iterator = this.frequencies.keySet().iterator();
		String result = "";
		while (iterator.hasNext()) {
			String key = iterator.next();
			result = result + System.getProperty("line.separator") + key
					+ " : " + frequencies.get(key);
		}

		return result;
	}
	
	private void purify(){
		Iterator<String> iterator = frequencies.keySet().iterator();
		
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			if (!isWord(string)) {
				iterator.remove();
			}
		}
	}
	
	private boolean isWord(String string){
		Pattern p = Pattern.compile("[a-zA-Z'-['-]]*+");
		Matcher m = p.matcher(string);
		return m.matches();
	}
}
