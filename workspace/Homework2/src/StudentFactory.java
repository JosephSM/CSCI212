import javax.swing.JOptionPane;

public class StudentFactory {
	public static void main(String[] args){

		String[] fnames = {"Steve", "Frank", "John", "Linda", "Martha", "Johnny"};
		String[] lnames = {"Johnson", "Jackson","Lincoln", "McArthur", "Washington", "Franklin"};

		String usrinput;
		int numStudentsToCreate = 0;
		
		boolean strHasError = false;
		do{
			try{
				usrinput = JOptionPane.showInputDialog("How many students would you like to make");
				numStudentsToCreate = Integer.parseInt(usrinput);
				strHasError = false;
			}
			catch(Exception e){
				strHasError = true;
			}
		}
		while(strHasError);
		Student[] students = new Student[numStudentsToCreate];
		for(int i = 0; i < numStudentsToCreate; i++){
			String firstName = fnames[randNum(fnames.length)];
			String lastName = lnames[randNum(lnames.length)];
			int id = randomRange(10000000, 89999999);
			String venus = lastName.substring(0,2).toLowerCase() + firstName.substring(0, 2).toLowerCase() + Integer.toString(id).substring(4, 8);
			double grade = Math.floor((Math.random()*300)+100)/100;
			
			students[i] = new Student(firstName, lastName, id, grade, venus);
			students[i].displayAttributes();
			System.out.println("Login Valid? : " + students[i].isValidVenusLogin());
			System.out.println();
		}
		System.out.println("The total number of students are: " + Student.getNumOfStudents());
		
	}
	public static int randNum(int range){
		return (int)(Math.random() * range);
	}
	public static int randomRange(int min, int max)
	{
	   int range = (max - min);     
	   return (int)(Math.random() * range + min);
	}
}
