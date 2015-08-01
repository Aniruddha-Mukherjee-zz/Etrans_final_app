/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epos;
//import com.sun.xml.internal.ws.policy.PolicyMapUtil;
import hibernate.helper.Data_handler;
import  hibernate.pojo.*;
import java.math.BigDecimal;
import java.util.Date;//import hibernate.helper.Plant_handler;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import hibernate.helper.Epos_handler;
import hibernate.helper.Exceptions_handler;
import hibernate.helper.LivePath_handler;
import hibernate.helper.Mapping_handler;
import hibernate.helper.Vehicle_flight_handler;
import hibernate.helper.sample_helper;
import java.text.DateFormat;
import java.time.Instant;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ani
 */
public class Beta_module{
     // private Thread thr;
   private String original_string;
    private String TModelNo;
   private String TDataStatus;
   private String TEvent;
    private int Tid;
    private int Mid;
    private String card_no;
    private TblData data;
    //Time time_v;
    private Date date_time;
    private TblVehicleFlight last_swipe=null;
    private int trip_id;
    
   

    public Beta_module(TblData dat) {
        this.data=dat;
    //this.original_string = original_string;   
    }  
    
    
    /**
     *
     * @param Str
     * @throws java.text.ParseException
     */
    private void set_values(String Str) throws ParseException
{
    String values[]=Str.split(",");
    /*for(int i=0;i<8;i++)
    {
            System.out.println(values[i]);
    }*/
    
    TModelNo=values[0];
    TDataStatus=values[1];
    TEvent=values[2];
    Tid=Integer.parseInt(values[3]);
    Mid=Integer.parseInt(values[4]);
    card_no=values[5];
    //date_time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(values[7]+values[6]);
   date_time = new SimpleDateFormat("ddMMyyyyHHmmss").parse(values[7]+values[6]);
   
   Vehicle_flight_handler vfh=new Vehicle_flight_handler();
           
   String hql_query[]={"FROM TblVehicleFlight WHERE c_card_id= :id ORDER BY dt_time ASC", "id",card_no};
           
    List emp=vfh.run_query(hql_query,1,false);                     
    // List l=vfh.run_query("FROM TblVehicleFlight WHERE c_card_id= :id ORDER BY dt_time ASC", 1);    //ORDER BY dt_time DESC"
                
           Iterator it=emp.iterator();
           try 
           {
               last_swipe=(TblVehicleFlight) it.next();
               
           } 
           catch (Exception e)
           {
               //(last_swipe==null)    {   save_this_swipe(false);return;   }
           }
           
       String hql_query2[]={"FROM TblMapping WHERE c_card_id= :id AND b_is_active=true ORDER BY dt_created DESC","id",card_no};
           emp=new Mapping_handler().run_query(hql_query2,1,false);
//List  emp= query.setMaxResults(1).list();
            it=emp.iterator();
        
        try {  
          
    //if the trip has not yet started, this will throw an exception
            if(it.hasNext()==false)
               throw  new Exception("Trip not started. trip id not found");
           
           TblMapping map=(TblMapping) it.next();
           
            trip_id=map.getITripId().intValueExact();

        } 
        catch (Exception e) 
       {
           System.out.println(e.getMessage());
            //Logger.getLogger(Beta_module.class.getName()).log(Level.SEVERE, null, ex);
       }
           
}
    private void save_this_swipe(boolean store_as_negative)
    {
       if(store_as_negative==true)  Mid=(-1)*Mid;           
                                                    
         
        String result="Failure";
        while (result.equals("Failure")) 
        {
            result= new Vehicle_flight_handler().insert_into_table(TModelNo, TDataStatus, TEvent, date_time, Mid, card_no,trip_id);
        }
                      
           //code to handle exceptions and call other functions
          Delta_module dm=new Delta_module(new Mapping_handler().get_tuple(trip_id),Mid,last_swipe,date_time);
               
             boolean islast=dm.handle_live_path_create_exceptions();
                  
                if(islast==true)
                    {
                        new Mapping_handler().close_trip(trip_id);
                    }
                          
    }
    
