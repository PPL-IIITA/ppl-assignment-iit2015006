/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q7;

/**
 *the boy class  contains all the fields which discribe a boy 
 *it also contain various functions which are required 
 * @author Aditya
 */
public class boy {
        /* 
            >the boy class  contains all fielda which discribe a boy 
            >it also contain various functions which are required   
         */
	public String name;
	public int attractiveness;
	public int budget;
	public int intelligent_level;
	public String relationship_status;
	public String girlfriend_name;
	public int happiness_level;
	public int min_attraction_requirement;
	public String boy_type;
	public int giftcost;
        /**
         * it is the constructor , used to initialize values accordingly
         * @param name name of the boy
         * @param attractiveness attractiveness of the boy
         * @param maintainance_budget maintainance budget  of the boy
         * @param intelligence_level intelligence level  of the boy
         * @param min_attraction_requirement minimum attraction level of the boy
         * @param boy_type the type of the boy
         */
            
            
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
		this.giftcost=0;
	}
        /**
         * this is used to set happiness level of a boy
         * @param happiness represents happiness level of the boy
         */
          
         
	public void set_happiness(int happiness){
            
		this.happiness_level=happiness;
	}
	/**
         * it is used to assign name of the alloted girlfriend to a boy
         * @param name represents the name  of girlfriends
         */
        
        
	public void set_girlfriend(String name){
            
		this.girlfriend_name=name;
	}
        /**
         * it is used to set status of a boy as committed.
         */
            
            
	public void set_commitment(){
            
		this.relationship_status="couple";
	}
        /**
         * this function is used to change values upon breakup
         */
        
        
        public void break_up_boy(){
            
            this.happiness_level=0;
            this.relationship_status="single";
            this.girlfriend_name="";
        }
}
