/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Timestamp;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
                this.dVista.jButton1.addActionListener(this);
        this.dVista.MovimientosTabla.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = dVista.MovimientosTabla.getSelectedRow();
                    if (selectedRow != -1) {
                        // Obtener datos de la fila seleccionada
                        Object[] rowData = new Object[modelo.getColumnCount()];
                         
                        for (int i = 0; i < modelo.getColumnCount(); i++) {
                            rowData[i] = modelo.getValueAt(selectedRow, i);
                        }
                        dVista.txtMovimientoID.setText(String.valueOf(rowData[0]));
                        dVista.comboEntrada.setSelectedItem(String.valueOf(rowData[3]));
                        dVista.comboElemento.setSelectedItem(String.valueOf(rowData[1]));
                        dVista.txtCantidad.setText(String.valueOf(rowData[2]));  
                    }
                }
            }
        });
        
        this.dVista.btnEliminar.addActionListener(this);
        this.dVista.btnActualizar.addActionListener(this);
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
        
        if(e.getSource()==dVista.btnEliminar){
            eliminar();
        }
        
        if(e.getSource()==dVista.btnActualizar){
            actualizar();
        }
        
        if(e.getSource()==dVista.jButton1){
            ClearALL();
        }
    }
    
    public void eliminar(){
        Movimientos m = new Movimientos();
        
        m.setMovimiento_ID(Integer.valueOf(dVista.txtMovimientoID.getText()));
         if(dao.Eliminar(m)==1){
            ClearALL();
            
        }else{
            System.out.println("ERROR");
        }
           
        listar(dVista.MovimientosTabla);
    }

    public void actualizar(){  
        
        String tipoMov = (String) dVista.comboEntrada.getSelectedItem();
        String elementoNombre = (String) dVista.comboElemento.getSelectedItem();
        Float cantidad = Float.valueOf(dVista.txtCantidad.getText());
        Integer id = Integer.valueOf(dVista.txtMovimientoID.getText()); 
        System.out.println(tipoMov+" "+elementoNombre+" "+cantidad+" "+id+" ");
        dao.Actualizar(new Movimientos(id,tipoMov, elementoNombre,cantidad));
        listar(dVista.MovimientosTabla);
    }
    
    public void ClearALL(){
        
        System.out.println("CALLED"); 
        dVista.txtMovimientoID.setText(" ");
        dVista.comboEntrada.setSelectedIndex(0);
        dVista.comboElemento.setSelectedIndex(0);
        dVista.txtCantidad.setText(" ");
    }
    
    public void agregar(){
        Movimientos m = new Movimientos();
        System.out.println("CONTROLLER");
        String tipoMov = (String) dVista.comboEntrada.getSelectedItem();
        String elementoNombre = (String) dVista.comboElemento.getSelectedItem();
        Float cantidad = Float.valueOf(dVista.txtCantidad.getText()); 
        
        int result = dao.Agregar(new Movimientos(tipoMov, elementoNombre,cantidad));
        if(result==1){
            dVista.comboEntrada.setSelectedIndex(0);
            dVista.comboElemento.setSelectedIndex(0);
            dVista.txtCantidad.setText(" ");
            
        }else{
            System.out.println("ERROR");
        }
            
        listar(dVista.MovimientosTabla);
    }
   
    public void arrayMembers(){
        JComboBox<String> comboBox = dVista.comboElemento;
        comboBox.removeAllItems();
        
        List<String> miembros = dao.elementos();
        for(String memberUnit : miembros){
            comboBox.addItem(memberUnit);
        }
        
    }
}

