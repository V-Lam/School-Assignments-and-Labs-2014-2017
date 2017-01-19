/**
 * this class is for the grad object
 * @author Vivian A. Lam
 *
 */
public class Grad extends Student {

	/**
	 * stores the name of the supervisor
	 */
	private String supervisor;
	

	/**
	 * constructor to initialize values
	 * @param supervisor used to store the string value: the name of the supervisor of the grad object
	 * @param ID inherited from student class
	 * @param name inherited from student class
	 * @param grade inherited from student class
	 */
	public Grad(String supervisor, int ID, String name, int grade) {
		super (ID, name, grade);//calls the Student's constructor to initalize the ID, name and grade
		this.supervisor = supervisor;
		
	
	}

	/**
	 * @param output is a string to return the letter grade
	 * method to convert number grade to letter grades
	 */
	public String convertGrade() {
		String output = "";
		if(grade>=80){                      //if the value of grade is equal to or above 80 
				output = "A";				//the output would be A
		}
		else if(grade>=70 && grade <80){    //if the value of grade is in between 70(inclusive) and 80
				output = "B";				//the output would be B
		}
		else if(grade <70){                 //if the value of grade is below 70
				output = "F"; 				//the output would be F
		}
		return output;  //returns the output
	}

	
	/**
	 * method to get the value of supervisor
	 * @return supervisor
	 */
	public String getSupervisor() {
		return supervisor;
	}

	
	/**
	 * method to set the value of supervisor
	 * @param sup parameter to retrieve the and become supervisor's new value
	 */
	public void setSupervisor(String sup) {
		this.supervisor = sup;      //sets supervisor equal to the parameter
	}

}
