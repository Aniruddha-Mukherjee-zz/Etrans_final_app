package epos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import hibernate.*;
import hibernate.pojo.*;

/**
 *
 * @author ani
 */
public class display {
    public display(TblVehicleFlight x)
    {
        if(x!=null)
        {
            System.out.println(x.getIVfid()+"\t"+x.getTblEpos().getIMachineId()+"\t"+x.getTblCard().getCCardId()+"\t"+x.getTblMapping().getITripId()+"\t"+x.getDtTime());
        }
        else    System.err.println("Cannot print Null value");
    }

    public display(TblPlant x)
    {
         if(x!=null)
        {
                System.out.println("\n"+x.getIPlantId()+"\t"+x.getTPlantName()+"\t"+x.getTPlantOwner());
        }
        else    System.err.println("Cannot print Null value");
    }

    public display(TblCard cd) {
         if(cd!=null)
        {
            System.out.println(cd.getCCardId()+"\t"+cd.getTblPlant().getIPlantId());
        }
          else    System.err.println("Cannot print Null value");
    }

    public display(TblEpos ep) {
        if(ep!=null)
        {
            System.out.println(ep.getIMachineId()+"\t"+ep.getTblPlant().getIPlantId()+"\t"+ep.getITerminalId()+"\t"+ep.getTGatewayName());
        }
        else    System.err.println("Cannot print null value");
        
    }

    public display(TblDriver ep){
       if(ep!=null)
        {
            System.out.println(ep.getIDriverId()+"\t"+ep.getTblTransporter().getITransporterId()+"\t"+ep.getTblPlant().getIPlantId()+"\t"+ep.getTLicenceNo());           
// System.out.println(ep.getIMachineId()+"\t"+ep.getTblPlant().getIPlantId()+"\t"+ep.getITerminalId()+"\t"+ep.getTGatewayName());
        }
        else    System.err.println("Cannot print null value");       
      
    }

    public display(TblDo ep) {
        if(ep!=null)
        {
        System.out.println(ep.getIDoId()+"\t"+ep.getTblPlant().getIPlantId()+"\t"+ep.getTConsignee()+"\t"+ep.getTConsigner()+"\t"+ep.getTDeliveryAddress()+"\t");    
    //System.out.println(ep.getIMachineId()+"\t"+ep.getTblPlant().getIPlantId()+"\t"+ep.getITerminalId()+"\t"+ep.getTGatewayName());
        }
        else    System.err.println("Cannot print null value");
        
    }

    public display(TblTransporter ep) { 
    
       if(ep!=null)
        {
            System.out.println(ep.getTTransporterName()+"\t"+ep.getITransporterPin()+"\t"+ep.getTTransporterAddr()+"\t"+ep.getTTransporterCity()+"\t"+ep.getTTransporterEmailId()+"\t"+ep.getTTransporterMobileNo()+"\t"+ep.getTTransporterState());
//System.out.println(ep.getIDriverId()+"\t"+ep.getTblTransporter()+"\t"+ep.getTblPlant()+"\t"+ep.getTLicenceNo());           
// System.out.println(ep.getIMachineId()+"\t"+ep.getTblPlant().getIPlantId()+"\t"+ep.getITerminalId()+"\t"+ep.getTGatewayName());
        }
        else    System.err.println("Cannot print null value");       
    }
    
    public display(TblData ep) { 
    
       if(ep!=null)
        {
                System.out.println(ep.getIDataId()+"\t"+ep.getEposData()+"\t"+ep.getseen());
        }
        else    System.err.println("Cannot print null value");       
    }
    
}
