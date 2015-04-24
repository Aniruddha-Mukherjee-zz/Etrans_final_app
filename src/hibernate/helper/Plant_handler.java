/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.helper;

import hibernate.pojo.TblPlant;
import java.math.BigDecimal;
import org.hibernate.Session;
import javax.persistence.Query;
import org.hibernate.Transaction;
import java.util.Iterator;
import java.util.List;

/*
    Handles operations of table objects; Returns "Success" / "Failure" accordingly
  public String insert_into_table(args)                       --inserts data into table 
  public String delete_from_table(args)                       --deletes data from table
  public String update_in_table(args)                         --updates data in table
 public List run_query(String HQl_Query,int n);                     --runs hql query and returns the list pointing to database objects
  


  */


public class Plant_handler extends sample_helper {
    private Session session;
     //public  final static boolean FLAG=true;          --inherited from interface

    public Plant_handler() {
    } 
  
    
    public String insert_into_table(String name,String owner)
  {
        boolean error_flag=false;
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    hibernate.pojo.TblPlant plant= new hibernate.pojo.TblPlant();
        org.hibernate.Transaction tx = null;
        try
        {
           // org.hibernate.Transaction tx=session.beginTransaction();
            tx=session.beginTransaction();
              //(new BigDecimal(3), "babu", "kochu");
          // plant.setIPlantId(new BigDecimal(id));
            plant.setTPlantName(name);
            plant.setTPlantOwner(owner);
            
            session.save(plant);
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
           if(error_flag==false) return plant.getIPlantId().toString();
           else         return "Failure";
        }   
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String delete_from_table(int id) 
   {
        
    session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    boolean error_flag=false;
        org.hibernate.Transaction tx = null;
        try
        {
           // org.hibernate.Transaction tx=session.beginTransaction();
            Plant_handler ph=new Plant_handler();
       TblPlant pl=ph.get_tuple(id);
       if(pl==null)     throw  new Exception("plant id not found");
       
            tx=session.beginTransaction();
            TblPlant plant= new TblPlant();  //(new BigDecimal(3), "babu", "kochu");
           plant.setIPlantId(new BigDecimal(id));
            //plant.setTPlantName("ja khusi");
            //plant.setTPlantOwner("ami");          
            session.delete(plant);
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
    
   
   
    
public TblPlant get_tuple(int id)   //, String &name, String &owner)
{
    session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;
    TblPlant plant = null;
        try
        {
           // org.hibernate.Transaction tx=session.beginTransaction();
            tx=session.beginTransaction();
             plant=(TblPlant) session.get(TblPlant.class ,new BigDecimal(id));
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
}

      /*
            if new parameters contain null value, then the previous value of that particular fiels will be retained
    */
    public String update_in_table(int id,String name,String owner) {
        
       session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
        
       boolean error_flag=false;
        
       org.hibernate.Transaction tx = null;
        try
        {
           // org.hibernate.Transaction tx=session.beginTransaction();
            
     TblPlant plant=(TblPlant) session.get(TblPlant.class ,new BigDecimal(id));
      
                 
        tx=session.beginTransaction();
          
           plant.setTPlantName(name);  
           plant.setTPlantOwner(owner);    
        // session.update(plant);
       //  session.flush();
            tx.commit();
        }
        catch(Exception e)
        {
           error_flag = true; 
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

    
    
}
