/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.business.custom;

import java.util.ArrayList;
import lk.ijse.thogakade.business.SuperBO;
import lk.ijse.thogakade.dto.CustomerDTO;

/**
 *
 * @author student
 */
public interface CustomerBO extends SuperBO<CustomerDTO>{
    public boolean add(CustomerDTO dto) throws Exception;
    public CustomerDTO get(String dto) throws Exception;
    public boolean update(CustomerDTO dto) throws Exception;
    public boolean delete(String dto) throws Exception;
    public ArrayList<CustomerDTO> getAll() throws Exception;
    
}
