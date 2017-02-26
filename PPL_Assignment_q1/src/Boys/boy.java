package Boys;

public class boy {
	public String name;
	public int attractiveness;
	public int budget;
	public int intelligent_level;
	public String relationship_status;
	public String girlfriend_name;
	public int happiness_level;
	public int min_attraction_requirement;
	public String boy_type;
	public boy(String name ,int attractiveness , int maintainance_budget , int intelligence_level , int min_attraction_requirement , String boy_type){
		this.name=name;
		this.attractiveness=attractiveness;
		this.budget=maintainance_budget;
		this.intelligent_level=intelligence_level;
		this.relationship_status="single";
		this.girlfriend_name="";
		this.happiness_level=0;
		this.min_attraction_requirement=min_attraction_requirement;
		this.boy_type=boy_type;
	}
	public void set_happiness(int happiness){
		this.happiness_level=happiness;
	}
	
	public void set_girlfriend(String name){
		this.girlfriend_name=name;
	}
	public void set_commitment(){
		this.relationship_status="couple";
	}
}
