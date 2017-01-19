/**
 * this class is for an undergrad object
 * @author Vivian A. Lam
 *
 */

public class Undergrad extends Student {//inherits some variables from the Student superclass

	/**
	 * field. gpa for storing gpa of student
	 */
	private float gpa;

	/**constructor
	 * Initializes values. inherits ID, name and grade from the super class, Student
	 * @param gpa stores the gpa
	 * @param ID inherited from superclass, stores value of student id
	 * @param name inherited from superclass, stores name of student
	 * @param grade inherited from superclass, stores grade of student
	 */
	public Undergrad(float gpa, int ID, String name, int grade) {
		super (ID, name, grade);//calls the Student's constructor to initialize the ID, name and grade
		this.gpa = gpa;
		
	}

	
	
	/**
	 * method to print out the properties of the grad object, including the gpa
	 */
	public void print() {
		System.out.println(this.toString() + "GPA is:" + gpa);//calls the toString() method to print the Id, name of an Undergrade object and the GPA
	}

	
	
	/**
	 * Accessor method to get gpa of undergrad
	 * @return value of gpa
	 */
	public float getGpa() {
		return gpa;
	}

	
	
	/**
	 * method to set the value of gpa
	 * @param gpa
	 */
	public void setGpa(float gpa) {
		this.gpa = gpa; //sets the new value of gpa
	}

}
