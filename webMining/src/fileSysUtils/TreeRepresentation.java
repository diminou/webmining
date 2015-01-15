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
	private DataValue data;

	public TreeRepresentation(String value, DataValue data) {
		super();
		this.data = data;
		this.value = value;
	}

	public TreeRepresentation(TreeRepresentation parent, String value,
			DataValue data) {
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

	public DataValue getData() {
		return this.data;
	}

	public void setData(DataValue data) {
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
		TreeRepresentation parent = this.getParent();
		boolean leftc = false;
		boolean rightc = false;
		if (this.parent != null) {
			leftc = this.parent.leftChild == this;
			rightc = this.parent.rightChild == this;
		}

		TreeRepresentation right = this.getRightChild();
		this.setRightChild(right.getLeftChild());
		if (this.rightChild != null) {
			this.rightChild.setParent(this);
		}
		right.setLeftChild(this);
		right.setParent(parent);
		this.setParent(right);

		if (leftc) {
			parent.setLeftChild(right);
		} else if (rightc) {
			parent.setRightChild(right);
		}

	}

	public void rotateRight() {

		TreeRepresentation parent = this.getParent();
		boolean leftc = false;
		boolean rightc = false;
		if (this.parent != null) {
			leftc = this.parent.leftChild == this;
			rightc = this.parent.rightChild == this;
		}
		TreeRepresentation left = this.getLeftChild();
		this.setLeftChild(left.getRightChild());
		if (this.leftChild != null) {
			this.leftChild.setParent(this);
		}
		left.setRightChild(this);
		left.setParent(parent);
		this.setParent(left);
		if (leftc) {
			parent.setLeftChild(left);
		} else if (rightc) {
			parent.setRightChild(left);
		}

	}

	public TreeRepresentation clone() {
		return new TreeRepresentation(this);
	}

	public TreeRepresentation lookup(String value) {
		TreeRepresentation temp = new TreeRepresentation(value, null);
		if (temp.compareToIgnoreCase(this) == 0) {
			return this;
		} else if (temp.compareToIgnoreCase(this) < 0) {
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

	public DataValue lookupDv(String key) {
		TreeRepresentation tr = this.lookup(key);
		if (tr != null) {
			return tr.getData();
		} else {
			return null;
		}

	}

	private void updateData(TreeRepresentation tr) {
		if (this.data != null) {
			this.data.update(tr.data);
		} else {
			this.setData(tr.data);

		}

	}

	protected void insert(TreeRepresentation tr) {

		if (tr.compareToIgnoreCase(this) > 0) {
			if (this.rightChild == null) {
				this.rightChild = tr;
				tr.parent = this;
				this.rightChild.rebalanceInsertion();
			} else {
				this.rightChild.insert(tr);
			}
		} else if (tr.compareToIgnoreCase(this) < 0) {
			if (this.leftChild == null) {
				this.leftChild = tr;
				tr.parent = this;
				this.leftChild.rebalanceInsertion();
			} else {
				this.leftChild.insert(tr);
			}

		} else if (tr.compareToIgnoreCase(this) == 0) {
			if (this.data != null) {
				this.data.update(tr.data);
			} else {
				this.data = tr.data;
			}

		}

	}

	private void rebalanceInsertion() {

		if (this.parent != null && this.parent.getParent() != null) {
			TreeRepresentation p = this.parent;

			TreeRepresentation gp = p.getParent();

			if (gp.balance() > 1) {
				if (p.balance() > 0) {

					gp.rotateRight();
					p.rebalanceInsertion();
				} else if (p.balance() < 0) {

					p.rotateLeft();
					gp.rotateRight();
					p.rebalanceInsertion();
				}
			} else if (gp.balance() < -1) {
				if (p.balance() > 0) {

					p.rotateRight();
					gp.rotateLeft();
					p.rebalanceInsertion();
				} else if (p.balance() < 0) {

					gp.rotateLeft();
					p.rebalanceInsertion();
				}

			} else {

				p.rebalanceInsertion();
			}

		}

	}

	public void insert(String value, DataValue data) {
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
