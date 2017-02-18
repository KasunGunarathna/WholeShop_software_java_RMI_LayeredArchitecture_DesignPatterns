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
import lk.ijse.thogakade.business.custom.OrderBO;
import lk.ijse.thogakade.dao.*;
import lk.ijse.thogakade.dao.custom.CustomerDAO;
import lk.ijse.thogakade.dao.custom.ItemDAO;
import lk.ijse.thogakade.dao.custom.OrderDAO;
import lk.ijse.thogakade.dao.custom.OrderDetailDAO;
import lk.ijse.thogakade.dto.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author student
 */

public class OrderBOImpl implements OrderBO{
    private OrderDAO orderDAO;
    private OrderDetailDAO orderDetailDAO;
    private ItemDAO itemDAO;
    private CustomerDAO customerDAO;
    private Connection connection;
        
    public OrderBOImpl() throws Exception {
        
        orderDAO=(OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
        orderDetailDAO=(OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
        itemDAO=(ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
        customerDAO=(CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    }

    @Override
    public boolean placeOrderSave(OrderDTO dto, ArrayList<OrderDetailDTO> arList) throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        orderDAO.setConnection(connection);
        orderDetailDAO.setConnection(connection);
        itemDAO.setConnection(connection);
        boolean result=orderDAO.add(dto);
        if(!result){
            connection.setAutoCommit(true);
            connection.close();
            return result;
        }
        for(OrderDetailDTO odto:arList){
            
            result= orderDetailDAO.add( odto);
            if(!result) {
                connection.rollback();
                connection.setAutoCommit(result);
                connection.close();
                return result;
            }
            ItemDTO itemDTO=new ItemDTO(odto.getItemCode());
            itemDTO = (ItemDTO) itemDAO.getbyID(itemDTO.getCode());
            int qty=itemDTO.getQtyOnHand()-odto.getQty();
            itemDTO.setQtyOnHand(qty);
            result= itemDAO.update(itemDTO);
            
            if(!result){
                connection.rollback();
                connection.setAutoCommit(result);
                connection.close();
                return result;
            }           
            
        }
        connection.commit();
        connection.setAutoCommit(result);
        connection.close();
        return result;
        
        
    }

    @Override
    public OrderDTO getorderId() throws Exception {
        connection=ConnectionFactory.getInstance().getConnection();
        orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
        orderDAO.setConnection(connection);
        OrderDTO dto=new OrderDTO();
        ArrayList<OrderDTO> al=orderDAO.getAll();
        OrderDTO x=al.get(al.size()-1);
        String lastId=x.getId();
        int a=Integer.parseInt(lastId.substring(1))+1;
        
        
        
        System.out.println(a);
        if(a<10){
            dto.setId("D00"+a);
        }else if(a<100){
            dto.setId("D0"+a);
        }else{
            dto.setId("D"+a);
        }
        connection.close();
        return dto;
    }

    public JasperPrint getJasper(){
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ijse/thogakade/jasperreports/InvoicePlace.jasper"));
            JasperPrint fillReport = JasperFillManager.fillReport(compileReport, new HashMap<String,Object>(), connection);
            return fillReport;
             
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
