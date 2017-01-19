/**
 * BinaryTreeNode represents a node in a binary tree with a left and 
 * right child.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

public class BinaryTreeNode<T>
{
   private T element;
   private BinaryTreeNode<T> left, right;

   /**
    * Creates a new tree node with the specified data.
    *
    * @param obj  the element that will become a part of the new tree node
   */
   BinaryTreeNode (T obj) 
   {
      element = obj;
      left = null;
      right = null;
   }

   /**
    * Returns the number of non-null children of this node.
    * This method may be able to be written more efficiently.
    *
    * @return  the integer number of non-null children of this node 
    */
   public int numChildren() 
   {
      int children = 0;

      if (left != null)
         children = 1 + left.numChildren();

      if (right != null)
         children = children + 1 + right.numChildren();

      return children;
   }
   
   /////////////////setters and getters
   public void setElement(T newElement){
	   this.element = (T) newElement;
   }
   public T getElement(){
	   return  this.element;
   }
   
   
   public void setLeft(BinaryTreeNode<T> newLeft){
	   this.left = newLeft;
   }
   public BinaryTreeNode<T> getLeft(){
	   return this.left;
   }

   
   public void setRight(BinaryTreeNode<T> newRight){
	   this.right = newRight;
   }
   public BinaryTreeNode<T> getRight(){
	   return this.right;
   }
   
   
}

