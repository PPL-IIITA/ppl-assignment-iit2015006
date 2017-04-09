/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q4;

/**
 *
 * @author Aditya
 */
import Boys.*;
import Girls.*;
import Vday_couples.*;
import Gifts_of_Vday.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.time.*;
import java.time.temporal.*;
import static java.lang.Math.pow;
import static java.lang.Math.log;
/**
 * the main class takes input from csv file , 
 * creating array of objects of boys and girls class
 * @author Aditya
 */


public class main_class_q4 {
	static int total_couples=0;
	static int total_boys=0;
	static int total_girls=0;
	static int total_Luxury_gifts=0;
	static int total_Essential_gifts=0;
	static int total_Utility_gifts=0;
        /**
         * 
         * @param args
         * @throws IOException 
         */
	public static void main(String [] args) throws IOException{
		boy[] boys= new boy[2000];
		girl[] girls=new girl[2000];
		couples_info[] couples=new couples_info[2000];
		Essential_gifts[] e_gift=new Essential_gifts[2000];
		Luxury_gifts[] l_gift=new Luxury_gifts[2000];
		Utility_gifts[] u_gift=new Utility_gifts[2000];
		/* 
		 * this section has been copied from google search result : how to take input from csv file in java
		 * 
		 */
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
                
                
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
		
		total_girls=i;
                /*
                called couple_matching function
                */
		total_couples=couple_matching(boys,girls,couples); 
		
		csvFile="essential.csv";
		i=0;
		int value;
		int cost;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] GG = line.split(cvsSplitBy);
                value = Integer.parseInt(GG[1]);
                cost = Integer.parseInt(GG[0]);
                e_gift[i++]=new Essential_gifts(value,cost);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
		total_Essential_gifts=i;
		
