/**
 * 
 */
package fileSysUtils;

/**
 * @author divanov
 *
 */
public class IndexWrapper {

	/**
	 * 
	 */
	private static TreeRepresentation root = null;

	public IndexWrapper() {
		// TODO Auto-generated constructor stub
	}

	public void insert(String label, DataValue dv) {
		if (root == null) {
			root = new TreeRepresentation(label, dv);
		} else {
			root.insert(label, dv);
			root = root.root();
		}
	}

	public DataValue lookup(String label) {
		if (root != null) {
			return root.lookupDv(label);
		} else {
			return null;
		}
	}
	
	public String toString(){
		return root.toString();
	}

}
