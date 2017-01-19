// CS1026B Assignment 2
// Vivian Lam
// modifies a picture in 7 different ways and places them (and the original image) all in one canvas to make a collage


import java.awt.Color;                       //imports class
public class Assign2
{
  public static void main (String[] args)    //main method
  {
    Picture [] picObjArray = new Picture [8];                       //creates a new picture array of size 8

    
    for(int i=0; i < picObjArray.length; i++)                       //loop to initilize the value of each element in the array so 
    {                                                               //that they contain the picture "guyFieri.jpg"
      picObjArray[i] = new Picture ("guyFieri.jpg");    
    }
    
    
    Picture canvas = new Picture(1000,700);             //creates a new blank canvas for the images for the collage
    canvas.show();                                      //displays the new canvas
    
    //puts picture in centre of the canvas
    canvas.copyPictureTo(picObjArray[0],350,250);
   
    //invokes the increase purple method and copies the modified image in top right corner
    picObjArray[1].increasePurple();
    canvas.copyPictureTo(picObjArray[1],700,0);
   
    //invokes the twoByTwoCheckeredInvert and copies the modified image to the middle left of the canvas
    picObjArray[2].twoByTwoCheckeredInvert();
    canvas.copyPictureTo(picObjArray[2],0,250);
    
    //invokes the clearBlue2 method and copies the modified image to the middle right of canvas
    picObjArray[2].clearBlue2();                   //NOT ORIGINAL METHOD
    canvas.copyPictureTo(picObjArray[2],700,250);
    
    //invokes the makeWhiteMoreRedAndShadowsGreen method and copies the modified image to the middle bottom
    picObjArray[3].makeWhiteMoreRedAndShadowsGreen();
    canvas.copyPictureTo(picObjArray[3],350,500);   
   
    //invokes the stripesOfSepiaAndGray method and copies the modified image to the bottom left corner
    picObjArray[4].stripesOfSepiaAndGray();
    canvas.copyPictureTo(picObjArray[4],0,500);
    
    //invokes the overlayRedBorderAndHorizMirror method and copies the modified image to the top left corner
    picObjArray[5].overlayRedBorderAndHorizMirror();
    canvas.copyPictureTo(picObjArray[5],0,0);
   
    //invokes he mirrorFourSquare method and copies the modified image to the bottom right corner
    picObjArray[6].mirrorFourSquare();
    canvas.copyPictureTo(picObjArray[6],700,500);
    
    //invokes the following methods and copies the modified image to the top middle
    picObjArray[7].stripesOfSepiaAndGray();
    picObjArray[7].makeWhiteMoreRedAndShadowsGreen();
    picObjArray[7].twoByTwoCheckeredInvert();
    picObjArray[7].increasePurple();
    picObjArray[7].overlayRedBorderAndHorizMirror();
    picObjArray[7].mirrorFourSquare();
    canvas.copyPictureTo(picObjArray[7],350,0);
    
    
    canvas.repaint();                   //Paints the picture
    
    canvas.write("myCollage.jpg");      //Saves the college (whatever is on the canvas as a new image)
  
  }//end main method
}//end main class