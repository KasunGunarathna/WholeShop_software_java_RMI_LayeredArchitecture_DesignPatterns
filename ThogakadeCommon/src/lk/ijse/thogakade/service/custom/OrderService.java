/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.service.custom;

import java.util.ArrayList;
import lk.ijse.thogakade.dto.OrderDTO;
import lk.ijse.thogakade.dto.OrderDetailDTO;
import lk.ijse.thogakade.dto.SuperDTO;
import lk.ijse.thogakade.service.SuperService;

/**
 *
 * @author student
 */
public interface OrderService extends SuperService<SuperDTO>{
    public boolean placeOrderSave(OrderDTO dto,ArrayList<OrderDetailDTO> arList) throws Exception;
    public OrderDTO getorderId() throws Exception;
    
}
