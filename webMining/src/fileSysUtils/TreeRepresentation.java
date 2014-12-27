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
	private Integer balance;

	
	public TreeRepresentation(String value, String data){
		super();
		this.data = data;
		this.value = value;
		this.balance = this.balance();
	}
	public TreeRepresentation(TreeRepresentation parent, String value,
			String data) {
		super();
		this.parent = parent;
		this.value = value;
		this.data = data;
		this.balance = this.balance();
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
		this.balance = tr.balance;
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
		this.balance = this.balance();
	}

	public TreeRepresentation getRightChild() {
		return rightChild;
	}

	public void setRightChild(TreeRepresentation rightChild) {
		this.rightChild = rightChild;
		this.balance = this.balance();
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
		Integer sizeLeft = new Integer(0);
		Integer sizeRight = new Integer(0);
		if (this.leftChild != null) {
			sizeLeft = this.leftChild.size();
		}
		if (this.rightChild != null) {
			sizeRight = this.rightChild.size();
		}
		return sizeLeft - sizeRight;
	}

	public void updateBalance() {
		this.balance = this.balance();
	}

	public void rotateLeft() {
		TreeRepresentation right = this.getRightChild();
		TreeRepresentation startNode = this.clone();
		startNode.setRightChild(right.getLeftChild());
		right.setLeftChild(startNode);
		right.setParent(startNode.getParent());
		startNode.setParent(right);
		this.copy(right);
	}

	public void rotateRight() {
		TreeRepresentation left = this.getLeftChild();
		TreeRepresentation startNode = this.clone();
		startNode.setLeftChild(left.getRightChild());
		left.setRightChild(startNode);
		left.setParent(startNode.getParent());
		startNode.setParent(left);
		this.copy(left);
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
		this.balance = this.balance();
	}
	
	public TreeRepresentation lookup(String value){
		TreeRepresentation temp = new TreeRepresentation(value, null);
		if(temp.compareTo(this) == 0){
			return this;
		} else if(temp.compareTo(this)<0){
			if(this.getLeftChild()!=null){
				return this.leftChild.lookup(value);
			}else{
				return null;
			}
		} else {
			if(this.getRightChild()!=null){
				return this.rightChild.lookup(value);
			}else{
				return null;
			}
		}
	}
	
	private void updateData(TreeRepresentation tr){
		if (this.lookup(tr.getValue())!=null) {
			this.lookup(tr.getValue()).setData(tr.getData());
		}
	}
	
	private void insert(TreeRepresentation tr){
		
	}
	
	public void insert(String value, String data){
		
	}

}
