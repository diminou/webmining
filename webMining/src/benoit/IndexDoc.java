package benoit;

import java.util.HashMap;

public class IndexDoc {

	private HashMap<String,Double> mapDocs;

	public HashMap<String, Double> getMapDocs() {
		return mapDocs;
	}

	public void setMapDocs(HashMap<String, Double> mapDocs) {
		this.mapDocs = mapDocs;
	}

	public IndexDoc(HashMap<String, Double> mapDocs) {
		super();
		this.mapDocs = mapDocs;
	}

	public IndexDoc() {
		super();
		HashMap<String,Double> hashMap=new HashMap<String, Double>();
		this.mapDocs=hashMap;
	}
	
	
	
	
}
