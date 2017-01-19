public class CentigradeToFaernheitWithUserInput
{
  public static void main (String[] args)
  {
    double temperature;
    double fdegrees;
    
    //get the temp from the user
    temperature = SimpleInput.getNumber ("Enter temperature:");
    
    fdegrees = (temperature/5.0) * 9.0 + 32;
    System.out.print(temperature + "degess C is");
    System.out.println(fdegrees+ " degrees F");
  }
}