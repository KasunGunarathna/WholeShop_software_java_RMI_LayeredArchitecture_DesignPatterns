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
import lk.ijse.thogakade.business.custom.ItemBO;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.observers.Observer;
import lk.ijse.thogakade.reservation.impl.Reservation;
import lk.ijse.thogakade.service.SuperService;
import lk.ijse.thogakade.service.custom.ItemService;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author lahiru
 */
public class ItemServiceImpl extends UnicastRemoteObject implements ItemService {

    private ItemBO itemBO;
    private static ArrayList<Observer> alObservers = new ArrayList<>();
    
    private static Reservation itemReservation = new Reservation();
    
    public ItemServiceImpl()throws Exception{
        itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
    }

    

    @Override
    public boolean add(ItemDTO dto) throws Exception {
        boolean result =itemBO.add(dto);
        notifyAllObservers();
        return result;
    }

    @Override
    public ArrayList<ItemDTO> getAll() throws Exception {
            return itemBO.getAll();
        
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
    public boolean update(ItemDTO dto) throws Exception {
            boolean result = itemBO.update(dto);
            notifyAllObservers();
            return result;
    }

 

    @Override
    public boolean reserve(String code, SuperService service) throws RemoteException {
        return itemReservation.reserve(code, this);
    }

    @Override
    public boolean release(String code) throws RemoteException {
        return itemReservation.release(code);
    }

    @Override
    public ItemDTO get(String code) throws Exception {
        ItemDTO result =itemBO.get(code);
        return result;
    }

    @Override
    public boolean delete(String id) throws Exception {
        boolean result = itemBO.delete(id);
            notifyAllObservers();
            return result;
    }

    @Override
    public JasperPrint getJasper() throws Exception {
        return itemBO.getJasper();
    }
    

    
}
