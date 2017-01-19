// CS1026B Assignment 3
// Vivian Lam
/*Creates and returns new Pictures. Methods are invoked to fix minor graininess and change the gray colours of the original image into brighter colours.
The program will break the original picture into even more new Pictures containting the components of the colourized image*/

//I'M REALLY SORRY IF I COMMENT TOO MUCH. I haven't recieved any feedback from the assignments so I don't know what's too much or too little :(

public class Assign3
{
  public static void main (String[] args)
  {
    Picture pic1 = new Picture("lazy.jpg");            //creates a new picture object and makes it contain the image "lazy.jpg"
    pic1.show();                                       //shows the picture pic1 (the original image)
    
    
    /*creates a new picture object and invokes the method correctColours on pic1, passing the colours orange, magenta and cyan to be values for the
      parameters c1,c2,c3 (respectively) in the correctColours method. pic2 is equal to the modified pic1 object i.e. pic2 is the colourized version of pic1 */
    Picture pic2 = pic1.correctColours(java.awt.Color.ORANGE,java.awt.Color.MAGENTA, java.awt.Color.CYAN);  
    pic2.show();                                       //shows the picture pic2

    
    
//creating picture objects and invoking the cropPicture method to break pic2 into new pictures containing its components (crops pic2)
    
    /*creates a picture called orangeP and invokes the cropPicture method on pic2, passing the colour orange as the parameter.
     * orangeP is equal to the modified pic2 (crops around the orange parts of pic2)*/
    Picture orangeP = pic2.cropPicture(java.awt.Color.ORANGE);        
  
    
    /*creates a picture called magentaP and invokes the cropPicture method on pic2, passing the colour magenta as the parameter.
    * magentaP is equal to the modified pic2 (crops around the magenta parts of pic2)*/
    Picture magentaP = pic2.cropPicture(java.awt.Color.MAGENTA);      
    
    
    /*creates a pictue called cyanP and invokes the cropPicture method on pic2, passing the colour cyan as the parameter.
     * cyanP is equal to the modified pic2 (crops around the cyan parts of pic2)*/
    Picture cyanP = pic2.cropPicture(java.awt.Color.CYAN);            
    
    orangeP.show();                                    //shows the picture orangeP
    magentaP.show();                                   //shows the picture magentaP
    cyanP.show();                                      //shows the picture cyanP
  
    
  }//end of main method
}//end of class