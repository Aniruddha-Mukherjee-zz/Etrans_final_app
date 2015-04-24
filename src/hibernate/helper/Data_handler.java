/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.helper;


import hibernate.pojo.TblData;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ani
 */
public class Data_handler extends sample_helper{
     private Session session;
     
     
   
        
  public String update_in_table( int data_id, boolean  seen) {
     
       session = hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        boolean error_flag=false;
        String str=null;
        
        Transaction tx = null;
        try
        {   
           TblData data=(TblData) session.get(TblData.class,new BigDecimal(data_id));
                
            
           tx=session.beginTransaction();
           
           str=data.getEposData();
           
          data.setEposData(str);
          data.setSeen(seen) ; 
          session.save(str);
            tx.commit();
          
           
            throw new Exception("Method Not Overwritten Yet");
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

  public void mark_seen_as_true(int id)
  {
      session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        
        Transaction tx = null;
        try
        {   
            TblData map=(TblData) session.get(TblData.class,new BigDecimal(id));
           tx=session.beginTransaction();
           map.setSeen(true);
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
  }
    //@Override
    public TblData get_tuple(int data_id) {
        
    session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;
    TblData data = null;
    try
        {
           // org.hibernate.Transaction tx=session.beginTransaction();
            tx=session.beginTransaction();
         data=(TblData) session.get(TblData.class,new BigDecimal(data_id));  
        
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
             return data;
        }      
       
        
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
