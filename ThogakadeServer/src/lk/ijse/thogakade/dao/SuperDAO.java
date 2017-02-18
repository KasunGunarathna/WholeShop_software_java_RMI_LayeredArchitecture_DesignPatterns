/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.thogakade.dto.SuperDTO;

/**
 *
 * @author student
 */
public interface SuperDAO<T extends SuperDTO> {
    
    
    
    public void setConnection(Connection connection)throws Exception;
    public Connection getConnection()throws Exception;
    
    public default boolean add(T dto) throws Exception {
        Class<? extends SuperDAO> aClass = this.getClass();

        Field constTabelName = aClass.getDeclaredField("TABLE_NAME");
        constTabelName.setAccessible(true);
        String tableName = (String) constTabelName.get(this);

        Connection connection = this.getConnection();
        Class<? extends SuperDTO> aClass1 = dto.getClass();
        Field[] declaredFields = aClass1.getDeclaredFields();
        String sqlStm = "INSERT INTO " + tableName + " VALUES(";
        for (int i = 1; i < declaredFields.length; i++) {
            sqlStm += "?,";
        }
        sqlStm += "?)";

        PreparedStatement pst = connection.prepareStatement(sqlStm);
        int i = 1;

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Object value = declaredField.get(dto);
            pst.setObject(i, value);
            i++;
        }

        int result = pst.executeUpdate();

        return result > 0;
    }
    public default boolean update(T dto) throws Exception {
        Class<? extends SuperDAO> aClass = this.getClass();

        Field constTabelName = aClass.getDeclaredField("TABLE_NAME");
        constTabelName.setAccessible(true);
        String tableName = (String) constTabelName.get(this);

        Connection connection = this.getConnection();

        Class<? extends SuperDTO> aClass1 = dto.getClass();
        Field[] declaredFields = aClass1.getDeclaredFields();
        Statement statement = connection.createStatement();
        ResultSet rstColoumnHedding = statement.executeQuery("desc " + tableName + " ;");

        rstColoumnHedding.next();
        String primaryField = rstColoumnHedding.getString(1);

        String sqlStm = "UPDATE " + tableName + " set ";

        while (rstColoumnHedding.next()) {
            sqlStm += (rstColoumnHedding.getString(1) + " = ?,");
        }
        sqlStm = sqlStm.substring(0, sqlStm.length() - 1);
        sqlStm += " WHERE " + primaryField + "=?;";
        PreparedStatement pst = connection.prepareStatement(sqlStm);
        int i = 4;

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Object value = declaredField.get(dto);
            if (i == 4) {
                pst.setObject(i, value);
                i = 0;
            } else {
                pst.setObject(i, value);
            }
            i++;
        }
        int result = pst.executeUpdate();

        return result > 0;

    }

    
//     public boolean add(T dto)throws Exception;
//     public boolean update(T dto)throws Exception;
     public boolean deletebyID(String id)throws Exception;
     public T getbyID(String id)throws Exception;
     public ArrayList<T> getAll()throws Exception;
     
    


}
