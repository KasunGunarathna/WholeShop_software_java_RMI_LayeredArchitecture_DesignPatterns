/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.service;

import java.rmi.Remote;
import lk.ijse.thogakade.dto.SuperDTO;
import lk.ijse.thogakade.observers.Subject;
import lk.ijse.thogakade.reservation.Reservation;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author student
 * @param <T>
 */
public interface SuperService<T extends SuperDTO> extends Remote, Subject, Reservation{
    public JasperPrint getJasper()throws Exception;
}
