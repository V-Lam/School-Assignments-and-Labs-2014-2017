import java.util.*;//import statement

/**
 * Modern Caesar Cipher decoder. Decodes an encoded string using the key 12345
 * @author Vivian A. Lam
 *
 */
public class decode {
	/**
	 * main method
	 * method to decode an input string using the key 12345, the input string is not allowed to contain any spaces
	 * @param args
	 */
	public static void main(String[] args)// void encoder()
	{
		// prompts user to enter a String that doesn't contain spaces
		System.out.println("Input a string you wish to Decode(no spaces please): ");
		Scanner scan = new Scanner(System.in);		//creates a new scanner
		String input = scan.nextLine();				//reads a complete line
				
		//declares and initialize variables
		String output= "";
		LinkedList encodedString = new LinkedList();	//creates a linked list to store the encoded string
		int keyValue = 0;								/*an integer to temporarily store the current key value in the queue (variable used to
														store the element at the front of the queue so that when that element is deleted,
														it can be added to the rear of the queue*/
		
		
		// stores the key 12345 into a Queue. queue is implemented using linked list class
		Queue<Integer> queue = new LinkedList<Integer>();//creating a queue(to store the key) as an instance of a Linked list
				
		for (int k=1; k <=5; k++)
		{
			queue.add(k);		
		}
		
				
		// Inputed string is parsed character by character
		for (int index = 0; index < input.length(); index++) { // scan the input string from left to right
			char c = input.charAt(index);						//obtains the current character
			
					
			//encodes the character by changing the ASCII value (adding the corresponding key value)
			int ascii = (int) c;//converts current character to ascii		
			keyValue = queue.remove();					//removes whatever is currently at the front of the queue and makes this the value of keyValue
			ascii = ascii - keyValue;					//SUBTRACTS the corresponding key value to the ascii
			char character = (char)ascii;				//convert ASCII to a character
			encodedString.addLast(character);			//stores this character in the linked list
			queue.add(keyValue);						//adds keyValue to the end of the queue
		
		}//end for
		
		
		//printing out the encoded string
		System.out.println("Output string: "); 
		for(int o=0; o<input.length(); o++){			//loops until all the characters from the input string are displayed
				System.out.print(encodedString.get(o));		//print the current element in the linked list
		}
		
	}//end method

}
