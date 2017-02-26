import Boys.*;
import Girls.*;
import Vday_couples.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.time.*;
import java.time.temporal.*;

public class main_class {
	static int total_couples=0;
	static int total_boys=0;
	static int total_girls=0;
	public static void main(String [] args) throws IOException{
		boy[] boys= new boy[2000];
		girl[] girls=new girl[2000];
		couples_info[] couples=new couples_info[2000];
		
		
		
		/* 
		 * this section has been copied from google search result
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
		//System.out.println("hello\n");
		total_couples=couple_matching(boys,girls,couples);
		
		
		
	}
	
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
				//System.out.println(man.length);
				//System.out.println(man[j].name+" "+man[j].relationship_status+" "+ man[j].budget+" "+women[i].maintainance_budget+" "+women[i].name+" "+man[j].min_attraction_requirement+" "+women[i].attractiveness);
				if(man[j].relationship_status.equals("single") && man[j].budget>=women[i].maintainance_budget && man[j].min_attraction_requirement<=women[i].attractiveness ){
					String girl_criteria=women[i].choosing_criteria;
					//System.out.println("enterred inside");
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
			//System.out.println(women[i].name+" "+max_index+" ");
			
			if(max_index>=0){
				man[max_index].set_girlfriend(women[i].name);
				man[max_index].set_commitment();
				women[i].set_boyfriend(man[max_index].name);
				women[i].set_commitment();
				couples[id_of_couple]= new couples_info(man[max_index].name ,man[max_index].happiness_level,man[max_index].budget,man[max_index].attractiveness,man[max_index].intelligent_level,women[i].name ,women[i].happiness_level,women[i].maintainance_budget,women[i].attractiveness,women[i].intelligent_level);
				couples[id_of_couple].calc_compactibility();
				couples[id_of_couple].calc_happiness();
				//System.out.println(women[i].name+" got committed to "+man[max_index].name+" "+man[max_index].budget+" "+women[i].maintainance_budget+"\n\n");
				
				dataoutputstream.writeBytes(women[i].name+" got committed to "+man[max_index].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");
				id_of_couple++;
			}
			
			//System.out.println();
		}
		return id_of_couple;
	}
	
	
}
