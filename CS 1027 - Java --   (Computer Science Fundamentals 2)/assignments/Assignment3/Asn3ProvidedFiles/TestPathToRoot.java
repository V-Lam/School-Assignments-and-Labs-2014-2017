import java.io.*;
import java.util.Iterator;

public class TestPathToRoot {

	public static void main(String[] args) {
		LinkedBinaryTree<String> theTree = null;
		TreeBuilder<String> theTreeBuilder = null;

		try{
			theTreeBuilder = new TreeBuilder<String>(args[0]);
			theTree = theTreeBuilder.buildTree();
		}
		catch( MalformedTreeFileException e) {
			System.out.println( e.getMessage());
		}
		catch( IOException e){
			System.out.println("Error reading file: " + args[0] + "\n" + e.getMessage());
		}

		//complete the program here

	}

}
