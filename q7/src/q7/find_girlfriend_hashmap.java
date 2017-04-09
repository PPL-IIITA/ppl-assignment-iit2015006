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
import java.util.HashMap;
import java.util.Map;

/**
 *this class is the child class which contains method for finding girlfriend by hashing
 * @author Aditya
 */
public class find_girlfriend_hashmap extends find_girlfriend{
    
    /**
     * this is the default method to find the girlfriend of a boy , it uses hashmap and map class and hashing technique 
     * @param boys this array contains list of all the boys
     * @param girls this array contains list of all the girls
     * @param couples this array contains list of all the couples
     * @param total_boys the total number of boys
     * @param total_girls the total number of of girls 
     * @param total_couples the total number of couples
     * @throws FileNotFoundException 
     */
    /*public void sort(){
    }*/
    public void find_girlfriend_func(String boys[]  , girl girls[] ,couples_info couples[]  , int total_boys , int total_girls ,int total_couples ) throws FileNotFoundException, IOException{
        DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream("find_girlfriend_hashmap.txt"));
        Map<String,String> map=new HashMap<String,String>();
        for(int i=0;i<total_couples;i++){
            map.put(couples[i].boyfriend , couples[i].girlfriend);
        }
        for(int j=0;j<total_boys;j++){
            if(map.get(boys[j])!=null){
                dataoutputstream.writeBytes(boys[j]+" and "+map.get(boys[j])+" are a couples\n");
                System.out.println(boys[j]+" and "+map.get(boys[j])+" are a couples");
            }else{
                dataoutputstream.writeBytes(boys[j]+" is not currently involved in a relation ship\n");
                     System.out.println(boys[j]+" is not currently involved in a relation ship");
            }
        }
    }
}
              
        


