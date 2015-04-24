/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.helper;
import hibernate.pojo.TblDo;
import hibernate.pojo.TblPlant;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import static hibernate.helper.sample_helper.FLAG;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
    Handles operations of table objects; Returns "Success" / "Failure" accordingly
  public String insert_into_table(args)                       --inserts data into table 
  public String delete_from_table(args)                       --deletes data from table
  public String update_in_table(args)                         --updates data in table
 public List run_query(String HQl_Query);                     --runs hql query and returns the list pointing to database objects
  
*/

public class Do_handler extends sample_helper{
    private Session session;

    
//@Override
    public TblDo get_tuple(int id) {
    session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;  
    TblDo d=null;
      try
        {
           // org.hibernate.Transaction tx=session.beginTransaction();
            tx=session.beginTransaction();
            d=(TblDo) session.get(TblDo.class,new BigDecimal(id));
            if(d==null) throw new Exception();
            }
         catch(Exception e)
        {
            System.err.println(" not found do corresponding to id given"); 
            if (tx != null) {
                tx.rollback();
            e.printStackTrace();
        }
        }
        finally
        {
            session.close();
             return d;
        }      
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
    
    public String insert_into_table(int do_id,int plant_id,Date issue_date,Date issue_time,String consignee, String consigner, String delivery_address) {
        
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
        
        boolean error_flag=false;
        
        Transaction tx = null;
        TblDo card=new TblDo();
        
        try
        {   
            tx=session.beginTransaction();
            
            card.setIDoId(new BigDecimal(do_id));
            Plant_handler ph=new Plant_handler();
            TblPlant plant= ph.get_tuple(plant_id);
           
            if(plant!=null)
                    card.setTblPlant(plant);
            else    throw new Exception();
            card.setDtIssueDate(issue_date);
           // card.setDtIssueTime(issue_time);
            card.setTConsignee(consignee);
            card.setTConsigner(consigner);
            card.setTDeliveryAddress(delivery_address);
            
            session.save(card);
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
            if(error_flag==false) return card.getIDoId().toString();//"Success";
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
            
           Do_handler d=new Do_handler();
            TblDo trp=d.get_tuple(id);
            if(trp==null)   throw  new Exception("Tuple not found corresponding to do_id");
            
            tx=session.beginTransaction();
            TblDo doo=new TblDo();
            doo.setIDoId(new BigDecimal(id));
            
            
            session.delete(doo);
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

  
    



    public String update_in_table(int do_id,int plant_id,Date issue_date,Date issue_time,String consignee, String consigner, String delivery_address) {
     
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
           // tx=session.beginTransaction();
           TblDo doo=(TblDo) session.get(TblDo.class,new BigDecimal(do_id));
            
           Plant_handler ph=new Plant_handler();
           TblPlant plant= ph.get_tuple(plant_id);
           tx=session.beginTransaction();
           
           //if(FLAG==true)   
           {
           if(plant==null)  throw  new Exception();
           }
           
             doo.setTblPlant(plant);
            //else          throw  new Exception();
           // session.save(card);
              doo.setDtIssueDate(issue_date);
            //doo.setDtIssueTime(issue_time);
            doo.setTConsignee(consignee);
            doo.setTConsigner(consigner);
            doo.setTDeliveryAddress(delivery_address);
            session.save(doo);
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
