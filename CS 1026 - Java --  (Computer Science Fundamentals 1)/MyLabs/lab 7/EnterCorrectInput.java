public class EnterCorrectInput
{
  public static void main (String[] args)
  {
    System.out.println("We want a number between 1 and 200 inclusive.");
      int num = -1;
    
    while ((num <0) || (num >100 ))
    {
      num = SimpleInput.getIntNumber("Enter Number: " );
    }
    
    System.out.println("The number you entered is " + num);
  }
}