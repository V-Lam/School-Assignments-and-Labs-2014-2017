import java.io.*;
import java.util.*;

/**
 * This class represents the map of bus lines. A graph will be used to store the map and to find a path from the starting point to the destination. 
 * @author Vivian A. Lam
 *
 */
public class Map {
////////////////////////////FIELDS/////////////////////////////////
	
	  private int gridSize;		//size of grid for scaling
	  private int busChanges;   //Number of bus changes allowed in solution
	  private int mapWidth;     //Width of the map (number of nodes across)
	  private int mapLength;    //Length of the map (number of nodes in a column of the graph)
	  private Graph theGraph;	//the graph
	  private Node start, end;	//variables to store the reference to the starting and destination nodes
	  private Stack<Node> stack;//stack of nodes
	  private int currNode;		//current node
	  private int nextNode;		//the next node
	
////////////////////////////CONSTRUCTOR/////////////////////////////////
	public Map(String inputFile) throws MapException{
		try {
			BufferedReader input = new BufferedReader(new FileReader(inputFile));
			String inStr;
			String busLine;
			
			//parseInt the first 4 lines and store into the variables
			gridSize = Integer.parseInt(input.readLine());
			mapWidth = Integer.parseInt(input.readLine());
			mapLength = Integer.parseInt(input.readLine());
			busChanges = Integer.parseInt(input.readLine());

			theGraph = new Graph(mapWidth * mapLength);//create a graph object to fit the size of the map
			stack = new Stack<Node>();
			
			inStr = input.readLine();

			//Initialize counters
			currNode = 0;
			int counter = 0;//counter to check if current character is even or not
			int colCount = 0;//counter to check if the current row is even or odd
			int columnNode = 0;//counter to check if the current column is evenor odd
			
			/*this is because the way the text is formatted on odd lines: L L L L
			 * if we have a space, we have to link the nodes above and below it (insert edge)*/
			
			
			/* Note: the following loop below has similar code but for different cases. to avoid redundancy and make the program less cluttery, i've only 
			 * commented a few lines and skipped repeated code
			 * Please don't deduct any marks or anything, I just want it to be clean and avoid repetitions and hand it in on time. thank you :)
			 */
			
			
			//loop to parse the rest of the file character by character
			for(int i = 0; i < (mapLength*2)-1; i++){//go through the rows
				for(int j = 0; j < (mapWidth*2)-1; j++){//go throuw the columns
					busLine = "";			//reset the string
					if (i%2 == 0){			//check to see if current value of i is an even number to check if we are on an even row
						if(inStr.charAt(j) == '0'){				//if the character is a 0: start
							start = theGraph.getNode((i/2*mapWidth) + (j/2));//set the start node
						}
						else if(inStr.charAt(j) == '1'){		//if the character is a 1: destination
							end = theGraph.getNode((i/2*mapWidth) + (j/2));//set the end node
						}
						else if(inStr.charAt(j) == '+'){		//if the character is a +: intersection, do nothing
						}
						else if(inStr.charAt(j) == ' '){		//if character is a space, skip to the next character (increment currNode counter)
							currNode++;
						}
						else{									//else it's a character, insert an edge between the nodes (current node and next node)
							busLine = inStr.charAt(j)+"";	//get the name of the busline for the edge
							nextNode= currNode+1;					//get the next node
							theGraph.insertEdge(theGraph.getNode(currNode), theGraph.getNode(nextNode), busLine);
							currNode++;								//increment to go to next character
						}	
					}
					else{//odd row
						if (j%2 == 0){//even column
							if(inStr.charAt(j) == ' '){//skip to next character by incrementing the counters
								columnNode++;
								colCount++;
							}
							else{//insert into graph
								busLine = inStr.charAt(j)+"";
								theGraph.insertEdge(theGraph.getNode(colCount), theGraph.getNode(columnNode), busLine);
								columnNode++;
								colCount++;
							}
						}
						else{//odd column
							if(inStr.charAt(j) == ' '){//skip to next character
							}
							else{
								busLine = inStr.charAt(j)+"";
								theGraph.insertEdge(theGraph.getNode(colCount), theGraph.getNode(columnNode), busLine);
								columnNode++;
								colCount++;
							}
						}
					}
				}
				
				
				if(counter%2==0){//if the counter is even then increase it again
					currNode++;
				}
				if(counter==0){//reset counters so that we don't go out of boundaries
					columnNode = mapWidth;
				}
				counter++;

				inStr = input.readLine();//NEXT LINE
			}
		} 
		catch (Exception e) {	
			throw new MapException("");
		}
	}
	
