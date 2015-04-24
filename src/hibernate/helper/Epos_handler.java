/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.helper;

import hibernate.pojo.TblEpos;
import hibernate.pojo.TblPlant;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
  Handles operations of table objects; Returns "Success" / "Failure" accordingly
  public String insert_into_table(8 args)                       --inserts data into table 

  public List run_query(String HQl_Query,int n);                     --runs hql query and returns the list pointing to database objects

public String delete_from_table(args)     /not working                      --deletes data from table
  
  public String update_in_table(8 args)                         --updates data in table
   
  public String get_tuple(int id)                       --get tuple corresponding to primary key  
*/
public class Epos_handler extends sample_helper{
     private Session session;

     
    
     /* public String insert_into_table(int machine_id,int plant_id,int terminal_id,String Gateway_name)
  {
        boolean error_flag=false;
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    
        org.hibernate.Transaction tx = null;
        try
        {
           
            tx=session.beginTransaction();
            TblEpos ep=new TblEpos();
            ep.setIMachineId(new BigDecimal(machine_id));
            
            Plant_handler ph=new Plant_handler();
            TblPlant plant= ph.get_tuple(plant_id);
           
            if(plant!=null)
                   ep.setTblPlant(plant);
            else    throw new Exception();
            
            ep.setITerminalId(new BigDecimal(terminal_id));
            ep.setTGatewayName(Gateway_name);
                               
            session.save(ep);
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
    
     
       public String update_in_table(int machine_id,int plant_id,int terminal_id,String Gateway_name)
  {
        session = hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
            TblEpos ep=(TblEpos) session.get(TblEpos.class,new BigDecimal(machine_id));
             Plant_handler ph=new Plant_handler();
            TblPlant plant= ph.get_tuple(plant_id);
            
           tx=session.beginTransaction();
           
           //if(FLAG==true)   
           {
           if(plant==null)  throw  new Exception();
           }
           
            ep.setTblPlant(plant);
            ep.setITerminalId(new BigDecimal(terminal_id));                 
            ep.setTGatewayName(Gateway_name);
           
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
    */
      
      public String insert_into_table(int machine_id,int plant_id,int terminal_id,int Gate_no,String Gateway_name, String location,boolean Bothways,int time_in_between)
  {
        boolean error_flag=false;
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    TblEpos ep=new TblEpos();
        org.hibernate.Transaction tx = null;
        try
        {
           
            tx=session.beginTransaction();
            
            ep.setIMachineId(new BigDecimal(machine_id));
            
            Plant_handler ph=new Plant_handler();
            TblPlant plant= ph.get_tuple(plant_id);
           
            if(plant!=null)
                   ep.setTblPlant(plant);
            else    throw new Exception();
            
            ep.setITerminalId(new BigDecimal(terminal_id));
            ep.setIGateNo(new BigDecimal(Gate_no));                 
            ep.setTGatewayName(Gateway_name);
            ep.setTLocation(location);
            ep.setBBothways(Bothways);
            ep.setITimeInBetween(new BigDecimal(time_in_between));           
                               
            session.save(ep);
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
           if(error_flag==false) return ep.getIMachineId().toString();//"Success";
           else         return "Failure";
        }
        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
      // Not working (??)
     //@Override
      public String delete_from_table(int id) {
   
        session = hibernate.NewHibernateUtil.getSessionFactory().openSession();
        
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
           Epos_handler t=new Epos_handler();
            TblEpos trp=t.get_tuple(id);
            if(trp==null)   throw  new Exception();
            
            tx=session.beginTransaction();
            
            TblEpos ep=new TblEpos();
            
            ep.setIMachineId(new BigDecimal(id));
            
            session.delete(ep);
          /* 
           TblCard card=new TblCard();
            card.setICardId(new BigDecimal(id));       
            session.delete(card);*/
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
    }
        
    /**
     *
     * @param machine_id
     * @param plant_id
     * @param terminal_id
     * @param Gate_no
     * @param Gateway_name
     * @param location
     * @param Bothways
     * @param time_in_between
     * @return
     */
    public String update_in_table(int machine_id,int plant_id,int terminal_id,int Gate_no,String Gateway_name, String location,boolean Bothways,int time_in_between) {
     
       session = hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
            TblEpos ep=(TblEpos) session.get(TblEpos.class,new BigDecimal(machine_id));
             Plant_handler ph=new Plant_handler();
            TblPlant plant= ph.get_tuple(plant_id);
            
           tx=session.beginTransaction();
           
          // if(FLAG==true)   
           {
           if(plant==null)  throw  new Exception();
           }
           
            ep.setTblPlant(plant);
            ep.setITerminalId(new BigDecimal(terminal_id));
            ep.setIGateNo(new BigDecimal(Gate_no));                 
            ep.setTGatewayName(Gateway_name);
            ep.setTLocation(location);
            ep.setBBothways(Bothways);
            ep.setITimeInBetween(new BigDecimal(time_in_between)); 
            
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

    }
     
    /**
     *
     * @param id
     * @return
     */
   // @Override
    public TblEpos get_tuple(int id) {
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;  
    TblEpos ep=null;
     try
        {
           
            tx=session.beginTransaction();
            ep=(TblEpos) session.get(TblEpos.class,new BigDecimal(id));
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
             return ep;
        }      
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
