/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * this package contains classes of the various gifts and abstract class 
 */
package Gifts_of_Vday;

/**
 * gifts is the abstract class which contains basic layout ie data member and member functions of every gift class
 * @author Aditya
 */
public abstract class gifts {
    /*
    * gifts is the abstract class which contains basic layout of every gift class
    */
    public int value;
    public int cost;
    public int available;
    /**
     * this function is used to check whether the gift of a type is currently available or not
     * @return boolean value indicating whether the gift of the type is available or not 
     */
    
    
    public abstract boolean availability();
}
