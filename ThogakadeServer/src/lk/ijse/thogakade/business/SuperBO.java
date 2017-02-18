/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.business;

import lk.ijse.thogakade.dto.SuperDTO;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author student
 * @param <T>
 */
public interface SuperBO<T extends SuperDTO> {
    public JasperPrint getJasper()throws Exception;
}
