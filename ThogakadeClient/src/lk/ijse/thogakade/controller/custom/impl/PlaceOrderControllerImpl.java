/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.controller.custom.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.thogakade.controller.ServerConnectorFactory;
import lk.ijse.thogakade.controller.custom.PlaceOrderController;
import lk.ijse.thogakade.dto.OrderDTO;
import lk.ijse.thogakade.dto.OrderDetailDTO;
import lk.ijse.thogakade.observers.Observer;
import lk.ijse.thogakade.service.ServiceFactory;
import lk.ijse.thogakade.service.custom.*;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author student
 */
public class PlaceOrderControllerImpl  implements PlaceOrderController{

    private final  OrderService orderService;
    private final  ItemService itemService;
    private final  CustomerService customerService;
    
    public PlaceOrderControllerImpl() throws Exception{
        orderService=(OrderService) ServerConnectorFactory.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);
        itemService=(ItemService) ServerConnectorFactory.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
        customerService=(CustomerService) ServerConnectorFactory.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
                
    }

    @Override
    public OrderDTO getorderId() throws Exception {
        return orderService.getorderId();
    }

    @Override
    public void registerObserver(Observer observer) {
        try {
            orderService.registerObserver(observer);
        } catch (RemoteException ex) {
            Logger.getLogger(CustomerControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void unregisterObserver(Observer observer){
        try {
            orderService.unregisterObserver(observer);
        } catch (RemoteException ex) {
            Logger.getLogger(CustomerControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean placeOrderSave(OrderDTO dto, ArrayList<OrderDetailDTO> arList) throws Exception {
        return orderService.placeOrderSave(dto, arList);
    }

    @Override
    public JasperPrint getJasper() throws Exception {
        return orderService.getJasper();
    }
 
}
