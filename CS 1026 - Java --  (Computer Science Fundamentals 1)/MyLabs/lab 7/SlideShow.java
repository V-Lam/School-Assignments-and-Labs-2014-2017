public class SlideShow
{
  public static void main (String[] args)
  {
    Picture[] pictures = new Picture[4];
   int arraySize = pictures.length;
    
  /*  
    for (int index = 0; index< arraySize; index++)
    {
      String fileName = FileChooser.pickAFile();
      Picture pic = new Picture(fileName);

    pictures[index] = pic;  
    pic.show();
    }
*/
          String fileName = FileChooser.pickAFile();
          Picture pictureObj1 = new Picture(fileName); 
          pictureObj1.show();
          pictures[0]= pictureObj1;
          String fileName2 = FileChooser.pickAFile();
          Picture pictureObj2 = new Picture(fileName2); 
          pictureObj2.show();
          pictures[1]= pictureObj1;
          String fileName3 = FileChooser.pickAFile();
          Picture pictureObj3 = new Picture(fileName3); 
          pictureObj3.show();
          pictures[2]= pictureObj1;
          String fileName4 = FileChooser.pickAFile();
          Picture pictureObj4 = new Picture(fileName4); 
          pictureObj4.show();
          pictures[3]= pictureObj1;
   

    
    String directoryName = "C:\\Users\\Vivian A. Lam\\Desktop\\lab 7\\slide show pics\\";
    FrameSequencer frameSequencer = new FrameSequencer(directoryName);
    for (int i = 0 ; i < pictures.length; i++)
    {
       for (int j=0;j<10;j++)
       {
         frameSequencer.addFrame(pictures[i]);
       }
    }
    
    MoviePlayer movie = new MoviePlayer(directoryName);
    movie.playMovie();
    
  }
}