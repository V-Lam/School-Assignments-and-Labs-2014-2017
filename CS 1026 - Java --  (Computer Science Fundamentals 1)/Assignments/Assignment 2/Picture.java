import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.text.*;

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * Copyright Georgia Institute of Technology 2004-2005
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param width the width of the desired picture
   * @param height the height of the desired picture
   */
  public Picture(int width, int height)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
///////////////////BELOW ARE THE METHODS FROM LECTURE AND LABS. KEEP SCROLLING TO FIND MY ASSIGNMENT METHODS/////////////////
  
  public void decreaseRed()
  {
    Pixel [] pixelArray = this.getPixels();
    Pixel pixelObj = null;
    int index = 0;
    int value = 0;
    
    //loop through all the pixels
    while(index<pixelArray.length)
    {
      pixelObj=pixelArray[index];      //get the current pixel   
      value = pixelObj.getRed();       //get the red value
      value = (int) (value * 0.5);     //decrease the red value
      pixelObj.setRed(value);          //set the pixel's red value  
      index++;                         //increment the index
    }
  }  
  
    public void changeRed(double howMuch)
    {
      Pixel [] pixelArray = this.getPixels();
      Pixel pixelObj = null;
      int index = 0;
      int value = 0;
      
      for(int index2 = 1; index2 <pixelArray.length ; index2 ++)
      {
        pixelObj=pixelArray[index];         //get the current pixel 
        value = pixelObj.getRed();          //get the red value
        value = (int) (value * howMuch);    //decrease the red value
        pixelObj.setRed(value);             //set the pixel's red value
        index++;                            //increment the index
      }
    }
 
  //method to mirror and image vertically
  public void mirrorVertical()
  {
    int mirrorPoint = this.getWidth()/2;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    
    for (int y=0; y<this.getHeight(); y++) //loop through the rows
    {
      for (int x=0; x< mirrorPoint; x++)   //loop from column 0 to just before the mirror point
      {
        leftPixel= this.getPixel(x,y);
        rightPixel = this.getPixel(this.getWidth()-1-x, y);
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  //method ot mirror and image vertically from right to left
  public void mirrorVerticalRightToLeft()
  {
    int mirrorPoint = this.getWidth()/2;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
   
    for (int y=0; y<this.getHeight(); y++)    //loop through the rows
    {
      for (int x=0; x< mirrorPoint; x++)      //loop from column 0 to just before the mirror point
      {
        leftPixel= this.getPixel(x,y);
        rightPixel = this.getPixel(this.getWidth()-1-x, y);
        leftPixel.setColor(rightPixel.getColor());
      }
    }
  }
  
  
  
  
  /////////////////For assignment 2 (above methods are from labs and stuff)/////////////////////////////////
  
  
  //method to make image more yellow by removing all the blue (alter every pixel's blue value to be 0)
  public void clearBlue2()
  {
    Pixel pixelObj;                           //creates a pixel object to get and set colours
    
    for (int x=0; x<this.getWidth(); x++)     //loop through the columns (x direction)
    {
      for (int y=0; y< this.getHeight(); y++) //loop through the rows (y direction)
      {
        pixelObj = this.getPixel(x,y);        //get pixel at the x and y location
        pixelObj.setBlue(0);                  //set its blue value to 0 (removes blue)
      }//end of inner loop
    }//end of outer loop
  }
  
  
  //method to grayscale (black, gray and white) using the luminance technique (percieve blue to be darker than red and green)
  public void grayscaleWithLuminance()
  {
    Pixel pixelObj = null;                                               //creates a pixel object to get and set colours
    int intensity = 0;                                                   //creates an integer "intensity" which is used as a factor to alter a pixel's colour
    
    // loop through all the pixels
    for (int y = 0; y < this.getHeight(); y++)                           //loops through the rows
    {
      for (int x = 0; x < this.getWidth(); x++)                          //loops through the columns
      {
        pixelObj = this.getPixel(x, y);                                  // get the current pixel
        
        //sets the value fo the variable intensity. Makes the colours more gray. Blue is percieved darkest and is multiplied by 0.114
        intensity = (int) (pixelObj.getRed() * 0.299 + pixelObj.getGreen() * 0.587 + pixelObj.getBlue() * 0.114);
        
        pixelObj.setColor(new Color(intensity,intensity,intensity));     // set the pixel color
      }
    }
  }
    
  
////////////////////////////////////ORIGINAL METHODS START HERE/////////////////////////////////
 
  
   //method for copying the source picture to a target picture. Source picture is passed as a parameter and target invokes object
  public void copyPictureTo(Picture sourcePicture, int xStart, int yStart)
  {
    Pixel sourcePixel = null;                                   //creates two pixel objects that are used to get pixels from the source
    Pixel targetPixel = null;                                   // pic to be copied to the target pic

    //loop through the columns
    for (int sourceX = 0, targetX = xStart; sourceX < sourcePicture.getWidth(); sourceX++, targetX++)
    {
      //loop through the rows
      for (int sourceY = 0, targetY = yStart; sourceY < sourcePicture.getHeight(); sourceY++, targetY++)
      {
        sourcePixel = sourcePicture.getPixel(sourceX,sourceY); //gets pixel from source image
        targetPixel = this.getPixel(targetX,targetY);          //gets pixel from target picture
        targetPixel.setColor(sourcePixel.getColor());          //sets the colour of the pixel in the target to match the colour of the pixel from the source
      }//end inner loop 
    }//end outer loop
  } 
  
  
  //increase purple method: increases the amount of purple in the picture
  public void increasePurple()
  {
    Pixel pixelObj = null;                                       //creates a new pixel object to get and set colours
    
    //loop through all the pixels
    for (int x = 0 ; x < this.getWidth() ; x++)                  //loops through columns
    {
      for (int y = 0 ; y < this.getHeight() ; y++)               //loops through the rows
      {
        int redValue, blueValue = 0; //declares the variables redValue and blueValue which will be used to store and then alter the red and blue values of a pixel
   
        pixelObj = this.getPixel(x,y);           //get the current pixel from the source image   
      
        redValue = pixelObj.getRed();            //get the red value and blue value of the pixel
        blueValue = pixelObj.getBlue();
      
        redValue = (int) (redValue * 1.5);       //increase the red value and the blue by 50%
        blueValue = (int) (blueValue * 1.5);
      
        pixelObj.setRed(redValue);               //set the pixel's new red and blue values  
        pixelObj.setBlue(blueValue); 
      }//end inner loop
    }//end outer loop
  }
  
  
  //mirror Four square method: mirrors the picture in a wacky 4 square pattern (divides the image into 4 equal quadrants
  //mirrors top left quadrant to bottom right, copies top right quadrant to bottom left and vertically flips top right)
  public void mirrorFourSquare()
  {
    int mirrorPoint = this.getWidth()/2;               //creates an integer variable to find the horizontal midpoint of the picture
    Pixel leftPixel, rightPixel = null;                //creates pixel objects to get and set colours
    
    //mirrors the top left part of the image onto the bottom right 
    for (int y=0; y<this.getHeight()/2; y++)                        //loop through the rows until it reaches the middle of the picture (top half of picture)
    {
      for (int x=0; x< mirrorPoint; x++)                            //loop from column 0 to just before the mirror point (left half of picture)
      {
        leftPixel= this.getPixel(x,y);                                           //gets the pixel in the top left corner
        rightPixel = this.getPixel(this.getWidth()-1-x, this.getHeight()/2 + y); //gets the pixel in the bottom right
        rightPixel.setColor(leftPixel.getColor());         //sets the pixel in the bottom right to be the same colour as the corresponding pixel in the top left
      }
    }
    
   //copies the top right part of the image to the bottom left
    for (int y= 0 ; y<this.getHeight()/2; y++)               //loop through the rows. Only loops for (vertical) half of the picture
    {
      for (int x=0; x< mirrorPoint; x++)                      //loop from column 0 until mirror point 
      {
        leftPixel= this.getPixel(x,this.getHeight()/2  + y);  //gets the pixel somewhere in the bottom left part of the image
        rightPixel = this.getPixel(mirrorPoint -1 +x, y);     //gets the pixel somewhere in the top right of the image
        leftPixel.setColor(rightPixel.getColor());            //sets the current pixel (in the bottom left) to be the same colour as the corresponding pixel in the top right
      }
    }
    
    //copies the bottom left and mirrors it to the top right
    for (int y=0; y<this.getHeight()/2; y++)                  //loop through the rows until it reaches the middle of the picture
    {
      for (int x=0; x< mirrorPoint; x++)                      //loop from column 0 to just before the mirror point
      {
        rightPixel= this.getPixel(this.getWidth()-1-x,y);     //gets pixel somewhere in top right part of image
        leftPixel = this.getPixel(x, this.getHeight()/2 + y); //gets pixel somewhere in bottom left
        rightPixel.setColor(leftPixel.getColor());            //sets the current pixel (in the top right) to be the same colour as the corresponding pixel in the bottom left
      }
    }
  }
 
  
  //2 by 2 checkered invert method: negates the colours the top left and bottom right part of the image
  public void twoByTwoCheckeredInvert()
  {
    Pixel pixelObj = null;                                            //creates a pixel object ot get and set colours
    int redValue,blueValue,greenValue = 0;                            //creates integer variabls to get, store and modify the red, green and blue values of a pixel
    
    for (int y=0 ; y< this.getHeight()/2; y++)                        //outer loop to loop through half the image (half the height)
      {
      for(int x=0; x< this.getWidth()/2; x++)                         //nested for loop to negate the top left part of the picture
      {
        pixelObj = this.getPixel(x,y);                                //get the current pixel 
      
        redValue = pixelObj.getRed();                                 //gives values to the variables by getting the colours from the pixel
        greenValue = pixelObj.getGreen();
        blueValue = pixelObj.getBlue();
      
        pixelObj.setColor(new Color(255-redValue,255-greenValue,255-blueValue)); //Negates the pixel's original color
      }
      
      for (int x= this.getWidth()/2 ; x< this.getWidth(); x++)        //nested for loop to negate the bottom right part of the picture
      {
        pixelObj = this.getPixel(x, this.getHeight()/2 + y);          //get the current pixel   
      
        redValue = pixelObj.getRed();                                 //gives values to the variables by getting the colours from the pixel
        greenValue = pixelObj.getGreen();
        blueValue = pixelObj.getBlue();
        
        pixelObj.setColor(new Color(255-redValue,255-greenValue,255-blueValue));   //Negates the pixel's original color
      }
    }//end outer loop
  }
  
  
  //stripes of sepia and gray method: creates vertical stripes that are alternating grayscale and sepia tint
  public void stripesOfSepiaAndGray()
  {
    Pixel pixelObj = null;                                        //creates a pixel object ot get and set colours
    int redValue, blueValue, greenValue = 0;                      //creates integer variabls to get, store and modify the red, green and blue values of a pixel
    
    int xIncrement = (int) (this.getWidth()/10);                  //creates an integer xIncrement that is a tenth of the original image's width. This is so that
                                                                  //the image is dividely evenly
   
    this.grayscaleWithLuminance();                                //invokes grayscaleWithLuminance method to change the current picture to grayscale
    
    
    //loops through the pixels and creates a stripe pattern. The stripes make the image a sepia tint
    for (int y = 0; y <this.getHeight(); y++)                      //loops through the rows
    {
      for (int x = 0; x < this.getWidth()- xIncrement; x= x + 2* xIncrement)    //loops through the columns. After xIncrement times it skips a column of width xIncrement
      {                                                                         //in order to create a stripe pattern
       
        for (int i = 0; i<xIncrement; i++)       //loops xIncrement times to create a column of sepia with width = value of xIncrement. this creates one stripe
        {
          pixelObj = this.getPixel(x+i,y);                         //gets the current pixel
      
          redValue = pixelObj.getRed();                            //gives values to the variables by getting the colours from the pixel
          greenValue = pixelObj.getGreen();
          blueValue = pixelObj.getBlue();
      
          if (redValue < 60)                                       //tint the shadows darker
          {
            redValue = (int) (redValue*0.8);                       //changes the value of the variables redValue, greenValue and blueValue so that red 
            greenValue = (int) (greenValue*0.6);                   //is 80% of its original value while blue and green are 60%
            blueValue = (int) (blueValue*0.6);
          }
          else if(redValue >190)                                   //tint the midtones a light brown by reducing the blue
          {
            blueValue = (int) (blueValue*0.6);                     //reduces blue to 60% of its original value
          }
          else                                                     // tint the highlights a light yellow by reducing the blue
          {
            blueValue = (int) (blueValue*0.6);                     //reduces blue to 60% of its original value
          }

          pixelObj.setRed(redValue);                              //sets the new colour for the pixel
          pixelObj.setBlue(blueValue);
          pixelObj.setGreen(greenValue);
        }//end of innermost loop
      }
    }//end outmost loop
  }
  
  
  //horizontally mirrors image and overlays transparent reddish border of width 20
  public void overlayRedBorderAndHorizMirror()
  {
    Pixel topPixel, bottomPixel = null;    //creates two pixels objects used to get the top pixel and mirror it to the bottom pixel.
                                          // these variables are reused to create the borders by increasing the red values of the pixels around the edges
    //mirrors image horizontally
    for(int y = 0; y< this.getHeight()/2; y++)                    //loops through the rows
    {
      for(int x = 0; x< this.getWidth(); x++)                     //loops through the columns
      {
        topPixel= this.getPixel(x,y);                             //gets the pixel at location (x,y) (somwhere along the top half of the image)
        bottomPixel = this.getPixel(x, this.getHeight()-1-y);     //gets the corresponding pixel somwhere along the bottom half of the image
        bottomPixel.setColor(topPixel.getColor());                //sets the colour fo the bottom pixel to match the top
      }
    }
    
    //paints a border over the image by changing the pixels around the edge to max red.
    //nested loops to create the top and bottom part of the border
    for(int y = 0; y<20; y++)                                   //loops 20 times to create a row of wdith 20pixels on the very top and bottom of the image
    {
      for(int x = 0; x< this.getWidth(); x++)                   //loops through the columns
      {
        topPixel = this.getPixel(x,y);                          //gets the pixel at location (x,y). This pixel should be somewhere at the top of the image
        bottomPixel = this.getPixel(x, this.getHeight()-1-y);   //get the pixel on the opposite end of the image (somewhere along the bottom)
        topPixel.setRed(255);                                   //makes the current pixels more red by setting the red value to 255
        bottomPixel.setRed(255);
      }
    }
    //nested loops to create the side parts of the border
    for(int y = 0; y< this.getHeight(); y++)                    //loops through the rows                
    {
      for(int x = 0; x<20; x++)                                 //loops 20 times to create a column of width 20pixels on the very left and right sides of the image
      {
        topPixel = this.getPixel(x,y);                          //gets the pixel at location (x,y). This pixel should be somewhere on the left side of the image
        bottomPixel = this.getPixel(this.getWidth()-1-x,y);     //get the pixel on the opposite side of the image (on the right side)
        topPixel.setRed(255);                                   //makes the current pixels more red by setting the red value to 255
        bottomPixel.setRed(255);
      }
    }
  }
  
   
  //make white pixels of an image more red and tints shadows green
  public void makeWhiteMoreRedAndShadowsGreen()
  {
    //nested loops to get all the pixels
    for (int x = 0; x < this.getWidth(); x++) {                          //loops through the columns                   
      for (int y = 0; y < this.getHeight(); y++) {                       //loops through the rows
        Pixel pixelObj = this.getPixel(x,y);                             //gets the pixel at location (x,y)
        
        int redValue = pixelObj.getRed();                                //gives values to the variables by getting the colours from the pixel
        int greenValue = pixelObj.getGreen();
        int blueValue = pixelObj.getBlue();
        
        if (redValue >= 200 && greenValue >= 200 && blueValue >= 200)    //tints the white to be more red (if the value is light)
        {
          pixelObj.setRed(255);                                          //makes the white pixel more red by maxing out its red value 
          pixelObj.setGreen(200);                                        //and setting its blue and green values to 200
          pixelObj.setBlue(200);
        }
        if (redValue<60)                                                 //tints the shadows green (if the value is dark increase green)
        {
          pixelObj.setRed((int) (redValue*0.4));                         //set the pixel's new red and blue values (makes it 40% of original)
          pixelObj.setBlue((int) (blueValue*0.4));
        }
      }//end inner loop
    }//end outer loop
  } 
  
  
} // end of class Picture, put all new methods before this
 