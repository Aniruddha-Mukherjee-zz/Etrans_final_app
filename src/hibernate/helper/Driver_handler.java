/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.helper;

import hibernate.pojo.TblPlant;
import hibernate.pojo.TblTransporter;
import hibernate.pojo.TblDriver;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ani
 */
public class Driver_handler extends sample_helper{
     private Session session;
     
    /**
     *
     * @param plant_id
     * @param transporter_id
     * @return
     */
 /*   public String insert_into_table( int dr_id,int plant_id)
  {
        boolean error_flag=false;
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    
        org.hibernate.Transaction tx = null;
        try
        {
           
            tx=session.beginTransaction();
            
            TblDriver drv=new TblDriver();
            drv.setIDriverId(new BigDecimal(dr_id));
            
            {
                Plant_handler ph=new Plant_handler();
                TblPlant plant= ph.get_tuple(plant_id);
           
            if(plant!=null)
                    drv.setTblPlant(plant);
            else    throw new Exception("Foreign Key Plant_id Dependency Failed ");
            
            }
            
            session.save(drv);
            
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
    
    public String insert_into_table(int tr_id,int plant_id,int dr_id,String dr_name, String dr_addr1,String dr_addr2,String dr_city,String dr_state,String dr_pin,String dr_country_id,String  dr_email_id,String dr_lcnno) 
    {
        boolean error_flag=false;
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    
        org.hibernate.Transaction tx = null;
        TblDriver drv=new TblDriver();
        try
        {
           
            tx=session.beginTransaction();
            
            
            drv.setIDriverId(new BigDecimal(1));
            
            {
                Plant_handler ph=new Plant_handler();
                TblPlant plant= ph.get_tuple(plant_id);
                
				Transporter_handler tr=new Transporter_handler();
                TblTransporter transporter= tr.get_tuple(tr_id);
           
            if(plant!=null)
                    drv.setTblPlant(plant);
            else    throw new Exception("Foreign Key Plant_id Dependency Failed ");
            
           if(transporter!=null)
                   drv.setTblTransporter(transporter);//Transp(plant);
            else    throw new Exception("Foreign Key Plant_id Dependency Failed ");
             
            drv.setTDriverName(dr_name);
          
            // drv.getTDriverMobileNo(dr_mob_no);
                drv.setTDriverAddr1(dr_addr1);
//drv.setTDrivererAddr1(dr_addr1);
drv.setTDriverAddr2(dr_addr2);//drv.setTDrivererAddr2(dr_address2);
             drv.setTDriverCity(dr_city);
             drv.setTStateId(dr_state);
             drv.setIDriverPin(dr_pin);
			 drv.setTCountryId(dr_country_id);
             drv.setTDriverEmailId(dr_email_id); 
		drv.setTLicenceNo(dr_lcnno);	// drv.getTLicenceNo(dr_lcnno);
            
            }
            
            session.save(drv);
            
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
           if(error_flag==false) return drv.getIDriverId().toString();//"Success";
           else         return "Failure";
        }
        
    }
    
    /**
     *
     * @param id
     * @return
     */
    //@Override
    public String delete_from_table(int id) {
                   
        session = hibernate.NewHibernateUtil.getSessionFactory().openSession();
        
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
            Driver_handler t=new Driver_handler();
            TblDriver drv=t.get_tuple(id);
            if(drv==null)   throw  new Exception();
            
            tx=session.beginTransaction();
          
             drv=new TblDriver();
            //TblCard card=new TblCard();
            drv.setIDriverId(new BigDecimal(id));  
            session.delete (drv);
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
       
    public String update_in_table(int tr_id,int plant_id,int dr_id,String dr_name, String dr_addr1,String dr_addr2,String dr_city,String dr_state,String dr_pin,String dr_country_id,String  dr_email_id,String dr_lcnno) 
    {
              session = hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {         
            TblDriver drv=(TblDriver) session.get(TblDriver.class,new BigDecimal(dr_id));
            //  TblCard Card=(TblCard) session.get(TblCard.class,new BigDecimal(card_id));
            
           Plant_handler ph=new Plant_handler();
           TblPlant plant= ph.get_tuple(plant_id);
		   Transporter_handler tr=new Transporter_handler();
           TblTransporter transporter= tr.get_tuple(tr_id);
        
            
           tx=session.beginTransaction();
           
           //if(FLAG==true)   
           {
           if(plant==null)  throw  new Exception();
           }
           
             drv.setTblPlant(plant);
           /* drv.setTDriverName(dr_name);
             drv.getTDriverMobileNo(dr_mob_no);
             drv.setTDrivererAddr1(dr_address1);
			 drv.setTDrivererAddr2(dr_address2);
             drv.setTDriverCity(dr_city);
             drv.setTDriverState(dr_state);
             drv.setIDriverPin(dr_pin);
			 drv.setTDriverCountryid(dr_country_id);
             drv.getTTransporterEmailId(dr_email_id); 
			 drv.getDriverlicenseno(dr_lcnno);           
            */
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
    
 /* public String update_in_table(int transporter_id,int plant_id) {
     
       session = hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
        
            TblDriver drv=(TblDriver) session.get(TblDriver.class,new BigDecimal(transporter_id));
            //  TblCard Card=(TblCard) session.get(TblCard.class,new BigDecimal(card_id));
            
           Plant_handler ph=new Plant_handler();
           TblPlant plant= ph.get_tuple(plant_id);
		   Transporter_handler tr=new Transporter_handler();
           TblTransporter transporter= tr.get_tuple(transporter_id);
        
            
           tx=session.beginTransaction();
           
           if(FLAG==true)   
           {
           if(plant==null)  throw  new Exception();
           }
           
             drv.setTblPlant(plant);
            
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

    */
    //@Override
    public TblDriver get_tuple(int id) {
           session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;
    TblDriver plant = null;
        try
        {
           // org.hibernate.Transaction tx=session.beginTransaction();
            tx=session.beginTransaction();
             plant=(TblDriver) session.get(TblDriver.class ,new BigDecimal(id));
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
             return plant;
        }      
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
