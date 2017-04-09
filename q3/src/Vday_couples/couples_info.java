/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *  the package which includes information about the couples formed on valentines day
 */
package Vday_couples;

/**
 *this class is used to describe all the couples which are formed 
 * @author Aditya
 */
import static java.lang.Math.abs;
public class couples_info {
        /*
            this class is used to describe all the couples which are fornes 
        */
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
	/**
         * this is the constructor , it is used to initialize the parameters/data members
         * @param b_name
         * @param b_happiness
         * @param b_budget
         * @param b_attract
         * @param b_intell
         * @param b_index
         * @param g_name
         * @param g_happiness
         * @param g_budget
         * @param g_attract
         * @param g_intell
         * @param g_index 
         */
            
            
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
	 /**
          * this is used to calculate the happiness level of the couple
          * @return it returns the the happiness of the couples
          */
            
            
	public int calc_happiness(){
           
		this.happiness=happiness_of_boy+happiness_of_girl;
		return happiness;
	}
        /**
         * this fucntion is used to calculate the compactibility level of a couple
         * @return it returns the compactibility of the after calculating it
         */
            
            
	public int calc_compactibility(){
            
		this.compactibility=budget_of_boy-budget_of_girl+abs(attract_boy-attract_girl)+abs(intell_boy-intell_girl);
		return compactibility;
	}
}
