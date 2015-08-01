/*
 Once a swipe is going to be saved, this module is called so that it can update the live_path_table and generate exceptions if necessary
 */
package epos;

import hibernate.helper.Exceptions_handler;
import hibernate.helper.Mapping_handler;
import hibernate.helper.sample_helper;
import hibernate.helper.LivePath_handler;
import hibernate.pojo.TblLivePath;
import hibernate.pojo.TblMapping;
import hibernate.pojo.TblVehicleFlight;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author ani
 */
public class Delta_module {
   private TblMapping map;
   private int machine_id;
   private TblVehicleFlight last_swipe;
   private Date date_time;
   private  DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
   //private Date expected_time;
   
private Date addMinutesToDate(int minutes, Date beforeTime)
{
    final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

    long curTimeInMs = beforeTime.getTime();
    Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
    return afterAddingMins;
}

    public Delta_module(TblMapping map, int machine_id, TblVehicleFlight last_swipe,Date date_time) 
    {
        this.map = map;
        this.machine_id = machine_id;
        this.last_swipe = last_swipe;
        this.date_time=date_time;
    }
   
    void create_an_exception(int epos_id,Date arrival_time,Date expected_time)
    {          
        //to get the trip_id 
      
        TblMapping v= map;
         
        int trip_id= v.getITripId().intValue();
        int plant_id=v.getTblPlant().getIPlantId().intValue();
        
        //this is dummy data, will be filled in reality by timer function provided by agent
         //expected_time=Date.from(Instant.EPOCH);
        
        Exceptions_handler eh=new Exceptions_handler();
        //eh.insert_into_table(plant_id, trip_id, machine_id, date_time, expected_time, true, date_time)
       
        //try to insert. if not successfull keep trying
       String result="Failure";
        while (result.equals("Failure")) 
        {            
            result=eh.insert_into_table(plant_id, trip_id, epos_id,arrival_time,expected_time ,false, new Date());
        }
       
        //eh.
    }
    
    public boolean handle_live_path_create_exceptions()
    {
        
        int path_id=map.getTblLivePath().getIPathId().intValue();
        
        boolean is_last_node=false;
        
        LivePath_handler lh=new LivePath_handler();
         TblLivePath tlp=lh.get_tuple(path_id);
         String head=tlp.getTNext();
         
         String path=tlp.getTCurrPath();
         
         String values[]=path.split(",");
         final int[] int_values = new int[values.length];
   
         for (int i=0; i < values.length; i++) 
        int_values[i] = Integer.parseInt(values[i]); 
        
         int index=-1;
         
        for(int i=0;i<int_values.length;i++)
        {
            if(int_values[i]==machine_id)
            {
               index=i;
               break;
            }             
        }
        
       if(index==-1)
       {
           //this node is not in the live path. vehicle has deviated from pre-determined path
           //create an exception where expected time will be null
           create_an_exception(machine_id, date_time, null);
           return false;
       }
       
       if(int_values[index]==machine_id && index==int_values.length-1)
        {
            is_last_node=true;
             head=null;
             path=null;
            
        }   
        else
       {
           head=Integer.toString(int_values[index+1]);
           
            path= String.valueOf(int_values[index+1]);
        
        for(int i=index+2;i<int_values.length;i++)
            {
                path=path+","+int_values[i];
            }
       
       }
       
        String result="Failure";
        while (result.equals("Failure")) 
        {
            result=new LivePath_handler().update_in_table(path_id, path,head );
        }
       
        //System.out.println(str);
      //if this is 1st swipe,then lastswipe will be null and no need to call exception class
       Date expected_time = null;
        if(last_swipe==null) 
               expected_time =date_time;
        
        else 
        {
            Gamma_module gamma=new Gamma_module(int_values, machine_id, last_swipe);
            expected_time=addMinutesToDate(gamma.return_time(), date_time);
        }
            
        //generate exceptions for missed swipes       
            
            for(int i=0;i<index;i++)               
            {
                if(last_swipe!=null)
                {
                Gamma_module gamma=new Gamma_module(int_values, int_values[i], last_swipe);                            
                expected_time=addMinutesToDate(gamma.return_time(), date_time);
            
                create_an_exception(int_values[i], null,expected_time);
                }
        }
       //genearate exception for late swipe i.e. arrival_time>expected time
     if(last_swipe!=null) 
     {

        Gamma_module gamma=new Gamma_module(int_values, int_values[index], last_swipe); 
        expected_time=addMinutesToDate(gamma.return_time(), date_time);
        if(date_time.compareTo(expected_time)>0)
                     create_an_exception(int_values[index], date_time, expected_time);
     }
                     
       return is_last_node;
    
         }
}

