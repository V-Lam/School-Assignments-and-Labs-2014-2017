package Assignment1.assign1;

public class Grad extends Student {

	//fields
	private String supervisor;
	
	//constructor
	public Grad(String supervisor, int ID, String name, int grade) {
		this.supervisor = supervisor;
		super (ID, grade, name);//calls the Student's constructor to initalize the ID, name and grade
	
	}

	public String convertGrade() {
		String output;
		if(grade>=80){                      //if the value of grade is equal to or above 80 
				output = "A";				//the output would be A
		}
		else if(grade>=70 && grade <80){    //if the value of grade is inbetween 70(inclusive) and 80
				output = "B";
		}
		else if(grade <70){                 //if the value of grade is below 70
				output = "F";
		}
		return output;  //returns the output
	}

	public String getSupervisor() {
		
	}

	public void setSupervisor() {
		
	}

}
