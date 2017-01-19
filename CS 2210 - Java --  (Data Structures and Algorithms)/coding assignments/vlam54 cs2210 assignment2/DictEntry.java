/**
 * this class represents and entry in the dictionary, associating a string key with an integer code
 */
public class DictEntry {
	
////////////////FIELDS////////////////
	private int code;
	private String key;

	
	
////////////////CONSTRUCTOR////////////////
	
	public DictEntry (String key, int code){
		this.key = key;
		this.code = code;
	}
	
	
	
////////////////METHODS////////////////
	
	/**/
	public String getKey(){
		return key;
	}
	
	/**/
	public int getCode(){
		return code;
	}
	
	/* You can implement any other methods that you want to in this class, but they must be declared as
	private methods (i.e. not accessible to other classes).*/
	
}
