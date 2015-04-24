/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import epos.Gamma_module;
import hibernate.helper.*;
import hibernate.pojo.TblLinks;
import hibernate.pojo.TblVehicleFlight;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ani
 */
public class tester3 {
   private int arr[];
    private TblVehicleFlight last_swipe;
    private int this_swipe_machine_id;

    public tester3(int[] arr, TblVehicleFlight last_swipe, int this_swipe_machine_id) {
        this.arr = arr;
        this.last_swipe = last_swipe;
        this.this_swipe_machine_id = this_swipe_machine_id;
    }
    
    public int return_time()
    {
        int time = 0;
        if(last_swipe!=null)
        {
            //this swipe is first swipe
            
        }
        return time;
    }
    
    public int internal_use_time_function(int to,int from)
    {
        int time=-1;
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
    public static void main(String[] args) {
        int arr[]={3,-3,4,-4,5};
        
        
        
        Gamma_module x=new Gamma_module(arr, -4, new Vehicle_flight_handler().get_tuple(36));
        System.out.println(x.return_time());
        System.exit(5);
    }
}
