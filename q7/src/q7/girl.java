/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * the package girl contaians the class girl
 */


package q7;

/**
 * the girl class  contains all fielda which discribe a girl 
 *it also contain various functions which are required   
 * @author Aditya
 */

public class girl {
        /* 
           
         */
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
         /**
          * *it is the constructor , used to initialize values accordingly
          * @param name name of the girl 
          * @param attractiveness attractiveness level of the girl
          * @param maintainance_budget maintainance budget of the girl 
          * @param intelligence_level intelligence level of the girl 
          * @param Choosing_criteria Choosing criteria  of the girl 
          * @param girl_type the type of the girl
          */
            
            
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
	/**
         * it is used to set happiness level of a girl
         * @param happiness happiness level of the girl
         */
            
            
	public void set_happiness(int happiness){
             
		this.happiness_level=happiness;
	}
	/**
         * it is used to allot appropriate boyfriend to a boy
         * @param name name of the boyfriend
         */
            
            
	public void set_boyfriend(String name){
            
		this.boyfirend_name=name;
	}
        /**
         * it is used to set status of a girl as committed
         */
            
           
	public void set_commitment(){
             
		this.relationship_status="couple";
	}
        /**
         * this function is used to change values upon breakup
         */
            
            
        public void break_up_girl(){
            
            this.boyfirend_name="";
            this.happiness_level=0;
            this.relationship_status="single";
        }
}
