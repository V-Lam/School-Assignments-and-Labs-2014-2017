/**
 * class to store information about each figure. Also used to check if figures collide
 * @author Vivian A. Lam
 *
 */
public class Figure implements FigureADT{

/////////////////////////////////////FIELDS//////////////////////////////////////////
	private int id;				//figure's identifier
	private int width;			//width of enclosing rectangle
	private int height;			//height of enclosing rectangle
	private int type;			//type of figure (moveable by human/cpu or static or target)
	private Position pos;		//offset of figure
	private BinarySearchTree tree;		//instance variable used to retrieve the BST
	
/////////////////////////////////////CONSTRUCTOR/////////////////////////////////////

	/**
	 * constructor to inialize
	 * @param id
	 * @param width
	 * @param height
	 * @param type
	 * @param pos
	 */
	public Figure (int id, int width, int height, int type, Position pos){
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		this.tree = new BinarySearchTree();
	}
	
/////////////////////////////////////METHODS/////////////////////////////////////////

	/**
	 * sets the type of figure to the specified value
	 * @param type
	 */
	public void setType(int type){
		this.type=type;
	}
	
	/**
	 * @return the width of the enclosing rectangle for this figure
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * @return the height of the enclosing rectangle for this figure
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * @return the type of this figure
	 */
	public int getType(){
		/* Note:
		 * 0: fixed figure
		 * 1: figure moved by the user
		 * 2: figure moved by the computer
		 * 3: target figure.*/
		return type;
	}
	
	/**
	 * @return the id of this figure
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * @return the offset of this figure
	 */
	public Position getOffset(){
		return pos;
	}
	
	/**
	 * changes the offset of this figure to the specified value
	 * @param value
	 */
	public void setOffset(Position value){
		this.pos = value;
	}
	
	/**
	 * creates a DictEntry to represent the givent pixel and inserts it into
	 * the BST associated with this figure. Throws BST exception if error when inserting
	 * @param x
	 * @param y
	 * @param colour
	 */
	public void addPixel(int x, int y, int colour) throws BSTException{
		Position pos = new Position(x,y);
		DictEntry pixinfo = new DictEntry(pos,colour);
		this.getBST().insert(pixinfo);
	}
	
	/**
	 * returns true if this figure intersects the one specified in paramter
	 * otheriwse return false
	 * @param fig
	 * @return
	 */
	public boolean intersects(Figure fig){
	
		//check if rectangles intersect:
		//if they do, compare trees and see if there are same coordinates
				
		/*if 	top edge of THIS 	> fig's bottom ||
		 * 		left edge of THIS 	> fig's right edge ||
		 * 		right edge of THIS 	< fig's left edge  || 
		 * 		bottom edge of THIS < fig's top edge
		 * THEN there is no way the rectanles are intersecting*/
		if(		this.getOffset().getY() 				 > fig.getOffset().getY()+fig.getHeight() 	|| 
				this.getOffset().getX() 				 > fig.getOffset().getX()+fig.getWidth() 	|| 
				this.getOffset().getX()+this.getWidth()  < fig.getOffset().getX()					||
				this.getOffset().getY()+this.getHeight() < fig.getOffset().getY()){//rectangles don't intersect																																																																																						
			return false;			
		}
		else{//rectangles are intersecting. compare trees via inorder traversal
			
			DictEntry pix=this.getBST().smallest();//object to store the pixel data
			Position pixpos= pix.getPosition();	//get the position of the first pixel
			
			//get one by one pixels of the first figure by using methods this.smallest() and this.successor()
			//for each one of these pixels p check if any of the pixels in fig by invoking method find from fig.getBST() on pixel p
			while(pix!=null){
				
				//convert THIS's pixel into terms fig's pixel:
				//THIS pixel subtract THIS's offset(in terms of the entire window) and then add fig's offset (in terms of fig)
				int newPixposX= pixpos.getX() + (this.getOffset().getX())  -fig.getOffset().getX();
				int newPixposY = pixpos.getY() + (this.getOffset().getY()) -fig.getOffset().getY();
				//store the translated pixel into 
				Position newPixpos = new Position(newPixposX, newPixposY); 
				
				//CHECKING: then pass into here to see if it exists in the fig's BST
				if(fig.getBST().find(newPixpos)!=null){//it's found, theres overlap
					return true;
				}
				
				//get the next pixel (passing THIS pixel's succ)
				pix = this.getBST().successor(pix.getPosition());
				if(pix!=null){//this ensures theres no null pointer exception
					pixpos=pix.getPosition();
				}
			}
		}
		return false;//??
	}
	
	

	
	/**
	 * @return the tree created in the constructor
	 */
	private BinarySearchTree getBST(){
		return this.tree;
	}
	
	
	
	
}
