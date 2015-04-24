/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/*import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
*/
/**
 *
 * @author ani
 */
public class date_tester {
   /* public static void main(String[] args) throws ParseException {
        String str_date="11062007 070845";
DateFormat formatter=new SimpleDateFormat("ddMMyyyy hhmmss") ;
Date date1 = formatter.parse(str_date);
Date date2  = formatter.parse("12062007 070845");
//System.out.println("Report Date: " + today);

DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

 //String x= df.format(today);
 
 //Date today2 =df.parse(str_date);
long difference = date2.getTime() - date1.getTime();
System.out.println("comparison result: " + difference/1000);
    }
    */
    private static int[] just()
    {
        int a[]={1,2,3};       
        return a;
    }
    
    public static void main(String[] args) {
        /*String Str="Px9600:,D,0,30640121,000000000101,03A956E8,163900,16032015,160315062# ";
        String values[]=Str.split(",");
        try {
            Date date_time = new SimpleDateFormat("ddMMyyyyHHmmss").parse(values[7]+values[6]);
            String x=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date_time);
            System.out.println(date_time.getTime());
        
        } 
        catch (ParseException ex) {
            Logger.getLogger(date_tester.class.getName()).log(Level.SEVERE, null, ex);
        
        }*/
        int x[]=just();
        for(int in: x)
        {
            System.out.println(in+"\t");
        }
    }
}
