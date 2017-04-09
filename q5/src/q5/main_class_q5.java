/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q5;
import Boys.boy;

import Girls.girl;
import Vday_couples.couples_info;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
/**
 * the main class takes input from csv file , 
 * creating array of objects of boys and girls class
 * @author Aditya
 */

public class main_class_q5 {
	static int total_couples=0;
	static int total_boys=0;
	static int total_girls=0;
	static int total_Luxury_gifts=0;
	static int total_Essential_gifts=0;
	static int total_Utility_gifts=0;
	public static void main(String [] args) throws IOException{
		boy[] boys= new boy[2000];
		girl[] girls=new girl[2000];
		couples_info[] couples=new couples_info[2000];
		
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
                //System.out.println(name+" "+girl_type+" "+attractiveness+" "+budget+" "+intelligent_level+" "+choosen_criteria);
                
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
		
		total_girls=i;
                /*
                called couple_matching function
                */
                System.out.println("***********************-------------------------------------------****************************");
                System.out.println("Couples can be formed in two ways \n"+"1) all the girls selsect the appropriate boy\n"+"2)a girl selects a boy then a boy selects a girl and so on\n"+"enter your choice(1/2)");
                Scanner sc=new Scanner(System.in);
                int choice=sc.nextInt();
                if(choice==2){
                    sort_boys(boys , total_boys);
                    sort_girl(girls, total_girls);
                    total_couples=couple_matching1(boys,girls,couples,total_boys,total_girls,0); 
                }
                else{
                    total_couples=couple_matching(boys, girls, couples);
                }
                
                
                
                
                
	}
    /**
         * this function is used to match couples and form couples according to each individuals need and constraints.
         * in it a girl selects a boy then a boy selects a girl and so on..
         * @param man this array represents all the boys as read from the boys.csv
         * @param women this array represents all the girls as read from girls.csv 
         * @param couples this array represents all the couples from
         * @return the number of couples matched
         * @throws IOException 
         */    
    public static int couple_matching1(boy []man , girl []women,couples_info couples[] , int total_boys , int total_girls , int total_couples) throws IOException{
		/*
            this is used to match couples and form couples according to each individuals need as given in the question , in this part first a girl gets to choose then a boy and so on
            */
                DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream("log_couple_data.txt"));
		int i=0;
		int j=0;
		int id_of_couple=0;
                while(i<total_girls && j<total_boys){
                    
                    if(total_couples%2 == 0){
                        if(women[i].relationship_status.equals("couple")){
                            i++;
                            continue;
                        }
                         int max_index=-1;
                        int max_value=-1;
                        int kl=0;
			for(;kl<total_boys;kl++){
				
				if(man[kl].relationship_status.equals("single") && man[kl].budget>=women[i].maintainance_budget && man[kl].min_attraction_requirement<=women[i].attractiveness ){
					String girl_criteria=women[i].choosing_criteria;
					switch(girl_criteria){
						case "attractive":
							if(man[kl].attractiveness > max_value){
								max_index=kl;
								max_value=man[kl].attractiveness;
							}
							break;
						case "rich":
							if(man[kl].budget > max_value){
								max_index=kl;
								max_value=man[kl].budget;
							}
							break;
						case "intelligent":
							if(man[kl].intelligent_level > max_value){
								max_index=kl;
								max_value=man[kl].intelligent_level;
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
				
                                    couples[total_couples]= new couples_info(man[max_index].name ,man[max_index].happiness_level,man[max_index].budget,man[max_index].attractiveness,man[max_index].intelligent_level,max_index,women[i].name ,women[i].happiness_level,women[i].maintainance_budget,women[i].attractiveness,women[i].intelligent_level,i);
                                    couples[total_couples].calc_compactibility();
                                    couples[total_couples].calc_happiness();
				
                                    dataoutputstream.writeBytes(women[i].name+" got committed to "+man[max_index].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");
                                    total_couples++;
				
                            }
                            i++;
                    }else{
                        if(man[j].relationship_status.equals("couple")){
                            j++;
                            continue;
                        }
                        
                        int kl=0;
			for(;kl<total_girls;kl++){
				//System.out.println(man.length);
				//System.out.println(man[j].name+" "+man[j].relationship_status+" "+ man[j].budget+" "+women[i].maintainance_budget+" "+women[i].name);
				if(women[kl].relationship_status.equals("single") && man[j].budget>=women[kl].maintainance_budget ){
					women[kl].set_boyfriend(man[j].name);
                    			women[kl].set_commitment();
                                        man[j].set_girlfriend(women[kl].name);
                                        man[j].set_commitment();
                                        couples[total_couples]= new couples_info(man[j].name ,man[j].happiness_level,man[j].budget,man[j].attractiveness,man[j].intelligent_level,j,women[kl].name ,women[kl].happiness_level,women[kl].maintainance_budget,women[kl].attractiveness,women[kl].intelligent_level,kl);
                                        couples[total_couples].calc_compactibility();
                                        couples[total_couples].calc_happiness();
				
                                        dataoutputstream.writeBytes(women[kl].name+" got committed to "+man[j].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");
                                        total_couples++;
                                        break;
                            	}
                            }
                        j++;
                     
            }
	}
        return total_couples;
    }
    /**
         * a simple swap function for object of class couples_info
         * @param boys this array represents all boys
         * @param total_boys this represents total number of boys
         */
    public static  void sort_boys(boy boys[] , int total_boys){
        /*
        a utility sort function used to sort the boys input array according to decreasing order of attractiveness  
        */
         for(int i=0;i<total_boys;i++){
			for(int j=0;j<total_boys-1;j++){
				if(boys[j].attractiveness < boys[j+1].attractiveness){
					swap(boys[j],boys[j+1]);
				}
			}
		}
    }
    /**
     * a utility function to sort the girl input array according to maintainance budget 
     * @param girls it is the array which contains details about all girls as read from the csv file 
     * @param total_girls  the total number number of girls in csv file
     */
    public static  void sort_girl(girl girls[] , int total_girls){
        /*
            a utility function to sort the girl input array according to maintainance budget
        */
        for(int i=0;i<total_girls;i++){
			for(int j=0;j<total_girls-1;j++){
				if(girls[j].maintainance_budget > girls[j+1].maintainance_budget){
					swap(girls[j],girls[j+1]);
				}
			}
		}
    }
    /**
         * a simple swap function for object of class girl
         * @param girl1 object of girl class
         * @param girl2 object of girl class
         */
    static void swap(girl girl1 , girl girl2){
            /*
            a simple swap function for object of class girl
            */
        String name=girl1.name;
	int attractiveness=girl1.attractiveness;
	int maintainance_budget=girl1.maintainance_budget;
	int intelligent_level=girl1.intelligent_level;
	String relationship_status=girl1.relationship_status;
	String boyfirend_name=girl1.boyfirend_name;
	int happiness_level=girl1.happiness_level;
	String choosing_criteria=girl1.choosing_criteria;
	String girl_type=girl1.girl_type;
	int giftcost=girl1.giftcost;
        
        girl1.attractiveness=girl2.attractiveness;
        girl1.maintainance_budget=girl2.maintainance_budget;
        girl1.boyfirend_name=girl2.boyfirend_name;
        girl1.choosing_criteria=girl2.choosing_criteria;
        girl1.giftcost=girl2.giftcost;
        girl1.girl_type=girl2.girl_type;
        girl1.happiness_level=girl2.happiness_level;
        girl1.intelligent_level=girl2.intelligent_level;
        girl1.name=girl2.name;
        girl1.relationship_status=girl2.relationship_status;
        
        girl2.attractiveness=attractiveness;
        girl2.maintainance_budget=maintainance_budget;
        girl2.boyfirend_name=boyfirend_name;
        girl2.choosing_criteria=choosing_criteria;
        girl2.giftcost=giftcost;
        girl2.girl_type=girl_type;
        girl2.happiness_level=happiness_level;
        girl2.intelligent_level=intelligent_level;
        girl2.name=name;
        girl2.relationship_status=relationship_status;
	}
    /**
     * a simple swap function to swap objects of boys class 
     * @param boy1 object of boy class
     * @param boy2 object of boy class 
     */
    static void swap(boy boy1 , boy boy2){
        /*
        a simple swap function to swap objects of boys class 
        */
        String name=boy1.name;
	int attractiveness=boy1.attractiveness;
	int budget=boy1.budget;
	int intelligent_level=boy1.intelligent_level;
	String relationship_status=boy1.relationship_status;
	String girlfriend_name=boy1.girlfriend_name;
	int happiness_level=boy1.happiness_level;
	int min_attraction_requirement=boy1.min_attraction_requirement;
        String boy_type=boy1.boy_type;
	int giftcost=boy1.giftcost;
        
        boy1.attractiveness=boy2.attractiveness;
        boy1.boy_type=boy2.boy_type;
        boy1.budget=boy2.budget;
                boy1.giftcost=boy2.giftcost;
                boy1.girlfriend_name=boy2.girlfriend_name;
                boy1.happiness_level=boy2.happiness_level;
                boy1.intelligent_level=boy2.intelligent_level;
                boy1.min_attraction_requirement=boy2.min_attraction_requirement;
                boy1.name=boy2.name;
                boy1.relationship_status=boy2.relationship_status;
        
        boy2.attractiveness=boy2.attractiveness;
        boy2.boy_type=boy_type;
        boy2.budget=budget;
                boy2.giftcost=giftcost;
                boy2.girlfriend_name=girlfriend_name;
                boy2.happiness_level=happiness_level;
                boy2.intelligent_level=intelligent_level;
                boy2.min_attraction_requirement=min_attraction_requirement;
                boy2.name=name;
                boy2.relationship_status=relationship_status;
                
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
    
}

