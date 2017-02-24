import javax.swing.JOptionPane;

public class StudentFactory {
	public static void main(String[] args){

		String[] fnames = {"Steve", "Frank", "John", "Linda", "Martha", "Johnny"};
		String[] lnames = {"Johnson", "Jackson","Lincoln", "McArthur", "Washington", "Franklin"};

		String usrinput;
		int numStudentsToCreate = 0;
		
		boolean inputHasError = false;
		do{
			try{
				usrinput = JOptionPane.showInputDialog("How many students would you like to make");
				numStudentsToCreate = Integer.parseInt(usrinput);
				inputHasError = false;
			}
			catch(Exception e){
				inputHasError = true;
			}
		}
		while(inputHasError);
		
		
		
		QueensCollegeStudent[] students = new QueensCollegeStudent[numStudentsToCreate];
		for(int i = 0; i < numStudentsToCreate; i++){
			String firstName = fnames[randNum(fnames.length)];
			String lastName = lnames[randNum(lnames.length)];
			int    id = randomRange(10000000, 89999999);
			int    day = randomRange(31,33);
			int    month = randomRange(1,12);
			int    year = randomRange(1900,2016);
			double grade = Math.floor((Math.random()*300)+100)/100;
			String venus = venusGenerator(lastName, firstName, id);
			students[i] = new QueensCollegeStudent(firstName, lastName, id, grade, day, month, year, venus);
			try {
				boolean isV = isValidDate(students[i].birthDate.getDay() , students[i].birthDate.getMonth(), students[i].birthDate.getYear());
				System.out.println("Date is Valid");
			}
			catch(MyInvalidDateException ex){
				System.out.println("Date is not Valid");
			}
			students[i].displayAttributes();
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
	
	public static String venusGenerator(String last, String first, int id){
		return last.substring(0,2).toLowerCase() + first.substring(0, 2).toLowerCase() + Integer.toString(id).substring(4, 8);
	}
	
	public static boolean isValidDate(int day, int month, int year) throws MyInvalidDateException {
		if(day > 31 || day < 1){
			throw new MyInvalidDateException("day is out of range");
		}
		return true;
	
	}
	
	

	
}
