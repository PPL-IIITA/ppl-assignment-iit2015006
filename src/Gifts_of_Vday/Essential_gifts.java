/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gifts_of_Vday;

/**
 *essential_gifts class contains data of all essential gifts  
 * @author Aditya
 */
public class Essential_gifts extends gifts {
        /*
         * 
         */
	public int value;
	public int cost;
	public int available;
	
	/**
         * this the constructor of the essential gift class and it initializes values
         * @param value it represents the value of the gift
         * @param cost it represents cost of the gift
         */
                 
                
	
	public Essential_gifts(int value, int cost) {
		// TODO Auto-generated constructor stub
                
		this.value=value;
		this.cost=cost;
		this.available=1;
	}
        /**
         * this function is used to check whether the gift of essential type is currently available or not
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
