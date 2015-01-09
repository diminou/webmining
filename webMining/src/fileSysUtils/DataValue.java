package fileSysUtils;

import java.util.Set;

public class DataValue {

	private Set<String> documents;
	private Set<Integer> stats;
	
	public DataValue(Set<String> documents, Set<Integer> stats) {
		this.documents = documents;
		this.stats = stats;
	}
	
	public Set<String> getDocuments(){
		return documents;
	}
	public Set<Integer> getStats(){
		return stats;
	}
	
	public void setDocuments(Set<String> docs){
		this.documents = docs; 
	}
	public void setStats(Set<Integer> stats){
		this.stats = stats;
	}
	
	public void update(DataValue dv){
		this.documents.addAll(dv.getDocuments());
		this.stats.addAll(dv.getStats());
	}

}
