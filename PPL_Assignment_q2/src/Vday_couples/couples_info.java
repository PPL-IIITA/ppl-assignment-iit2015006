package Vday_couples;
import static java.lang.Math.abs;
public class couples_info {
	public String boyfriend;
	public String girlfriend;
	public int happiness_of_boy;
	public int happiness_of_girl;
	public int  budget_of_boy;
	public int budget_of_girl;
	public int attract_boy;
	public int attract_girl;
	public int intell_boy;
	public int intell_girl;
	public int happiness;
	public int compactibility;
	public int index_of_boyfriend;
	public int index_of_girlfriend;
	public couples_info(String b_name , int b_happiness , int b_budget , int b_attract , int b_intell, int b_index,String g_name , int g_happiness , int g_budget , int g_attract , int g_intell,int g_index){
		this.boyfriend=b_name;
		this.happiness_of_boy=b_happiness;
		this.budget_of_boy=b_budget;
		this.attract_boy=b_attract;
		this.intell_boy=b_intell;
		this.girlfriend=g_name;
		this.happiness_of_girl=g_happiness;
		this.budget_of_girl=g_budget;
		this.attract_girl=g_attract;
		this.intell_girl=g_intell;
		this.index_of_boyfriend=b_index;
		this.index_of_girlfriend=g_index;
	}
	
	public int calc_happiness(){
		this.happiness=happiness_of_boy+happiness_of_girl;
		return happiness;
	}	
	public int calc_compactibility(){
		this.compactibility=budget_of_boy-budget_of_girl+abs(attract_boy-attract_girl)+abs(intell_boy-intell_girl);
		return compactibility;
	}
}