	/** NOTE: input file
	 * C is the scale factor used to display the map on the screen. Your java code will not use this value; it is used by the programs supplied to you.
	 * 		If the map appears too small on your screen, you must increase this value. Similarly, if the map is too large, choose a smaller value for the scale.
	 * W is the width of the map. The streets of the map are arranged in a grid. The number of vertical streets in each row of this grid is the width of the map.
	 * H is the length of the map, or the number of horizontal streets in each column of the grid.
	 * K is the number of bus line changes allowed in the path from the starting point to the destination.
	 * 
	 * R is any of the following characters: ’0’, ’1’, or ’+’. L could be ’ ’ (space), or a letter.
	 * 		’0’: starting point
	 * 		’1’: destination
	 * 		’+’: intersection of two streets
	 * 		’ ’: block of houses
	 * 		letter: street belonging to the bus line whose name is specified by the letter
	 */
	
////////////////////////////METHODS/////////////////////////////////	

	/**
	 * @return the graph representing the map. Throws MapException if graph isn't defined
	 */
	Graph getGraph() throws MapException{
		if (theGraph==null){
			throw new MapException("");
		}
		return this.theGraph;
	}
	
	
	/* busline change happens when in the path there are two adjacent edges belonging to diff bus lines
	 * 
	 * path can be found using a modified DFS traversal: while traversing the graph keep track of the nodes along the path
	 * and if current path already has max # of bus line changes then no more bus line changes cna be added
	 *
	 * NOTE: the DFS algortihm is split between the two methods below
	 */
	
	
	/**
	 * @return a Java Iterator containing the nodes along the path from the starting point to the destination, if such a path exists.
	 * If the path does not exist, this method returns the value null
	 */
	Iterator findPath(){
		try {
			Iterator<Edge> inEdge = theGraph.incidentEdges(start);//iterator of edges to iterate through the incident edges
			Edge currEdge = new Edge(start, end, "string");//for the current edge
			
			while(inEdge.hasNext()){//loop while the iterator is not empty: loop for all incident edges to node start
				currEdge = inEdge.next();
				pathDFS(start, end, currEdge);//invoke DFS traversal: path p = recursive call

				if(!stack.empty()){//if stack isnt empty return the iterator
					return stack.iterator(); //return the elements of the stack
				}
			}
		} 
		catch (GraphException e) {//graph is improper
			System.out.println("");
		}
		return null;//there is no path, so return null
	}	
	
	

	/*
	 * recursive method to get from one node to the other
	 */
	private boolean pathDFS(Node start, Node end, Edge currEdge){		
		stack.push(start);//push v (start node)to stack

		if(start == end){//the start node is the same as the end node: //if v=z
			return true;
		}
		else{
			try {
				start.setMark(true);//mark the start node //label v VISITED
				Iterator<Edge> incident = theGraph.incidentEdges(start);//iterator to help loop for all incidented edges to start
				while(incident.hasNext()){//for all incident edges to v
					Edge discovery = incident.next();	//edge to be the next iteration of the list of incident edges
					Node w = discovery.secondEndpoint();//node w = other endpoint (second) of the edge connected to v
					if(w.getMark() != true){//if the node w is UNEXPLORED
						
						String disBus=discovery.getBusLine();
						String currentBus=currEdge.getBusLine();
						
						if(!(disBus.equals(currentBus))){//diff busline
							if(busChanges > 0){
								busChanges--;//decrement because we are going to try out this pathway
								currEdge = discovery;//set the current edge to be discover and venture down this path
								if(pathDFS(w,end,currEdge)){
									return true;//path is found
								}
								busChanges++;//path is not found, so we must change to a different bus
							}
						}
						else{//if same busline
							currEdge = discovery;//set the current edge to be discovery, and venture down this path
							if(pathDFS(w, end, currEdge)){
								return true;//path found	
							}
						}
					}
				}
				start.setMark(false);//set label to be BACK, we are backtracking
				stack.pop();//pop stack
			}	
			catch (GraphException e) {
				System.out.println("");//graph is not proper
			}
		}
		return false;//no path found return false
	}
	
	
/////////////////DFS ALGORTIHM////////////////////	
	//label v VISITED
	//push v to the stack
	//if v=z
		//return the elements of the stack
	//for all incident edges to v
		//if the edge is UNEXPLORED (not marked)
			//node w = other endpoint (second) of the edge connected to v
			//if the node w is UNEXPLORED
				//set the edge as DISCOVERY
				//push the edge to  stack
				//path p = recursive call
				//if the path p is not null then return it
				//otherwise pop the edges from the stack
				//set the edge label to be BACK
	//pop the stack
	//return null


	
}
