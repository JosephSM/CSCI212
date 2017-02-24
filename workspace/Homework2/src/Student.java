
public class Student{
	private static int numberOfStudents = 0; 
	public String firstName;
	public String lastName;
	private int cunyId;
	private double GPA; 
	private String Venus;

	
	public Student(String firstName, String lastName, int cunyId, double GPA, String Venus){
		this.firstName = firstName;
		this.lastName = lastName;
		this.cunyId = cunyId;
		this.GPA = GPA;
		this.Venus = Venus;
		numberOfStudents++;
		
	}
	
	public static int getNumOfStudents(){
		return numberOfStudents;
	}
	
	public double getGPA(){
		return this.GPA;
	}
	
	public boolean isValidVenusLogin(){
		boolean isValid = false;
		if(this.Venus.length() != 8){
			System.out.println("Bad longin str");
			return isValid;
		}
		else if(!(this.Venus.substring(0,2)).equals(this.lastName.substring(0,2).toLowerCase())){
			System.out.println(this.Venus.substring(0,2)); 
			System.out.println(this.lastName.substring(0,2).toLowerCase());
			System.out.println("failed lname");
			return isValid;
		}
		else if (!(this.Venus.substring(2, 4).equals(this.firstName.substring(0, 2).toLowerCase()))){
			System.out.println("failed fname");
			return isValid;
		}
		else if(!(this.Venus.substring(4, 8).equals(Integer.toString(cunyId).substring(4, 8)))){
			System.out.println("failed cuny");
			return isValid;
		}
		else{
			isValid = true;
			return isValid;
		}	
	}
		
	public void displayAttributes(){
		System.out.println("Student Name: "  + this.firstName + " " + this.lastName);
		System.out.println("Student ID : " + this.cunyId + " GPA : "  + GPA);
		System.out.println("Venus Login " + this.Venus);
	}
}



