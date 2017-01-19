import java.io.*;//import statement
import java.util.Iterator;
import java.util.*;
/**
 * main method to test the pathToRoot and the pathToRootAgain method. outputs the path for each element in the tree to the root
 * @author Vivian A. Lam
 * 
 * @param args arbitrary variable for the creation of main method
 * @throws MalformedTreeFileException if tree isnt formed properly
 * @throws IOException if cannot read file
 */
public class TestPathToRoot {

	public static void main(String[] args) {
		
		//declaring variables. treeBuilder and LinkedBinaryTree objects to build a tree from reading a file
		LinkedBinaryTree<String> theTree = null;
		TreeBuilder<String> theTreeBuilder = null;
		
		try{
			//prompts user to enter a file name
			System.out.println("Enter a filename");
			Scanner scan = new Scanner(System.in);								//creates a new scanner object to get the input
			String fileName = scan.nextLine();									//reads the line. creates a string called fileName to store this value
			scan.close();
			
			theTreeBuilder = new TreeBuilder<String>(fileName);					//sets the value of theTreeBuilder to be a tree (constructs a tree from the document file)
			theTree = theTreeBuilder.buildTree();								//builds the tree
							
			
		}//try
		catch( MalformedTreeFileException e) {												//exception for malformed tree (tree isnt formed correctly)
			System.out.println( e.getMessage());
		}
		catch( IOException e){																//exception for IO (cannot read file)
			System.out.println("Error reading file: " + args[0] + "\n" + e.getMessage());
		}

		////////////complete the program here
		
		//declaring variables
		Iterator<String> treeElements= theTree.iteratorInOrder();		//creates an iterator of strings. Uses the iteratorInOder method to iterate the elements inorder
		String current;													//creates a string variable called current to store the current node element

		while(treeElements.hasNext())									//while there is a next element in the tree
		{
			current = treeElements.next();								//sets the value of current to be the next element in the tree
			
			//iterator to store and print the paths (all the elements) from a node to a root
			Iterator<String> pathFromCurrent= theTree.pathToRoot(current);//calls pathToRoot method and sets the value of pathFromCurrent equal to this
			
			System.out.print("For element: "+ current + " - the path to the root is: "); //prints out the current element
			while (pathFromCurrent.hasNext())							//while loop to print out (iterate) through the elements in pathFromCurrent
				System.out.print(pathFromCurrent.next() + " ");			//prints out the elements in the path
				System.out.println();
		}
	
	
	}

}
