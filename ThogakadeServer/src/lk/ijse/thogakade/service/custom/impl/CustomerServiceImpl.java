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
import lk.ijse.thogakade.dto.CustomerDTO;
import lk.ijse.thogakade.observers.Observer;
import lk.ijse.thogakade.reservation.impl.Reservation;
import lk.ijse.thogakade.service.SuperService;
import lk.ijse.thogakade.service.custom.CustomerService;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author student
 */
public class CustomerServiceImpl extends UnicastRemoteObject implements CustomerService{
    private CustomerBO customerBO;
    private static ArrayList<Observer> alObservers = new ArrayList<>();
    
    private static Reservation customerReservation = new Reservation();
    
    public CustomerServiceImpl()throws Exception{
        customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
        
    }
    
    @Override
    public boolean add(CustomerDTO customer) throws Exception {
            boolean result = customerBO.add(customer);
            notifyAllObservers();
            return result;
    }

    @Override
    public CustomerDTO get(String id) throws Exception {
            CustomerDTO result =customerBO.get(id);
            return result;
    }
    

    @Override
    public ArrayList<CustomerDTO> getAll() throws Exception {
            ArrayList<CustomerDTO> all=customerBO.getAll();
            return all;
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
    public boolean update(CustomerDTO dto) throws Exception {
            boolean result = customerBO.update(dto);
            notifyAllObservers();
            return result;
    }


    @Override
    public boolean reserve(String Id, SuperService service) throws RemoteException {
        return customerReservation.reserve(Id, this);
    }

    @Override
    public boolean release(String Id) throws RemoteException {
        return customerReservation.release(Id);
    }

    @Override
    public boolean delete(String id) throws Exception {
            boolean result = customerBO.delete(id);
            notifyAllObservers();
            return result;
    }

    public JasperPrint getJasper()throws Exception{
        return customerBO.getJasper();
    }
    
}
