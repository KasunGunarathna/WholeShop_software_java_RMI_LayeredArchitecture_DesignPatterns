/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.controller;

import java.rmi.Naming;
import lk.ijse.thogakade.service.ServiceFactory;
import lk.ijse.thogakade.service.SuperService;
import lk.ijse.thogakade.service.custom.*;

public class ServerConnectorFactory {
    private static ServerConnectorFactory serverConnector;
    private static ServiceFactory serviceFactory;
    private static CustomerService customerService;
    private static OrderService orderService;
    private static ItemService itemService;
    

    private  ServerConnectorFactory() throws Exception{
        serviceFactory = (ServiceFactory) Naming.lookup("rmi://localhost:5050/ThogakadeServer");
        customerService = (CustomerService) serviceFactory.getService(ServiceFactory.ServiceTypes.CUSTOMER);
        orderService=(OrderService) serviceFactory.getService(ServiceFactory.ServiceTypes.ORDER);
        itemService=(ItemService) serviceFactory.getService(ServiceFactory.ServiceTypes.ITEM);
        }
    
    public static ServerConnectorFactory getInstance() throws Exception{
        if (serverConnector==null) {
            serverConnector = new ServerConnectorFactory();
        }
        return serverConnector;
    
    }
//    public  void getRegisted(Observer observer) throws Exception{
//        serviceFactory.registerObserver(observer);
//    }
    
    public SuperService getService(ServiceFactory.ServiceTypes serviceType){
        switch(serviceType){
            case CUSTOMER:
                return customerService;
            case ORDER:
                return orderService;
            case ITEM:
                return itemService;
            default:
                return null;
        }
    }
}
