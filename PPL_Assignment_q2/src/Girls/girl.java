package Girls;

public class girl {
	public String name;
	public int attractiveness;
	public int maintainance_budget;
	public int intelligent_level;
	public String relationship_status;
	public String boyfirend_name;
	public int happiness_level;
	public String choosing_criteria;
	public String girl_type;
	public int giftcost;
	public girl(String name ,int attractiveness , int maintainance_budget , int intelligence_level, String Choosing_criteria,String girl_type ){
		this.name=name;
		this.attractiveness=attractiveness;
		this.maintainance_budget=maintainance_budget;
		this.intelligent_level=intelligence_level;
		this.relationship_status="single";
		this.boyfirend_name="";
		this.happiness_level=0;
		this.choosing_criteria =Choosing_criteria;
		this.girl_type=girl_type;
	}
	
	public void set_happiness(int happiness){
		this.happiness_level=happiness;
	}
	
	public void set_boyfriend(String name){
		this.boyfirend_name=name;
	}
	public void set_commitment(){
		this.relationship_status="couple";
	}
}
