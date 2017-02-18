/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.controller.custom;

import java.rmi.RemoteException;
import java.util.ArrayList;
import lk.ijse.thogakade.controller.SuperController;
import lk.ijse.thogakade.dto.CustomerDTO;
import net.sf.jasperreports.engine.JasperPrint;


public interface CustomerController extends SuperController<CustomerDTO>{
    public boolean reserve(String customerId)throws RemoteException;
    public boolean release(String customerId)throws RemoteException;
    public boolean add(CustomerDTO t) throws Exception;
    public CustomerDTO get(String id) throws Exception;
    public boolean update(CustomerDTO dto) throws Exception;
    public boolean delete(String id) throws Exception;
    public ArrayList<CustomerDTO> getAll() throws Exception;
    public JasperPrint getJasper()throws Exception;
}
