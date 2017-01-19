package Assignment1.assign1;

public class Student implements People {

	//fields
	protected int grade;
	protected int ID;
	protected String name;

	//constructor
	public Student(int ID, String name, int grade) {
		this.ID = ID;
		this.name = name;
		this.grade = grade;
	}

	public String convertGrade() {
		String output;
		if(grade>=90){                      //if the value of grade is equal to or above 90 
				output = "A+";				//the output would be A+
		}
		else if(grade>=80 && grade <90){    //if the value of grade is inbetween 80(inclusive) and 90
				output = "A";
		}
		else if(grade>=70 && grade <80){    //if the value of grade is inbetween 70(inclusive) and 80
				output = "B";
		}
		else if(grade>=60 && grade <70){    //if the value of grade is inbetween 60(inclusive) and 70
				output = "C";
		}
		else{								//if the value of grade is below 60
				output = "F";
		}
		return output;  //returns the output
	}

	public String toString() {
		return ("name is: " + name + "ID is: " + ID); //returns the name and ID of person objects
	}

}
