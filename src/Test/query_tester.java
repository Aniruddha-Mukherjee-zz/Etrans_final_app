/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import hibernate.helper.*;
import java.util.Iterator;
import java.util.List;
import hibernate.pojo.*;
import java.util.Date;
//import hibernate.helper.sample_helper;

/**
 *
 * @author ani
 */
public class query_tester {
    
    public static void main(String[] args)
 {
    //sample_helper sh=new sample_helper();
    
    //Date d=new Date("2015-04-23 07:00:00");
    String x="'2015-04-23 07:00:00'";
    String y="'2015-04-23 08:00:00'";
   // String hq[]={"FROM TblExceptions WHERE dt_arrival_time> :id","id",x};
    // System.out.println(x);
    
    { String hq[]={"FROM TblExceptions WHERE dt_arrival_time >"+x+"AND dt_arrival_time < "+y }; 
            List l=new sample_helper().run_query(hq, 0,false);                           
                Iterator ite=l.iterator();                            
                while(ite.hasNext())
                {
                TblExceptions e=(TblExceptions) ite.next();
                    System.out.println(e.getDtArrivalTime());
                
                }  
                //System.exit(2000);
    }
    { 
        int mid=3;
    
        String hq[]={"FROM TblExceptions WHERE i_machine_id =:id","id",String.valueOf(mid) }; 
           
        List l=new sample_helper().run_query(hq, 0,true);                           
                Iterator ite=l.iterator();                            
                while(ite.hasNext())
                {
                TblExceptions e=(TblExceptions) ite.next();
                    System.out.println("\n"+e.getTblPlant().getTPlantName());
                
                }  
                
    }
    System.exit(2000);
    
    }   
}
