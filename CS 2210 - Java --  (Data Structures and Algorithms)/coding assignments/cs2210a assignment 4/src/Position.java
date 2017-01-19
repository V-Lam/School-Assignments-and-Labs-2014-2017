/**
 * represents the position (x,y) of a pixel
 * @author Vivian A. Lam
 *
 */
public class Position {
	/////////////////////////////////////FIELDS//////////////////////////////////////////
	int x;
	int y;
	
	/////////////////////////////////////CONSTRUCTOR/////////////////////////////////////
	/**
	 * constructor to return a new Position with the specified coordinates
	 * @param x
	 * @param y
	 */
	public Position (int x, int y){
		this.x = x;
		this.y =y;
	}
	
	/////////////////////////////////////METHODS/////////////////////////////////////////

	/**
	 * 
	 * @return the x coordinate of this Position
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * 
	 * @return the y coordinate of this Position
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * compares this Position with p using row order
	 * @param p
	 * @return
	 */
	public int compareTo(Position p){
		//(x,y)< (x',y'): y < y' or if the y's are equal and x < x'
		/*
		 *if this > p return 1
		 *if this = p return 0
		 *if this < p return -1
		 * */
		
		if (this.getY() < p.getY()){			//this<p
			return -1;
		}
		else if(this.getY() == p.getY()){		//y values are equal: compare x values
			if(this.getX() < p.getX()){//this's x value is less thanp
				return -1;						//this<p
			}
			else if(this.getX() == p.getX()){//xvalues are equal
				return 0;						//this = p
			}
			else{//x > x'
				return 1;						//this > p
			}
		}
		else{// this y > p's y
			return 1;						//this>p
		}
		
	}
}
