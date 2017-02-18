/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.reservation;

import java.rmi.RemoteException;
import lk.ijse.thogakade.service.SuperService;

/**
 *
 * @author Ranjith Suranga
 */
public interface Reservation {
    public boolean reserve(String Id, SuperService service)throws RemoteException;
    
    public boolean release(String cstomerId)throws RemoteException;        
}
