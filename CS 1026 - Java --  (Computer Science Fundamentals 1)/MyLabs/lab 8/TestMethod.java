public class TestMethod
{
  public static void main (String[] args)
  {
  String fileName= FileChooser.pickAFile();
  String fileName2= FileChooser.pickAFile();
  Picture pic1 = new Picture(fileName);
  Picture pic2 = new Picture(fileName2);
  
  pic2.copyExceptWhite(pic1,0,0);
  pic2.explore();
  }
}