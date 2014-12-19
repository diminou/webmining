/**
 * 
 */
package td.td1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author divanov
 *
 */
public class Documents {

	private Map<Integer, FrequencyMap> index ;
	private Integer num ;
	public Documents(){
		this.index = new HashMap<Integer, FrequencyMap>();
		this.num = 0;
	}
	
	public void add(FrequencyMap fm){
		num++;
		index.put(num, fm);
	}
	
	
	
}
