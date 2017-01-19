public class CentigradeToFahrenheit
{
  public static void main (String[] args)
  {
    double temperature;
    double fdegrees;
    temperature = 27.9;
    fdegrees = (temperature / 5.0) * 9.0 +32;
    System.out.println(temperature + " degrees C is " + fdegrees + " degrees F");
  }
}