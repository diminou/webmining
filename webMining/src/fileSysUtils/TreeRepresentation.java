/**
 * 
 */
package fileSysUtils;

/**
 * @author divanov
 *
 */
public class TreeRepresentation implements Comparable<TreeRepresentation> {

	/**
	 * 
	 */
	private TreeRepresentation parent;
	private TreeRepresentation leftChild;
	private TreeRepresentation rightChild;
	private String value;

	public TreeRepresentation(TreeRepresentation parent, String value) {
		super();
		this.parent = parent;
		this.value = value;
	}

	@Override
	public int compareTo(TreeRepresentation arg0) {
		return this.getValue().compareTo(arg0.getValue());
	}

	public int compareToIgnoreCase(TreeRepresentation arg0) {
		return this.getValue().compareToIgnoreCase(arg0.getValue());
	}

	public TreeRepresentation getParent() {
		return parent;
	}

	public void setParent(TreeRepresentation parent) {
		this.parent = parent;
	}

	public TreeRepresentation getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(TreeRepresentation leftChild) {
		this.leftChild = leftChild;
	}

	public TreeRepresentation getRightChild() {
		return rightChild;
	}

	public void setRightChild(TreeRepresentation rightChild) {
		this.rightChild = rightChild;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isRoot() {
		return (this.parent == null);
	}

	public TreeRepresentation root() {
		if (this.isRoot()) {
			return this;
		} else {
			return this.parent.root();
		}
	}

	public Integer size() {
		Integer result = new Integer(1);
		if (this.leftChild != null) {
			result = result + leftChild.size();
		}
		if (this.rightChild != null) {
			result = result + rightChild.size();
		}

		return result;
	}
}
