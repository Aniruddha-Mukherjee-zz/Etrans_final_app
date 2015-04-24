/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.helper;

import hibernate.pojo.TblLivePath;
import java.math.BigDecimal;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ani
 */
public class LivePath_handler extends sample_helper{

    private Session session;
          
    public TblLivePath get_tuple(int id) {
    
        session=hibernate.NewHibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;  
   TblLivePath lp=null;
      try
        {
           // org.hibernate.Transaction tx=session.beginTransaction();
            tx=session.beginTransaction();
            lp=(TblLivePath) session.get(TblLivePath.class, new BigDecimal(id));
            //pr=(TblProcess) session.get(TblProcess.class,new BigDecimal(id));
            tx.commit();
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
             return lp;
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
        LivePath_handler lh=new LivePath_handler();
        //Plant_handler ph=new Plant_handler();
       TblLivePath l=lh.get_tuple(id);
        //TblPlant pl=ph.get_tuple(id);
       if(l==null)     throw  new Exception("plant id not found");
       
            tx=session.beginTransaction();
            TblLivePath l1=new TblLivePath();
            //TblPlant plant= new TblPlant();  //(new BigDecimal(3), "babu", "kochu");
           l1.setIPathId(new BigDecimal(id));
            //plant.setTPlantName("ja khusi");
            //plant.setTPlantOwner("ami");          
            session.delete(l1);
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
       public String update_in_table (int path_id,String cur_path,String head)
    {
              session = hibernate.NewHibernateUtil.getSessionFactory().openSession();
       
        boolean error_flag=false;
        
        Transaction tx = null;
        try
        {         
            TblLivePath vhc=(TblLivePath) session.get(TblLivePath.class,new BigDecimal(path_id));
            //  TblCard Card=(TblCard) session.get(TblCard.class,new BigDecimal(card_id));                              
            
           tx=session.beginTransaction();
           
          vhc.setTCurrPath(cur_path);
          vhc.setTNext(head);
          
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
    
}
