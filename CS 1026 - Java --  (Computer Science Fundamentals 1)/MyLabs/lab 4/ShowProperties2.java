import java.awt.*;
public class ShowProperties2
{
  public static void main (String[] args)
  {
    String fileName1 = FileChooser.pickAFile();
    Picture picObj = new Picture(fileName1);
    picObj.show();
    
    System.out.println("The picture is " + picObj.getWidth() + " pixels wide and " + picObj.getHeight() + " pixels high");
    int xcoord = 639;
    int ycoord = 479;
    
    Pixel pixelObj = picObj.getPixel (xcoord,ycoord);
    
    int redValue = pixelObj.getRed();
    int greenValue = pixelObj.getGreen();
    int blueValue = pixelObj.getBlue();
    
    System.out.println(" Colors of pixel at ("+ xcoord + " " + ycoord + ") are: = "+ redValue +" green = "+ greenValue + " blue = "+ blueValue );
  }
}