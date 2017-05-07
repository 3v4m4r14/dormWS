/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dormitorywsclient;

import ee.ttu.idu0075._2015.ws.dormitory.AddDormitoryRequest;
import ee.ttu.idu0075._2015.ws.dormitory.AddDormitoryResponse;
import ee.ttu.idu0075._2015.ws.dormitory.DormitoryType;
import ee.ttu.idu0075._2015.ws.dormitory.GetDormitoryRequest;
import java.math.BigInteger;

/**
 * Client application for Dormitory Web Service.
 * 
 * @author Eva Maria Veitmaa
 */
public class DormitoryWSClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String token = "asd";
        
        AddDormitoryRequest addDormReq = new AddDormitoryRequest();
        addDormReq.setToken(token);
        addDormReq.setRequestCode(BigInteger.ONE);
        addDormReq.setAdministrativeArea("Tallinn");
        addDormReq.setDormitoryAddress("Akadeemia tee 5");
        addDormReq.setDormitoryCapacity(BigInteger.valueOf(250));
        addDormReq.setDormitoryOwner("TTU");
        addDormReq.setDormitoryCondition("old");
        
        try {
            addDormitory(addDormReq);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
      
        GetDormitoryRequest getDormReq = new GetDormitoryRequest();
        getDormReq.setToken(token);
        getDormReq.setId(BigInteger.valueOf(1));
        System.out.println("Dormitory with id=1: \n" + getDormitory(getDormReq));
    }
    
    private static AddDormitoryResponse addDormitory(AddDormitoryRequest parameter) {
        ee.ttu.idu0075._2015.ws.dormitory.DormitoryService1 service = new ee.ttu.idu0075._2015.ws.dormitory.DormitoryService1();
        ee.ttu.idu0075._2015.ws.dormitory.DormitoryPortType port = service.getDormitoryPort();
        return port.addDormitory(parameter);
    }
    
    private static DormitoryType getDormitory(GetDormitoryRequest parameter) {
        ee.ttu.idu0075._2015.ws.dormitory.DormitoryService1 service = new ee.ttu.idu0075._2015.ws.dormitory.DormitoryService1();
        ee.ttu.idu0075._2015.ws.dormitory.DormitoryPortType port = service.getDormitoryPort();
        return port.getDormitory(parameter);
    }
}
