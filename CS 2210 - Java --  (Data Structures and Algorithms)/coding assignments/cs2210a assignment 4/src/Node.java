

/**
 * Node class used to make a node object to point to DictEntry objects in the BST
 * @author Vivian A. Lam
 */

public class Node {
	////////////////FIELDS////////////////
	private Node left; 				// nodes to get the left and right children
	private Node right;
	private DictEntry element; 		// stores the position and colour
	private Node parent;//parent of current node
	
	////////////////CONSTRUCTOR////////////////
	/**
	 * constructor to create and empty node
	 */
	Node() {
		this.left = null;
		this.element = null;
		this.right = null;
		this.parent = null;
	}

	/**
	 * constructor
	 */
	Node(DictEntry element) {
		left = null;
		right = null;
		this.element = element;
		this.parent = null;
	}

	////////////////METHODS////////////////

	/**
	 * set the element at the node
	 * @param newElement
	 */
	public void setElement(DictEntry newElement) {
		this.element = newElement;
	}

	/**
	 * get the element at the node
	 * @return
	 */
	public DictEntry getElement() {
		return this.element;
	}

	/**
	 * sets left child
	 * @param newLeft
	 */
	public void setLeft(Node newLeft) {
		this.left = newLeft;
	}

	/**
	 * sets right child
	 * @param newRight
	 */
	public void setRight(Node newRight) {
		this.right = newRight;
	}

	/*
	 * 
	 */
	public void setParent(Node newParent){
		this.parent = newParent;
	}
	
	/**
	 * returns left child
	 * @return
	 */
	public Node getLeft() {
		return this.left;
	}

	/**
	 * returns right child
	 * @return
	 */
	public Node getRight() {
		return this.right;
	}
	
	/**
	 * 
	 * @return
	 */
	public Node getParent(){
		return this.parent;
	}

	
	
	
}// end of node class

