//define a class for representing employees of a University
public class Employee
{

////////////////ATTRIBUTES (INSTANCE VARIABLES)///////////////////////
 
  private String firstName;
  private String lastName;
  private String employeeNumber;
  private String jobTitle;
  private String department;
  private double salary;

/////////////////////////////CONSTRUCTORS//////////////////////////////

  public Employee(String empFirst, String empLast, String empNumber)
  {
  }
    
  public Employee(String empFirst, String empLast, String empNumber, String empTitle, String empDepartment, double empSalary)
  {
    firstName = empFirst;
    lastName = empLast;
    employeeNumber = empNumber;
    jobTitle = empTitle;
    department = empDepartment;
    salary = empSalary;
  }
////////////////////////////METHODS///////////////////////////////////
  
  public String toString()
  {       
    if(employeeNumber==null)
    {
      employeeNumber = " ";
    }
    if (firstName==null)
    {
      firstName =" ";
    }
    if (lastName==null)
    {
      lastName = " ";
    }
    if (jobTitle==null)
    {
      jobTitle = " ";
    }
    if (department==null)
    {
      department = " ";
    }    
     String output = (firstName + " " + lastName + "Employee Number "+ employeeNumber + " " + jobTitle + ", "+ department + " " + salary);    
    return output;
  }
    
    
  public String getFirstName()
  {
    return this.firstName;
  }
  
  public String getLastName()
  {
    return this.lastName;
  }
  
  public double getSalary()
  {
    return this.salary;
  }
  
  public void setSalary(double newSalary)
  {
    salary = newSalary;
  }
  
  
  public String lastNameFirst()
  {
    String newName = (lastName + ", " +firstName);
    return newName;
  }
  
///////////////for testing: main method for the testing class
   
    public static void main (String[] args)
  {
    //create 2 sample Employee objects
    Employee emp1 = new Employee ("Albert", "Einstein", "186000");
    Employee emp2 = new Employee ("Grace", "Hopper", "19451245", "Faculty", "Computer Science", 86333.76);
    
    //show the employee info
    System.out.println(emp1.toString());
    System.out.println(emp2.toString());
      
    //try out the accessor and modifier methods 
    System.out.println (emp1.getFirstName() + " " + emp1.getLastName());
    emp2.setSalary(emp2.getSalary() + 100.00);
    System.out.println(emp2.getSalary());
   
    
    System.out.println(emp1);
    System.out.println(emp2);   
    
    System.out.println(emp2.lastNameFirst());
    ////////////////other methods///////////////////
    
    
    
    
  }
     
}