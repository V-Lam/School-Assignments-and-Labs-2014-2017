import java.util.Iterator;

/**
 *  @author Lewis and Chase
 *
 *  Represents a linked implementation of a stack.
 */

public class LinkedStack<T> implements StackADT<T>
{
  /** indicates number of elements stored */
  private int count;  
  /** pointer to top of stack */
  private LinearNode<T> top; 

  /**
   * Creates an empty stack.
   */
  public LinkedStack()
  {
    count = 0;
    top = null;
  }

  /**
   * Adds the specified element to the top of this stack.
   * @param element element to be pushed on stack
   */
  public void push (T element)
  {
    LinearNode<T> temp = new LinearNode<T> (element);

    temp.setNext(top);
    top = temp;
    count++;
  }

  /**
   * Removes the element at the top of this stack and returns a
   * reference to it. Throws an EmptyCollectionException if the stack
   * is empty.
   * @return T element from top of stack
   * @throws EmptyCollectionException on pop from empty stack
   */
  public T pop(){
    if (isEmpty())
      throw new EmptyCollectionException("Stack");

    T result = top.getElement();
    top = top.getNext();
    count--;
 
    return result;
  }
   
  /**
   * Returns a reference to the element at the top of this stack.
   * The element is not removed from the stack.  Throws an
   * EmptyCollectionException if the stack is empty.
   * @return T element on top of stack
   * @throws EmptyCollectionException on peek at empty stack  
   */
  public T peek()  {
    if (isEmpty())
      throw new EmptyCollectionException("Stack"); 

    return top.getElement();
  }

  /**
   * Returns true if this stack is empty and false otherwise. 
   * @return boolean true if stack is empty
   */
  public boolean isEmpty()
  {
    return (count == 0);
  }
 
  /**
   * Returns the number of elements in this stack.
   * @return int number of elements in this stack
   */
  public int size()
  {
    return count;
  }

  /**
   * Returns a string representation of this stack. 
   * @return String representation of this stack
   */
  public String toString()
  {
	  String result = "";
	  LinearNode current = top;

      while (current != null)//if current node isnt null
     {
        result = result + (current.getElement()).toString() + ", ";//gets the item at the current element and calls toString to convert it to string
        current = current.getNext();//moves the node
      }

    return result; //returns the result
  }
  
  
  
  
  

  //lab 9 exercise
  public Iterator<LinkedStack> iterator()
  {
	  return new LinkedIterator(top, count);
  }

  
  
  
  
  
  
  
  
  
  //--------------------------------------------------------------------
  // Test harness for Linked Stack
  //--------------------------------------------------------------------
  public static void main(String[] args)
  {
    LinkedStack<String> stack = new LinkedStack<String>();
    String onTop;

    stack.push("A");
    stack.push("B");
    stack.push("C");
    stack.push("D");

    // test size and toString
    System.out.println("stack contains "+ stack.size() + " items: \n" + stack.toString());
    System.out.println("");
    
    
    /*EXERCISE 3: Lab 5 had code for a test harness for your LinkedStack.java. Add code to the test harness 
     * to test your iterator method, by iterating through the stack and printing the items in 
     * the stack. This is similar to what you did in Exercise 1 step 3. In what order are the items 
     * on the stack printed: top to bottom or bottom to top?
     * 
     * answer: top to bottom
     * */
    System.out.println("Iteration:");
    Iterator<LinkedStack> iter = stack.iterator();
    while(iter.hasNext() )
    {
  	  System.out.println(iter.next());
    }
    
    
    System.out.println("");
    
    
    // test isEmpty
    while (! stack.isEmpty())
    {
         onTop = stack.pop();
         System.out.println("popped item is " + onTop);
    }
    

    
    
    /*
     * Add a test harness to your ArrayStack class similar to the one you had for LinkedStack in step 2 of 
     * this exercise. In what order are the items on the stack printed: top to bottom or bottom to top?
     * */
    
    
    
 }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}