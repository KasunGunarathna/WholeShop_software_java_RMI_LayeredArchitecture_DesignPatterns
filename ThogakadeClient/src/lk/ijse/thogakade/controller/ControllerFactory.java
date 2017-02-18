/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.controller;

import lk.ijse.thogakade.controller.custom.impl.CustomerControllerImpl;
import lk.ijse.thogakade.controller.custom.impl.ItemContrllerImpl;
import lk.ijse.thogakade.controller.custom.impl.PlaceOrderControllerImpl;

public class ControllerFactory {
    
    public enum ControllerTypes{
        CUSTOMER,PLACE_ORDER,ITEM;
    }
    
    private static ControllerFactory controllerFactory;
    
    private ControllerFactory(){
    }
    public static ControllerFactory getInstance(){
        if (controllerFactory == null){
            controllerFactory = new ControllerFactory();
        }
        return controllerFactory;
    }

    public SuperController getController(ControllerTypes controllerType) throws Exception{
        switch(controllerType){
            case CUSTOMER:
                return new CustomerControllerImpl();
            case PLACE_ORDER:
                return new PlaceOrderControllerImpl();
            case ITEM:
                return new ItemContrllerImpl();    
            default:
                return null;
        }
    }
    
}
