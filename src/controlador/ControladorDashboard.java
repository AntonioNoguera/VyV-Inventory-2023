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
        this.dVista.btnGuardar.addActionListener(this);
    }
    
    public void listar(JTable tabla){ 
        modelo=(DefaultTableModel)tabla.getModel();
        modelo.setRowCount(0);
        List<Movimientos> lista=dao.listar();
        Object[] object = new Object[6]; 
        for(int i=lista.size()-1;i>-1;i--){
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
        if(e.getSource()==dVista.btnGuardar){
            agregar();
        }
    }
    
    public void agregar(){
        Movimientos m = new Movimientos();
        
        String tipoMov = (String) dVista.comboEntrada.getSelectedItem();
        Integer elementoID = (dVista.comboElemento.getSelectedIndex())+1;
        Float cantidad = Float.valueOf(dVista.txtCantidad.getText());
        
        
        
        int result = dao.Agregar(new Movimientos(tipoMov, elementoID,cantidad));
        if(result==1){
            System.out.println("Ingresado con éxito");
            dVista.comboEntrada.setSelectedIndex(0);
            dVista.comboElemento.setSelectedIndex(0);
            dVista.txtCantidad.setText(" ");
            
        }else{
            System.out.println("ERROR");
        }
            
        listar(dVista.MovimientosTabla);
    }
   
}

