package Gifts_of_Vday;

public class Essential_gifts {
	public int value;
	public int cost;
	public int available;
	
	
	
	public Essential_gifts(int value, int cost) {
		// TODO Auto-generated constructor stub
		this.value=value;
		this.cost=cost;
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
