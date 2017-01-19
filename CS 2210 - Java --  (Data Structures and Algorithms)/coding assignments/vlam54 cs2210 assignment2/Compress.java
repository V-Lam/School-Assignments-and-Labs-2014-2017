import java.util.*;//import statement
import java.io.*;
import java.io.*;

/**
 * compresses files using dictionaries and hash tables
 * @author Vivian A. Lam
 *
 */
public class Compress {

	public static void main(String[] args) throws DictionaryException, IOException {
		
		
	
	////////////Initialization of Variables:////////////////////
			
			//String inputFile = "C:\\Users\\Vivian A. Lam\\Desktop\\cs 2210 assignment 2\\test2";		
			//out = new BufferedOutputStream( new FileOutputStream("C:\\Users\\Vivian A. Lam\\Desktop\\cs 2210 assignment 2\\test2.zzz"));

			
			/* variables to open a file for reading */
			String inputFile = args[0];
			BufferedInputStream in;
			in = new BufferedInputStream(new FileInputStream(inputFile));

			/* variables to create the output file */
			/* saves the compressed file with the extension ".zzz" */
			BufferedOutputStream out;
			String outputFile = inputFile + ".zzz";
			out = new BufferedOutputStream(new FileOutputStream(outputFile));
			MyOutput compOut = new MyOutput();

			int n = 256; 			// the current number of elements in the dictionary
			String p = "";			//initializes the working string p and q. p stores the current working string and the next character from input.
			String q = "";			//q stores p minus the last character of p

			try {	
				Dictionary dict = new Dictionary(4111);		// constructs dictionary	

				// inserts the first 255 characters into the dictionary
				for (int i = 0; i < 256; i++) {
					DictEntry first255 = new DictEntry((char) i + "", i);
					dict.insert(first255);
				}
		
	////////////Compression:////////////////////
		
				System.out.println("This is the file you passed: " +args[0]);		//prints the argument passed to the screen
		
				//while(in.available()>0&&(n<=4096)){
				while((in.available()>0)){
					int current = in.read();	//read the next byte
					q=p;						//sets q = to p (meaning q is p before appending the current character)
					p=p+ (char)current;			//appends the current character to p
			
					if (dict.find(p) !=null){		//find: if it exists in the dictionary keep looping and read next character
						//do nothing
					}
					else{							//else it doesn't exist	
													// get code for q and write to output
						int codeOut;
						codeOut = dict.find(q).getCode();
						compOut.output(codeOut, out);
						if (n < 4096) {			//if the number of elements in the dictionary is less than 4096
							// add p to dictionary by creating a new DictEntry object called temp
							DictEntry temp = new DictEntry(p, n);
							dict.insert(temp);		//insert temp into the dictionary. temp contains the string p and the code n
						}
						
						p = (char) current + "";		// reset p to the last character of p
						n++;							// increment n
					}
				}
		
				//after the while loop is done and the dictionary is full, if p still has any characters left, write the 
				//code for the remaining characters to the output file
				if (!p.equals("")){
					int codeOut;
					codeOut = dict.find(p).getCode();
					compOut.output(codeOut, out);
				}
				
				
				/*Before closing the output file, invoke the following method
				  to send to the output file any bits that are still left in the buffer*/
				compOut.flush(out);
		
			}//end of try
		
			
			//exception for DictionaryException
			catch (DictionaryException e){
				System.out.println(e.getMessage());
			}
			finally {
				//close the input and output files
				in.close();
				out.close();
			}
		
			
	}//end of main
}//end of class