		csvFile="luxury.csv";
		i=0;
		int luxury_rating;
		int difficulty_in_obtaining;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] GG = line.split(cvsSplitBy);
                value = Integer.parseInt(GG[1]);
                cost = Integer.parseInt(GG[0]);
                luxury_rating= Integer.parseInt(GG[2]);
                difficulty_in_obtaining= Integer.parseInt(GG[3]);
                l_gift[i++]=new Luxury_gifts(value,cost,luxury_rating,difficulty_in_obtaining);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
		total_Luxury_gifts=i;
		
		csvFile="utility.csv";
		i=0;
		int utility_class;
		int utility_value;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] GG = line.split(cvsSplitBy);
                value = Integer.parseInt(GG[1]);
                cost = Integer.parseInt(GG[0]);
                utility_value=Integer.parseInt(GG[2]);
                utility_class=Integer.parseInt(GG[3]);
                u_gift[i++]=new Utility_gifts(value,cost,utility_value,utility_class);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
		total_Utility_gifts=i;
		interchange_gifts(boys,girls,couples,e_gift,l_gift,u_gift);
		
		k_most_compactible_and_happy(couples,7);
                couples_info temp_for_breakup=new couples_info();
                int broken_girls[]=new int[100];
                int broken_boys[]=new int[100];
                
                total_couples=temp_for_breakup.break_up(couples, total_couples, 4, girls, boys,broken_girls,broken_boys);
               
                temp_for_breakup.patch_up(couples, total_couples, 4, girls, boys, total_girls, total_boys,broken_girls,broken_boys);
                
	}
	
        
       /**
         * this function is used to interchange the gifts from boy to girl 
          *         it uses brute force algorithm to interchange  gifts
         * @param man this array represents all the boys as read from the boys.csv
         * @param women this array represents all the girls as read from girls.csv 
         * @param couples this array represents all the couples from
         * @param e_gift this array represents all the essential gifts as read from the csv file 
         * @param l_gift this array represents all the luxury gifts as read from the csv file
         * @param u_gift this array represents all the utility gifts as read from csv file
         * @throws IOException 
         */
        
	public static void interchange_gifts(boy []man , girl []women,couples_info couples[] , Essential_gifts e_gift[],Luxury_gifts l_gift[],Utility_gifts u_gift[]) throws IOException{
		
                DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream("log_gift_exchange.txt"));
		for(int i=0;i<total_couples;i++){
			
			int totalvalue=0;
			int index_of_boy=couples[i].index_of_boyfriend;
			int index_of_girl=couples[i].index_of_girlfriend;
			String boy_type=man[index_of_boy].boy_type;
			switch (boy_type){
				case "Miser":
					for(int j=0;j<total_Essential_gifts;j++){
						if(e_gift[j].availability() && women[index_of_girl].maintainance_budget >= women[index_of_girl].giftcost && man[index_of_boy].budget-man[index_of_boy].giftcost>=e_gift[j].cost ){
							man[index_of_boy].giftcost=man[index_of_boy].giftcost+e_gift[j].cost;
							women[index_of_girl].giftcost+=e_gift[j].cost;
							totalvalue+=e_gift[j].cost;
							e_gift[j].available=0;
							dataoutputstream.writeBytes("essential gift valued : "+e_gift[j].value+"  and priced : "+e_gift[j].cost+"  gifted by "+man[index_of_boy].name+" to "+women[index_of_girl].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");
						}
					}
					for(int j=0;j<total_Utility_gifts;j++){
						if(u_gift[j].availability() && women[index_of_girl].maintainance_budget >= women[index_of_girl].giftcost && man[index_of_boy].budget-man[index_of_boy].giftcost>=u_gift[j].cost ){
							man[index_of_boy].giftcost=man[index_of_boy].giftcost+u_gift[j].cost;
							women[index_of_girl].giftcost+=u_gift[j].cost;
							totalvalue+=u_gift[j].cost;
							u_gift[j].available=0;
							dataoutputstream.writeBytes("utility gift valued : "+u_gift[j].value+"  and priced : "+u_gift[j].cost+"  gifted by "+man[index_of_boy].name+" to "+women[index_of_girl].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");
						}
					}
					for(int j=0;j<total_Luxury_gifts;j++){
						if(l_gift[j].availability() && women[index_of_girl].maintainance_budget >= women[index_of_girl].giftcost && man[index_of_boy].budget-man[index_of_boy].giftcost>=l_gift[j].cost ){
							man[index_of_boy].giftcost=man[index_of_boy].giftcost+l_gift[j].cost;
							women[index_of_girl].giftcost+=l_gift[j].cost;
							totalvalue+=l_gift[j].cost;
							l_gift[j].available=0;
							dataoutputstream.writeBytes("luxury gift valued : "+l_gift[j].value+"  and priced : "+l_gift[j].cost+"  gifted by "+man[index_of_boy].name+" to "+women[index_of_girl].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");
						}
					}
					String girl_type=women[index_of_girl].girl_type;
					switch(girl_type){
						case "choosy":
							women[index_of_girl].happiness_level=(int)log(women[index_of_girl].giftcost);
							break;
						case "normal":
							women[index_of_girl].happiness_level=women[index_of_girl].giftcost+totalvalue;
							break;
						case "desperate":
							women[index_of_girl].happiness_level=(int)pow(1,women[index_of_girl].giftcost);
							break;
					}
					man[index_of_boy].happiness_level=man[index_of_boy].budget-man[index_of_boy].giftcost;
					break;
				case "Geek":
					for(int j=0;j<total_Essential_gifts;j++){
						if(e_gift[j].availability() && women[index_of_girl].maintainance_budget >= women[index_of_girl].giftcost && man[index_of_boy].budget-man[index_of_boy].giftcost>=e_gift[j].cost ){
							man[index_of_boy].giftcost=man[index_of_boy].giftcost+e_gift[j].cost;
							women[index_of_girl].giftcost+=e_gift[j].cost;
							totalvalue+=e_gift[j].cost;
							e_gift[j].available=0;
							dataoutputstream.writeBytes("essential gift valued : "+e_gift[j].value+"  and priced : "+e_gift[j].cost+"  gifted by "+man[index_of_boy].name+" to "+women[index_of_girl].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");
						}
					}
					for(int j=0;j<total_Utility_gifts;j++){
						if(u_gift[j].availability() && women[index_of_girl].maintainance_budget >= women[index_of_girl].giftcost && man[index_of_boy].budget-man[index_of_boy].giftcost>=u_gift[j].cost ){
							man[index_of_boy].giftcost=man[index_of_boy].giftcost+u_gift[j].cost;
							women[index_of_girl].giftcost+=u_gift[j].cost;
							totalvalue+=u_gift[j].cost;
							u_gift[j].available=0;
							dataoutputstream.writeBytes("utility gift valued : "+u_gift[j].value+"  and priced : "+u_gift[j].cost+"  gifted by "+man[index_of_boy].name+" to "+women[index_of_girl].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");

						}
					}
					for(int j=0;j<total_Luxury_gifts;j++){
						if(l_gift[j].availability() && women[index_of_girl].maintainance_budget >= women[index_of_girl].giftcost && man[index_of_boy].budget-man[index_of_boy].giftcost>=l_gift[j].cost ){
							man[index_of_boy].giftcost=man[index_of_boy].giftcost+l_gift[j].cost;
							women[index_of_girl].giftcost+=l_gift[j].cost;
							totalvalue+=l_gift[j].cost;
							l_gift[j].available=0;
							dataoutputstream.writeBytes("luxury gift valued : "+l_gift[j].value+"  and priced : "+l_gift[j].cost+"  gifted by "+man[index_of_boy].name+" to "+women[index_of_girl].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");

						}
					}
					int remaining_budget=man[index_of_boy].budget-man[index_of_boy].giftcost;
					for(int j=0;j<total_Luxury_gifts;j++){
						if(l_gift[j].availability() && man[index_of_boy].budget-man[index_of_boy].giftcost>=l_gift[j].cost ){
							man[index_of_boy].giftcost=man[index_of_boy].giftcost+l_gift[j].cost;
							women[index_of_girl].giftcost+=l_gift[j].cost;
							totalvalue+=l_gift[j].cost;
							l_gift[j].available=0;
							dataoutputstream.writeBytes("luxury gift valued : "+l_gift[j].value+"  and priced : "+l_gift[j].cost+"  gifted by "+man[index_of_boy].name+" to "+women[index_of_girl].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");

							break;
						}
					}
					String girl_type1=women[index_of_girl].girl_type;
					switch(girl_type1){
						case "choosy":
							women[index_of_girl].happiness_level=(int)log(women[index_of_girl].giftcost);
							break;
						case "normal":
							women[index_of_girl].happiness_level=women[index_of_girl].giftcost+totalvalue;
							break;
						case "desperate":
							women[index_of_girl].happiness_level=(int)pow(1,women[index_of_girl].giftcost);
							break;
					}
					man[index_of_boy].happiness_level=women[index_of_girl].intelligent_level;
					break;
				case "Generous":
					for(int j=0;j<total_Essential_gifts;j++){
						if(e_gift[j].availability() && man[index_of_boy].budget-man[index_of_boy].giftcost>=e_gift[j].cost ){
							man[index_of_boy].giftcost=man[index_of_boy].giftcost+e_gift[j].cost;
							women[index_of_girl].giftcost+=e_gift[j].cost;
							totalvalue+=e_gift[j].cost;
							e_gift[j].available=0;
							dataoutputstream.writeBytes("essential gift valued : "+e_gift[j].value+"  and priced : "+e_gift[j].cost+"  gifted by "+man[index_of_boy].name+" to "+women[index_of_girl].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");

						}
					}
					for(int j=0;j<total_Utility_gifts;j++){
						if(u_gift[j].availability() && man[index_of_boy].budget-man[index_of_boy].giftcost>=u_gift[j].cost ){
							man[index_of_boy].giftcost=man[index_of_boy].giftcost+u_gift[j].cost;
							women[index_of_girl].giftcost+=u_gift[j].cost;
							totalvalue+=u_gift[j].cost;
							u_gift[j].available=0;
							dataoutputstream.writeBytes("utility gift valued : "+u_gift[j].value+"  and priced : "+u_gift[j].cost+"  gifted by "+man[index_of_boy].name+" to "+women[index_of_girl].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");

						}
					}
					for(int j=0;j<total_Luxury_gifts;j++){
						if(l_gift[j].availability() && man[index_of_boy].budget-man[index_of_boy].giftcost>=l_gift[j].cost ){
							man[index_of_boy].giftcost=man[index_of_boy].giftcost+l_gift[j].cost;
							women[index_of_girl].giftcost+=l_gift[j].cost;
							totalvalue+=l_gift[j].cost;
							l_gift[j].available=0;
							dataoutputstream.writeBytes("luxury gift valued : "+l_gift[j].value+"  and priced : "+l_gift[j].cost+"  gifted by "+man[index_of_boy].name+" to "+women[index_of_girl].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");

						}
					}
					String girl_type2=women[index_of_girl].girl_type;
					switch(girl_type2){
						case "choosy":
							women[index_of_girl].happiness_level=(int)log(women[index_of_girl].giftcost);
							break;
						case "normal":
							women[index_of_girl].happiness_level=women[index_of_girl].giftcost+totalvalue;
							break;
						case "desperate":
							women[index_of_girl].happiness_level=(int)pow(1,women[index_of_girl].giftcost);
							break;
					}
					man[index_of_boy].happiness_level=women[index_of_girl].happiness_level;
					break;
			}
		
			couples[i].happiness=man[index_of_boy].happiness_level+women[index_of_girl].happiness_level;
		}
	}
	
	/**
         * this function is used to interchange the gifts from boy to girl 
          *         it uses brute force algorithm to interchange  gifts
         * @param man this array represents all the boys as read from the boys.csv
         * @param women this array represents all the girls as read from girls.csv 
         * @param couples this array represents all the couples from
         * @throws IOException 
         */
	
	public static int couple_matching(boy []man , girl []women,couples_info couples[]) throws IOException{
		
                DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream("log_couple_data.txt"));
		int i=0;
		int j=0;
		int id_of_couple=0;
		for(;i<total_girls;i++){
			int max_index = -1;
			int max_value=-1;
			j=0;
			for(;j<total_boys;j++){
				
				if(man[j].relationship_status.equals("single") && man[j].budget>=women[i].maintainance_budget && man[j].min_attraction_requirement<=women[i].attractiveness ){
					String girl_criteria=women[i].choosing_criteria;
					
					switch(girl_criteria){
						case "attractive":
							if(man[j].attractiveness > max_value){
								max_index=j;
								max_value=man[j].attractiveness;
							}
							break;
						case "rich":
							if(man[j].budget > max_value){
								max_index=j;
								max_value=man[j].budget;
							}
							break;
						case "intelligent":
							if(man[j].intelligent_level > max_value){
								max_index=j;
								max_value=man[j].intelligent_level;
							}
							break;
					}
				}
			}
			
			if(max_index>=0){
				man[max_index].set_girlfriend(women[i].name);
				man[max_index].set_commitment();
				women[i].set_boyfriend(man[max_index].name);
				women[i].set_commitment();
				
				couples[id_of_couple]= new couples_info(man[max_index].name ,man[max_index].happiness_level,man[max_index].budget,man[max_index].attractiveness,man[max_index].intelligent_level,max_index,women[i].name ,women[i].happiness_level,women[i].maintainance_budget,women[i].attractiveness,women[i].intelligent_level,i);
				couples[id_of_couple].calc_compactibility();
				couples[id_of_couple].calc_happiness();
				
				dataoutputstream.writeBytes(women[i].name+" got committed to "+man[max_index].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");
				id_of_couple++;
				
			}
			
		}
		return id_of_couple;
	}
	

	/**
         * a simple swap function for object of class couples_info
         * @param couple1 this represents the an object of the couple class
         * @param couple2 this represents the other object of the couple class
         */
	
	static void swap(couples_info couple1 , couples_info couple2){
            
		String boyfriend=couple1.boyfriend;
		String girlfriend=couple1.girlfriend;
		int happiness_of_boy=couple1.happiness_of_boy;
		 int happiness_of_girl=couple1.happiness_of_girl;
		 int  budget_of_boy=couple1.budget_of_boy;
		 int budget_of_girl=couple1.budget_of_girl;
		 int attract_boy=couple1.attract_boy;
		 int attract_girl=couple1.attract_girl;
		 int intell_boy=couple1.intell_boy;
		 int intell_girl=couple1.attract_girl;
		 int happiness=couple1.happiness;
		 int compactibility=couple1.compactibility;
		 int index_of_boyfriend=couple1.index_of_boyfriend;
		 int index_of_girlfriend=couple1.index_of_girlfriend;
	
		  couple1.boyfriend=couple2.boyfriend;
		  couple1.girlfriend=couple2.girlfriend;
			 couple1.happiness_of_boy=couple2.happiness_of_boy;
			 couple1.happiness_of_girl=couple2.happiness_of_girl;
			  couple1.budget_of_boy=couple2.budget_of_boy;
			  couple1.budget_of_girl=couple2.budget_of_girl;
			  couple1.attract_boy=couple2.attract_boy;
			  couple1.attract_girl=couple2.attract_girl;
			  couple1.intell_boy=couple2.intell_boy;
			  couple1.intell_girl=couple2.attract_girl;
			  couple1.happiness=couple2.happiness;
			  couple1.compactibility=couple2.compactibility;
			  couple1.index_of_boyfriend=couple2.index_of_boyfriend;
			  couple1.index_of_girlfriend=couple2.index_of_girlfriend;
			  
			  
			  couple2.boyfriend=boyfriend;
			  couple2.girlfriend=girlfriend;
				 couple2.happiness_of_boy=happiness_of_boy;
				 couple2.happiness_of_girl=happiness_of_girl;
				  couple2.budget_of_boy=budget_of_boy;
				  couple2.budget_of_girl=budget_of_girl;
				  couple2.attract_boy=attract_boy;
				  couple2.attract_girl=attract_girl;
				  couple2.intell_boy=intell_boy;
				  couple2.intell_girl=attract_girl;
				  couple2.happiness=happiness;
				  couple2.compactibility=compactibility;
				  couple2.index_of_boyfriend=index_of_boyfriend;
				  couple2.index_of_girlfriend=index_of_girlfriend;
	}
	/**
         * this function calculates the  k most compactible couples and happy couples displays answer in log file
         * @param couple this represents an object of the couple class
         * @param k this represents the number of most happy and compactible couple
         * @throws IOException 
         */
	static void k_most_compactible_and_happy(couples_info couple[],int k) throws IOException{
            
		DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream("k_most_compactible.txt"));
		for(int i=0;i<total_couples;i++){
			for(int j=0;j<total_couples-1;j++){
				if(couple[j].compactibility <couple[j+1].compactibility){
					swap(couple[j],couple[j+1]);
				}
			}
		}
		for(int i=0;i<k;i++){
			dataoutputstream.writeBytes(i+" most compactible couple are : "+couple[i].boyfriend+"  and  "+couple[i].girlfriend+" "+couple[i].compactibility+"\n");
		}
		
		dataoutputstream = new DataOutputStream(new FileOutputStream("k_most_happy.txt"));
		for(int i=0;i<total_couples;i++){
			for(int j=0;j<total_couples-1;j++){
				if(couple[j].happiness < couple[j+1].happiness){
					swap(couple[j],couple[j+1]);
				}
			}
		}
		for(int i=0;i<total_couples;i++){
			dataoutputstream.writeBytes(i+" most happy couple are : "+couple[i].boyfriend+"  and  "+couple[i].girlfriend+"  "+couple[i].happiness+"\n");
		}
	
	}
	
	
	
	
}

