//package Assignment1.assign1;
/**
 * this is the superclass used to create student object
 * @author Vivian A. Lam
 *
 */
public class Student implements People {

	/**
	 * fields: gpa, id and name of student
	 */
	protected int grade;
	protected int ID;
	protected String name;

	
	/**
	 * constructor to initialize values
	 * @param ID used to store the id number of the student
	 * @param name used to store the name of the student
	 * @param grade stores the student's gpa
	 */
	public Student(int ID, String name, int grade) {
		this.ID = ID;			//initializes variable values
		this.name = name;
		this.grade = grade;
	}

	
	/**
	 * method to convert number grade into leter grade
	 * @return output value (the letter representation of a grade)
	 */
	public String convertGrade() {
		String output;
		if(grade>=90){                      //if the value of grade is equal to or above 90 
				output = "A+";				//the output would be A+
		}
		else if(grade>=80 && grade <90){    //if the value of grade is in between 80(inclusive) and 90
				output = "A";				//the output would be A
		}
		else if(grade>=70 && grade <80){    //if the value of grade is in between 70(inclusive) and 80
				output = "B";				//the output would be B
		}
		else if(grade>=60 && grade <70){    //if the value of grade is in between 60(inclusive) and 70
				output = "C";				//the output would be C
		}
		else{								//if the value of grade is below 60
				output = "F";				//the output would be F
		}
		return output;  //returns the output
	}

	/**
	 * method to return the string representation of the attributes of a student object
	 * @return name and id
	 */
	public String toString() {
		return ("name is: " + name + " ID is: " + ID); //returns the name and ID of person objects
	}
	
	
	
	////below are the setters and getter methods
	
	
	/**
	 * sets the value of id
	 * @param id
	 */
	public void setId(int id)
	{
		this.ID = id;
	}
	
	/**
	 * gets the value of Id
	 * @return id
	 */
	public int getId()
	{
		return ID;
	}
	
	
	/**
	 * gets the value of name
	 * @return name
	 */
	public String geName()
	{
		return name;
	}
	
	/**
	 * sets the value of name
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	/**
	 * gets the value of grade
	 * @return grade
	 */
	public int getGrade()
	{
		return grade;
	}
	/**
	 * sets the value of grade
	 * @param grade
	 */
	public void setGrade(int grade)
	{
		this.grade = grade;
	}
	
	

	
	
}
