/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *  the package which includes information about the couples formed on valentines day
 */
package Vday_couples;

/**
 *this class is used to describe all the couples which are formed 
 * @author Aditya
 */
import static java.lang.Math.abs;
import Girls.girl;
import Boys.boy;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class couples_info {
        /*
            
        */
	public String boyfriend;
	public String girlfriend;
	public int happiness_of_boy;
	public int happiness_of_girl;
	public int  budget_of_boy;
	public int budget_of_girl;
	public int attract_boy;
	public int attract_girl;
	public int intell_boy;
	public int intell_girl;
	public int happiness;
	public int compactibility;
	public int index_of_boyfriend;
	public int index_of_girlfriend;
        public couples_info(){
            
        }
        /**
         * this is the constructor , it is used to initialize the parameters/data members
         * @param b_name name of the boy relationship
         * @param b_happiness happiness level of the boy
         * @param b_budget budget of the boy
         * @param b_attract attractiveness level of the boy
         * @param b_intell intelligence level of the boy
         * @param b_index index of the boy in the boys arrays
         * @param g_name name of the girl in relationship
         * @param g_happiness happiness level of the girl
         * @param g_budget maintainance budget of the girl
         * @param g_attract attractiveness of girl
         * @param g_intell intelligence level of the girl
         * @param g_index index of the girl in the girl array
         */
            
            
	public couples_info(String b_name , int b_happiness , int b_budget , int b_attract , int b_intell, int b_index,String g_name , int g_happiness , int g_budget , int g_attract , int g_intell,int g_index){
            
                this.boyfriend=b_name;
		this.happiness_of_boy=b_happiness;
		this.budget_of_boy=b_budget;
		this.attract_boy=b_attract;
		this.intell_boy=b_intell;
		this.girlfriend=g_name;
		this.happiness_of_girl=g_happiness;
		this.budget_of_girl=g_budget;
		this.attract_girl=g_attract;
		this.intell_girl=g_intell;
		this.index_of_boyfriend=b_index;
		this.index_of_girlfriend=g_index;
	}
	 /**
          * this is used to calculate the happiness level of the couple
          * @return it returns the the happiness of the couples
          */
            
            
	public int calc_happiness(){
           
		this.happiness=happiness_of_boy+happiness_of_girl;
		return happiness;
	}
        /**
         * this fucntion is used to calculate the compactibility level of a couple
         * @return it returns the compactibility of the after calculating it
         */
            
            
	public int calc_compactibility(){
            
		this.compactibility=budget_of_boy-budget_of_girl+abs(attract_boy-attract_girl)+abs(intell_boy-intell_girl);
		return compactibility;
	}
         /**
          * the patch_up function is used to perform patch up between boys and girls
          * @param couples array storing all the couples
          * @param id_of_couple total number of couples
          * @param k number of broken-up girls who need patchup
          * @param women array containing all the girls 
          * @param man array containing all the boys
          * @param total_girls total number of girls
          * @param total_boys total number of boys
          * @param broken_girls array containing indices of the broken-up girls
          * @param broken_boys array containing indices of the broken-up boys
          * @return it returns the new size of the couples array after patching up or re-assingning girls and boys
          * @throws FileNotFoundException
          * @throws IOException 
          */
             
            
        public int patch_up(couples_info couples[] ,int id_of_couple, int  k , girl women[] , boy man[] , int total_girls , int total_boys,int broken_girls[],int broken_boys[]) throws FileNotFoundException, IOException{ 
          
             DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream("log_couple_data_after_patchup.txt"));
		int i=total_girls-1;
		int j=total_boys-1;
                /*for(int op=0;op<total_girls;op++){
                    if(women[op].relationship_status.equals("single")){
                        System.out.println(women[op].name+"");
                    }
                }*/
		for(;i>=0;i--){
                    
			int max_index = -1;
			int max_value=-1;
			
                        int flag=1;
                        
                        for(int in=0;in<k;in++){
                            if(broken_girls[in]==i){
                                flag=1;
                            }
                        }
                        if(women[i].relationship_status.equals("single")&& flag>=1  ){
                            //System.out.println(women[i].name+"");
                            j=total_boys-1;
			for(;j>=0;j--){
				//System.out.println(man.length);
				//System.out.println(man[j].name+" "+man[j].relationship_status+" "+ man[j].budget+" "+women[i].maintainance_budget+" "+women[i].name);
				/*if(man[j].name.equals("ned")){
                                    System.out.print(man[j].relationship_status);
                                }*/
                                //System.out.println(man[j].name+"  "+women[i].name);
                                
                                if(j!=broken_boys[i] && man[j].relationship_status.equals("single") && man[j].budget>=women[i].maintainance_budget && man[j].min_attraction_requirement<=women[i].attractiveness ){
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
		}
		return id_of_couple;
        }
        /**
         * the break_up function is used to perform the break_up between k least happy couples
          * @param couples array storing all the couples
          * @param total_couples total number of couples
          * @param k number of broken-up girls who need patch up
          * @param girls array containing all the girls 
          * @param boys array containing all the boys
          * @param broken_girls array containing indices of the broken-up girls
          * @param broken_boys array containing indices of the broken-up boys
         * @return it returns the new size of the couples array are breaking up k least happy couple 
         */
            
            
        public int break_up(couples_info couples[] ,int total_couples, int k, girl girls[] , boy boys[],int broken_girls[] , int broken_boys[]){
            
            int in=0;
            for(int i=total_couples-1 ;i>=(total_couples-k);i--){
                //System.out.println(i);
                boys[couples[i].index_of_boyfriend].break_up_boy();
                girls[couples[i].index_of_girlfriend].break_up_girl();
                broken_girls[in++]=couples[i].index_of_girlfriend;
                broken_boys[broken_girls[in-1]]=couples[i].index_of_boyfriend;
                //System.out.println(boys[couples[i].index_of_boyfriend].name+" "+couples[i].index_of_boyfriend+"     "+girls[couples[i].index_of_girlfriend].name+" "+couples[i].index_of_girlfriend);
            }
             
            int new_total=total_couples-k;
            return new_total;
        }
}
