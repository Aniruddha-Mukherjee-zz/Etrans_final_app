/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epos;

import hibernate.helper.Data_handler;
import hibernate.pojo.TblData;
import java.security.AccessControlContext;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ani
 */

public class Alpha_module implements Runnable {
   //private Thread t;
   int number_of_threads;
   //private String sample_runnableName;

    public Alpha_module(int number_of_threads) {
        this.number_of_threads = number_of_threads;
    }
   
  
   
   @Override
   public void run() {
       
       //System.out.println("working");
       
       Data_handler dh=new Data_handler();
       
       //System.out.println("\nworking");
       
       List list=dh.run_query("FROM TblData WHERE seen=false", number_of_threads);
        
        Iterator it=list.iterator();
        
        //System.out.println(it.hasNext());
        
        while (it.hasNext()) {
           
               //if(it.hasNext()==false)  break;
               TblData data=(TblData) it.next();
              // display d=new display(data);
             Beta_module eph=new Beta_module(data)   ;         
            eph.run();
             //System.out.println("status2 = " + t.isAlive());
        }
       

   }
   
  /* public void start ()
   {
      //System.out.println("Starting "  );
      if (t == null)
      {
         t = new Thread (this);//, sample_runnableName);
         t.start ();
      }
   }*/

}

/*public class Alpha_module implements Runnable{

    int number_of_threads;
    private Thread t;
     private String threadName;

    public Alpha_module(int number_of_threads,String name) {
        this.number_of_threads = number_of_threads;
      threadName = name;
    }

    public void start ()
   {
      //System.out.println("Starting " +  threadName );
      if (t == null)
      {
          //AccessControlContext threadName = null;
         t = new Thread (this, threadName);
         t.start ();
      }
   }
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     *
     */
    
    
    
   /* @Override
    public void run() {
        
       // System.out.println("working");
        Data_handler dh=new Data_handler();
        List list=dh.run_query("FROM TblData WHERE seen=false", number_of_threads);
        
        Iterator it=list.iterator();
        
           while (it.hasNext()) {
           
               TblData data=(TblData) it.next();
           //  Beta_module eph=new Beta_module(data)   ;         
            // eph.start();
            
        }
        //TblVehicleFlight vf_obj=(TblVehicleFlight) it.next();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}*/
