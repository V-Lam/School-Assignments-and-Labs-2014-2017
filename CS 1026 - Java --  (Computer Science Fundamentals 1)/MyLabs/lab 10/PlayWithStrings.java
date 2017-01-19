public class PlayWithStrings
{
  public static void main (String[] args)
  {
    /*
    String firstName = "harry";
    System.out.println(firstName.toUpperCase());
    System.out.println(firstName);
    
    String test = "hello";
    System.out.println(test.charAt(0));
    System.out.println(test.charAt(3));
    System.out.println(test.charAt(4));
    
    
    String str1 ="Hello";
    String str2 ="Goodbye";
    String str3 ="Goodbye";
    System.out.println(str1.equals(str2)); 
    
    if ( str1.equals(str2)true)
    {
    System.out.println("The strings are the same");
    }
    else
    {
    System.out.println("The strings are different"); 
    }   
    if ( str2.equals(str3))
    System.out.println("The strings are the same")
    else
    System.out.println("The strings are different"); 
    */
    
    String test = "this is a test.";
    
    for (int i = 0; i < test.length(); i++)
    {
      System.out.println(test.charAt(i));
    }
    
    for (int i = test.length() -1; i >=0; i--)
    {
      System.out.println(test.charAt(i));
    }
    
  }
}


