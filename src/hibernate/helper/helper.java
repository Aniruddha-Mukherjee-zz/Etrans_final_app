/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.helper;

import java.util.List;


/*
    Handles operations of table objects; Returns "Success" / "Failure" accordingly
  public String insert_into_table(args)                       --inserts data into table 
  public String delete_from_table(args)                       --deletes data from table
  public String update_in_table(args)                         --updates data in table
 public List run_query(String HQl_Query);                     --runs hql query and returns the list pointing to database objects
  
public  final static boolean FLAG=true;     //true indicates not to replace solid values with null values in update query

  public String insert_into_table()                       --inserts test data into table
  public String delete_from_table()                       --deletes test data from table
  public String update_in_table()                         --updates test data in table
  public String get_tuple(int id)                       --get tuple corresponding to primary key  
*/

public interface helper {

   
    public Object get_tuple(int id);
    
    public String insert_into_table();
    //public void insert_into_table_test();
    
    public  String delete_from_table();
    //public  void delete_from_table_test();
    
    public String update_in_table();
    //public void update_in_table_test();
    
   //public List run_query(String HQl_Query);
    
     
}
