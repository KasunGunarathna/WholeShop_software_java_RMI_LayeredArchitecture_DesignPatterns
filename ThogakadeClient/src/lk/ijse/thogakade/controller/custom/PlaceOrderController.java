/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.controller.custom;

import java.util.ArrayList;
import lk.ijse.thogakade.controller.SuperController;
import lk.ijse.thogakade.dto.*;


public interface PlaceOrderController extends SuperController<SuperDTO>{
    public OrderDTO getorderId() throws Exception;
    public boolean placeOrderSave(OrderDTO dto,ArrayList<OrderDetailDTO> arList) throws Exception;

}
