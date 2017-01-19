/**
 * this class represents the edge of the graph
 * @author Vivian A. Lam
 *
 */
public class Edge {
////////////////////////////FIELDS/////////////////////////////////
	private Node u;
	private Node v;
	private String busLine;
////////////////////////////CONSTRUCTOR/////////////////////////////////
	/**
	 * constructor for the class. first two parameters are the endpoints of the edge
	 * last parameter is bus line to which the street represented by the edge belongs
	 * Each bus line has a name that consists of a single letter, for example ”a”, ”b”, and ”c” might be the names of three bus lines.
	 */
	Edge(Node u, Node v, String busLine){
		this.u=u;
		this.v=v;
		this.busLine=busLine;
	}
	
	
////////////////////////////METHODS/////////////////////////////////	
	
	/**
	 * @return the first endpoint of the edge
	 */
	Node firstEndpoint(){
		return u;
	}
	
	/**
	 * @return the second endpoint of the edge
	 */
	Node secondEndpoint(){
		return v;
	}
	
	/**
	 * @return the busLine to which the edge belongs
	 */
	String getBusLine(){
		return this.busLine;
	}
	
	
	
	
	
}
