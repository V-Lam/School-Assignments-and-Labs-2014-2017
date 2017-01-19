/**
 * Class that represents a turtle which is similar to a Logo turtle.
 * This class inherts from SimpleTurtle and is for students
 * to add methods to.
 *
 * Copyright Georgia Institute of Technology 2004
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class Turtle extends SimpleTurtle
{
  ////////////////// constructors ///////////////////////
  
  /** Constructor that takes the x and y and a picture to
   * draw on
   * @param x the starting x position
   * @param y the starting y position
   * @param picture the picture to draw on
   */
  public Turtle (int x, int y, Picture picture) 
  {
    // let the parent constructor handle it
    super(x,y,picture);
  }
  
  /** Constructor that takes the x and y and a model
   * display to draw it on
   * @param x the starting x position
   * @param y the starting y position
   * @param modelDisplayer the thing that displays the model
   */
  public Turtle (int x, int y, ModelDisplay modelDisplayer) 
  {
    // let the parent constructor handle it
    super(x,y,modelDisplayer);
  }
  
  /** Constructor that takes the model display
   * @param modelDisplay the thing that displays the model
   */
  public Turtle (ModelDisplay modelDisplay) 
  {
    // let the parent constructor handle it
    super(modelDisplay);
  }
  
  /**
   * Constructor that takes a picture to draw on
   * @param p the picture to draw on
   */
  public Turtle (Picture p)
  {
    // let the parent constructor handle it
    super(p);
  }  
  
  /////////////////// methods ///////////////////////
  public void drawSquare (int width)//draw square method
  {
    this.turnRight();//turns turtle right
    this.forward(width);//moves the turtle forward a certain width
    this.turnRight();
    this.forward(width);
    this.turnRight();
    this.forward(width);
    this.turnRight();
    this.forward(width);
  }

  public void drawVee (int length)//method to draw the letter V
  {
    this.turn(225);
    this.forward(length);
    this.turn(90);
    this.forward(length);
  }
  
  
/////////////////// Assignment 1 methods ///////////////////////
  
  public void drawKey () //drawKey method to draw a key. No parameters returned
  {
    this.forward(5);//moves the turtle forward5
    this.turn(-90);//turns the turtle left
    this.forward(30);
    this.turn(90);//turns the turtle right
    this.forward(30);
    this.turn(90);
    this.forward(20);
    this.turn(90);
    this.forward(10);
    this.turn(90);
    this.forward(10);
    this.turn(-90);
    this.forward(10);
    this.turn(-90);
    this.forward(20);
    this.turn(-90);
    this.forward(25);
  }
  
  
  //drawKeyRow method to draw a row of keys
  public void drawKeyRow(int numKeys)    //parameter is an integer called numKeys
  {
    for (int i = 0; i<numKeys; i++)      //For lop:number of loops = the value of numKeys
    {
      this.drawKey();                    //calls the drawKey method. By looping this method we can create a row of Keys
    } 
  }
  
  
  //drawKeyFrame method to draw a frame of keys
  public void drawKeyFrame(int numKeys)   //parameter is an integer called numKeys
  {
    for (int i=0; i<4; i++)          //For loop: loops 4 times because there are 4 edges in a frame
    {
      this.drawKeyRow(numKeys);     //calls the drawKeyRow method to draw the key in a row. number of keys in a rorw = value of numKeys
      this.turn(90);                //turns the turtle right
    }
  }    
   
  
  //drawKeyFrameRow method to draw a row of frames
  public void drawKeyFrameRow(int numKeys, int numFrames)//parameters are integers called numKeys and numFrames
  {
    //declares and initializes the integer variable frameWidth. This will be used to teleport the turtle a certain distance
    int frameWidth = (2*30)+ (40 * numKeys);        // 30 is width of a vertical column of Keys. 40 is the width of one Key
  
    
    for (int i = 1; i<=numFrames; i++)             //For loop: loops for the number of frames desired (number of loops = value of numFrames)
    {  
      this.drawKeyFrame(numKeys);                  //calls the drawKeyFrame method to draw a frame with  "numKeys" amount of keys in each frame  
      
      //teleports the turtle (moves turtle to a diff position without drawing so that he can draw the next frame in the new position)
      this.penUp();
      this.turnRight();
      this.forward(frameWidth);           //moves turtle a distance of frameWidth (relocates beside each new frame in order to draw the next)
      this.turnLeft();
      this.penDown();
    }
  }
    
  
  //drawKeyFrameGrid method to draw multiple rows of frames
  public void drawKeyFrameGrid (int numKeys, int numFramesRow, int numFramesCol)
  {
    //decalres and initalizes variables
   int frameRowWidth = (60* numFramesRow)+ (40 * numKeys * numFramesRow); //width of a row of frames: 60=width of the two vertical columns of keys on the sides 
                                                                          //of the frame. this is multiplied by the number of frames per row. 40 = width of key
                                                                          //which is multiplied by the number of keys in a frame and the number of frames in a row
   
   int frameHeight = (2*30)+ (40 * numKeys);                             //height of a frame: 2*30= height of the horizontal rows of keys on top and bottom of the frame
                                                                         //40 = height of one vertical key. this is multiplied by the number of keys to get the 
                                                                         //height of the frame sides
   
   
     for (int i =1; i<=numFramesCol; i++)  //For loop: the number of loops = the amount of columns desired
    {  
      this.drawKeyFrameRow(numKeys, numFramesRow);    //calls the drawKeyFramesRow method
      
      //teleports the turtle (moves turtle to a diff position without drawing so that he can draw the next row of frames
      this.penUp();
      this.backward(frameHeight);                    //moves the turtle backwards a distance of frameHeight (moves him down a row)
      this.turnLeft();
      this.forward(frameRowWidth);                   //moves turtle forward so his x position is the same as the start (moves him to the left to start a new row)
      this.turnRight();                              //turns turtle facing "North," in position to draw the new row of frames
      this.penDown();
    }
  }
  
  
  //drawStar method moves a turtle to draw a star
  public void drawStar (int length)
  { 
    for (int i=0; i<4; i++)     //loops 4 times. this draws 4 out of the 5 lines of a star
    {
      this.forward(length);     //moves turtle a distance = the value of the parameter "length"
      this.turn(144);           //turns the turtle 144 degrees
    }
    this.forward(length);       //moves the turtle to close the shape
  }
  
  
} // end of class Turtle, put all new methods before this