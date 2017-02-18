/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.service.custom;

import java.util.ArrayList;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.service.SuperService;

/**
 *
 * @author lahiru
 */
public interface ItemService extends SuperService<ItemDTO>{
    public boolean add(ItemDTO dto) throws Exception;
    public ItemDTO get(String id)throws Exception;
    public boolean update(ItemDTO dto)throws Exception;
    public boolean delete(String id)throws Exception;
    public ArrayList<ItemDTO> getAll() throws Exception;
    
}
