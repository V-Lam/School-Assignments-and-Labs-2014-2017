public class TestMirroring
{
  public static void main (String[] args)
  {
    String fileName = FileChooser.pickAFile();
    Picture pictureObj = new Picture(fileName);
    Picture pic2 = new Picture(fileName);
    
    pictureObj.explore();
    pictureObj.mirrorVertical();
    pictureObj.explore();
    pictureObj.mirrorVerticalRightToLeft();
    pic2.explore();
    pictureObj.explore();
  }
}