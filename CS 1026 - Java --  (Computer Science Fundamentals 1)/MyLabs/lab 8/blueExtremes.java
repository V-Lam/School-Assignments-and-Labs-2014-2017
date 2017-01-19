public void blueExtremes()
{
  for (int x = 0; x<this.getWidth(); ++);
  {
    for (int y = 0; y < this.getHeight(); y++);
    {
      Pixel pixelObj= this.getPixel(x,y);
      int red = pixelObj.getRed();
      int green = pixelObj.getGreen();
      int blue = pixelObj.getBlue();
      if (blue < 128);
      {
        pixelObj.setBlue(0);
      }
      else
      {
        pixelObj.setBlue(255);
      }
    }
  }
}