package Gifts_of_Vday;

public class Utility_gifts {
	public int value;
	public int cost;
	public int utility_value;
	public int utility_class;
	public int available;
	
	public Utility_gifts(int value, int cost ,int utility_value,int utility_class){
		this.value=value;
		this.cost=cost;
		this.utility_class=utility_class;
		this.utility_value=utility_value;
		this.available=1;
	}
	
	public boolean availability(){
		if(available==1){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
}
