/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.dao.custom.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.thogakade.dao.custom.OrderDAO;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.dto.OrderDTO;

/**
 *
 * @author student
 */
public class OrderDAOImpl implements OrderDAO {

    private Connection connection = null;
    private final String TABLE_NAME = "Orders";
    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;

    }
    @Override
    public Connection getConnection() {
        return connection;
    }

//    @Override
//    public boolean add(OrderDTO dto) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

//    @Override
//    public boolean update(OrderDTO dto) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public boolean deletebyID(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderDTO getbyID(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<OrderDTO> getAll() throws Exception {
        
        ArrayList<OrderDTO> alOrder = new ArrayList<>();
        String sql = "select * from Orders";
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);

        while (rset.next()) {
            String id = rset.getString(1);
            Date date = rset.getDate(2);
            String customerId = rset.getString(3);
            OrderDTO order = new OrderDTO(id, date, customerId);
            alOrder.add(order);
        }
        
        return alOrder;
    }
}
