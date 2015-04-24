/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.helper;
import java.util.Date;
import hibernate.pojo.TblPlant;
import hibernate.pojo.TblVehicle;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ani
 */
public class Vehicle_handler extends sample_helper{
     private Session session;
     
    /**
     *
     * @param plant_id
     * @param Vehicle_id
     * @return
     */
    /*public String insert_into_table( String vehicle_id,int plant_id)
  {
        boolean error_flag=false;
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    
        org.hibernate.Transaction tx = null;
        try
        {
           
            tx=session.beginTransaction();
            
            TblVehicle vhc=new TblVehicle();
            vhc.setTVehicleId(vehicle_id);//vhc.setIVehicle(new BigDecimal(vehicle_id));
            
            {
                Plant_handler ph=new Plant_handler();
                TblPlant plant= ph.get_tuple(plant_id);
           
            if(plant!=null)
                    vhc.setTblPlant(plant);
            else    throw new Exception("Foreign Key Plant_id Dependency Failed ");
            
            }
            
            session.save(vhc);
            
            tx.commit();                               
             //throw new Exception("Method Not Overwritten Yet");
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
    }*/
    
    public String insert_into_table(String v_id,int plant_id,String v_chasis, String v_type,long v_load,String v_owner,String v_permit,Date v_lcnsedate) 
    {
        boolean error_flag=false;
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    
        org.hibernate.Transaction tx = null;
        try
        {
           
            tx=session.beginTransaction();
            
            TblVehicle vhc=new TblVehicle();
            vhc.setTVehicleId(v_id);
            
            {
                Plant_handler ph=new Plant_handler();
                TblPlant plant= ph.get_tuple(plant_id);
           
            if(plant!=null)
                    vhc.setTblPlant(plant);
            else    throw new Exception("Foreign Key Plant_id Dependency Failed ");
            
             vhc.setTChasisNo(v_chasis);
             vhc.setTVehicleType(v_type);
             vhc.setILoadCapacity(v_load);
             vhc.setTOwner(v_owner);
             vhc.setTNationalPermit(v_permit);
             vhc.setDtLicenseExpiryDate(v_lcnsedate);
            
            }
            
            session.save(vhc);
            
            tx.commit();                               
             //throw new Exception("Method Not Overwritten Yet");
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
        
    }
    
    /**
     *
     * @param id
     * @return
     */
    public String delete_from_table(String id) {
                   
        session = hibernate.NewHibernateUtil.getSessionFactory().openSession();
        
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
            Vehicle_handler t=new Vehicle_handler();
            TblVehicle vhc=t.get_tuple(id);
            if(vhc==null)   throw  new Exception();
            
            tx=session.beginTransaction();
          
             vhc=new TblVehicle();
            //TblCard card=new TblCard();
            vhc.setTVehicleId(id);
            session.delete(vhc);
            tx.commit();
            
            //throw new Exception("Method Not Overwritten Yet");
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
        
    }
       
    /**
     *
     * @param v_id
     * @param plant_id
     * @param v_chasis
     * @param v_type
     * @param v_load
     * @param v_owner
     * @param v_permit
     * @param v_lcnsedate
     * @return
     */
    public String update_in_table (String v_id,int plant_id,String v_chasis, String v_type,long v_load,String v_owner,String v_permit,Date v_lcnsedate)
    {
              session = hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {         
            TblVehicle vhc=(TblVehicle) session.get(TblVehicle.class,new BigDecimal(v_id));
            //  TblCard Card=(TblCard) session.get(TblCard.class,new BigDecimal(card_id));
            
           Plant_handler ph=new Plant_handler();
           TblPlant plant= ph.get_tuple(plant_id);
        
            
           tx=session.beginTransaction();
           
          // if(FLAG==true)   
           {
           if(plant==null)  throw  new Exception();
           }
           
             vhc.setTVehicleId(v_id);
             vhc.setTblPlant(plant);
             vhc.setTChasisNo(v_chasis);
             vhc.setTVehicleType(v_type);
             vhc.setILoadCapacity(v_load);
             vhc.setTOwner(v_owner);
             vhc.setTNationalPermit(v_permit);
             vhc.setDtLicenseExpiryDate(v_lcnsedate);
                         
            
            tx.commit();       
           
           // throw new Exception("Method Not Overwritten Yet");
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

    }
    
  /*public String update_in_table(int transporter_id,int plant_id) {
     
       session = hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
        
            TblVehicle vhc=(TblVehicle) session.get(TblVehicle.class,id);
            //  TblCard Card=(TblCard) session.get(TblCard.class,new BigDecimal(card_id));
            
           Plant_handler ph=new Plant_handler();
           TblPlant plant= ph.get_tuple(plant_id);
        
            
           tx=session.beginTransaction();
           
           if(FLAG==true)   
           {
           if(plant==null)  throw  new Exception();
           }
           
             trp.setTblPlant(plant);
            
            tx.commit();       
           
           // throw new Exception("Method Not Overwritten Yet");
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

    }*/

    public TblVehicle get_tuple(String id) {
           session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;
    TblVehicle vhc = null;
        try
        {
           // org.hibernate.Transaction tx=session.beginTransaction();
            tx=session.beginTransaction();
             vhc=(TblVehicle) session.get(TblVehicle.class ,id);
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
             return vhc;
        }      
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

