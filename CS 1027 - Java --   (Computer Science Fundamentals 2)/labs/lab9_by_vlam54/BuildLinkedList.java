import java.util.*;

/**
 * Build a linked list of integers from 1 to 10
 * @author CS1027
 */
public class BuildLinkedList {

	public static void main(String[] args) {
		
		// create a linked list that holds 1, 2, ..., 10
		// by starting at 10 and adding each node at head of list
		
		LinearNode<Integer> head = null;	//create empty linked list
		LinearNode<Integer> intNode;

		
		//original: for (int i = 10; i >= 1; i--)
		//			for (int i = 1; i <= 10; i++)
		int counter = 0;
		intNode = new LinearNode<Integer>(new Integer(counter));
		
		//prompts user for size of linked list
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the size of the linked list");
		int listSize = scan.nextInt();
		
		
		/*
		 * I wasn't sure what the question was asking
		 */
		while(intNode!=null && counter<=listSize)//we can use a while loop that stops looping when the next field of the current node is null.
		{
			// create a new node for i
			intNode = new LinearNode<Integer>(new Integer(counter));
			// add it at the head of the linked list
			intNode.setNext(head);
			head = intNode;
			counter++;//increments counter
			
			//prints out the notes
			LinearNode<Integer> current = head; 	
			System.out.println(current.getElement());
			current = current.getNext();
			
			
			
		}
		
		
		
		/*
		
		// traverse list and display each data item
		// current will point to each successive node, starting at the first node
		
		LinearNode<Integer> current = head; 
		for (int i = 1; i <= 10; i++)
		{
			System.out.println(current.getElement());
			current = current.getNext();
		}
		
		
		*/
		
		
		
		
	}

}
