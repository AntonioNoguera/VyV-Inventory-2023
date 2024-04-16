/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo_dao.MovimientosDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.security.Timestamp;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel; 
import modelo.Movimientos;
import vistas.MovimientosVista;

/**
 *
 * @author Antonio Noguera
 */
public class ControladorMovimientos implements ActionListener, KeyListener {  
    MovimientosDAO dao = new MovimientosDAO();
    Movimientos e = new Movimientos();
    MovimientosVista dVista = new MovimientosVista();
    DefaultTableModel modelo = new DefaultTableModel(); 
    
    public ControladorMovimientos(MovimientosVista v){
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
                        
                        dVista.btnEliminar.setEnabled(true);
                        dVista.jButton1.setEnabled(true);
                        dVista.btnGuardar.setEnabled(false);
                        dVista.btnActualizar.setEnabled(true); 
                        dVista.comboElemento.setEnabled(false);
                    }
                }
            }
        });
        
        this.dVista.btnEliminar.addActionListener(this); 
        this.dVista.txtCantidad.addKeyListener(this);
        this.dVista.btnActualizar.addActionListener(this);
        baseStateButtons();   
    }
    
    void baseStateButtons(){
        this.dVista.btnGuardar.setEnabled(false);
        this.dVista.btnEliminar.setEnabled(false);
        this.dVista.jButton1.setEnabled(false);
        dVista.comboElemento.setEnabled(true);
        dVista.comboEntrada.setEnabled(true);
        dVista.btnActualizar.setEnabled(false);  
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
         
        if(e.getSource()==dVista.jButton1){
            ClearALL();
        }
        
        if(e.getSource()==dVista.btnActualizar){
            actualizar();
        }
    }
    
    public void eliminar(){
        String tipoMov = (String) dVista.comboEntrada.getSelectedItem();
        String elementoNombre = (String) dVista.comboElemento.getSelectedItem();
        Float cantidad = Float.valueOf(dVista.txtCantidad.getText());
        Integer id = Integer.valueOf(dVista.txtMovimientoID.getText());  
        if(dao.Eliminar(new Movimientos(id,tipoMov, elementoNombre,cantidad))==1){
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
        dVista.txtMovimientoID.setText("");
        dVista.comboEntrada.setSelectedIndex(0);
        
        try{
            dVista.comboElemento.setSelectedIndex(0);
        }catch(Exception e){
            System.out.println("ComboBox Vacio");
        }
        
        dVista.txtCantidad.setText("");
        dVista.MovimientosTabla.clearSelection();
        baseStateButtons();
    }
    
    public void agregar(){
        Movimientos m = new Movimientos();
        System.out.println("CONTROLLER");
        String tipoMov = (String) dVista.comboEntrada.getSelectedItem();
        String elementoNombre = (String) dVista.comboElemento.getSelectedItem();
        
        if(!validation(dVista.txtCantidad.getText())){
            return ;
        }
        
        Float cantidad = Float.valueOf(dVista.txtCantidad.getText());
        
        int result = dao.Agregar(new Movimientos(tipoMov, elementoNombre,cantidad));
        if(result==1){
            ClearALL();
            
        }else{
            System.out.println("ERROR");
        }
        listar(dVista.MovimientosTabla);
        
    }
    
    boolean validation(String amount){
        if(amount.isBlank()){
            JOptionPane.showMessageDialog(null, "La cantidad del movimiento no puede ser nula.",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(!numberValidation(amount)){
            JOptionPane.showMessageDialog(null, "La cantidad del movimiento debe ser un número decimal o entero", 
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(amount.length()>32){
            JOptionPane.showMessageDialog(null, "La longitud del movimiento no puede ser mayor a 32 caracteres.", 
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(Float.parseFloat(amount)<=0){
            JOptionPane.showMessageDialog(null, "La cantidad del debe ser mayor a cero.", 
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    public static boolean numberValidation(String texto) {  
        return texto.matches("\\d+(\\.\\d+)?");
    }
   
    public void arrayMembers(){
        JComboBox<String> comboBox = dVista.comboElemento;
        comboBox.removeAllItems();
        
        List<String> miembros = dao.elementos();
        for(String memberUnit : miembros){
            comboBox.addItem(memberUnit);
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        verifyingTextBoxs();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        verifyingTextBoxs();
    }
    
    void verifyingTextBoxs(){
        try{
            if(!this.dVista.txtCantidad.getText().isBlank() && 
                this.dVista.txtMovimientoID.getText().isBlank() &&
                !this.dVista.comboElemento.getSelectedItem().equals("")
                    ){
                this.dVista.btnGuardar.setEnabled(true);
            }else{
                this.dVista.btnGuardar.setEnabled(false);
            }

            this.dVista.jButton1.setEnabled(true);
        
        }catch(Exception e){
            System.out.println("COMBO BOX VACIO");
        }
        
        
    }
}

