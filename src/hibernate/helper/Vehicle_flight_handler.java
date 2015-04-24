/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.helper;

import hibernate.pojo.TblCard;
import hibernate.helper.Card_handler;

//import hibernate.pojo.TblDo;
//import hibernate.pojo.TblDriver;
import hibernate.pojo.TblMapping;
import hibernate.helper.Mapping_handler;
import hibernate.pojo.TblVehicleFlight;
//import hibernate.pojo.TblPlant;
//import hibernate.pojo.TblProcess;
import hibernate.helper.Epos_handler;
import hibernate.pojo.TblEpos;
import hibernate.pojo.TblPlant;
//import hibernate.pojo.TblTransporter;
//import hibernate.pojo.TblVehicle;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ani
 */
public class Vehicle_flight_handler extends sample_helper{
     private Session session;
 
    
    public String insert_into_table(String t_model_no, String data_status, String t_event, Date dt_time,int machine_id,String card_id, int trip_id)       //(int plant_id,String card_id,int process_id,String vehicle_id,int driver_id,int trasport_id,int do_id,Date dt_created,boolean is_active) {
    {    
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
        
        boolean error_flag=false;
        TblVehicleFlight  vf=new TblVehicleFlight();
        
        Transaction tx = null;
        try
        {   
            tx=session.beginTransaction();
            
          //  tp.setIProcessId(new BigDecimal(Process_id));
            Epos_handler eh=new Epos_handler();
            TblEpos epos=eh.get_tuple(machine_id);
            if(epos!=null)
                    vf.setTblEpos(epos);//setTblPlant(plant);
            else    throw new Exception("epos machine id not found");
            
            Card_handler cd=new Card_handler();
            TblCard card=cd.get_tuple(card_id);
         
            if(card!=null)    vf.setTblCard(card);
             else    throw new Exception("card id not found");
          
            Mapping_handler mh=new Mapping_handler();
           TblMapping map=mh.get_tuple(trip_id);
           if(map!=null)    vf.setTblMapping(map);
           else throw  new Exception("Trip id not found");
           
            vf.setDtTime(dt_time);
            vf.setTDataStatus(data_status);
            vf.setTEvent(t_event);
            vf.setTModelNo(t_model_no);
            
            //map.setDtCreated(dt_created);
            //map.setBIsActive(is_active);
           // tp.setTProcessType(process_type);
            session.save(vf);
            tx.commit();
        }
        catch(Exception e)
        {
            error_flag=true; 
            if (tx != null) {
                tx.rollback();
            e.printStackTrace();
        }
        }
        finally
        {
            session.close();
            if(error_flag==false) return vf.getIVfid().toString();//"Success";
           else         return "Failure";
        }
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
        public TblVehicleFlight get_tuple(int id) {
    session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;  
 TblVehicleFlight vf=null;    
//TblMapping map=null;
      try
        {
           // org.hibernate.Transaction tx=session.beginTransaction();
            tx=session.beginTransaction();
            vf=(TblVehicleFlight) session.get(TblVehicleFlight.class,new BigDecimal(id));
            }
         catch(Exception e)
        {
             if (tx != null) {
                tx.rollback();
            e.printStackTrace();
        }
        }
        finally
        {
            session.close();
             return vf;
        }      
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


    
    //@Override
        
    public String delete_from_table(int id) {
   
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
        
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
            
     Vehicle_flight_handler mh=new Vehicle_flight_handler();//Mapping_handler();
     TblVehicleFlight vhf=mh.get_tuple(id);
// TblMapping map=mh.get_tuple(id);
            //Process_handler t=new Process_handler();
            //TblProcess tp=t.get_tuple(id);
            
            if(vhf==null)   throw  new Exception("vehicle flight  id not found");
   
            
           // if(veh_fli==null)   throw  new Exception("vehicle flight id not found");
            
            tx=session.beginTransaction();
            TblVehicleFlight vf=new TblVehicleFlight();
            vf.setIVfid(new BigDecimal(id));
           // TblProcess tpr=new TblProcess();
           //tpr.setIProcessId(new BigDecimal(id));
            
            
            session.delete(vf);
            tx.commit();
        }
        catch(Exception e)
        {
            error_flag=true; 
            if (tx != null) {
                tx.rollback();
            e.printStackTrace();
        }
        }
        finally
        {
            session.close();
            if(error_flag==false) return "Success";
           else         return "Failure";
        }
      
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

 
