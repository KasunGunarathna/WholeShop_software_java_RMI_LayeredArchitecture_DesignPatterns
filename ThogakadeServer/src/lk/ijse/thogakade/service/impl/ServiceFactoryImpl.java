/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLDataException;
import lk.ijse.thogakade.service.ServiceFactory;
import lk.ijse.thogakade.service.SuperService;
import lk.ijse.thogakade.service.custom.impl.CustomerServiceImpl;
import lk.ijse.thogakade.service.custom.impl.ItemServiceImpl;
import lk.ijse.thogakade.service.custom.impl.OrderServiceImpl;

/**
 *
 * @author student
 */
public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory{
    private static ServiceFactory serviceFactory;
    
    private ServiceFactoryImpl()throws Exception{
        
        
    }
    
    public static ServiceFactory getInstance()throws Exception{
        if (serviceFactory == null){
            serviceFactory = new ServiceFactoryImpl();
        }
        return serviceFactory;
    }

    @Override
    public SuperService getService(ServiceTypes serviceType) throws Exception {
        switch(serviceType){
            case CUSTOMER:
                return new CustomerServiceImpl();
            case ORDER:
                return new OrderServiceImpl();
            case ITEM:
                return new ItemServiceImpl();
            default:
                return null;
        }
    }

    
}
