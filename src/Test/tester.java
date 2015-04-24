package Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import hibernate.helper.*;

import hibernate.pojo.*;

import java.math.BigDecimal;
import epos.*;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import epos.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;


public class tester {   
     
    public static void main(String[] args) throws ParseException, Exception {
       
      /* String str_date="110607 070845";
DateFormat formatter=new SimpleDateFormat("ddMMyyyy hhmmss") ;
Date today = formatter.parse(str_date);
       // System.out.println(date.getHours());
       
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

// Get the date today using Calendar object.
//Date today = Calendar.getInstance().getTime();        
// Using DateFormat format method we can create a string 
// representation of a date with the defined format.
String reportDate = df.format(today);

// Print what date is today!
System.out.println("Report Date: " + reportDate);
        
        
    /*  Session session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
                Query query=session.createQuery("FROM TblPlant WHERE t_plant_owner= :owner ORDER BY i_plant_id DESC");
                //query.setParameter("name","jrd");
                query.setParameter("owner","tata");
                List emp= query.setMaxResults(5).list();
              
    // List l=vfh.run_query("FROM TblVehicleFlight WHERE c_card_id= :id ORDER BY dt_time ASC", 1);    //ORDER BY dt_time DESC"
                
                Iterator it=emp.iterator();
                while(it.hasNext())
                {
                TblPlant last_swipe=(TblPlant) it.next();
                display d=new display(last_swipe);
                }
                
        /* try {
            Thread t=new Thread(new starter(2));
            t.start();
            System.out.println("status1 = " + t.isAlive());
            t.join();
            //display dis=new display(data);
          
            //Do_handler d=new Do_handler();
            //TblDo doo=d.get_tuple(10);
// Plant_handler ph=new Plant_handler();
            // ph.delete_from_table(14);//get_tuple(10);
            //display d=new display(a);
            //    ph.insert_into_table("jrd", "tata");
            // String str=ph.insert_into_table("dsA", "Cdsds");
            /*Driver_handler d=new Driver_handler();
            d.insert_into_table(23, 1, 3455,null, null, null, null, null, null, null, null, "5dfmdkf2");
            d.insert_into_table(23, 1, 66, "sf", null, null, null, null, null, null, null, "7sfdvj");
            // TblDriver tb=d.get_tuple(34);
            // display drt=new display(tb);*/
            //  Process_handler ph=new Process_handler();
            //ph.delete_from_table(2);
            // ph.insert_into_table(1,"jhjhj");
//Vehicle_handler vh=new Vehicle_handler();
            //vh.insert_into_table("abc123", 1, null, null, 12345, null, null, null);
            //Do_handler d=new Do_handler();
            //TblDo doo=d.get_tuple(1);
            ///d.insert_into_table(1, 4, null, null, "F", "U", "c");d.
            //display dksakjs=new display(doo);
            
            /* List emp= ph.run_query("FROM TblPlant WHERE i_plant_id",3);
            for(Iterator it=emp.iterator();it.hasNext();)
            {
            TblPlant plant = (TblPlant) it.next();
            display d=new display(plant);
            }
            */
            /*Transporter_handler cd=new Transporter_handler();
            cd.insert_into_table(1, 7, "fdf","dfd","jh",null,null,null,null );
            System.out.println(cd);/*/
            
            // Card_handler ch=new Card_handler();
            //   ch.delete_from_table("a600");
            //String cd=ch.insert_into_table("a600",10);
            // System.out.println(cd);
            
            
            
            /*  Epos_handler eph=new Epos_handler();
            eph.insert_into_table(101,1 ,2, "GATE A");
            TblEpos ep=(TblEpos)eph.get_tuple(65);
            display d=new display(ep);
            String str=eph.update_in_table(101, 1, 3, "Gate B");
            String str=eph.delete_from_table(101);
            System.err.println(str);
            eph.insert_into_table(machine_id, plant_id, terminal_id, null)
            
            
            
            ph.insert_into_table("notankit","raj");
            String s= ph.delete_from_table(8);    */
            // epos_data_handling ep=new epos_data_handling(null);
            //ep.run();
            
            /*Vehicle_flight_handler vfh=new Vehicle_flight_handler();
            
            String id="1";
            Session session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
            Query query=session.createQuery("FROM TblVehicleFlight WHERE c_card_id= :id ORDER BY dt_time DESC");
            query.setParameter("id","1");
            // query.setMaxResults(1);
            List emp= query.setMaxResults(2).list();
            
            // List l=vfh.run_query("FROM TblVehicleFlight WHERE c_card_id= :id ORDER BY dt_time ASC", 1);    //ORDER BY dt_time DESC"
            Iterator it=emp.iterator();
            TblVehicleFlight vf_obj=(TblVehicleFlight) it.next();
            
            epos.display d=new epos.display (vf_obj);
            vf_obj=(TblVehicleFlight) it.next();
            
            epos.display dff=new epos.display (vf_obj);
            */
           /*int plant_id=2;
           String card_id=null;
        String x[]={"FROM TblMapping WHERE i_plant_id= :plantid AND c_card_id= :cardid AND b_is_active=true ","plantid",String.valueOf(plant_id),"cardid",card_id};
        List emp= new sample_helper().run_query(x, 10, true);
        Iterator it=emp.iterator();
        
        if(it.hasNext())    throw  new  Exception("trip not yet started");
                  TblMapping map=(TblMapping) it.next(); 
                int path_id=map.getIPathId().intValue();
            //do your business here      */
        
        /*Delta_module d=new Delta_module(new Mapping_handler().get_tuple(1),1, null, new Date());
        //Delta_module d=new Delta_module(null, 3, null, new Date());
        boolean b=d.handle_live_path_create_exceptions();
        
        if(b)   System.out.println("last");
        else    System.out.println("not last");
               
        /*String x=new Process_handler().insert_into_table(1, null);
        String y=new Vehicle_handler().insert_into_table("1", 1, x, x, 1, x, x, null);
        String x1=new Transporter_handler().insert_into_table(1,1, y, y, y, y, y, y, y);
        String x2=new Driver_handler().insert_into_table(Integer.parseInt(x1), 1, 1, x1, x1, x1, y, x1, x1, y, x1, x1);
        String d0=new Do_handler().insert_into_table(1, 1, null, null, x2, x2, y);
        
        String x=new Mapping_handler().insert_into_table(1, "xt1032",1, "1", 1, 1,1,1,new Date(), true);
        */
       Thread t=new Thread(new Alpha_module(5));
       t.start();
       t.join();
        
        
        System.exit(1);
    } 
     
    }
