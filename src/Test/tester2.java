package Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ani
 */
import epos.Alpha_module;
import hibernate.helper.*;

import hibernate.pojo.*;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
//import org.hibernate.Session;

public class tester2 {
    public static void main(String[] args) //throws InterruptedException
    {
        //starter s=new Alpha_module(1);
        String hql_query[]={"FROM TblData WHERE seen=false"};
       
        List emp= new sample_helper().run_query(hql_query, 10,false);
        Iterator it=emp.iterator();
        
            while(it.hasNext())
            {
               TblData d=(TblData)it.next();
                //display function is just to display the data, nothing else
                System.out.println(d.getEposData()+"\t"+d.getseen());
                    //display de=new display(d);
            }
                    System.exit(0x1);
// Plant_handler ph=new Plant_handler(); //Plant_Ops();
            //String str=ph.insert_into_table("yes", "working");
            /*  //System.out.println ("If you need to 'quote' in Java");
            //System.out.println ("you can use double \" quote");
            String hq[]={"FROM TblPlant WHERE t_plant_name= :name AND t_plant_owner=:owner ORDER BY i_plant_id DESC","name","JRD","owner","Tata"};
            
            List l=ph.run_query(hq, 5);
            
            
            Iterator it=l.iterator();
            while(it.hasNext())
            {
            TblPlant last_swipe=(TblPlant) it.next();
            display d=new display(last_swipe);
            }
            //String replies with "success" or "failure";
            */
            
            // String hql_query[]={"FROM TblMapping WHERE b_is_active=true", "id","21"};
            
            //List emp2=new Card_handler().ru
            // List emp=new Card_handler().run_query("FROM TblMapping WHERE b_is_active=true",5);
       
        
         System.exit(2);
       
              
    // List l=vfh.run_query("FROM TblVehicleFlight WHERE c_card_id= :id ORDER BY dt_time ASC", 1);    //ORDER BY dt_time DESC"
         /*   Iterator it=emp.iterator();
            while (it.hasNext()) {
           
                TblCard last_swipe=(TblCard) it.next();
                display d=new display(last_swipe);
             */                      
    //System.exit(2);
            
    }
    
}
