import java.io.*;//import statements
import java.util.Iterator;
/**
 * main method to test the lowest common ancestor method. prompts user to enter two elements, reads a file and finds the lowest common ancestor
 * between these two elements in the tree
 * 
 * @author Vivian A. Lam
 * 
 * @param args arbitrary variable for the creation of main method
 * @throws MalformedTreeFileException if tree isnt formed properly
 * @throws IOException if cannot read file
 */
public class FindCommonAncestor {
	public static void main(String[] args){

		//declaring variables. creates a BufferedReader call consoleReader to read the line of what the user inputs
		BufferedReader consoleReader  = new BufferedReader(new InputStreamReader(System.in));
		
		//declaring variables. treeBuilder and LinkedBinaryTree objects to build a tree from reading a file
		LinkedBinaryTree<String> linkedBinaryTree = null;
		TreeBuilder<String> theTreeBuilder = null;

		try{
			//prompts user to enter a file name
			System.out.println("Enter a filename");
			String fileName=consoleReader.readLine();					//reads the line. creates a string called fileName to store this value
			
			theTreeBuilder = new TreeBuilder<String>(fileName); 				//sets the value of theTreeBuilder to be a tree (constructs a tree from the document file)
			linkedBinaryTree = theTreeBuilder.buildTree();						//builds the tree
			
			
			//declaring variables. iterator to print out all the elements in the tree
			Iterator<String> treeElements= linkedBinaryTree.iteratorInOrder();		//creates an iterator of strings. Uses the iteratorInOder method to iterate the elements inorder
			
			//prints out all the elements in the tree
			System.out.println("The tree contains");
			while(treeElements.hasNext())									//while there is a next element in the tree
			{
				System.out.print(treeElements.next() + " ");				//prints the element	
			}
			System.out.println();
			
			
			//prompts user to enter two elements
			System.out.print("Enter first element: ");
			String firstNode = consoleReader.readLine();					//creates a string called firstNode to read the line and store the value inputted by the user
			
			System.out.print("Enter second element: ");
			String secondNode = consoleReader.readLine();					//creates a string called secondNode to read the line and store the value inputted by the user
			
			
			//prints out the lowest common ancestor by calling the method lowestCommonAncestor and passing firstNode and secondNode as the arguements
			System.out.println("The lowest common ancestor is: " +  linkedBinaryTree.lowestCommonAncestor(firstNode,secondNode));
		
		}
		catch( MalformedTreeFileException e) {
			System.out.println( e.getMessage());
		}//catch malformed tree
		catch( IOException e){
			System.out.println("Error reading file: " + args[0] + "\n" + e.getMessage());
		}//catch IO
		
		
		
		/*
		catch( Exception e ){
			// you must handle exceptions as specified in the assignment description
		}*/
		
		
	}
}
