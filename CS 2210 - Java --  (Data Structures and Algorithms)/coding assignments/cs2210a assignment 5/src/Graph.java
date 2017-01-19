import java.util.*;
import java.util.Iterator;

/**
 * this class represents an undirected graph using adjacency matrix/lists representation for graph
 * @author Vivian A. Lam
 *
 */
public class Graph implements GraphADT{

////////////////////////////FIELDS/////////////////////////////////
	private Edge[][] adjacencyMatrix;	//the adjacency matrix
	private Node[] vertices;//node array for the vertices
	
////////////////////////////CONSTRUCTOR/////////////////////////////////
	/**
	 * constructor: creates a graph with n nodes and no edges
	 * This is the constructor for the class. The names of the nodes are 0, 1, . . . , n-1.
	 */
	public Graph(int n){
		adjacencyMatrix= new Edge[n][n];
		vertices = new Node[n];
		for(int i=0; i<n; i++){
			vertices[i] = new Node(i);//create and label the node
		}
	}
	
////////////////////////////METHODS/////////////////////////////////	
	
	/**
	 * adds an edge connecting u and v and belonging to a specified bus line
	 * throws a GraphException if either node does not exist or if in the graph there is already an edge connecting the given nodes.
	 */
	@Override
	public void insertEdge(Node u, Node v, String busLine) throws GraphException {
		//first check if the nodes(the end points) exists by seeing if their names belong in array of vertices
		if (u.getName()<0||u.getName()>=vertices.length ||v.getName()<0||v.getName()>=vertices.length){
			throw new GraphException("");	//if it doesn't belong throw exception
		}
			if(!noEdges(u,v)||!noEdges(v,u)){	//check if there's already an existing an edge between the two check adjacency matrix at u.getname and v.getname
				throw new GraphException("");	//if there's an edge (not null), cannot insert, throw exception
			}
			
			//add the edge in both directions
			adjacencyMatrix[u.getName()][v.getName()]= new Edge(u,v,busLine);//insert edge 
			adjacencyMatrix[v.getName()][u.getName()]= new Edge(u,v,busLine);
	}

	/**
	 * @return the node with the specified name. If no node with this name exists, the method should throw a GraphException.
	 */
	@Override
	public Node getNode(int name) throws GraphException {
		if(name<0||name>=vertices.length){//if node DNE throw exception
			throw new GraphException("");
		}
		else{
			return vertices[name];	//return the node from the list of vertices
		}
	}

	/**
	 * @return a Java Iterator storing all the edges incident on node u. It returns null if u does not have any edges incident on it.
	 * throw a GraphException if u is not a node of the graph.
	 */
	@Override
	public Iterator incidentEdges(Node u) throws GraphException {
		if(u.getName()<0||u.getName()>=vertices.length){	//check if u exists by checking if the name is in the array of vertices
			throw new GraphException("");					//if DNE throw exception
		}
		else{
			Stack<Edge> edgeStack = new Stack<Edge>();		//create a stack of type edge to store the incident edges
			for(int i=0; i<vertices.length;i++){			//go through the length of the array of nodes and check
				if(adjacencyMatrix[u.getName()][i] !=null){ //if there's an adjacent edge to that node
					edgeStack.push(adjacencyMatrix[u.getName()][i]);	//push edge at location [u.getName()][i] to the stack
				}
			}
			if(edgeStack.isEmpty()){						//if stack is empty return null
				return null;
			}
			return edgeStack.iterator();					//return an iterator of the stack that contains incident edges
		}
			
	}

	/**
	 * @return the edge connecting nodes u and v. This method throws a GraphException if there is no edge between u and v.
	 */
	@Override
	public Edge getEdge(Node u, Node v) throws GraphException {
		if(!areAdjacent(u,v)){	//if no edge between u and v throw exception. also if nodes u and v DNE the method areAdjacent will throw exception
			throw new GraphException("");
		}
		return adjacencyMatrix[u.getName()][v.getName()];//return the edge
	}

	/**
	 * @return Returns true if nodes u and v are adjacent; it returns false otherwise.
	 * throw a GraphException if u or v are not nodes of the graph.
	 */
	@Override
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		//if nodes u or v DNE throw exception 
		if(u.getName()<0||u.getName()>=vertices.length ||v.getName()<0||v.getName()>=vertices.length){
			throw new GraphException("");
		}
		else{
			if(noEdges(u,v)){//check if there exists an edge
				return false;//if not then return false
			}
			else{//otherwise an edge exists and therefore theyre adjacent
				return true;		
			}
		}
	}
	
	/*
	 * @return true if there exists an edge between nodes u and v. otherwise false
	 */
	private boolean noEdges(Node u, Node v){
		if (adjacencyMatrix[u.getName()][v.getName()] ==null){//will check matrix if an edge exists at that location. if so return true
			return true;
		}
		return false;//otherwise theres no edge, return false
	}

}
