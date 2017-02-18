/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.reservation.impl;

import java.util.HashMap;
import lk.ijse.thogakade.service.SuperService;

/**
 *
 * @author Ranjith Suranga
 */
public class Reservation implements lk.ijse.thogakade.reservation.Reservation{
    
    private HashMap<String, SuperService> reserveList = new HashMap<>();
    
    public boolean reserve(String customerId, SuperService superService){
        
        if (reserveList.containsKey(customerId)){
            if (reserveList.get(customerId) == superService){
                return true;
            }else{
                return false;
            }
        }else{
            reserveList.put(customerId, superService);
            return true;
        }
    }
    
    public boolean release(String customerId){
        if (reserveList.containsKey(customerId)){
            reserveList.remove(customerId);
            return true;
        }else{
            return false;
        }
    }
    
}
