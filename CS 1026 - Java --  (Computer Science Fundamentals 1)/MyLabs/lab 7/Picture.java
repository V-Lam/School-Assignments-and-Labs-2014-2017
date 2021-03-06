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

  
  public void decreaseRed()
  {
    Pixel [] pixelArray = this.getPixels();
    Pixel pixelObj = null;
    int index = 0;
    int value = 0;
    
    //loop through all the pixels
    while(index<pixelArray.length)
    {
      //get the current pixel
      pixelObj=pixelArray[index];      
      
      //get the red value
      value = pixelObj.getRed();
        
      //decrease the red value
      value = (int) (value * 0.5);  
        
      //set the pixel's red value
      pixelObj.setRed(value);   
        
      //increment the index
      index++;    
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
        //get the current pixel
        pixelObj=pixelArray[index];      
      
        //get the red value
        value = pixelObj.getRed();
        
        //decrease the red value
        value = (int) (value * howMuch);  
        
        //set the pixel's red value
        pixelObj.setRed(value);   
        
        //increment the index
        index++;    
      }
    }
  
  
  public void clearBlue2()
  {
    Pixel pixelObj;
    //loop through the columns (x direction)
    for (int x=0; x<this.getWidth(); x++)
    {
      //loop through the rows (y direction)
      for (int y=0; y< this.getHeight(); y++)
      {
        //get pixel at the x and y location
        pixelObj = this.getPixel(x,y);
        
        //set its blue to 0
        pixelObj.setBlue(0);
        
      }//end of inner loop
    }//end of outer loop
  }
  
  
  public void mirrorVertical()
  {
    int mirrorPoint = this.getWidth()/2;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    
    //loop through the rows
    for (int y=0; y<this.getHeight(); y++)
    {
      //loop from column 0 to just before the mirror point
      for (int x=0; x< mirrorPoint; x++)
      {
        leftPixel= this.getPixel(x,y);
        rightPixel = this.getPixel(this.getWidth()-1-x, y);
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  
  public void mirrorVerticalRightToLeft()
  {
    int mirrorPoint = this.getWidth()/2;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    
    //loop through the rows
    for (int y=0; y<this.getHeight(); y++)
    {
      //loop from column 0 to just before the mirror point
      for (int x=0; x< mirrorPoint; x++)
      {
        leftPixel= this.getPixel(x,y);
        rightPixel = this.getPixel(this.getWidth()-1-x, y);
        leftPixel.setColor(rightPixel.getColor());
      }
    }
  }
  
} // end of class Picture, put all new methods before this
 