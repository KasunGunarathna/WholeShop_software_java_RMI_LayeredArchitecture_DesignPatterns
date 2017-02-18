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
import lk.ijse.thogakade.controller.custom.ItemController;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.observers.Observer;
import lk.ijse.thogakade.service.ServiceFactory;
import lk.ijse.thogakade.service.custom.ItemService;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author kasun
 */
public class ItemContrllerImpl implements ItemController{
    private ItemService itemService;

    public ItemContrllerImpl() throws Exception {
            itemService =  (ItemService) ServerConnectorFactory.getInstance().getService(ServiceFactory.ServiceTypes.ITEM);
    }

    @Override
    public boolean add(ItemDTO t) throws Exception {
       return itemService.add(t);
    }
    @Override
    public void registerObserver(Observer observer) {
        try {
            itemService.registerObserver(observer);
        } catch (RemoteException ex) {
            Logger.getLogger(CustomerControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void unregisterObserver(Observer observer) {
        try {
            itemService.unregisterObserver(observer);
        } catch (RemoteException ex) {
            Logger.getLogger(CustomerControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public boolean reserve(String itemId) throws RemoteException {
        return itemService.reserve(itemId, itemService);
    }

    @Override
    public boolean release(String itemId) throws RemoteException {
        return itemService.release(itemId);
    }

    @Override
    public boolean update(ItemDTO dto) throws Exception {
        return itemService.update(dto);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return itemService.delete(id);
    }


    @Override
    public ArrayList<ItemDTO> getAll() throws Exception {
        return itemService.getAll();
    }

    @Override
    public ItemDTO get(String id) throws Exception {
        return itemService.get(id);
    }

    @Override
    public JasperPrint getJasper() throws Exception {
        return itemService.getJasper();
    }




}
