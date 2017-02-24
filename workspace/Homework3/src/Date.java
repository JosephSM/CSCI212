
public class Date {
	protected int day;
	protected int month;
	protected int year;
	
	public Date(){
		this.day = 1;
		this.month = 1;
		this.year = 1950;
	}
	
	public Date(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;

	}
	
	public int getDay(){
		return this.day; 
	}
	
	public int getMonth(){
		return this.month;
	}
	
	public int getYear(){
		return this.year;
	}
	

	
}
