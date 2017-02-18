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
import lk.ijse.thogakade.business.custom.ItemBO;
import lk.ijse.thogakade.dao.ConnectionFactory;
import lk.ijse.thogakade.dao.DAOFactory;
import lk.ijse.thogakade.dao.custom.ItemDAO;
import lk.ijse.thogakade.dto.ItemDTO;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author lahiru
 */
public class ItemBOImpl implements ItemBO{
    private ItemDAO itemDAO;
    private Connection connection;   
    
    public ItemBOImpl() throws Exception {
            
        
    }
     
    @Override
    public boolean add(ItemDTO dto) throws Exception {
        connection=ConnectionFactory.getInstance().getConnection();
        itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
        itemDAO.setConnection(connection);
        boolean result = itemDAO.add(dto);
        connection.close();
        return result;
    }
    
    @Override
    public ArrayList<ItemDTO> getAll() throws Exception {
        connection=ConnectionFactory.getInstance().getConnection();
        itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
        itemDAO.setConnection(connection);
        ArrayList<ItemDTO> all = itemDAO.getAll();
        connection.close();
        return all;
    }



    @Override
    public boolean update(ItemDTO dto) throws Exception {
        connection=ConnectionFactory.getInstance().getConnection();
        itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
        itemDAO.setConnection(connection);
        boolean result = itemDAO.update(dto);
        connection.close();
        return result;
    }

 

    @Override
    public boolean delete(String id) throws Exception {
        connection=ConnectionFactory.getInstance().getConnection();
        itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
        itemDAO.setConnection(connection);
        boolean result = itemDAO.deletebyID(id);
        connection.close();
        return result;
    }

    @Override
    public ItemDTO get(String id) throws Exception {
        connection=ConnectionFactory.getInstance().getConnection();
        itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
        itemDAO.setConnection(connection);
        ItemDTO result = itemDAO.getbyID(id);
        connection.close();
        return result;
    }

    @Override
    public JasperPrint getJasper() throws Exception {
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ijse/thogakade/jasperreports/InvoiceItem.jasper"));
            JasperPrint fillReport = JasperFillManager.fillReport(compileReport, new HashMap<String,Object>(), connection);
            return fillReport;
             
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    

    
    
}
