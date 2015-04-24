/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.helper;

import hibernate.pojo.TblPlant;
import hibernate.pojo.TblTransporter;
import hibernate.pojo.TblDriver;
import hibernate.pojo.TblCard;
import hibernate.pojo.TblProcess;
import hibernate.pojo.TblVehicle;
import hibernate.pojo.TblDo;
import hibernate.pojo.TblMapping;
import hibernate.pojo.TblLivePath;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.pojo.TblCard;
import hibernate.pojo.TblMapping;
import hibernate.pojo.TblPlant;
import hibernate.pojo.TblProcess;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sayantan
 */
public class Mapping_handler extends sample_helper{
    private Session session;

 
   
  
    
    public String insert_into_table(int plant_id,String card_id,int process_id,String vehicle_id,int driver_id,int trasport_id,int do_id,int path_id,Date dt_created,boolean is_active) {
        
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
        
        boolean error_flag=false;
        TblMapping  map=new TblMapping();
        
        Transaction tx = null;
        try
        {   
            tx=session.beginTransaction();
            
          //  tp.setIProcessId(new BigDecimal(Process_id));
            Plant_handler ph=new Plant_handler();
            TblPlant plant= ph.get_tuple(plant_id);
           
            if(plant!=null)
                    map.setTblPlant(plant);
            else    throw new Exception("plant id not found");
            
            Card_handler cd=new Card_handler();
            TblCard card=cd.get_tuple(card_id);
         
            if(card!=null)    map.setTblCard(card);
             else    throw new Exception("card id not found");
            
            Process_handler p =new Process_handler();
            TblProcess pr =p.get_tuple(process_id);
            
            if(pr!=null)        map.setTblProcess(pr);
            else    throw new Exception("process id not found");
            
         //   Vehicle_handler vh =new Vehicle_handler();
           // Tbl
            Vehicle_handler v = new Vehicle_handler();
                TblVehicle vehicle  = v.get_tuple(vehicle_id);
             if(vehicle!=null)  map.setTblVehicle(vehicle);
             else    throw new Exception("vehicle id not found");
             
              
                
                Driver_handler  d = new Driver_handler();
                TblDriver drive = d.get_tuple(driver_id);
                if(drive!=null) map.setTblDriver(drive);
                else    throw new Exception("driver id not found");
            
                
                Transporter_handler t =new Transporter_handler();
                TblTransporter transporter = t.get_tuple(trasport_id);
                if(transporter!=null)       map.setTblTransporter(transporter);
                else    throw new Exception("transporter id not found");
            
                Do_handler del_o = new Do_handler();
                TblDo dl_o = del_o.get_tuple(do_id);
                   if(dl_o!=null)   map.setTblDo(dl_o);
                   else    throw new Exception("do id not found");
           
                   TblLivePath Lp=new LivePath_handler().get_tuple(path_id);
                  if(Lp!=null) map.setTblLivePath(Lp);
            map.setDtCreated(dt_created);
            map.setBIsActive(is_active);
           // tp.setTProcessType(process_type);
            session.save(map);
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
            if(error_flag==false) return map.getITripId().toString();//"Success";
           else         return "Failure";
        }
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
        public TblMapping get_tuple(int id) {
    session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;  
    TblMapping map=null;
      try
        {
           // org.hibernate.Transaction tx=session.beginTransaction();
            tx=session.beginTransaction();
            map=(TblMapping) session.get(TblMapping.class,new BigDecimal(id));
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
             return map;
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
            
            Mapping_handler mh=new Mapping_handler();
            TblMapping map=mh.get_tuple(id);
            //Process_handler t=new Process_handler();
            //TblProcess tp=t.get_tuple(id);
            
            if(map==null)   throw  new Exception("trip_id not found");
            
            tx=session.beginTransaction();
            
            TblProcess tpr=new TblProcess();
           tpr.setIProcessId(new BigDecimal(id));
            
            
            session.delete(map);
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

    public void close_trip(int trip_id)
    {
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        
        Transaction tx = null;
        try
        {   
            TblMapping map=(TblMapping) session.get(TblMapping.class,new BigDecimal(trip_id));
           tx=session.beginTransaction();
           map.setBIsActive(false);
           session.saveOrUpdate(map);
            tx.commit();
        }
        catch(Exception e)
        {
            //error_flag=true; 
            if (tx != null) {
                tx.rollback();
            e.printStackTrace();
        }
        }
        finally
        {
            session.close();
         }   
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     *
     * @param process_id
     * @param plant_id
     * @param process_type
     * @return
     */
    public String update_in_table(int trip_id,int plant_id,String card_id,int process_id,String vehicle_id,int driver_id,int trasport_id,int do_id,Date dt_created,boolean is_active) {
     
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
                     
                 
           TblMapping map=(TblMapping) session.get(TblMapping.class,new BigDecimal(trip_id));
           tx=session.beginTransaction();
         
             Plant_handler ph=new Plant_handler();
            TblPlant plant= ph.get_tuple(plant_id);
           
            if(plant!=null)
                    map.setTblPlant(plant);
            else    throw new Exception("plant id not found");
            
            Card_handler cd=new Card_handler();
            TblCard card=cd.get_tuple(card_id);
         
            if(card!=null)    map.setTblCard(card);
             else    throw new Exception("card id not found");
            
            Process_handler p =new Process_handler();
            TblProcess pr =p.get_tuple(process_id);
            
            if(pr!=null)        map.setTblProcess(pr);
            else    throw new Exception("process id not found");
            
         //   Vehicle_handler vh =new Vehicle_handler();
           // Tbl
            Vehicle_handler v = new Vehicle_handler();
                TblVehicle vehicle  = v.get_tuple(vehicle_id);
             if(vehicle!=null)  map.setTblVehicle(vehicle);
             else    throw new Exception("vehicle id not found");
             
              
                
                Driver_handler  d = new Driver_handler();
                TblDriver drive = d.get_tuple(driver_id);
                if(drive!=null) map.setTblDriver(drive);
                else    throw new Exception("driver id not found");
            
                
                Transporter_handler t =new Transporter_handler();
                TblTransporter transporter = t.get_tuple(trasport_id);
                if(transporter!=null)       map.setTblTransporter(transporter);
                else    throw new Exception("transporter id not found");
            
                Do_handler del_o = new Do_handler();
                TblDo dl_o = del_o.get_tuple(do_id);
                   if(dl_o!=null)   map.setTblDo(dl_o);
                   else    throw new Exception("do id not found");
            
            map.setDtCreated(dt_created);
            map.setBIsActive(is_active);
          
            //else          throw  new Exception();
           // session.save(card);
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
