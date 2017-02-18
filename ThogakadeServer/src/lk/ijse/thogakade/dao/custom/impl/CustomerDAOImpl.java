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
import lk.ijse.thogakade.dao.custom.CustomerDAO;
import lk.ijse.thogakade.dto.CustomerDTO;

/**
 *
 * @author student
 */

public class CustomerDAOImpl implements CustomerDAO{
    private Connection connection=null;
    
    private final String TABLE_NAME = "Customer";
    
    @Override
    public void setConnection(Connection connection) throws Exception{
        this.connection=connection;
        
    }
    @Override
    public Connection getConnection() throws Exception{
        return this.connection;
    }

//    @Override
//    public boolean add(CustomerDTO dto) throws Exception {
//        PreparedStatement pst = connection.prepareStatement("INSERT INTO Customer VALUES(?,?,?,?)");
//        pst.setObject(1, dto.getId());
//        pst.setObject(2, dto.getName());
//        pst.setObject(3, dto.getAddress());
//        pst.setObject(4, dto.getSalary());
//        int result = pst.executeUpdate();
//        return (result > 0);
//    }

//    @Override
//    public boolean update(CustomerDTO dto) throws Exception {
//        String sql = "UPDATE Customer SET name = ?, address = ?, salary = ? WHERE id = ? ";
//        PreparedStatement pst = connection.prepareStatement(sql);
//        pst.setObject(4, dto.getId());
//        pst.setObject(1, dto.getName());
//        pst.setObject(2, dto.getAddress());
//        pst.setObject(3, dto.getSalary());
//        int result = pst.executeUpdate();
//        return (result > 0);
//    }

    @Override
    public boolean deletebyID(String id) throws Exception {
        String sql = "DELETE FROM Customer WHERE id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setObject(1, id);
        int result = pst.executeUpdate();
        return (result > 0);
    }

    @Override
    public CustomerDTO getbyID(String id) throws Exception {
        CustomerDTO customer = null;
        String sql = "SELECT * FROM Customer WHERE id = '" + id + "' ";
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);
        if (rset.next()) {
            customer = new CustomerDTO(
                    rset.getString(1),
                    rset.getString(2),
                    rset.getString(3),
                    rset.getDouble(4));
        }
        return customer;
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws Exception {
        ArrayList<CustomerDTO> alCustomers = new ArrayList<>();
        String sql = "select * from Customer";
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);

        while (rset.next()) {
            String id = rset.getString("id");
            String name = rset.getString("name");
            String address = rset.getString("address");
            double salary = rset.getDouble("salary");

            CustomerDTO customer = new CustomerDTO(id, name, address, salary);
            alCustomers.add(customer);
        }
        
        return alCustomers;
    }

    

    
    
}
