import java.io.*;

public class FindCommonAncestor {
	public static void main(String[] args){

		BufferedReader consoleReader  = new BufferedReader(new InputStreamReader(System.in));

		LinkedBinaryTree<String> linkedBinaryTree = null;
		TreeBuilder<String> theTreeBuilder = null;

		try{

			theTreeBuilder = new TreeBuilder<String>(args[0]);
			linkedBinaryTree = theTreeBuilder.buildTree();

			//Example prompt for user
			System.out.print("Enter first element: ");
			String firstElement = consoleReader.readLine();

		}
		catch( Exception e ){
			// you must handle exceptions as specified in the assignment description
		} 

	}
}
