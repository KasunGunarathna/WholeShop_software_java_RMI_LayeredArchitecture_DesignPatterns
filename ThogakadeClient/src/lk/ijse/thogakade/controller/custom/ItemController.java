/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.controller.custom;

import java.rmi.RemoteException;
import java.util.ArrayList;
import lk.ijse.thogakade.controller.SuperController;
import lk.ijse.thogakade.dto.ItemDTO;

public interface ItemController extends SuperController<ItemDTO>{
    public boolean reserve(String itemId)throws RemoteException;
    public boolean release(String itemId)throws RemoteException;
    public boolean add(ItemDTO t) throws Exception;
    public ItemDTO get(String id) throws Exception;
    public boolean update(ItemDTO t) throws Exception;
    public boolean delete(String id) throws Exception;
    public ArrayList<ItemDTO> getAll() throws Exception;
}
