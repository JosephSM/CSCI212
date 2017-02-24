
public class Reverse {
	public static void main(String args[]){
		int count = 0;

		for(int i = 0; i < 10; i++){

		     count = 1;

		}
		
		System.out.println(count);
		System.out.println(11.1%3.7);
		System.out.println(reverseNum3(12345));
	}
	
	
	static int reverseNum1(int num){
		if(num < 10){
			return num;
		}
		int save = num;
		int currentNumber = num%10;
		while (save >= 10)
		{
			currentNumber *= 10; //This is done here to ensure that you multiply 1 less digit
			save /= 10;
		}
		
		return currentNumber + reverseNum1(num/10);
	}
	
	static int reverseNum2(int num){
		if (num < 10){
			return num;
		}
		
		int currentNum = num;
		int myNum = num%10;
		while(currentNum >= 10){
			myNum *= 10;
			currentNum /= 10;
		}
		return myNum + reverseNum2(num/10);
	}
	
	
	
	
	
	
	static int reverseNum3(int num){
		return num;
	}

	
	
}
