/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.service.custom;

import java.util.ArrayList;
import lk.ijse.thogakade.dto.CustomerDTO;
import lk.ijse.thogakade.service.SuperService;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author student
 */
public interface CustomerService extends SuperService<CustomerDTO>{
    public boolean add(CustomerDTO dto) throws Exception;
    public CustomerDTO get(String id)throws Exception;
    public boolean update(CustomerDTO dto)throws Exception;
    public boolean delete(String id)throws Exception;
    public ArrayList<CustomerDTO> getAll() throws Exception;
    public JasperPrint getJasper()throws Exception;
}
