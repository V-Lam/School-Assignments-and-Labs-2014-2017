import java.util.*;//import statement

/**
 * this class implements a dictionary using a hash table and separate chaining. Max 4096 items can be stored in the table and table size is a prime number
 * @author Vivian A. Lam
 */
public class Dictionary implements DictionaryADT {

	/**
	 * Node class inside the dictionary class. Used to make a node object to point to DictEntry objects
	 * @author Vivian A. Lam
	 */
	private class Node {
		////////////////FIELDS////////////////
		private Node next;
		private DictEntry element; //stores the key and the code

		////////////////CONSTRUCTOR////////////////
		/**
		 * constructor to create and empty node
		 */
		private Node(){
			this.next = null;
			this.element = null;
		}
		
		/**
		 * constructor
		 */
		private Node(DictEntry element, Node next){
			this.next = next;
			this.element = element;

		}

		////////////////METHODS////////////////	
		/**
		 * Returns the next node
		 * @return get the next node
		 */
		private Node getNext(){
			return this.next;
		}

		/**
		 * gets the element at that node
		 * @return the element at that node
		 */
		private DictEntry getElement() {
			 return this.element;
		 }

		 /**
		  * sets the next node
		  * @param sets the next node
		  */
		 private void setNext(Node next) {
			 this.next = next;
		 }

		 /**
		  * @param element the element to set
		  */
		 private void setElement(DictEntry element) {
			 this.element = element;
		 }
	}//end of node class

	
////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////	
	
	
	////////////////FIELDS////////////////
	private Node[] table;			//table is an array of nodes
	private int size; 				//size of hash table
	private int numEle; 			//number of elements in the table
	
	////////////////CONSTRUCTOR////////////////
	/**
	 * creates a dictionary: create an array "table" where the size is a prime number. The array will hold a list of nodes
	 */
	public Dictionary (int size) {
		this.size = size;
		table = new Node[size];
		numEle = 0;
		
		//makes each index in the table null
		for(int i = 0; i < size; i++){
			table[i] = null;
		}
	}
	
	////////////////METHODS////////////////	
	//using separate chaining
	
		/**
		 * inserts the given pair (string, code) in the dictionary and throws a Dictionary Exception if the
		 * key associated with pair, pair.getKey(), is already in the dictionary.
		 * if there is something in the place we want to insert, deal with it using separate chaining
		 * @param DictEntry pair
		 * @return 1 if collision
		 * @return 0 if no collision
		 */
		public int insert (DictEntry pair) throws DictionaryException{		
			String key = pair.getKey();
			
			int pos = hash(key);						//compute hash function
			Node p = new Node(pair, null); 
			if (find(key) != null) {				//if the key is already in the dictionary throw exception
				throw new DictionaryException("");
			}
			else{									//otherwise if key isn't in dictionary insert
				if(table[pos]==null){					//if there is nothing at that position insert
					table[pos] = p;							//puts node in this spot of the table
					numEle++;								//increment number of elements and return zero
					return 0;
				}
				else{									//otherwise if there is something at that spot, collision
					p.setNext(table[pos]);					//makes the node p point to the current node at the spot in the table
					table[pos]=p;							//makes the current position store the node p
					numEle++;								//increment number of elements and return 1
					return 1;
				}
			}	
		}
		
		
		/**
		 * removes entry with the given key from the dictionary and throws a DictionaryException if the key is not in the dictionary
		 * @param key
		 */
		public void remove (String key) throws DictionaryException{
			
			if(find(key)!= null){					//if it exists in dictionary remove it
				int pos = hash(key);					//creates nodes to point at the node containing the item we want to remove and the node before it		
				Node p = (table[pos]);	
				Node previous= (table[pos]); 			//node to find the the node previous of the one we want to remove
				int counter=0;							//counter to count how many times we have to loop to find p. This will be used to find previous
				
				//loop to iterate through the nodes at that position in the table until the node we want to remove is found
				while((p!=null) && (!(p.getElement()).getKey().equals(key))){
					p = p.getNext();
					counter++;							//increment the counter so we know how many times it loops
				}
				for(int i=0; i<counter; i++){			//loops so we get the node before the node we want to remove
					previous = previous.getNext();	
				}
				previous.setNext(p.getNext());			//sets the value of Next for the previous node to the node after the node we want to remove
				numEle--;								//decrease the number of elements in the dictionary
			}
			else{									//otherwise it's not in the dictionary, throw exception
				throw new DictionaryException("");
			}
		}
		
		
		/**
		 * returns the DictEntry object stored in the dictionary with the given key, or null if no entry in the dictionary is found
		 * @param key
		 * @return null if not found
		 * @return DictEntry pair if found
		 */
		public DictEntry find (String key){
			int pos = hash(key); 			//position is computed by hash function so we know where to start
			Node p = (table[pos]);			//node to point at an index in the table
			
			//loop until the element is found
			while((p!=null) && (!(p.getElement()).getKey().equals(key))){
				p = p.getNext();			//go through all the nodes
			}
			if(p == null){					//if not found return null
				return null;
			}
			else{							//otherwise if found, return the key and code of the node
				return (p.getElement());				
			}
		}
		
		
		/**
		 * returns the number of DicEntry objects (number of elements) stored in the dictionary
		 * @return numEle
		 */
		public int numElements(){
			return numEle;
		}
		
		
		/**
		 * hash function to index data
		 * @param x
		 * @return hV hash value
		 */
		private int hash(String x){
			int hV = 0;						//initialize the hash value variable to be 0
			int length = x.length();		//variable to store the integer value of the length of the string we want to hash
			
			//loops for all the characters of the string
			for(int i = length-1; i>=0; i--){
				hV= ((hV*109) + ((int)x.charAt(i))) % size; 	//compute new hash values: multiply by prime number 109 and add the integer representation 
																//of the character and then mod by the size of the table so that it stays within
																//the range of the table size
			}
			return hV;//returns the hash value
		}
				
		
}//end of class
