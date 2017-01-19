/**
 * represents a node of the graph
 * @author Vivian A. Lam
 *
 */
public class Node {
////////////////////////////FIELDS/////////////////////////////////
	private int name;
	private boolean mark;
////////////////////////////CONSTRUCTOR/////////////////////////////////
	/**
	 * constructor, creates an UNMARKED node with the given name.
	 * The name of a node is an integer value between 0 and n - 1,
	 * where n is the number of nodes in the graph.
	 * @param name
	 */
	Node(int name){
		/*A node can be marked with a value that is either true or false using method setMark. This is
		useful when traversing the graph to know which vertices have already been visited*/
		this.name=name;
		
	}
////////////////////////////METHODS/////////////////////////////////	
	
	/**
	 * marks the node with the specified value
	 * @param mark
	 */
	public void setMark(boolean mark){
		this.mark=mark;
	}
	
	/**
	 * @return the value which the node has been marked
	 */
	public boolean getMark(){
		return this.mark;
	}
	
	/**
	 * @return the name of the vertex
	 */
	public int getName(){
		return this.name;
	}
	
}
