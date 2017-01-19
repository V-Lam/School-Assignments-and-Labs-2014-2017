import java.util.Iterator;

/**
 *  @author Lewis and Chase
 *
 *  Represents an array implementation of a stack.
 */

public class ArrayStack<T> implements StackADT<T>
{
  /**
   * constant to represent the default capacity of the array
   */
  private final int DEFAULT_CAPACITY = 100;

  /**
   * int that represents both the number of elements and the next
   * available position in the array
   */
  private int top;  

  /**
   * array of generic elements to represent the stack
   */
  private T[] stack;
  
  /**
   * Creates an empty stack using the default capacity.
   */
  public ArrayStack()
  {
    top = 0;
    stack = (T[])(new Object[DEFAULT_CAPACITY]);
  }

  /**
   * Creates an empty stack using the specified capacity.
   * @param initialCapacity represents the specified capacity 
   */
  public ArrayStack (int initialCapacity)
  {
    top = 0;
    stack = (T[])(new Object[initialCapacity]);
  }

  /**
   * Adds the specified element to the top of this stack, expanding
   * the capacity of the stack array if necessary.
   * @param element generic element to be pushed onto stack
   */
  public void push (T element)
  {
    if (size() == stack.length) 
      expandCapacity();

    stack[top] = element;
    top++;
  }

  /**
   * Removes the element at the top of this stack and returns a
   * reference to it. Throws an EmptyCollectionException if the stack
   * is empty.
   * @return T element removed from top of stack
   * @throws EmptyCollectionException if a pop is attempted on empty stack
   */
  public T pop() throws EmptyCollectionException
  {
    if (isEmpty())
      throw new EmptyCollectionException("Stack");

    top--;
    T result = stack[top];
    stack[top] = null; 

    return result;
  }
   
  /**
   * Returns a reference to the element at the top of this stack.
   * The element is not removed from the stack.  Throws an
   * EmptyCollectionException if the stack is empty.  
   * @return T element on top of stack
   * @throws EmptyCollectionException if a peek is attempted on empty stack
   */
  public T peek() throws EmptyCollectionException
  {
    if (isEmpty())
      throw new EmptyCollectionException("Stack");

    return stack[top-1];
  }

  /**
   * Returns true if this stack is empty and false otherwise. 
   * @return boolean true if this stack is empty, false otherwise
   */
  public boolean isEmpty()
  {
    return (top == 0);
  }
 
  /**
   * Returns the number of elements in this stack.
   * @return int the number of elements in this stack
   */
  public int size()
  {
    return top;
  }

  /**
   * Returns a string representation of this stack. 
   * @return String representation of this stack
   */
  public String toString()
  {
    String result = "";

    for (int scan=0; scan < top; scan++)
      result = result + stack[scan].toString() + "\n";

    return result;
  }

  /**
   * Creates a new array to store the contents of this stack with
   * twice the capacity of the old one.
   */
  private void expandCapacity()
  {
    T[] larger = (T[])(new Object[stack.length*2]);
    
    for (int index=0; index < stack.length; index++)
      larger[index] = stack[index];
    
    stack = larger;
  }
  
  
  //lab 9 exercise 3:
	public Iterator<ArrayStack> iterator() {
		int size = this.size();
		
		return new ArrayIterator(stack, size);
	}


	  //--------------------------------------------------------------------
	  // Test harness 
	  //--------------------------------------------------------------------
	  public static void main(String[] args)
	  {
	    ArrayStack<String> stack = new ArrayStack<String>();
	    String onTop;

	    stack.push("A");
	    stack.push("B");
	    stack.push("C");
	    stack.push("D");

	    // test size and toString
	    System.out.println("stack contains "+ stack.size() + " items: \n" + stack.toString());
	    System.out.println("");
	    
	    
	    /*lab9 EXERCISE 3: 
	     * In what order are the items on the stack printed: top to bottom or bottom to top?
	     * answer: bottom to top
	     * */
	    System.out.println("Iteration:");
	    Iterator<ArrayStack> iter = stack.iterator();
	    while(iter.hasNext() )
	    {
	  	  System.out.println(iter.next());
	    }
	  }
 
  
}


