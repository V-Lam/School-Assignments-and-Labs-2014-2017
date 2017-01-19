package Assignment1.assign1;

public class Undergrad extends Student {

	//fields
	private float gpa;

	//constructor
	public Undergrad(float gpa, int ID, String name, int grade) {
		this.gpa = gpa;
		super (ID, grade, name);//calls the Student's constructor to initalize the ID, name and grade
	}

	public void print() {
		Systemp.out.println(this.toString() + "GPA is:" + gpa);//calls the toString() method to print the Id, name of an Undergrade object and the GPA
	}

	public float getGpa() {
		
	}

	public void setGpa() {
		
	}

}
