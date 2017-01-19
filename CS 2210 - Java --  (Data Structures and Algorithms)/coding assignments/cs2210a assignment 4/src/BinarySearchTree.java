import java.util.*;

/**
 * implements an ordered dictionary using a binary search tree. each node of the tree
 * will store a DictEntry object; the attribute Position of the DictEntry will be its key
 * note: ONLY THE INTERNAL NODES WILL STORE INFO, leaves are nodes (not null), they just don't store data
 * @author Vivian A. Lam
 *
 */
public class BinarySearchTree implements BinarySearchTreeADT{

	/////////////////////////////////////FIELDS//////////////////////////////////////////
	private Node root;
	
	/////////////////////////////////////CONSTRUCTOR/////////////////////////////////////

	/**
	 * constructor to create a tree whose root is a leaf node (an empty binary tree)
	 */
	public BinarySearchTree(){
		root = new Node(null);
	}
	
	/**
	 * constructor to create a tree with a root node containing the passed element
	 */
	public BinarySearchTree(DictEntry element){
		root = new Node(element);
	}
	
	/////////////////////////////////////METHODS/////////////////////////////////////////

	/**
	 * returns the DictEntry storing the given key if it exists in the tree. Returns null otherwise
	 */
	@Override
	public DictEntry find(Position key) {
		Node current = findN(key, root);		//invoke find method to find the node storing the key
		
		if(current.getElement() == null){		//if key doesn't exist (node is not found)
			return null;
		}
		else{									//else key exists
			return current.getElement();		//returns the element of the node
		}
	}
	
	
	/**
	 * finds the node containing the key
	 * @return the node containing the key
	 */
	private Node findN(Position key, Node next){
		if (next == null){		//if next is a leaf then return it (where it should have been)
			return next;
		}
		else{					//otherwise it's not a leaf
			Node p = next;
			while (p.getLeft() !=null && p.getRight()!=null){		//while p is not a leaf: children are not null
				
				//compare the Position object at node p and see if it matches/is greater/less than key
				if(p.getElement().getPosition().compareTo(key) ==0){	//if they are equal (found!)
					return p;											//return the node
				}
				else if( p.getElement().getPosition().compareTo(key) ==1){	//p>key
					p= p.getLeft();											//go left
				}
				else{														//p<key
					p = p.getRight();										//go right
				}
			}
			return p;														//return the node
		}
	}
	
	

	/**
	 * insert given data in the tree if no data item with he same key is already there.
	 * if node already stores the same key the algorithm throws an exception
	 */
	@Override
	public void insert(DictEntry data) throws BSTException {
		Position key = data.getPosition();			//variable to store the Position object of the passed DictEntry
		
		if (find(key)!= null){						//if it is found throw and exception
			throw new BSTException("");
		}
		else{										//otherwise it DNE, invoke the node insert method to insert into tree
			insertN(root,data);
		}
	}
	
	/**
	 * insert a node into the BST
	 */
	private void insertN(Node r, DictEntry data){
		Position key = data.getPosition();			//variable to store the Position object of the passed DictEntry
		
		Node p = findN(key, r); 		//check if key exists already throw exception. otherwise
		p.setElement(data); 			//store the key and data in Node p
	
		//create two leaf nodes and set them as children of p
		Node newLeft = new Node(null);
		Node newRight = new Node(null);
		p.setLeft(newLeft);
		p.setRight(newRight);
			
		//make p the parent of these leaf nodes
		newRight.setParent(p);
		newLeft.setParent(p);
	}
	
	
	
	
	/**
	 * removes the data item with he given key if it exists in the tree
	 * otherwise throw and exception
	 */
	@Override
	public void remove(Position key) throws BSTException {
		//invokes find method to search for key
		if (find(key)!= null){			//if it is found
			removeN(root,key);			//invoke the node removal method
		}
		else{		//otherwise it's not in the BST, throw exception
			throw new BSTException("");
			
		}	
	}
	
	/**
	 * removes a node in the BST, throws exception if the key DNE
	 */
	private void removeN(Node r, Position key)throws BSTException{
		Node p = findN(key, r);						//create a node p and make it point to the node we want to remove
		
		if (p.getLeft()==null && p.getRight() == null){		//p is a leaf, key DNE
			throw new BSTException("");						//throw exception
		}
		else{
			//create two nodes that point to the children of p
			Node c1=p.getLeft();
			Node c2=p.getRight();
			
			if ((c1.getLeft()==null && c1.getRight()==null)){			//if one child (left) is a leaf
				if(p == root){							//if p is the root of the BST
					//set c2 as new root, parent of c2 is null (c2 is now new root)
					root=c2;
					c2.setParent(null);
				}
				else{													//p is not the root of the BST
					//create a node s that points to the parent of p. s is used to check whether p is the left or right child
					Node s = p.getParent();					
					
					//check whether p is the left or right child of s
					if(s.getRight()==p){		//if p is the right child of s
						s.setRight(c2);				//remove p and c1 by making s the parent of c2 (s's new right child is c2)
						c2.setParent(s);
					}
					else{						//if p was the left child of s
						s.setLeft(c2);				//remove p and c1 by making s the parent of c2 (s's new left child is c2)
						c2.setParent(s);
					}
				}
			}
			else if(c2.getLeft()==null && c2.getRight()==null){		//if one child (right) is a leaf
				if(p == root){						//if p is the root of the BST
					//set c1 as new root, parent of c1 is null (c1 is now new root)
					root=c1;
					c1.setParent(null);
				}
				else{													//p is not the root of the BST
					//create a node s that points to the parent of p. s is used to check whether p is the left or right child
					Node s = p.getParent();
					
					if(s.getRight()==p)			{//if p is the right child of s
						s.setRight(c1);				//remove p and c2 by making s the parent of c1 (s's new right child is c1)
						c1.setParent(s);
					}
					else{						//if p was the left child of s
						s.setLeft(c1);				//remove p and c2 by making s the parent of c1 (s's new left child is c1)
						c1.setParent(s);
					}
				}
			}
			else{											//both children of p are internal
					Node q = smallest(p.getRight());			//create a node q to find the node containing the smallest key
					p.setElement(q.getElement());				//store the data from q into p				
					Node t = q.getParent();					//create a parent node t to check if q is the left or right child
					Node tnewChild =q.getRight();			//create a node to point to the right child of q
					
					//check if q is the left or right child of t
					if(t.getLeft() == q){			//if q is the left child of t
						t.setLeft(tnewChild);			//make t point to the right child of q (this removes q)
						tnewChild.setParent(t);
					}
					else{							//q is the right child of t
						t.setRight(tnewChild);		//make t point to the right child of q (this removes q)
						tnewChild.setParent(t);
					}
			}
		}	
	}
	

