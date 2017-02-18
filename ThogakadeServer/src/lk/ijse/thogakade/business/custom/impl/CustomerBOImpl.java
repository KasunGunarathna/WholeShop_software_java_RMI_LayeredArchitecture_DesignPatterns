/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.business.custom.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.thogakade.business.custom.CustomerBO;
import lk.ijse.thogakade.dao.ConnectionFactory;
import lk.ijse.thogakade.dao.DAOFactory;
import lk.ijse.thogakade.dao.custom.CustomerDAO;
import lk.ijse.thogakade.dto.CustomerDTO;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author student
 */
public class CustomerBOImpl implements CustomerBO{
    private CustomerDAO customerDAO;
    private Connection connection;

    public CustomerBOImpl() throws Exception {
//            this.customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
//            customerDAO.setConnection(connection);
    }
    
    /**
     *
     * @param customer
     * @return
     * @throws Exception
     */
    @Override
    public boolean add(CustomerDTO customer) throws Exception{
        connection=ConnectionFactory.getInstance().getConnection();
        customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        customerDAO.setConnection(connection);
        boolean result = customerDAO.add(customer);
        connection.close();
        return result;
    }


    @Override
    public ArrayList<CustomerDTO> getAll() throws Exception {
        connection=ConnectionFactory.getInstance().getConnection();
        customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        customerDAO.setConnection(connection);
        ArrayList<CustomerDTO> all = customerDAO.getAll();
        connection.close();
        return all;
        
    }

    @Override
    public boolean update(CustomerDTO dto) throws Exception {
        connection=ConnectionFactory.getInstance().getConnection();
        customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        customerDAO.setConnection(connection);
        boolean result = customerDAO.update(dto);
        connection.close();
        return result;
    }

    @Override
    public boolean delete(String dto) throws Exception {
        connection=ConnectionFactory.getInstance().getConnection();
        customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        customerDAO.setConnection(connection);
        boolean result = customerDAO.deletebyID(dto);
        connection.close();
        return result;
    }

    @Override
    public CustomerDTO get(String dto) throws Exception {
        connection=ConnectionFactory.getInstance().getConnection();
        customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        customerDAO.setConnection(connection);
        CustomerDTO result = customerDAO.getbyID(dto);
        connection.close();
        return result;
    }

    @Override
    public JasperPrint getJasper() throws Exception {
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ijse/thogakade/jasperreports/InvoiceCus.jasper"));
            JasperPrint fillReport = JasperFillManager.fillReport(compileReport, new HashMap<String,Object>(), connection);
            return fillReport;
             
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    

    
}
