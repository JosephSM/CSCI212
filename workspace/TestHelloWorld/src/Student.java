import javax.swing.JOptionPane;

public class Student {
	private static int numStudents = 0;
	private int cunyFirst; 
	private int GPA;
	private String venus;
	private String fName;
	private String lName;
	
	public static void main(String[] args){
		Student[] Students;
		String howManyStudents = JOptionPane.showInputDialog(null, "How many students would you like to create?");
	}
	public Student(){
		numStudents++;
		
		
	}
	
	double getGPA(){
		
	}
	bool isVenusValid(){
		
	}
	void displayAttributes(){
		System.out.println("Student Name:"+ fName + " " + lName);
		System.out.println("GPA: "+ GPA);
	}
	
	
	
}
