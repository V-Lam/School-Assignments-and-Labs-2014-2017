/**
 * LinkedBinaryTree implements the BinaryTreeADT interface
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

import java.util.Iterator;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T>
{
   protected int count;
   private BinaryTreeNode<T> root; 

   /**
    * Creates an empty binary tree.
    */
   public LinkedBinaryTree() 
   {
      count = 0;
      root = null;
   }

   /**
    * Creates a binary tree with the specified element as its root.
    *
    * @param element  the element that will become the root of the new binary
    *                 tree
    */
   public LinkedBinaryTree (T element) 
   {
      count = 1;
      root = new BinaryTreeNode<T> (element);
   }

    /**

    * Constructor creates tree from element as root and two subtrees

    * as left and right subtrees of root.

    * @param element		element at root	

    * @param leftSubtree	left subtree

    * @param rightSubtree	right subtree

    */


///////constructor from txt file
   public LinkedBinaryTree (T element, LinkedBinaryTree<T> leftSubtree, LinkedBinaryTree<T> rightSubtree) 
   {
	 root = new BinaryTreeNode<T> (element);
	 count = 1;
	 if (leftSubtree != null)
	 {
		count = count + leftSubtree.size();
		root.setLeft(leftSubtree.root);
	 }
	 else
		root.setLeft(null);
	 if (rightSubtree !=null)
	 {
		count = count + rightSubtree.size(); 
	 	root.setRight(rightSubtree.root);
	 }
	 else
		root.setRight(null);
   }
 
 
 
 
   /**
    * Returns a reference to the element at the root
    *
    * @return                           a reference to the specified target
    * @throws EmptyCollectionException  if the tree is empty
    */
   public T getRoot() throws EmptyCollectionException
   {
	   return (T) this.root;		//returns the root of a tree
   }

   /**
    * Returns true if this binary tree is empty and false otherwise.
    *
    * @return  true if this binary tree is empty
    */
   public boolean isEmpty() 
   {
	   return (count == 0);			//check if tree is empty: if there is nothing in the tree, returns true
   }

   /**
    * Returns the integer size of this tree.
    *
    * @return  the integer size of this tree
    */
   public int size() 
   {
	   return count;				//returns the size of the tree
   }
   
   /**
    * Returns true if this tree contains an element that matches the
    * specified target element and false otherwise.
    *
    * @param targetElement              the element being sought in this tree
    * @return                           true if the element in is this tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public boolean contains (T targetElement) //check if tree contains a certain element<- returns true if it contains it
   {
	      T temp;
	      boolean found = false;

	      try 
		 {
	         temp = find (targetElement);
	         found = true;
	      }//try  
	      catch (Exception ElementNotFoundException) 
		 {
	         found = false;							//if element is not in tree returns false
	      }//catch

	      return found;
   }
   
   /**
    * Returns a reference to the specified target element if it is
    * found in this binary tree.  Throws a NoSuchElementException if
    * the specified target element is not found in the binary tree.
    *
    * @param targetElement              the element being sought in this tree
    * @return                           a reference to the specified target
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public T find(T targetElement) throws ElementNotFoundException
   {
      BinaryTreeNode<T> current = findAgain( targetElement, root );//calls the recursive method to find the element
      
      if( current == null )										//iff current node is null
         throw new ElementNotFoundException("binary tree");		//throws an exception
      
      return (T) (current.getElement()); //returns the element
   }

   /**
    * Returns a reference to the specified target element if it is
    * found in this binary tree.
    *
    * @param targetElement  the element being sought in this tree
    * @param next           the element to begin searching from
    */
   private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next)
   {
      if (next == null)													//if theres nothing return null
         return null;
      if (next.getElement().equals(targetElement))						//if the current element equals the targetElement (what we're searching for)
         return next;													//return the node
      
      //recursively calls the method to keep searching if a match is not found
      BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());	//check left child
      
      if (temp == null)														//if it's not left
    	  temp = findAgain(targetElement, next.getRight());     			//check right child
  
      return temp;															//return the node
   }
   
   /**
    * Returns a string representation of this binary tree.
    *
    * @return  a string representation of this binary tree
    */
   public String toString() 
   {
        if (this.root == null) {				//if tree is empty print representation of empty tree (the square brackets)
            return "[]";
        }
        String recStr = this.toString(this.root);			//creates a string and recursively calls the toString method
        return "[" + recStr.substring(0,recStr.length()-1) + "]";		//adds the substring
   }
       private String toString(BinaryTreeNode<T> current) {
        if (current ==  null) {								//if the current node is null
            return "";
        }
        return this.toString(current.getLeft()) + current.getElement().toString() + "," + this.toString(current.getRight());
    }
   

   /**
    * Performs an inorder traversal on this binary tree by calling an
    * overloaded, recursive inorder method that starts with
    * the root.
    *
    * @return  an in order iterator over this binary tree
    */
   public Iterator<T> iteratorInOrder()
   {
      ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();	//creates an array unordered list
      inorder (root, tempList);										//calls the inorder method, passing the root and the list
      
      return tempList.iterator();	//returns the iterator
   }

   /**
    * Performs a recursive inorder traversal.
    *
    * @param node      the node to be used as the root for this traversal
    * @param tempList  the temporary list for use in this traversal
    */
   protected void inorder (BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) 
   {
      if (node != null)								//if node is not null
      {
         inorder (node.getLeft(), tempList);		//calls the inorder method, passing the left child
         tempList.addToRear(node.getElement());		//adds the element at the node to the end of the list
         inorder (node.getRight(), tempList);		//calls the inorder method, passing the right child
        
         /*original code
         inorder (node.left, tempList);
         tempList.addToRear(node.element);
         inorder (node.right, tempList);
		*/
      }
   }

   /**
    * Performs an preorder traversal on this binary tree by calling 
    * an overloaded, recursive preorder method that starts with
    * the root.
    *
    * @return  an pre order iterator over this tree
    */
   public Iterator<T> iteratorPreOrder() 
   {
   		ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();		//creats an arrayunordered list
   	
   		preorder (root, tempList);								//calls the preorder method, passing the root and list
		return tempList.iterator();								//returns the iterator
   }

   /**
    * Performs a recursive preorder traversal.
    *
    * @param node  the node to be used as the root for this traversal
    * @param tempList  the temporary list for use in this traversal
    */
   protected void preorder (BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) 
   {
      	if(node!=null){										//if the node isnt null
      		tempList.addToRear(node.getElement());			//add the node to the end of the list
      		preorder (node.getLeft(), tempList);			//calls the preorder method, passing the left child
  			preorder (node.getRight(), tempList); 			//calls the preorder method, passing the right child
      	} 
   }

   /**
    * Performs an postorder traversal on this binary tree by calling
    * an overloaded, recursive postorder method that starts
    * with the root.
    *
    * @return  a post order iterator over this tree
    */
   public Iterator<T> iteratorPostOrder() 
   {
    	ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();	//creates an array unordered list
   
	   	postorder (root, tempList);										//calls the postorder method, passing the root and list
		return tempList.iterator();										//returns the iterator
   }

   /**
    * Performs a recursive postorder traversal.
    *
    * @param node      the node to be used as the root for this traversal
    * @param tempList  the temporary list for use in this traversal
    */
   protected void postorder (BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) 
   {
		if(node!=null){										//if the node is not null
      		postorder (root.getLeft(), tempList);			//calls the postorder method and passes left child
  			postorder (root.getRight(), tempList); 			//calls the postorder method passes right child
  			tempList.addToRear(node.getElement());			//adds the element at the node to the end of the list
      	} 
   }

   /**
    * Performs a levelorder traversal on this binary tree, using a
    * templist.
    *
    * @return  a levelorder iterator over this binary tree
    */
   public Iterator<T> iteratorLevelOrder() 
   {
	      ArrayUnorderedList<BinaryTreeNode<T>> nodes = new ArrayUnorderedList<BinaryTreeNode<T>>();
	      ArrayUnorderedList<T> result = new ArrayUnorderedList<T>();
	      BinaryTreeNode<T> current;
	      nodes.addToRear (root);
	      while (! nodes.isEmpty()) 
		 {
	         current = (BinaryTreeNode<T>)nodes.removeFirst();
	         if (current != null) 
		    {
	            result.addToRear(current.getElement());
	            nodes.addToRear (current.getLeft());
	            nodes.addToRear (current.getRight());
	         }//if
	         else
	            result.addToRear(null);
	      }//while
	      return result.iterator();
   }
   
   
   ///////////////////////assignment 3
   /*************************    Assignment 3    ****************************************/

   /**
    * Finds the target elements and returns a path from the targetElement to the root. It calls a recursive method to do this.
    * 
    * @param targetElement the element we want to find
	* @param node the current node
    * @return Iterator which will iterate over elements on the path from the targetElement 
    * to the root of the tree (includes both).
	* @throws ElementNotFoundException if the element isnt in the tree
    */
   public Iterator<T> pathToRoot(T targetElement) throws ElementNotFoundException{
	   
	  ArrayUnorderedList<T> pathToRoot = new ArrayUnorderedList<T>();		//declare pathToRoot to be an unordered list array object
	  pathToRootAgain(targetElement, root, pathToRoot);						//calls a recursive method, pathToRootAgain, passing the targetElement, root and pathToRoot as parameters 
	
	   if(pathToRoot.isEmpty()==true){ 										//if the list is empty (no path)
		   throw new ElementNotFoundException("binary tree");				//throw exception
	   }	   
	   return pathToRoot.iterator();										//returns the iterator
   }
   


   /**
    * Will add to the pathToRoot ArrayUnorderedList<T> visitor if the node is on the path from the targetElement to the root of the tree
    * 
    * recursive method to find the element in the tree, and all the other elements on the path from the targetElement to the root
	*
    * @param targetElement the element we want to find
    * @param node the current node
    * @param pathToRoot a list to store all the elements from the targetElement to the root
    */
   protected void pathToRootAgain(T targetElement, BinaryTreeNode<T> node, ArrayUnorderedList<T> pathToRoot){
    //method to actually find the element and then add the elements on the path to a list
	  
     	if(node!=null){											//if node is not null
     		if(node.getElement().equals(targetElement)){		//check if the node's element is equal to targetElement
     			pathToRoot.addToRear(node.getElement());		//add the current element to the end of the pathToRoot list
     		}
     		else{
     			//check the left child
    			pathToRootAgain(targetElement,node.getLeft(),pathToRoot);	//recursively calls the method, this time passing the left child
    			if(pathToRoot.isEmpty()){									//if the list is empty
       				//check the right child
       				pathToRootAgain(targetElement,node.getRight(),pathToRoot);//recursively calls the method, this time passing the right child
       			}
    			if (!pathToRoot.isEmpty()){ 					//if the list is not empty
 			   		pathToRoot.addToRear(node.getElement());	//add the elements on the path to a list
    			}
     		}
     	}
   }


   /**
    * Finds the lowest (ie. deepest) or maximal-level node common to both the path from 
    * targetOne to the root and the path from targetTwo to the root.
	*
	* i.e. using paths to find the lowest common ancestor between two elements
	*
    * @param targetOne The first element to find
    * @param targetTwo The second element to find
    * @return the element found in the lowest common ancestor node for targetOne and targetTwo
    * @throws ElementNotFoundException if the element isnt in the tree
    */
   public T lowestCommonAncestor( T targetOne, T targetTwo) throws ElementNotFoundException{
    	
    	//one and two are iterators for targetOne and targetTwo
		Iterator<T> one = pathToRoot(targetOne);
		Iterator<T> two = pathToRoot(targetTwo);
    	
    	ArrayUnorderedList<T> onPathOne= new ArrayUnorderedList<T>();			//unordered array list of type T

		while(one.hasNext()){										//while there is an element for the iterator one
     		onPathOne.addToRear(one.next());						//add one.next to the rear of the onPathOne list
 		}
		while(two.hasNext()){										//while there is an element for the iterator two
			T temp =  two.next();									//creates a variable temp of type T to store the current element of two
			if(onPathOne.contains(temp)){							//if the list onPathOne contains the current element of two
				return temp;										//return temp
			}		
		}
		// in the worst case the common ancestor of the root should not ever get here
		return root.getElement(); //if there is no lower common ancestor, then the lowest common ancestor is the root. returns the element at the root		 
   }
  
   
}

