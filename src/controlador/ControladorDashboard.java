/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.DashboardDAO;
import modelo.Movimientos;
import vistas.MainDashBoard;

/**
 *
 * @author Antonio Noguera
 */
public class ControladorDashboard implements ActionListener{ 
    DashboardDAO dao = new DashboardDAO();
    Movimientos e = new Movimientos();
    MainDashBoard dVista = new MainDashBoard();
    DefaultTableModel modelo = new DefaultTableModel(); 
    
    public ControladorDashboard(MainDashBoard v){
        this.dVista=v;  
    }
    
    public void listar(JTable tabla){ 
        modelo=(DefaultTableModel)tabla.getModel();
        modelo.setRowCount(0);
        List<Movimientos> lista=dao.listar();
        Object[] object = new Object[6]; 
        for(int i=0;i<lista.size();i++){
            object[0] = lista.get(i).getMovimiento_ID();
            object[1] = lista.get(i).getElementoNombre();
            object[2] = lista.get(i).getMovimiento_Cant();
             
            object[3] = lista.get(i).getMovimiento_Tipo();
            object[4] = lista.get(i).getMovimiento_Tiempo();
            modelo.addRow(object); 
        }
        dVista.MovimientosTabla.setModel(modelo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

