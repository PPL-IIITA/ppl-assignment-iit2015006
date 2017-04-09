/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q7;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 *the main class
 * @author Aditya
 */
public class Q7 {
    static int total_couples=0;
	static int total_boys=0;
	static int total_girls=0;
        static int total_input_boys=0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        boy[] boys=new boy[2000];
	girl[] girls=new girl[2000];
        String[] input_boys=new String[2000];
        couples_info[] couples=new couples_info[2000];
        input input1=new input(boys,girls ,total_boys,total_girls);
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
            total_girls=i;       
        }catch (IOException e) {
            e.printStackTrace();
        }
       
		csvFile="input_data.csv";
		i=0;
		String boyname; 
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] GG = line.split(cvsSplitBy);
                name = GG[0];
                
                input_boys[i++]=new String(name);
                //System.out.println(name+" "+girl_type+" "+attractiveness+" "+budget+" "+intelligent_level+" "+choosen_criteria);
                
            }
            total_input_boys=i;       
        }catch (IOException e) {
            e.printStackTrace();
        }
        total_couples=couple_matching(boys,girls,couples);
        find_girlfriend obj;
        System.out.println("*********************************** Welcome to tri-girlfriend finder *******************************\n");
        System.out.println("there are three algorithms to find girlfriend of a boy \n"+"1)Linear Search (array)\n"+"2)Binary Search\n"+"3)Hashing\n");
        System.out.println("enter your choice(1/2/3)");
        int choice;
        Scanner sc=new Scanner(System.in);
        choice=sc.nextInt();
        if(choice ==1){
            obj=new find_girlfriend_array();
            obj.find_girlfriend_func(input_boys, girls, couples, total_input_boys, total_girls, total_couples);
        }else if(choice == 2){
            obj=new find_girlfriend_sorted_array();
            obj.find_girlfriend_func(input_boys, girls, couples, total_input_boys, total_girls, total_couples);
        }else if(choice == 3){
            obj=new find_girlfriend_hashmap();
            obj.find_girlfriend_func(input_boys, girls, couples, total_input_boys, total_girls, total_couples);
        }else{
            obj=new find_girlfriend();
            obj.find_girlfriend_func(input_boys, girls, couples, total_input_boys, total_girls, total_couples);
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
}
