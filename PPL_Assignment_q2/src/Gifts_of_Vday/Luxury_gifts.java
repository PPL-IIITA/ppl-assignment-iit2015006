package Gifts_of_Vday;

public class Luxury_gifts {
	public int value;
	public int cost;
	public int luxury_rating;
	public int difficulty_in_obtaining;  /// dificulty to obtain
	public int available;
	
	public Luxury_gifts(int value, int cost , int luxury_rating , int difficulty_in_obtaining ){
		this.value=value;
		this.cost=cost;
		this.available=1;
		this.luxury_rating=luxury_rating;
		this.difficulty_in_obtaining=difficulty_in_obtaining;
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
