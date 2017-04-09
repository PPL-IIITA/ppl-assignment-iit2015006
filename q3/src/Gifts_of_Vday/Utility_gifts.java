/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gifts_of_Vday;

/**
 * Utility_gifts class contains data of all essential gifts  
 * @author Aditya
 */

public class Utility_gifts extends gifts {
        /*
         *
         */
	public int value;
	public int cost;
	public int utility_value;
	public int utility_class;
	public int available;
	/**
         * this the constructor of the utility gift class and it initializes values
         * @param value the value of the gift
         * @param cost the cost of the gift
         * @param utility_value the utility value of the gift
         * @param utility_class the utility class of the gift
         */
                 
                
	public Utility_gifts(int value, int cost ,int utility_value,int utility_class){
                
		this.value=value;
		this.cost=cost;
		this.utility_class=utility_class;
		this.utility_value=utility_value;
		this.available=1;
	}
	/**
         * this function is used to check whether the gift of utility type is currently available or not
         * @return boolean value indicating whether the gift of the type is available or not
         */
                 
                
	public boolean availability(){
                
		if(available==1){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
}
    

