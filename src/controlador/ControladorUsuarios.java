package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane; 
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
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
    DefaultTableModel modeloB = new DefaultTableModel(); 
    UsuarioDAO daoInstance = new UsuarioDAO();
    
    Usuario actualUser = new Usuario() ;
    
    Integer selectedID = -1;
    
    public ControladorUsuarios(UsuariosVista u){ 
        this.uVista = u;
        
        //Buttons
        this.uVista.btnDenegar.addActionListener(this);
        this.uVista.btnAceptarSolicitud.addActionListener(this);
        this.uVista.btnLimpiarCancelar.addActionListener(this);
        this.uVista.btnEliminarUsuario.addActionListener(this);
        this.uVista.btnEstablecerPermisos.addActionListener(this);
        this.uVista.btnVolver.addActionListener(this); 
        
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
                        
                        if(! actualUser.getUsuario_Nombre().equals(String.valueOf(rowData[1]))){
                            
                            uVista.TablaUsuarios.clearSelection(); 

                            setSelecction(rowData[2].toString(), rowData[4].toString());

                            selectedID = (Integer) rowData[0];

                            pendingUsers();
                        } else { 
                            startButtons();JOptionPane.showMessageDialog(null, "No es posible establecer permisos a tu usuario.", 
                                                "Alerta", JOptionPane.WARNING_MESSAGE);
                            
                            System.out.println("Logged User" + actualUser.getUsuario_Nombre().toString());
                        }
                    }
                }
            }
        });
        
         this.uVista.TablaUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    
                    int selectedRow = uVista.TablaUsuarios.getSelectedRow();
                    if (selectedRow != -1) {
                        
                        Object[] rowData = new Object[modeloB.getColumnCount()];
                        
                        for (int i = 0; i < modeloB.getColumnCount(); i++) {
                            rowData[i] = modeloB.getValueAt(selectedRow, i);
                        } 
                        if( !actualUser.getUsuario_Nombre().equals(String.valueOf(rowData[1]))){
                            
                        
                            uVista.TablaSolicitudes.clearSelection();   
                            setSelecction(rowData[2].toString(), rowData[4].toString()); 

                            selectedID = (Integer) rowData[0];

                            enabledUsers();
                        }else {
                            startButtons();
                            startButtons();JOptionPane.showMessageDialog(null, "No es posible modificar los permisos de tu usuario.", 
                                                "Alerta", JOptionPane.WARNING_MESSAGE);

                        }
                    }
                }
            }
        });
         
         setTableSizes();
    }
    
    public void setTableSizes(){
        this.uVista.TablaSolicitudes.getColumnModel().getColumn(0).setPreferredWidth(20);
        this.uVista.TablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(20);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
 
        for (int columnIndex = 0; columnIndex < this.uVista.TablaSolicitudes.getColumnCount(); columnIndex++) {
            this.uVista.TablaSolicitudes.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }
        
        for (int columnIndex = 0; columnIndex < this.uVista.TablaUsuarios.getColumnCount(); columnIndex++) {
            this.uVista.TablaUsuarios.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }
    }
    
    public void setSelecction(String userName,String userType){
        
        ArrayList<String> rolesDisponibles = new ArrayList<>(List.of( "Empleado", "Administrador"));

        
        Integer selection= rolesDisponibles.indexOf(userType);
        
        if(selection != -1){ 
            uVista.comboBoxPermisos.setSelectedIndex(selection);
        }else{
            System.out.println("selection "+ selection); 
        }
        
        uVista.txtUsuarioSeleccionado.setText(userName);
    }
    
    public void setUser(Usuario user){
        this.actualUser = user;
        System.out.println("Logged User" + actualUser.getUsuario_Nombre().toString());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Nuevos
        if(e.getSource() == uVista.btnAceptarSolicitud){ 
            daoInstance.SetPermisos(selectedID ,uVista.comboBoxPermisos.getSelectedItem().toString());
            startButtons();
            listarTablas();
        }
        
        if(e.getSource() == uVista.btnDenegar){ 
            daoInstance.Eliminar(selectedID);
            startButtons();
            listarTablas();
        }
        
        //Existentes
        if(e.getSource() == uVista.btnEstablecerPermisos){ 
            daoInstance.SetPermisos(selectedID ,uVista.comboBoxPermisos.getSelectedItem().toString());
            startButtons();
            listarTablas();
        }
        
        if(e.getSource() == uVista.btnEliminarUsuario){  
            daoInstance.Eliminar(selectedID);
            startButtons();
            listarTablas();
        }
        
        if(e.getSource() == uVista.btnLimpiarCancelar){ 
            startButtons();
        }
        
        //Volver
        if(e.getSource() == uVista.btnVolver){ 
            launchMovementView(this.actualUser);
            
        }
           
    }
    
    public void listarTablas(){
        try {
            listarPendientes();
            listarActivos();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void launchMovementView(Usuario datos){
        MovimientosVista mVista = new MovimientosVista();
        mVista.setVisible(true);
        mVista.setLocationRelativeTo(null); 
        
        ControladorMovimientos mController = new ControladorMovimientos(mVista);
         
        mController.arrayMembers();
        mController.setUser(datos);
        mController.listar(mVista.MovimientosTabla);
        mController.arrayMembers();
        
        this.uVista.setVisible(false);
        this.uVista.dispose();
    }
    
    public void listarPendientes() throws SQLException{
        System.out.println("Listar lanzado");
        
        modelo = (DefaultTableModel)uVista.TablaSolicitudes.getModel();
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
    
    public void listarActivos() throws SQLException{ 
        
        modeloB = (DefaultTableModel)uVista.TablaUsuarios.getModel();
        modeloB.setRowCount(0);
        
        List<Usuario> lista= daoInstance.getUsers(1);
        
        Object[] object = new Object[6]; 
        for(int i=0;i<lista.size();i++){
            object[0] = lista.get(i).getUsuario_ID(); 
            object[1] = lista.get(i).getUsuario_Nombre(); 
            object[2] = lista.get(i).getUsuario_Completo(); 
            object[3] = lista.get(i).getUsuario_Telefono();
            object[4] = lista.get(i).getUsuario_Permisos();
            
            modeloB.addRow(object); 
        }
        
        uVista.TablaUsuarios.setModel(modeloB);
    }
    
    public void startButtons(){
        uVista.txtUsuarioSeleccionado.setEnabled(false);
        uVista.comboBoxPermisos.setEnabled(false);
        uVista.btnDenegar.setEnabled(false);
        uVista.btnAceptarSolicitud.setEnabled(false);
        uVista.btnLimpiarCancelar.setEnabled(false);
        uVista.btnEliminarUsuario.setEnabled(false);
        uVista.btnEstablecerPermisos.setEnabled(false);
        
        uVista.comboBoxPermisos.setSelectedIndex(0);
        
        uVista.TablaSolicitudes.clearSelection(); 
        uVista.TablaUsuarios.clearSelection();
        
        uVista.txtUsuarioSeleccionado.setText("");
    }
    
    public void pendingUsers(){
        uVista.comboBoxPermisos.setEnabled(true);
        uVista.btnDenegar.setEnabled(true);
        uVista.btnAceptarSolicitud.setEnabled(true);
        uVista.btnLimpiarCancelar.setEnabled(true); 
        
        uVista.btnEliminarUsuario.setEnabled(false);
        uVista.btnEstablecerPermisos.setEnabled(false); 
    }
    
    public void enabledUsers(){ 
        uVista.comboBoxPermisos.setEnabled(true);
        uVista.btnDenegar.setEnabled(false);
        uVista.btnAceptarSolicitud.setEnabled(false); 
        
        uVista.btnLimpiarCancelar.setEnabled(true);
        uVista.btnEliminarUsuario.setEnabled(true); 
        
        uVista.btnEstablecerPermisos.setEnabled(true);
        
        uVista.txtUsuarioSeleccionado.setEnabled(false);
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
