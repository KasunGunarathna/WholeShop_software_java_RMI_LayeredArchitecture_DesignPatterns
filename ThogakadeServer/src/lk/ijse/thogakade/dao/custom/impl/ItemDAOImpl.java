/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.thogakade.dao.custom.ItemDAO;
import lk.ijse.thogakade.dto.ItemDTO;

/**
 *
 * @author kasun
 */
public class ItemDAOImpl implements ItemDAO{
    private Connection connection=null;
    private final String TABLE_NAME = "Item";
    
    @Override
    public void setConnection(Connection connection) {
        this.connection=connection;
        
    }
    @Override
    public Connection getConnection() {
        return connection;
    }

//    @Override
//    public boolean add(ItemDTO dto) throws Exception {
//        PreparedStatement pst = connection.prepareStatement("INSERT INTO Item VALUES(?,?,?,?)");
//        pst.setObject(1, dto.getCode());
//        pst.setObject(2, dto.getDescription());
//        pst.setObject(3, dto.getUnitPrice());
//        pst.setObject(4, dto.getQtyOnHand());
//        int result = pst.executeUpdate();
//        return (result > 0);
//    }

//    @Override
//    public boolean update(ItemDTO dto) throws Exception {
//        String sql = "UPDATE Item SET description = ?, unitPrice = ?, qtyOnHand = ? WHERE code = ?";
//        PreparedStatement pst = connection.prepareStatement(sql);
//        pst.setObject(4, dto.getCode());
//        pst.setObject(1, dto.getDescription());
//        pst.setObject(2, dto.getUnitPrice());
//        pst.setObject(3, dto.getQtyOnHand());
//        int result = pst.executeUpdate();
//        return (result > 0);
//    }

    @Override
    public boolean deletebyID(String id) throws Exception {
        String sql = "DELETE FROM Item WHERE code = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setObject(1, id);
        int result = pst.executeUpdate();
        return (result > 0);
    }

    @Override
    public ItemDTO getbyID(String id) throws Exception {
        ItemDTO item = null;
        String sql = "SELECT * FROM Item WHERE code = '" + id + "' ";
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);
        if (rset.next()) {
            item = new ItemDTO(
                    rset.getString(1),
                    rset.getString(2),
                    rset.getDouble(3),
                    rset.getInt(4));
        }
        return item;
    }

    @Override
    public ArrayList<ItemDTO> getAll() throws Exception {
        ArrayList<ItemDTO> alItems = new ArrayList<>();
        String sql = "select * from Item";
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);

        while (rset.next()) {
            String code = rset.getString(1);
            String description = rset.getString(2);
            double unitPrice = rset.getDouble(3);
            int qtyOnHand = rset.getInt(4);

            ItemDTO item = new ItemDTO(code, description, unitPrice, qtyOnHand);
            alItems.add(item);
        }
        
        return alItems;
    }

    
}
