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
import lk.ijse.thogakade.controller.custom.CustomerController;
import lk.ijse.thogakade.dto.CustomerDTO;
import lk.ijse.thogakade.observers.Observer;
import lk.ijse.thogakade.service.ServiceFactory;
import lk.ijse.thogakade.service.custom.CustomerService;
import net.sf.jasperreports.engine.JasperPrint;


public class CustomerControllerImpl implements CustomerController {
    private CustomerService customerService;
    private JasperPrint fillReport;
    public CustomerControllerImpl() throws Exception {
            customerService = (CustomerService) ServerConnectorFactory.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
    }

    
    @Override
    public boolean add(CustomerDTO t) throws Exception {
       return customerService.add(t);
    }
    @Override
    public void registerObserver(Observer observer) {
        try {
            customerService.registerObserver(observer);
        } catch (RemoteException ex) {
            Logger.getLogger(CustomerControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void unregisterObserver(Observer observer) {
        try {
            customerService.unregisterObserver(observer);
        } catch (RemoteException ex) {
            Logger.getLogger(CustomerControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public boolean reserve(String customerId) throws RemoteException {
        return customerService.reserve(customerId, customerService);
    }
    @Override
    public boolean release(String customerId) throws RemoteException {
        return customerService.release(customerId);
    }
    @Override
    public boolean update(CustomerDTO dto) throws Exception {
        return customerService.update(dto);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return customerService.delete(id);
    }
    @Override
    public ArrayList<CustomerDTO> getAll() throws Exception {
        return customerService.getAll();
    }
    @Override
    public CustomerDTO get(String id) throws Exception {
        return customerService.get(id);
    }
    public JasperPrint getJasper()throws Exception{
        return customerService.getJasper();
    }
}
