/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q7;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *this class is the child class which contains method for finding girlfriend by linear search 
 * @author Aditya
 */
public class find_girlfriend_array extends find_girlfriend{
    /**
     * this is the default method to find the girlfriend of a boy , it uses linear search technique 
     * @param boys this array contains list of all the boys
     * @param girls this array contains list of all the girls
     * @param couples this array contains list of all the couples
     * @param total_boys the total number of boys
     * @param total_girls the total number of of girls 
     * @param total_couples the total number of couples
     * @throws FileNotFoundException 
     */
    public void find_girlfriend_func(String boys[]  , girl girls[] ,couples_info couples[]  , int total_boys , int total_girls ,int total_couples ) throws FileNotFoundException, IOException{
        DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream("find_girlfriend_array.txt"));
        for(int i=0;i<total_boys;i++){
            int j;   
            for(j=0;j<total_couples;j++){
                    if(boys[i].equals(couples[j].boyfriend)){
                        dataoutputstream.writeBytes(boys[i]+" and "+couples[j].girlfriend+" are a couples\n");
                        System.out.println(boys[i]+" and "+couples[j].girlfriend+" are a couples\n");
                           break;
                    }
                 
            }
                if(j>=total_couples){
                     dataoutputstream.writeBytes(boys[i]+" is not currently involved in a relation ship\n");
                     System.out.println(boys[i]+" is not currently involved in a relation ship\n");
                      
                 }
        }
    }
}
