import java.util.*;//import statement

/**
 * advanced version of Caesar Cipher (using the key 12345), encodes a string inputed by the user
 * @author Vivian A. Lam
 *
 */

public class encode {

	/**
	 * main method
	 * method to encode an input string using the key 12345. The output encoded string excludes spaces
	 * @param args arbitrary parameter
	 */
	public static void main(String[] args)
	{
		// prompts user to enter a String
		System.out.println("Input a string you wish to encode: ");
		Scanner scan = new Scanner(System.in);		//creates a new scanner
		String input = scan.nextLine();				//reads a complete line
				
		
		//Declaring and initializing variables
		String output= "";
		int newLength = input.length();					//an integer to store the value of the length of the input string without spaces
		LinkedList encodedString = new LinkedList();	//creates a linked list to store the encoded string
		int keyValue = 0;								/*an integer to temporarily store the current key value in the queue (variable used to
														store the element at the front of the queue so that when that element is deleted,
														it can be added to the rear of the queue*/
		
		
		// stores the key 12345 into a Queue(using for loop). queue is implemented using linked list class
		Queue<Integer> queue = new LinkedList<Integer>();//creating a queue(to store the key) as an instance of a Linked list
		
		for (int k=1; k <=5; k++)	//for loop to add the numbers 1,2,3,4,5 into the queue
		{
			queue.add(k);			//adds the current loop number
		}
		
		
		// Inputed string is parsed character by character
		for (int index = 0; index < input.length(); index++) { 	// scan the input string from left to right
			char c = input.charAt(index);						//obtains the current character from the input string
			
			
			//encodes the character by changing the ASCII value (adding the corresponding key value)
			int ascii = (int) c;							//converts current character to ascii	
				
			if (ascii != 32){					//if the current character is not a space
				keyValue = queue.remove();					//removes whatever is currently at the front of the queue and makes this the value of keyValue
				ascii = ascii + keyValue;					//adds the corresponding key value to the ascii
				char character = (char)ascii;				//convert ASCII to a character
				encodedString.addLast(character);			//stores this character in the linked list
				queue.add(keyValue);						//adds keyValue to the end of the queue
			}//end if
			
			else if (ascii == 32)				//if the current character is a space
			{
				newLength = newLength - 1;//decrease the value of newLength by 1
			}
		
		}//end for
		

		//printing out the encoded string
		System.out.println("Output string: "); 
		for(int o=0; o<newLength; o++){			//loops newLength amount of times. newLength is the value of the input string without the spaces
				System.out.print(encodedString.get(o));		//print the current element in the linked list
		}
	
	
	}//end method
	
}
