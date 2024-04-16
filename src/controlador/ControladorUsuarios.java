/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import modelo_dao.UsuarioDAO;
import vistas.MovimientosVista;
import vistas.UsuariosVista;

/**
 *
 * @author Michael Noguera
 */
public class ControladorUsuarios implements ActionListener, KeyListener { 
    UsuariosVista uVista = new UsuariosVista();
    DefaultTableModel modelo = new DefaultTableModel(); 
    UsuarioDAO daoInstance = new UsuarioDAO();
    
    public ControladorUsuarios(UsuariosVista u){
        System.out.println("Runned COntroler bidn?");
        this.uVista = u;
        
        //Buttons
        this.uVista.btnDenegar.addActionListener(this);
        this.uVista.btnAceptarSolicitud.addActionListener(this);
        this.uVista.btnLimpiarCancelar.addActionListener(this);
        this.uVista.btnEliminarUsuario.addActionListener(this);
        this.uVista.btnEstablecerPermisos.addActionListener(this);
        this.uVista.btnVolver.addActionListener(this);
        
        
        
        //Tables
        // this.uVista.btnDenegar.addActionListener(this);
        // this.uVista.btnDenegar.addActionListener(this);
        
        //Combo
        //this.uVista.btnDenegar.addActionListener(this);
        
        this.uVista.TablaSolicitudes.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    
                    int selectedRow = uVista.TablaSolicitudes.getSelectedRow();
                    if (selectedRow != -1) {
                        // Obtener datos de la fila seleccionada
                        Object[] rowData = new Object[modelo.getColumnCount()];
                         
                        for (int i = 0; i < modelo.getColumnCount(); i++) {
                            rowData[i] = modelo.getValueAt(selectedRow, i);
                        }
                        
                        /*
                        eVista.txtElementoID.setText(String.valueOf(rowData[0]));
                        eVista.txtElementoNombre.setText(String.valueOf(rowData[1]));
                        eVista.txtElementoDesc.setText(String.valueOf(rowData[2]));
                        String[] splitedChain = String.valueOf(rowData[3]).split(" ");
                        eVista.txtElementoCantidad.setText(String.valueOf(splitedChain[0]));
                        eVista.txtElementoUnidad.setText(String.valueOf(splitedChain[1]));
                        eVista.comboBoxGrupo.setSelectedItem(String.valueOf(rowData[4]));
                                
                        System.out.println(Arrays.toString(rowData));
                         */
                        
                    }
                }
            }
        });
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Nuevos
        if(e.getSource() == uVista.btnAceptarSolicitud){ 
            System.out.println("CLICKED RIGHT");
        }
        
        if(e.getSource() == uVista.btnDenegar){ 
            System.out.println("CLICKED RIGHT");
        }
        
        //Existentes
        if(e.getSource() == uVista.btnEstablecerPermisos){ 
            System.out.println("CLICKED RIGHT");
        }
        
        if(e.getSource() == uVista.btnEliminarUsuario){ 
            
            System.out.println("CLICKED RIGHT");
        }
        
        if(e.getSource() == uVista.btnLimpiarCancelar){ 
            System.out.println("CLICKED RIGHT");
        }
        
        //Volver
        if(e.getSource() == uVista.btnVolver){ 
            System.out.println("CLICKED RIGHT");
            MovimientosVista mVista = new MovimientosVista(); 

            mVista.setVisible(true);
            mVista.setLocationRelativeTo(null); 
            
            uVista.setVisible(false);
            uVista.dispose();
            
        }
        
           
    }
    
    public void launch(){
        System.out.println("testing");
    }
    
    public void listar(JTable tabla) throws SQLException{
        System.out.println("Listar lanzado");
        modelo = (DefaultTableModel)tabla.getModel();
        modelo.setRowCount(0);
        
        List<Usuario> lista= daoInstance.getUsers(0);
        
        Object[] object = new Object[6]; 
        for(int i=0;i<lista.size();i++){
            object[0] = lista.get(i).getUsuario_ID(); 
            object[1] = lista.get(i).getUsuario_Nombre(); 
            object[2] = lista.get(i).getUsuario_Completo(); 
            object[3] = lista.get(i).getUsuario_Telefono();
            object[4] = lista.get(i).getUsuario_Permisos();
            
            modelo.addRow(object); 
        }
        
        uVista.TablaSolicitudes.setModel(modelo);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
