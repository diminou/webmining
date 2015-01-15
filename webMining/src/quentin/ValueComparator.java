package quentin;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator<Integer> {

	Map<Integer, Double> map;
	
	public ValueComparator(Map<Integer, Double> map) {
		this.map = map;
	}

	public int compare(Integer a, Integer b) {
		if (map.get(a) >= map.get(b)) {
			return -1;
		} else {
			return 1;
		} 
	}

}
