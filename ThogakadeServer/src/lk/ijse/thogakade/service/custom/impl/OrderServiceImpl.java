/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import lk.ijse.thogakade.business.BOFactory;
import lk.ijse.thogakade.business.custom.CustomerBO;
import lk.ijse.thogakade.business.custom.ItemBO;
import lk.ijse.thogakade.business.custom.OrderBO;
import lk.ijse.thogakade.dto.OrderDTO;
import lk.ijse.thogakade.dto.OrderDetailDTO;
import lk.ijse.thogakade.observers.Observer;
import lk.ijse.thogakade.reservation.impl.Reservation;
import lk.ijse.thogakade.service.SuperService;
import lk.ijse.thogakade.service.custom.OrderService;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author student
 */
public class OrderServiceImpl extends UnicastRemoteObject implements OrderService{
    private OrderBO orderBO;
    private ItemBO itemBO;
    private CustomerBO customerBO;
    private static ArrayList<Observer> alObservers = new ArrayList<>();
    private static Reservation orderReservation = new Reservation(); 
    
    public OrderServiceImpl() throws Exception{
            orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
            itemBO=(ItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
            customerBO=(CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    }

    @Override
    public OrderDTO getorderId() throws Exception{
        OrderDTO result =orderBO.getorderId();
        return result;
    }

    @Override
    public void registerObserver(Observer observer) throws RemoteException {
        alObservers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) throws RemoteException {
        alObservers.remove(observer);
    }

    @Override
    public void notifyAllObservers() throws RemoteException {
        for (Observer alObserver : alObservers) {
            alObserver.update();
        }
    }

    @Override
    public boolean reserve(String Id, SuperService service) throws RemoteException {
        return orderReservation.reserve(Id, this);
    }

    @Override
    public boolean release(String Id) throws RemoteException {
            return orderReservation.release(Id);
    }

    @Override
    public boolean placeOrderSave(OrderDTO dto, ArrayList<OrderDetailDTO> arList) throws Exception {
        boolean result=orderBO.placeOrderSave(dto, arList);
        notifyAllObservers();
        return result;
    }

    @Override
    public JasperPrint getJasper() throws Exception {
        return orderBO.getJasper();
    }

 

    

    
}
