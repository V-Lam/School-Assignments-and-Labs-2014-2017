/**
 * @author Vivian A. Lam
 * main class and  method used to create objects and print them out
 * @param u undergrad object
 * @param g grad object
 * @param s student object
 */
		
public class StudentDemo {

	public static void main(String[] args) {
		
		
		Undergrad u = new Undergrad((float) 3.8,100,"ali",95);      //creates an undergrad object
		u.print();                                          //calls the print() method to print the details of the object
		u.setId(500);                                       //changes the value of variable ID of u to be 500
		u.print();
		System.out.println(u.convertGrade());               //Call the method (and print the results) convertGrade() of u


		Grad g = new Grad("mike", 200, "sami", 95);         //creates a new Grad object
		g.setSupervisor("tim");                             //changes the supervisor of g to tim
		System.out.print(g);                                //prints the string representation of the object g
		System.out.println(u.convertGrade());               //calls the method and prints the results of convertGrade()
		
		
		//Create a variable s of Student type and refers it to Undergrad object u (demonstrating polymorphism)
		Student s = u;
		System.out.println(s.convertGrade());            //Call and print the method convertGrade() of s
		s = g;                                           //Have s refer it to Grad object g (demonstrating polymorphism)
		System.out.println(s.convertGrade());            //Call and print the method convertGrade() of s

		
	}

}
