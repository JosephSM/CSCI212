import javax.swing.JOptionPane;

public class Homework1 {
	public static void main(String[] args){
		String usrinput = JOptionPane.showInputDialog("Enter integers separated by commas");
		while(usrinput == null || !ProcessInput(usrinput)){
			JOptionPane.showMessageDialog(null,"Didn't Parse! Please re-enter integers");
			usrinput = JOptionPane.showInputDialog("Enter integers");
		}	
	}
	
	//checks that a string contains only ints
	static boolean CheckInt(String s){
		boolean passed = true;
		try{
			Integer.parseInt(s);
		}
		catch(Exception e){
			passed = false;
		}
		return passed;
	}
	
	
	//calculates the sum of all numbers in int array
	static int CalculateSum(int[] arr){
		int temp = 0;
		for(int i = 0; i < arr.length; i++){
			temp += arr[i];
		}
		return temp;
	}
	
	
	//
	static boolean ProcessInput(String s){
		String[] arr = s.split(",");
		int len = arr.length;
		
		//check that array contains strings with only numbers
		for(int i = 0; i < len; i++){
			if(!CheckInt(arr[i])){
				return false;
			}
		}
		
		String message = "You have entered " + s + ". Is this correct?";
		int Dialog = JOptionPane.showConfirmDialog(null, message, "Confirm", JOptionPane.YES_NO_OPTION);
		
		//if "No" option is clicked return false
		if(Dialog == 1){
			return false;
		}
		
		
		int[] arr2 = new int[len];
		for(int i = 0; i < len;i++){
			arr2[i] = Integer.parseInt(arr[i]);
		}
		
		double Avg = (double)CalculateSum(arr2)/len;
		String message2 = "The average of the numbers entered is: " + Avg;
		JOptionPane.showMessageDialog(null, message2, "Information", JOptionPane.INFORMATION_MESSAGE);
		return true;
	}
}
