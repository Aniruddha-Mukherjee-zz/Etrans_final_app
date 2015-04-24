/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.helper;
import hibernate.pojo.TblCard;
import hibernate.pojo.TblPlant;
import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.helper.Plant_handler;
import java.math.BigDecimal;
import java.util.List;

/*
    Handles operations of table objects; Returns "Success" / "Failure" accordingly
  public String insert_into_table(args)                       --inserts data into table 
  public String delete_from_table(args)                       --deletes data from table
  public String update_in_table(args)                         --updates data in table
 public List run_query(String HQl_Query,int n);                     --runs hql query and returns the list pointing to database objects
  


  
*/

public class Card_handler extends sample_helper{
    private Session session;

    /**
     *
     * @param HQL_Query
     * @return
     */



    public TblCard get_tuple(String id) {
    session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;  
    TblCard Card=null;
      try
        {
           // org.hibernate.Transaction tx=session.beginTransaction();
            tx=session.beginTransaction();
            Card=(TblCard) session.get(TblCard.class,id);
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
             return Card;
        }      
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
    
    public String insert_into_table(String card_id,int plant_id) {
        
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
        
        boolean error_flag=false;
        
        Transaction tx = null;
        TblCard card=new TblCard();
        try
        {   
            tx=session.beginTransaction();
            
            card.setCCardId(card_id);
            
            Plant_handler ph=new Plant_handler();
            TblPlant plant= ph.get_tuple(plant_id);
           
            if(plant!=null)
                    card.setTblPlant(plant);
            else    throw new Exception();
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
            if(error_flag==false) return card.getCCardId();
           else         return "Failure";
        }
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
public String delete_from_table(String id) {
   
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
        
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
            
            Card_handler t=new Card_handler();
            TblCard trp=t.get_tuple(id);
            if(trp==null)   throw  new Exception();
            
            tx=session.beginTransaction();
            TblCard card=new TblCard();
            card.setCCardId(id);
            
            
            session.delete(card);
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

  
    



    public String update_in_table(String card_id, int plant_id) {
     
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {   
           // tx=session.beginTransaction();
           TblCard Card=(TblCard) session.get(TblCard.class,card_id);
            
           Plant_handler ph=new Plant_handler();
           TblPlant plant= ph.get_tuple(plant_id);
           tx=session.beginTransaction();
           
           
           {
           if(plant==null)  throw  new Exception();
           }
           
             Card.setTblPlant(plant);
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
