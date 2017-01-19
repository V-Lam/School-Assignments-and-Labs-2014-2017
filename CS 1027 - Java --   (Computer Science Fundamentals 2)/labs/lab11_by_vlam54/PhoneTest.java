import java.io.*;
import java.util.Iterator;
/**
 * PhoneTest.java:
 * This class creates an Ordered List of Phone objects.
 * @author CS1027 for Lab
 */

public class PhoneTest {

   public static void main(String[] args) throws Exception {

   // get the filename from the user
     
      BufferedReader keyboard = new BufferedReader
                                 (new InputStreamReader(System.in),1);       
      System.out.println("Enter name of the input file: ");
      String filename= keyboard.readLine();
    
   // create object that controls file reading and opens the file
         
      InStringFile reader = new InStringFile(filename);
      System.out.println("\nReading from file " + filename + "\n");

    // your code to create (empty) ordered list here

      ArrayOrderedList list = new ArrayOrderedList();    
      
      
     
   // read data from file two lines at a time (name and phone number)
     
      String name, phone;
      do {
        name = (reader.read());
        phone = (reader.read());

        // your code to add the entry to your ordered list here
        
        //Store each name/phone number pair from your file in a Phone object
        //Add each new Phone object to the Ordered List
        Phone p = new Phone (name, phone);
        list.add(p);
        
     

              
      } while (!reader.endOfFile()); 
   
      System.out.println("Here is my phone book:");

      // your code to print the ordered list here

      System.out.println(list);


 /////////////////////add code for lab 9 here/////////////
     
      //iterate through your ordered list and print only the names (not the phone numbers)
    System.out.println("\n"+"printing out the names only");
      Iterator<Phone> iter = list.iterator();
      while(iter.hasNext() )
      {
    	  System.out.println(iter.next().getName());
      }
      
      
      // iterate through your ordered list again and print only the phone numbers (not the names)
      System.out.println("\n"+"printing out the numbers only");
      
      iter = list.iterator(); // start new iteration
      while(iter.hasNext() )
      {
    	  System.out.println(iter.next().getPhone());
      }
      
      
      /*Answer the following questions:
    	  Q:What file(s) would you need to add to your project if you changed phoneList so that it was declared and created by
    	  	LinkedOrderedList phoneList = new LinkedOrderedList();
    	  A:I would have to add the classes needed for a linked list:
    	  
    	  
    	  Q:What would you then need to change in the code you wrote for steps 3 and 5?
      	  A:I have no idea what steps are 3 and 5, the lab steps arent numbered, but I will guess it's the iterator steps
      	  	in that case I would need nodes to point to current and front i think
      	  		
      */
      
      System.out.println("\n"+ "exercise 2:");
      System.out.println(list.toString2());
      /*
       Question: Consider the linked implementation of the ListADT, LinkedList.java. Would the exact same toString2 method work in LinkedList.java too? 
       A: i think so?? I don't really know
       */
      
      
    //  C:\\Users\\Vivian A. Lam\\Desktop\\albedotest.txt

   // close file
      
      reader.close();
      System.out.println("\nFile " + filename + " is closed.");      
     
   }
}
