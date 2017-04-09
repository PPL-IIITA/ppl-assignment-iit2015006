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
 *this class is the child class which contains method for finding girlfriend by binary search
 * @author Aditya
 */
public class find_girlfriend_sorted_array extends find_girlfriend {
    
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
    /*public void sort(){
    }*/
    public void find_girlfriend_func(String boys[]  , girl girls[] ,couples_info couples[]  , int total_boys , int total_girls ,int total_couples ) throws FileNotFoundException, IOException{
        DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream("find_girlfriend_bsearch.txt"));
        couples_info temp[]=new couples_info[total_couples];
        for(int k=0;k<total_couples;k++){
            temp[k]=couples[k];
        }
        sort_couples(couples , total_couples);
        /*for(int yo=0;yo<total_couples;yo++){
            System.out.println(couples[yo].boyfriend);
        }*/
        for(int i=0;i<total_boys;i++){
               int high=total_couples-1;
               int low=0;
               int mid=(high+low)/2;
               int idx=-1;
               //System.out.println(boys[i]+ "    ***********************************"
               //        + "");
               while(low<high){
                   
                   mid=(high+low)/2;
                   //System.out.println(couples[mid].boyfriend);
                   if(couples[mid].boyfriend.equals(boys[i])){
                       idx=mid;
                       break;
                   }
                   if(couples[mid].boyfriend.compareTo(boys[i])>0){
                       high=mid-1;
                   }else{
                       low=mid+1;
                   }
               }
               if(couples[low].boyfriend.equals(boys[i])){
                   dataoutputstream.writeBytes(boys[i]+" and "+couples[low].girlfriend+" are a couples\n");
                    System.out.println(boys[i]+" and "+couples[low].girlfriend+" are a couples");
               }
               else if(idx==-1){
                   dataoutputstream.writeBytes(boys[i]+" is not currently involved in a relation ship\n");
                     System.out.println(boys[i]+" is not currently involved in a relation ship");
               }else{
                   dataoutputstream.writeBytes(boys[i]+" and "+couples[idx].girlfriend+" are a couples\n");
                    System.out.println(boys[i]+" and "+couples[idx].girlfriend+" are a couples");
               }
            }
        
        }
    /**
     * the sort function sorts the couples according to boyfriend name
     * @param couples this array contains all the couples formed
     * @param total_couples the total number of couples
     */
     public void sort_couples(couples_info couples[] , int total_couples){
        for(int i=0;i<total_couples;i++){
            for(int j=0;j<total_couples;j++){
                if(couples[j].boyfriend.compareTo(couples[i].boyfriend)>0){
                    couples_info temp;
                    temp=couples[i];
                    couples[i]=couples[j];
                    couples[j]=temp;
                }
            }
        }
    }
    }
   


