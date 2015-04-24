/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.helper;

//import com.sun.org.apache.bcel.internal.generic.AALOAD;
import hibernate.pojo.TblMapping;
import hibernate.helper.Mapping_handler;
import hibernate.helper.Epos_handler;
import hibernate.pojo.TblEpos;
import hibernate.pojo.TblPlant;
import hibernate.helper.Plant_handler;
import hibernate.pojo.TblPlant;
import hibernate.pojo.TblExceptions;
import org.hibernate.Session;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class Exceptions_handler extends sample_helper{
     private Session session;
 
    /**
     *
     * @param plant_id
     * @param trip_id
     * @param machine_id
     * @param expected_time
     * @param is_checked
     * @param dt_created
     * @param Date
     * @param is
     * @return
     */
    public String insert_into_table(int plant_id, int trip_id, int machine_id,Date arrival_time,Date  expected_time, boolean is_checked, Date dt_created)    //(String t_model_no, String data_status, String t_event, Date dt_time,int machine_id,String card_id, int trip_id)       //(int plant_id,String card_id,int process_id,String vehicle_id,int driver_id,int trasport_id,int do_id,Date dt_created,boolean is_active) {
    {    
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
        is_checked=false;
        
        boolean error_flag=false;
        
        TblExceptions exc =new  TblExceptions();
        
        Transaction tx = null;
        try
        {   
            tx=session.beginTransaction();
             //TblVehicleFlight();
          //  tp.setIProcessId(new BigDecimal(Process_id));
            Epos_handler eh=new Epos_handler();
            TblEpos epos=eh.get_tuple(machine_id);
            
            if(epos!=null)
                    exc.setTblEpos(epos);//setTblPlant(plant);
            else    throw new Exception("epos machine id not found");
            
            
            Mapping_handler mh=new Mapping_handler();
           TblMapping map=mh.get_tuple(trip_id);
           if(map!=null)    exc.setTblMapping(map);
           else throw  new Exception("trip id not found");
           
           Plant_handler ph=new Plant_handler();
                TblPlant plant= ph.get_tuple(plant_id);
           
            if(plant!=null)
                    exc.setTblPlant(plant);
            else    throw new Exception("Foreign Key Plant_id Dependency Failed ");
           
           exc.setBIsChecked(is_checked);
           exc.setDtCreated(dt_created);
           exc.setDtExpectedTime(expected_time);
           exc.setDtArrivalTime(arrival_time);
            
            //map.setDtCreated(dt_created);
            //map.setBIsActive(is_active);
           // tp.setTProcessType(process_type);
            session.save(exc);
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
            if(error_flag==false) return exc.getIExceptionId().toString();//"Success";
           else         return "Failure";
        }
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
        public TblExceptions get_tuple(int id) {
    session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;  
 TblExceptions exc=null;    
//TblMapping map=null;
      try
        {
           // org.hibernate.Transaction tx=session.beginTransaction();
            tx=session.beginTransaction();
            exc=(TblExceptions) session.get(TblExceptions.class,new BigDecimal(id));
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
             return exc;
        }      
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 public String update_in_table(int exception_id,int plant_id, int trip_id, int machine_id, boolean is_checked)  //Date  expected_time, boolean is_checked, Date dt_created)    //(String t_model_no, String data_status, String t_event, Date dt_time,int machine_id,String card_id, int trip_id)       //(int plant_id,String card_id,int process_id,String vehicle_id,int driver_id,int trasport_id,int do_id,Date dt_created,boolean is_active) 
 {
     
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
 TblExceptions exc=(TblExceptions) session.get(TblExceptions.class,new BigDecimal(exception_id));
                    
 tx=session.beginTransaction();
             
              Epos_handler eh=new Epos_handler();
            TblEpos epos=eh.get_tuple(machine_id);
            
            if(epos!=null)
                    exc.setTblEpos(epos);//setTblPlant(plant);
            else    throw new Exception("epos machine id not found");
            
            
            Mapping_handler mh=new Mapping_handler();
           TblMapping map=mh.get_tuple(trip_id);
           if(map!=null)    exc.setTblMapping(map);
           else throw  new Exception("trip id not found");
           
           Plant_handler ph=new Plant_handler();
                TblPlant plant= ph.get_tuple(plant_id);
           
            if(plant!=null)
                    exc.setTblPlant(plant);
            else    throw new Exception("Foreign Key Plant_id Dependency Failed ");
           
            exc.setBIsChecked(is_checked);
           //exc.setBIsChecked(is_checked);
           //exc.setDtCreated(dt_created);
           //exc.setDtExpectedTime(expected_time);
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
    

    
    //@Override
        
    public String delete_from_table(int id) {
   
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
        
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
            
     Exceptions_handler eh=new Exceptions_handler();//Vehicle_flight_handler();//Mapping_handler();
     TblExceptions exc=eh.get_tuple(id);
// TblMapping map=mh.get_tuple(id);
            //Process_handler t=new Process_handler();
            //TblProcess tp=t.get_tuple(id);
            
            if(exc==null)   throw  new Exception("exception id not found");
   
            
           // if(veh_fli==null)   throw  new Exception("vehicle flight id not found");
            
            tx=session.beginTransaction();
          TblExceptions ex =new TblExceptions();//Exception_handler();//TblVehicleFlight();
            ex.setIExceptionId(new BigDecimal(id));
           // TblProcess tpr=new TblProcess();
           //tpr.setIProcessId(new BigDecimal(id));
            
            session.delete(ex);
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