  /*  void create_an_exception()
    {          
        //to get the trip_id 
       Mapping_handler mh=new Mapping_handler(); //Vehicle_flight_handler();
        String hql_query[]={"FROM TblMapping WHERE c_card_id= :card AND b_is_active= true","id",card_no};
        List emp=mh.run_query(hql_query, 1,false);
        TblMapping v= (TblMapping) emp.iterator().next();
         
        int trip_id= v.getITripId().intValue();
        int plant_id=v.getTblPlant().getIPlantId().intValue();
        
        //this is dummy data, will be filled in reality by timer function provided by agent
        Date expected_time=Date.from(Instant.EPOCH);
        
        Exceptions_handler eh=new Exceptions_handler();
        eh.insert_into_table(plant_id, trip_id, Mid,date_time,expected_time ,false, date_time);
        //eh.
    }*/
    
    
    public void run() {
        
        //original_string=data.getEposData();
       // System.out.println("is it working");
       try {
           original_string=data.getEposData();
           
           set_values(original_string);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            } 
       catch (Exception ex) 
       {
           ex.printStackTrace();
            //Logger.getLogger(Beta_module.class.getName()).log(Level.SEVERE, null, ex);
       }
      
       //retrive the Tblepos mapect for this swipe
       Epos_handler eh=new Epos_handler();
       TblEpos epos_map=eh.get_tuple(Mid);
       
       if(epos_map.getisBBothways()==false)
       {
        //if this swipe is first swipe, just save it
    if(last_swipe==null)       save_this_swipe(false);
    else {                                                  
                //check Mid of last and this swipe                               
                if(last_swipe.getTblEpos().getIMachineId().intValueExact()==Mid)
                {
                    if(is_in_live_path()==true) save_this_swipe(false);
                    //Mid of this swipe matches with Machineid of last swipe. 
                    //Multiple swipes in same machine 
                    //reject this swipe
                }
                else
                {
                    //last swipe was at different machine_id. so this swipe is new.
                   
                    save_this_swipe(false);
                }                            
    }
       }
       else
       {                         
           Session  session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
           int x=( (Long) session.createQuery("select count(*) from TblVehicleFlight WHERE i_machine_id ="+Mid+" OR i_machine_id ="+(-1)*Mid +"AND i_trip_id ="+trip_id).iterate().next() ).intValue();
           //x indicates the number of swipes @ this machine by this card, in this trip 
          
           if(x%2==0)   
           {
               //this may be an inswipe or a repeat swipe since already even no of swipes has occured
               if(is_in_live_path()==true)
                                    save_this_swipe(false);
               else
               {
                  
           
                   if(is_in_live_path()==false && last_swipe.getTblEpos().getIMachineId().intValueExact()==Mid )  
                   {
                       //System.out.println("working");
                        //this is not in live path and machine id of last swipe is same as this swipe
                       //hence repeat swipe @ same machine id
                       //reject it
                   }                   
                   else
                         save_this_swipe(false); 
                      /*
                       {
                           //vehicle has deviated from predetermined path. hence swipe will be saved,exception will be generated, and expected time will  be null
                           int plant_id=new Mapping_handler().get_tuple(trip_id).getTblPlant().getIPlantId().intValueExact();
                           
                          String result="Failure";
                                while (result.equals("Failure")) 
                                {            
                                     result=new Exceptions_handler().insert_into_table(plant_id, trip_id,Mid,date_time,null,false, new Date());
                                 }
                       } */         
                                 
                   
                                        
               }
           }
               
       else         
           {
                    //already an odd number of swipes has occured indicating this may be a out swipe
              
            String hq[]={"from TblVehicleFlight WHERE i_machine_id ="+Mid+" OR i_machine_id ="+(-1)*Mid +"AND i_trip_id ="+trip_id+" ORDER BY dt_time DESC" }; 
            List l=new sample_helper().run_query(hq, 0,false);                           
                Iterator ite=l.iterator();                            
                if(ite.hasNext())
                {
                    TblVehicleFlight v=(TblVehicleFlight) ite.next();
                  
                    Date date1=date_time;
                    Date date2=v.getDtTime();
                    long time_diff_in_minutes=(date1.getTime()-date2.getTime());
                     time_diff_in_minutes=time_diff_in_minutes/60000;
                       
                        if(time_diff_in_minutes>=v.getTblEpos().getITimeInBetween().intValueExact())
                        {
                           save_this_swipe(true);
                           //this is an out swipe 
                        }   
                            
                        else
                        {
                            //reject this swipe as this is an out- swipe
                        }
                
                    
               
           }
               
       
       }
      /* {
            String hql_query[]={"FROM TblVehicleFlight WHERE c_card_id= :id ORDER BY dt_time ASC", "id",card_no};
           
            Vehicle_flight_handler vfh=new Vehicle_flight_handler();
            List emp=vfh.run_query(hql_query,2,false);
           
            //try{
            
            Iterator it=emp.iterator();
                
           try 
           {
               last_swipe=(TblVehicleFlight) it.next();
               
           } 
           catch (Exception e)
           {
               if(last_swipe==null)    {   save_this_swipe(false);return;   }
           }
           
            //last_swipe=(TblVehicleFlight) it.next();
         
                 TblVehicleFlight last_to_last_swipe = null;
         try
         {
              last_to_last_swipe=(TblVehicleFlight) it.next();
         }
         catch(Exception e)
         {
              if(last_to_last_swipe==null)    {   save_this_swipe(false);return;   }
         }
               
         
        
              
                int last_swipe_machine_id=last_swipe.getTblEpos().getIMachineId().intValueExact();
                              
          if( last_swipe_machine_id!=Mid)
                   {
                    
                     //diferent machine ids represent this is 1st swipe @this machine.
                      //This is in swipe @ this machine, i will save it.
                    save_this_swipe(false);                   
                    
                   }
                else
                {    // represents this & last swipe of this card @ same machine 
                    //represents this swipe can be out-/out/out+ swipe
                    //check the time interval from last swipe
                    
                    DateFormat formatter=new SimpleDateFormat("ddMMyyyy hhmmss") ;
                    Date last_swipe_time= last_swipe.getDtTime();
                   // Date last_to_last_swipe_time= last_to_last_swipe.getDtTime();
                    long time_difference_in_minutes=last_swipe_time.getTime()-date_time.getTime();
                   
                    // make the time difference in minutes from miliseconds                  
                    time_difference_in_minutes=time_difference_in_minutes/1000;
                    
          if (time_difference_in_minutes<epos_map.getITimeInBetween().intValueExact()) 
                   {
                            //reject this swipe
                            //out- swipe
                    } 
                    
            else 
                {
               
                    int last_to_last_machine_id = last_to_last_swipe.getTblEpos().getIMachineId().intValueExact();
                           
                           if(last_swipe_machine_id==(-1)*last_to_last_machine_id)
                            {
                                  //here already last & this swipe @ same machine
                                 //repeat out swipe at this epos machine
                                //consecutive 3 swipes @ epos with boolean=true
                                //out+ swipe
                                //reject this swipe
                            }
                           else 
                           {
                              if(Mid==last_swipe_machine_id)
                                  save_this_swipe(true);
                                //out swipe
                              
                           }
                        }
                }
       }    */
             
        //marks the boolean field in raw data table as false               
    
       }
        Data_handler dh=new Data_handler();
               
                 dh.mark_seen_as_true(data.getIDataId().intValue());                  
                return;
 }

private boolean is_in_live_path()
{

           
    //int path_id=new Mapping_handler().get_tuple(trip_id).getTblLivePath().getIPathId().intValueExact();
        
        boolean is_last_node=false;
        
        //LivePath_handler lh=new LivePath_handler();
        // TblLivePath tlp=lh.get_tuple(path_id);
        TblLivePath tlp=new Mapping_handler().get_tuple(trip_id).getTblLivePath();
        
        String head=tlp.getTNext();
         
         String path=tlp.getTCurrPath();
         
         String values[]=path.split(",");
         final int[] int_values = new int[values.length];
   
         for (int i=0; i < values.length; i++) 
        int_values[i] = Integer.parseInt(values[i]); 
         
        for(int i=0;i<int_values.length;i++)
        {
            if(int_values[i]==Mid)
            {
               
               return true;
            }             
        }
         
    
       return false;
}
    
}