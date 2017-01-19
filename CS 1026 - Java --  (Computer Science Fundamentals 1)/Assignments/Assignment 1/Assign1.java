//Vivian Lam

public class Assign1
{
  public static void main (String args[])   //main method
  {
    //creates a World and Turtle object called world1 and turtle1 respectively
    World world1 = new World();          //new World object
    Turtle turtle1 = new Turtle(world1); //turtle1 object in world1

    
    //sets the colour of the turtle's instance turtle1 to be black
    turtle1.setColor(java.awt.Color.BLACK);
    
    //teleports the turtle to the starting position (turtle moves to position (200, 200) without drawing a line)
    turtle1.penUp();               //lifts turtle1's pen
    turtle1.moveTo(200,200);       //moves turtle1 to location 200,200
    turtle1.penDown();             //puts turtle1's pen down
    
    
    //invokes the drawKeyFrameGrid method from the turtle class
    turtle1.drawKeyFrameGrid(3,2,2); // assigns the values 3, 2, and 2 to the parameters numKeys, numFramesRow, and numFramesCol respectively
    
    
    //calls the drawPic method to draw a picture in each of the frames
    Assign1.drawPic(turtle1, 250, 170, java.awt.Color.RED);       //moves turtle to (250,270) and changes its colour to red
    Assign1.drawPic(turtle1, 470, 120, java.awt.Color.YELLOW);    //moves turtle to (470,120) and changes its colour to yellow
    Assign1.drawPic(turtle1, 220, 320, java.awt.Color.CYAN);      //moves turtle to (220,320) and changes its colour to cyan
    Assign1.drawPic(turtle1, 470, 345, java.awt.Color.MAGENTA);   //moves turtle to (470,345) and changes its colour to magenta
  }
  
  
  
  //drawPic method makes the turtle draw pictures, each of diff colour inside each key frames
  public static void drawPic(Turtle inTurtle, int x, int y, java.awt.Color inColour)
  {
    inTurtle.penUp();                 //pen up
    inTurtle.moveTo(x,y);             //moves turtle to x and y coordinates provided as paramters
    inTurtle.penDown();               //pen down
    inTurtle.setColor(inColour);      //changes turtle colour specified to the last parametre

    //invokes the drawStar method from the turtle class
    inTurtle.drawStar(70);//assigns a value of 70 to the parameter "length" from the method
  }
}