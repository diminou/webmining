/**
 * 
 */
package fileSysUtils;

import java.util.Comparator;

/**
 * @author divanov
 *
 */
public class TreeRepresentationComparator implements
		Comparator<TreeRepresentation> {

	/**
	 * 
	 */
	public TreeRepresentationComparator() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(TreeRepresentation arg0, TreeRepresentation arg1) {
		return arg0.compareTo(arg1);
	}

}
