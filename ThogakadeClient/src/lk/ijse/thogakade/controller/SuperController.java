/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.controller;

import java.rmi.RemoteException;
import lk.ijse.thogakade.dto.SuperDTO;
import lk.ijse.thogakade.observers.Observer;
import net.sf.jasperreports.engine.JasperPrint;


public interface SuperController <T extends SuperDTO> { 
    public JasperPrint getJasper()throws Exception;
    public void registerObserver(Observer observer) throws RemoteException;
    public void unregisterObserver(Observer observer) throws RemoteException;
    
    
    
}