	/**
	 * returns the DictEntr with the smallest key larger than the given one.
	 * if no successor, returns null
	 */
	@Override
	public DictEntry successor(Position key) {
		Node theNode = succ(root, key);			//creates a node and makes it point to whatever is returned from the succ method
		if(theNode!=null){						//if the node isn't null then return the element it contains
			return (theNode.getElement());
		}
		else{
			return null;						//otherwise return null
		}
		
	}
	/*
	 * returns the node containing the smallest key larger than the given one
	 * if no successor, returns null*/
	private Node succ(Node r, Position key){
		
		if(r.getLeft()==null && r.getRight()==null){ //if r is a leaf, successor DNE: return null
			return null;
		}
		
		
		/* pointer node p = find the node with the key
		 * if right child of p is an internal node then return smallest(right child of p) (go right once then keep going left)
		 * else p is not an internal node: move up the tree*/
		Node p = findN(key, r);						//get the node in the BST
		
		if(p.getRight().getElement() !=null){		//right child of p is an internal node
			return smallest(p.getRight());			//invoke the smallest method to find the smallest key the right subtree of p
		}
		else{
			Node newP = p.getParent();		//node to get the parent of p
			
			while ((newP != null) && (p == newP.getRight())){//keep going as long as there is a parent and p is the right child of the parent
				//move up the tree: make p point to the parent, and make the parent point to the new parent
				p=newP;
				newP = p.getParent();
			}
			if(newP == null){	//parent is null
				return null;
			}
			else{				//return the parent
				return newP;
			}
		}
	}

	
	
	
	/**
	 * returns the DictEntry with the largest key smaller than the given one
	 * returns null if given key has no predecessor
	 */
	@Override
	public DictEntry predecessor(Position key) {
		Node theNode = pred(root, key);//creates a node and makes it point to whatever is returned from the succ method
		
		if(theNode!=null){						//if the node isn't null then return the element it contains
			return (theNode.getElement());
		}
		else{
			return null;						//otherwise return null
		}
	}
	/*
	 * returns the node with the largest key smaller than the given one
	 * returns null if given key has no predecessor*/
	private Node pred(Node r, Position key){

		if(r.getLeft()==null && r.getRight()==null){ //if r is a leaf, predecessor DNE: return null
			return null;
		}
		
		/* pointer node p = find the node with the key*/
		Node p = findN(key, r);
		
		if(p.getLeft().getElement() !=null){	//left child of p is an internal node
			return largest(p.getLeft());		//invoke the largest method to find the smallest key the right subtree of p
		}
		else{
			Node newP = p.getParent();						//node to get the parent of p
			
			while ((newP != null) && (p == newP.getLeft())){//keep going as long as there is a parent and p is the left child of the parent
				//move up
				p=newP;
				newP = p.getParent();
			}
			if(newP == null){	//parent is null
				return null;
			}
			else{				//return the parent
				return newP;
			}
		}
	}
	

	/**
	 * returns the DictEntry with the smallest key
	 * otherwise null if tree doesn't contain data
	 */
	@Override
	public DictEntry smallest() {
		return smallest(root).getElement();					//invoke the smallest method
	}
	
	private Node smallest(Node r){
		if (r.getLeft() ==null && r.getRight() == null){	//if Node r is a leaf
			return r;										//return it
		}
		else{												//r is not a leaf
			Node p=r;						//node to traverse to find the node containing the smallest key
			//while(p.getLeft()!=null && p.getRight()!=null){//while p is not a leaf
			while (p.getElement() !=null){					//while p is not a leaf: doesn't contain null
				p=p.getLeft();								//go left
			}
			return p.getParent();							//return the parent of p
			
		}
	}
	
	

	/**
	 * returns the DictEntry with the largest key
	 * otherwise null if the tree doesn't contain any data
	 */
	@Override
	public DictEntry largest() {
		return largest(root).getElement();					//invoke the largest method
	}
	
	private Node largest(Node r){
		if (r.getLeft() == null && r.getRight() == null){//if Node r is a leaf
			return r;									//return it
		}
		else{
			Node p=r;					//node to traverse to find the node containing the largest key
			while(p.getElement()!=null){		//while p is not a leaf, doesn't contain null
				p=p.getRight();					//go right
			}
			return p.getParent();						//return the parent of p
		}
	}
	
	
	
	
	
	
}
