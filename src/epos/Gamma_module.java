/*
This module calculates the time required between 2 node based on live path followed to get there
 */
package epos;

import hibernate.helper.sample_helper;
import hibernate.pojo.TblLinks;
import hibernate.pojo.TblVehicleFlight;
import hibernate.helper.Epos_handler;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ani
 */
public class Gamma_module {
    int arr[];
    private TblVehicleFlight last_swipe;
    private int this_swipe_machine_id;

   
    public Gamma_module(int[] arr,int this_swipe_machine_id,TblVehicleFlight last_swipe) 
    {
        this.arr = arr;
        this.last_swipe = last_swipe;
        this.this_swipe_machine_id = this_swipe_machine_id;
    }
  
    public int return_time()
    {
        int time = 0;int index=-1;
        if(last_swipe==null)
        {
            //this swipe is first swipe
        }
        
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==this_swipe_machine_id)
            {
               index=i;
               break;
            }                       
        }
        
       // if(index<5)    return  index;
        
        if(arr[0]==(-1)*last_swipe.getTblEpos().getIMachineId().intValueExact())
            {
            //these swipes are in and out swipes @ same epos machine
            time+=last_swipe.getTblEpos().getITimeInBetween().intValueExact();
             }
        else
        {
            time+=internal_use_time_function(arr[0],last_swipe.getTblEpos().getIMachineId().intValueExact());
        }
        
        for(int i=0;i<index;i++)
        {
            if(arr[i]==(-1)*arr[i+1])
                time+=new Epos_handler().get_tuple(arr[i]).getITimeInBetween().intValueExact();
            else
                time+=internal_use_time_function(arr[i], arr[i+1]);
        }
        
        return time;
    }
   
    private int internal_use_time_function(int to,int from)
    {
        int time=0;
        if(to <0)   to=to*(-1);    
        if(from <0)   from=from*(-1);
        String x=""+to;
        String y=""+from;
        
        String hq[]={"FROM TblLinks WHERE i_to = :to AND i_from= :from OR  i_to=:from AND i_from=:to","to",x,"from",y}; 
            List l=new sample_helper().run_query(hq, 0,true);                           
           Iterator ite=l.iterator();                            
              
                if(ite.hasNext())
                {
                    TblLinks t=(TblLinks) ite.next();
                    time=t.getNTimediffInMin().intValueExact();
                //TblExceptions e=(TblExceptions) ite.next();
                 //   System.out.println(e.getDtArrivalTime());
                
                }  
        
       return time;
        
    }
    
}
