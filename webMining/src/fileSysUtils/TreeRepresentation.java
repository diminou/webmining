/**
 * 
 */
package fileSysUtils;

/**
 * @author divanov
 *
 */
public class TreeRepresentation implements Comparable<TreeRepresentation>,
		Cloneable {

	/**
	 * 
	 */
	private TreeRepresentation parent = null;
	private TreeRepresentation leftChild = null;
	private TreeRepresentation rightChild = null;
	private String value;
	private String data;

	public TreeRepresentation(String value, String data) {
		super();
		this.data = data;
		this.value = value;
	}

	public TreeRepresentation(TreeRepresentation parent, String value,
			String data) {
		super();
		this.parent = parent;
		this.value = value;
		this.data = data;
	}

	@Override
	public int compareTo(TreeRepresentation arg0) {
		return this.getValue().compareTo(arg0.getValue());
	}

	public int compareToIgnoreCase(TreeRepresentation arg0) {
		return this.getValue().compareToIgnoreCase(arg0.getValue());
	}

	private TreeRepresentation(TreeRepresentation tr) {
		super();
		this.parent = tr.getParent();
		this.leftChild = tr.getLeftChild();
		this.rightChild = tr.getRightChild();
		this.value = tr.getValue();
		this.data = tr.data;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
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

	public Integer height(TreeRepresentation tr) {
		if (tr == null) {
			return new Integer(0);
		} else {
			Integer result = new Integer(1);
			Integer heightLeft = height(tr.getLeftChild());
			Integer heightRight = height(tr.getRightChild());
			if (heightLeft > heightRight) {
				result = result + heightLeft;
			} else {
				result = result + heightRight;
			}
			return result;
		}
	}

	public Integer balance() {
		Integer heightLeft = new Integer(0);
		Integer heightRight = new Integer(0);
		if (this.leftChild != null) {
			heightLeft = this.leftChild.height(this.leftChild);
		}
		if (this.rightChild != null) {
			heightRight = this.rightChild.height(this.rightChild);
		}
		return heightLeft - heightRight;
	}

	public void rotateLeft() {
		System.err.println("rotate right");
		TreeRepresentation right = this.getRightChild();
		TreeRepresentation startNode = this.clone();
		startNode.setRightChild(right.getLeftChild());
		right.setLeftChild(startNode);
		right.setParent(startNode.getParent());
		startNode.setParent(right);
		this.copy(right);
		System.err.println(this.toString());
	}

	public void rotateRight() {
		System.err.println("rotate left");
		TreeRepresentation left = this.getLeftChild();
		TreeRepresentation startNode = this.clone();
		startNode.setLeftChild(left.getRightChild());
		left.setRightChild(startNode);
		left.setParent(startNode.getParent());
		startNode.setParent(left);
		this.copy(left);
		System.err.println(this.toString());
	}

	public TreeRepresentation clone() {
		return new TreeRepresentation(this);
	}

	private void copy(TreeRepresentation tr) {
		this.parent = tr.getParent();
		this.leftChild = tr.getLeftChild();
		this.rightChild = tr.getRightChild();
		this.value = tr.getValue();
		this.data = tr.getData();
	}

	public TreeRepresentation lookup(String value) {
		TreeRepresentation temp = new TreeRepresentation(value, null);
		if (temp.compareTo(this) == 0) {
			return this;
		} else if (temp.compareTo(this) < 0) {
			if (this.getLeftChild() != null) {
				return this.leftChild.lookup(value);
			} else {
				return null;
			}
		} else {
			if (this.getRightChild() != null) {
				return this.rightChild.lookup(value);
			} else {
				return null;
			}
		}
	}

	private void updateData(TreeRepresentation tr) {
		if (this.lookup(tr.getValue()) != null) {
			this.lookup(tr.getValue()).setData(tr.getData());
		}
	}

	protected void insert(TreeRepresentation tr) {
		System.err.println("insert " + tr.getValue());
		System.err.println("into " + this.value);
		if (tr.compareTo(this) > 0) {
			if (this.rightChild == null) {
				this.rightChild = tr;
				tr.parent = this;
				this.rightChild.rebalanceInsertion();
			} else {
				this.rightChild.insert(tr);
			}
		} else if (tr.compareTo(this) < 0) {
			if (this.leftChild == null) {
				this.leftChild = tr;
				tr.parent = this;
				this.leftChild.rebalanceInsertion();
			} else {
				this.leftChild.insert(tr);
			}

		}

	}

	private void rebalanceInsertion() {
		System.err.println("rebalancing");
		System.err.println("inserted");
		System.err.println(this.value);
		if (this.parent != null && this.parent.getParent() != null) {
			TreeRepresentation p = this.parent;

			System.err.println("p");
			System.err.println(p.getValue() + p.balance().toString());

			TreeRepresentation gp = p.getParent();

			System.err.println("gp");
			System.err.println(gp.getValue() + gp.balance().toString());

			if (gp.balance() > 1) {
				if (p.balance() > 0) {
					System.err.println("left left");
					gp.rotateRight();
					p.rebalanceInsertion();
				} else if (p.balance() < 0) {
					System.err.println("left right");
					p.rotateLeft();
					gp.rotateRight();
					p.rebalanceInsertion();
				}
			} else if (gp.balance() < -1) {
				if (p.balance() > 0) {
					System.err.println("right left");
					p.rotateRight();
					gp.rotateLeft();
					p.rebalanceInsertion();
				} else if (p.balance() < 0) {
					System.err.println("right right");
					gp.rotateLeft();
					p.rebalanceInsertion();
				}

			} else {
				System.err.println("hierarchy climb " + p.getValue());
				p.rebalanceInsertion();
			}

		}

	}

	public void insert(String value, String data) {
		TreeRepresentation tr = new TreeRepresentation(value, data);
		this.insert(tr);
	}

	public String toString() {
		String result = "";
		String nl = System.lineSeparator();
		result += this.value + nl;
		if (this.leftChild == null) {
			result += "leftChild : " + "null" + nl;
		} else {
			result += "leftChild : " + leftChild.toString() + nl;
		}
		if (this.rightChild == null) {
			result += "rightChild : " + "null" + nl;
		} else {
			result += "rightChild : " + rightChild.toString() + nl;
		}
		return result;
	}

}
