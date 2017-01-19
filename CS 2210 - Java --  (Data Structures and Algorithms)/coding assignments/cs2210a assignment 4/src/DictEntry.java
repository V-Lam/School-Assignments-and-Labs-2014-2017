/**
 * this class represents a data item sorted in the binary search tree. each data item consists of two parts:
 *  	a Position and an int colour
 * @author Vivian A. Lam
 *
 */

public class DictEntry {

	/////////////////////////////////////FIELDS//////////////////////////////////////////
	Position p;
	int colour;
	
	/////////////////////////////////////CONSTRUCTOR/////////////////////////////////////
	
	/**
	 * a constructor which returns a new DictEntry with the specified coordinates and colour.
	 * Position p is the key for the DictEntry
	 * @param p
	 * @param colour
	 */
	public DictEntry(Position p, int colour){
		this.p =p;
		this.colour = colour;
	}
	/////////////////////////////////////METHODS/////////////////////////////////////////
	
	/**
	 * 
	 * @return the Position in the DictEntry
	 */
	public Position getPosition(){
		return p;
	}
	
	/**
	 * 
	 * @return the colour in the DictEntry
	 */
	public int getColour(){
		return colour;
	}
}
