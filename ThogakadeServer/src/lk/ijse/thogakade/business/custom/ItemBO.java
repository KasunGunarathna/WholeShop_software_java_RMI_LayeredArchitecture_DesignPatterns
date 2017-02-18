/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.business.custom;

import java.util.ArrayList;
import lk.ijse.thogakade.business.SuperBO;
import lk.ijse.thogakade.dto.ItemDTO;

/**
 *
 * @author lahiru
 */
public interface ItemBO extends SuperBO<ItemDTO>{
    public boolean add(ItemDTO dto) throws Exception;
    public ItemDTO get(String dto) throws Exception;
    public boolean update(ItemDTO dto) throws Exception;
    public boolean delete(String dto) throws Exception;
    public ArrayList<ItemDTO> getAll() throws Exception;
    
}
