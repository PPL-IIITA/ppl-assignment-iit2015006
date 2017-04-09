/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gifts_of_Vday;

/**
 *luxury_gifts class contains data of all luxury gifts 
 * @author Aditya
 */
public class Luxury_gifts extends gifts {
        /*
         *  
         */
	public int value;
	public int cost;
	public int luxury_rating;
	public int difficulty_in_obtaining;  /// dificulty to obtain
	public int available;
	/**
         * this the constructor of the luxury gift class and it initializes values
         * @param value the value of the gift
         * @param cost the cost of the gift
         * @param luxury_rating the rating of the gift
         * @param difficulty_in_obtaining it represents the the difficulty in obtaining the gift
         */
                 
                
	public Luxury_gifts(int value, int cost , int luxury_rating , int difficulty_in_obtaining ){
                
		this.value=value;
		this.cost=cost;
		this.available=1;
		this.luxury_rating=luxury_rating;
		this.difficulty_in_obtaining=difficulty_in_obtaining;
	}
	/**
         * this function is used to check whether the gift of luxury type is currently available or not
         * @return boolean value indicating whether the gift is available or not 
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
