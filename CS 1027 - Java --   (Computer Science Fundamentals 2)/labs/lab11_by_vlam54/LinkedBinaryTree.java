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
   protected BinaryTreeNode<T> root; 

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
      //left as programming project
	   return (T) this.root;
   }

   /**
    * Returns true if this binary tree is empty and false otherwise.
    *
    * @return  true if this binary tree is empty
    */
   public boolean isEmpty() 
   {
      //left as programming project
	   return (count == 0);
   }

   /**
    * Returns the integer size of this tree.
    *
    * @return  the integer size of this tree
    */
   public int size() 
   {
      //left as programming project
	   return count;
   }
   
   /**
    * Returns true if this tree contains an element that matches the
    * specified target element and false otherwise.
    *
    * @param targetElement              the element being sought in this tree
    * @return                           true if the element in is this tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public boolean contains (T targetElement) 
   {
      //left as programming project

	      T temp;
	      boolean found = false;

	      try 
		 {
	         temp = find (targetElement);
	         found = true;
	      }//try
	  
	      catch (Exception ElementNotFoundException) 
		 {
	         found = false;
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
      BinaryTreeNode<T> current = findAgain( targetElement, root );
      
      if( current == null )
         throw new ElementNotFoundException("binary tree");
      
      return (T) (current.getElement()); 
//original code:      return (current.element); 
   }

   /**
    * Returns a reference to the specified target element if it is
    * found in this binary tree.
    *
    * @param targetElement  the element being sought in this tree
    * @param next           the element to begin searching from
    */
   private BinaryTreeNode<T> findAgain(T targetElement, 
                                       BinaryTreeNode<T> next)
   {
      if (next == null)
         return null;
      if (next.getElement().equals(targetElement))    //if (next.element.equals(targetElement))
         return next;
      
      BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());	// BinaryTreeNode<T> temp = findAgain(targetElement, next.left);
      
      if (temp == null)
    	  temp = findAgain(targetElement, next.getRight());     // temp = findAgain(targetElement, next.right);
  
      return temp;
   }
   
   /**
    * Returns a string representation of this binary tree.
    *
    * @return  a string representation of this binary tree
    */
   public String toString() 
   {
      //left as programming project
        if (this.root == null) {
            return "[]";
        }

        String recStr = this.toString(this.root);
        return "[" + recStr.substring(0,recStr.length()-1) + "]";
    
   }
       private String toString(BinaryTreeNode<T> current) {
        if (current ==  null) {
            return "";
        }

        return this.toString(current.getLeft()) +
               current.getElement().toString() + "," +
               this.toString(current.getRight());
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
      ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
      inorder (root, tempList);
      
      return tempList.iterator();
   }

   /**
    * Performs a recursive inorder traversal.
    *
    * @param node      the node to be used as the root for this traversal
    * @param tempList  the temporary list for use in this traversal
    */
   protected void inorder (BinaryTreeNode<T> node, 
                           ArrayUnorderedList<T> tempList) 
   {
      if (node != null)
      {
         inorder (node.getLeft(), tempList);
         tempList.addToRear(node.getElement());
         inorder (node.getRight(), tempList);
        
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
      //left as programming project 
   		ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
   	
   		preorder (root, tempList);
		return tempList.iterator();
   }

   /**
    * Performs a recursive preorder traversal.
    *
    * @param node  the node to be used as the root for this traversal
    * @param tempList  the temporary list for use in this traversal
    */
   protected void preorder (BinaryTreeNode<T> node, 
                            ArrayUnorderedList<T> tempList) 
   {
      //left as programming project
      	if(node!=null){
      		tempList.addToRear(node.getElement());
      		preorder (node.getLeft(), tempList);
  			preorder (node.getRight(), tempList); 
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
      //left as programming project 
    	ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
   
	   	postorder (root, tempList);
		return tempList.iterator();
   }

   /**
    * Performs a recursive postorder traversal.
    *
    * @param node      the node to be used as the root for this traversal
    * @param tempList  the temporary list for use in this traversal
    */
   protected void postorder (BinaryTreeNode<T> node, 
                             ArrayUnorderedList<T> tempList) 
   {
      //left as programming project 
		if(node!=null){
      		preorder (root.getLeft(), tempList);
  			preorder (root.getRight(), tempList); 
  			tempList.addToRear(node.getElement());
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
      //left as programming project
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
}

