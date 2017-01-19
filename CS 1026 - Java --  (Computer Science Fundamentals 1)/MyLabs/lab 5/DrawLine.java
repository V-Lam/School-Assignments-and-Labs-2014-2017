import java.awt.Color;
public class DrawLine
{
  public static void main (String[] args)
  {
    String file = FileChooser.pickAFile();
    Picture pictureObj = new Picture (file);
    pictureObj.show();
  
    int i = 0;
    while(i<25)
    {
      pictureObj.getPixel(100+i,100+i).setColor(Color.black);
      i++;
    }
    
    int j = 0;
    while(j<25)
    {
      pictureObj.getPixel(100,100+j).setColor(Color.magenta);
      j++;
    }
    
    int k = 0;
    while(k<25)
    {
      pictureObj.getPixel(100+k,100).setColor(Color.white);
      k++;
    }
    
    pictureObj.explore(); 
  
  }
}