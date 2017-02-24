
abstract public class Student{
	private static int numberOfStudents = 0; 
	public String  firstName;
	public String  lastName;
	protected int  ID;
	private double GPA; 
	protected Date   birthDate;
	
	public Student(String firstName, String lastName, int ID, double GPA, int day, int month, int year){
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID = ID;
		this.GPA = GPA;
		this.birthDate = new Date(day, month, year);
		numberOfStudents++;
		
	}
	
	public static int getNumOfStudents(){
		return numberOfStudents;
	}
	
	public double getGPA(){
		return this.GPA;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public int getID(){
		return this.ID;
	}

	
		
	public void displayAttributes(){
		System.out.println("Student Name: "  + this.firstName + " " + this.lastName);
		System.out.println("Student ID : " + this.ID + " GPA : "  + GPA);
		System.out.println("Birthday: " + birthDate.getDay() + " " + birthDate.getMonth() + " " + birthDate.getYear());
	}
}



