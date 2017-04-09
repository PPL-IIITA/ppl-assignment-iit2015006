/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 *for taking input through csv
 * @author Aditya
 */
public class input {
                /**
                 * this function/constructor is made to take input from the csv files
                 * @param boys this array will contain all the boys from the csv file
                 * @param girls this array will contain all the girls from the csv file
                 * @param total_boys this int varaible will contain total number of boys
                 * @param total_girls  this variable will contain total number of girls
                 */
                public input(boy boys[] , girl girls[] ,int total_boys,int total_girls){
                
		
    
    
    String name;
		int attractiveness,budget,intelligent_level,min_attraction_requirement;
		String boy_type;
		String csvFile = "boys.csv";
		String line = "";
		String cvsSplitBy = ",";
		int i=0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] BB = line.split(cvsSplitBy);
                name = BB[0];
                boy_type = BB[1];
                attractiveness = Integer.parseInt(BB[2]);
                budget = Integer.parseInt(BB[3]);
                intelligent_level = Integer.parseInt(BB[4]);
                min_attraction_requirement=  Integer.parseInt(BB[5]);
                boys[i++]=new boy(name, attractiveness, budget, intelligent_level,min_attraction_requirement,boy_type);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
		total_boys=i;
		csvFile="girls_data.csv";
		i=0;
		String girl_type; 
		String choosen_criteria;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] GG = line.split(cvsSplitBy);
                name = GG[0];
                girl_type = GG[1];
                attractiveness = Integer.parseInt(GG[2]);
                budget = Integer.parseInt(GG[3]);
                intelligent_level = Integer.parseInt(GG[4]);
                choosen_criteria=  GG[5];
                girls[i++]=new girl(name, attractiveness, budget, intelligent_level,choosen_criteria,girl_type);
                //System.out.println(name+" "+girl_type+" "+attractiveness+" "+budget+" "+intelligent_level+" "+choosen_criteria);
                
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
		
		
                
       }
}
