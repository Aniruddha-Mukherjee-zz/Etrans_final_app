/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.helper;
//import hibernate.pojo.TblCard;
import hibernate.pojo.TblPlant;
import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.helper.Plant_handler;
//import static hibernate.helper.sample_helper.FLAG;
//import hibernate.pojo.TblCard;
import hibernate.pojo.TblProcess;
import java.math.BigDecimal;
import java.util.List;

/*
    Handles operations of table objects; Returns "Success" / "Failure" accordingly
  public String insert_into_table(args)                       --inserts data into table 
  public String delete_from_table(args)                       --deletes data from table
  public String update_in_table(args)                         --updates data in table
 public List run_query(String HQl_Query,int n);                     --runs hql query and returns the list pointing to database objects
  

*/

public class Process_handler extends sample_helper{
    private Session session;

 
   
  
    
    public String insert_into_table(int plant_id,String process_type) {
        
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
        
        boolean error_flag=false;
        TblProcess tp=new TblProcess();
        
        Transaction tx = null;
        try
        {   
            tx=session.beginTransaction();
            
          //  tp.setIProcessId(new BigDecimal(Process_id));
            Plant_handler ph=new Plant_handler();
            TblPlant plant= ph.get_tuple(plant_id);
           
            if(plant!=null)
                    tp.setTblPlant(plant);
            else    throw new Exception();
            tp.setTProcessType(process_type);
            session.save(tp);
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
            if(error_flag==false) return tp.getIProcessId().toString(); //"Success";
           else         return "Failure";
        }
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
        public TblProcess get_tuple(int id) {
    session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;  
    TblProcess pr=null;
      try
        {
           // org.hibernate.Transaction tx=session.beginTransaction();
            tx=session.beginTransaction();
            pr=(TblProcess) session.get(TblProcess.class,new BigDecimal(id));
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
             return pr;
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
            
            Process_handler t=new Process_handler();
            TblProcess tp=t.get_tuple(id);
            
            if(tp==null)   throw  new Exception();
            
            tx=session.beginTransaction();
            
            TblProcess tpr=new TblProcess();
           tpr.setIProcessId(new BigDecimal(id));
            
            
            session.delete(tp);
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

    /**
     *
     * @param process_id
     * @param plant_id
     * @param process_type
     * @return
     */
    public String update_in_table(int process_id, int plant_id, String process_type) {
     
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
           // tx=session.beginTransaction();
           TblProcess pro=(TblProcess) session.get(TblProcess.class,new BigDecimal(process_id));
            
           Plant_handler ph=new Plant_handler();
           TblPlant plant= ph.get_tuple(plant_id);
           tx=session.beginTransaction();
           
           //if(FLAG==true)   
           {
           if(plant==null)  throw  new Exception();
           }
           
             pro.setTblPlant(plant);
              pro.setTProcessType(process_type);
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
