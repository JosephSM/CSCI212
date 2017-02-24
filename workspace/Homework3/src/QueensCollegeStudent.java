
public class QueensCollegeStudent extends Student{
	
	private String Venus;
	public QueensCollegeStudent(String firstName, String lastName, int ID, double GPA, int day, int month, int year, String venus){
		super(firstName, lastName, ID, GPA, day, month, year);
		this.Venus = venus;
	}
	
	public void displayAttributes(){
		super.displayAttributes();
		System.out.println("Venus: " + Venus );
		
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
		else if(!(this.Venus.substring(4, 8).equals(Integer.toString(ID).substring(4, 8)))){
			System.out.println("failed cuny");
			return isValid;
		}
		else{
			isValid = true;
			return isValid;
		}	
	}
	
	
}
