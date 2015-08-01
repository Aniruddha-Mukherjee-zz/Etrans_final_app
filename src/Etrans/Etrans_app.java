/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Etrans;

import epos.Alpha_module;

/**
 *
 * @author ani
 */
public class Etrans_app {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
       // System.out.println("Hello");
        //The argument to alpha module marks the number of rows to processed at a time. if argument is <=0 then all rows with boolean seen will be processed right away
        Thread t=new Thread(new Alpha_module(0));
       t.start();
       t.join();
       System.exit(2);
        
        // TODO code application logic here
    }
    
}
