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
import modelo.Grupo;
import modelo.Movimientos;
import modelo.Usuario;
import vistas.ElementoVista;
import vistas.GrupoVista;
import vistas.MovimientosVista;
import vistas.UsuariosVista;

/**
 *
 * @author Antonio Noguera
 */
public class ControladorMovimientos implements ActionListener, KeyListener {  
    MovimientosDAO dao = new MovimientosDAO();
    Movimientos e = new Movimientos();
    MovimientosVista dVista = new MovimientosVista();
    DefaultTableModel modelo = new DefaultTableModel(); 
    
    Usuario loggedUser = new Usuario();
    
    public ControladorMovimientos(MovimientosVista v){
        this.dVista=v;  
        this.dVista.btnGuardar.addActionListener(this);
        this.dVista.jButton1.addActionListener(this);
        
        this.dVista.btnElementos.addActionListener(this);
        this.dVista.btnGrupos.addActionListener(this);
        this.dVista.btnUsuario.addActionListener(this);
        
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
                        
                        if(loggedUser.getUsuario_Permisos().equals("Administrador") 
                                || loggedUser.getUsuario_Completo().equals(String.valueOf(rowData[5]))){
                            
                            dVista.txtMovimientoID.setText(String.valueOf(rowData[0])); 
                            dVista.comboElemento.setSelectedItem(String.valueOf(rowData[1]));
                            dVista.txtCantidad.setText(String.valueOf(rowData[2]));  
                            dVista.comboEntrada.setSelectedItem(String.valueOf(rowData[3]));
                           

                            dVista.btnEliminar.setEnabled(true);
                            dVista.jButton1.setEnabled(true);
                            dVista.btnGuardar.setEnabled(false);
                            dVista.btnActualizar.setEnabled(true); 
                            dVista.comboElemento.setEnabled(false);
                        } else {
                            //Toast no tu movimiento
                            ClearALL(); 
                        }
                        
                        
                    }
                }
            }
        });
        
        this.dVista.btnEliminar.addActionListener(this); 
        this.dVista.txtCantidad.addKeyListener(this);
        this.dVista.btnActualizar.addActionListener(this);
        baseStateButtons();   
    }
    
    public void setUser(Usuario logUser){
        this.loggedUser = logUser;
        
        System.out.println("Logged User" + this.loggedUser.getUsuario_Nombre().toString());
        
    }
    
    private void baseStateButtons(){
        this.dVista.btnGuardar.setEnabled(false);
        this.dVista.btnEliminar.setEnabled(false);
        this.dVista.jButton1.setEnabled(false);
        dVista.comboElemento.setEnabled(true);
        dVista.comboEntrada.setEnabled(true);
        dVista.btnActualizar.setEnabled(false);  
    }
    
    public void listar(JTable tabla){ 
        modelo = (DefaultTableModel)tabla.getModel();
        modelo.setRowCount(0);
        List<Movimientos> lista=dao.listar();
        Object[] object = new Object[7]; 
        for(int i=lista.size()-1; i > -1; i--){
            
            object[0] = lista.get(i).getMovimiento_ID();
            object[1] = lista.get(i).getElementoNombre();
            object[2] = lista.get(i).getMovimiento_Cant();
             
            object[3] = lista.get(i).getMovimiento_Tipo();
            object[4] = lista.get(i).getMovimiento_Tiempo();
            object[5] = lista.get(i).getUsuario_Responsable();
            modelo.addRow(object); 
            
        }
        dVista.MovimientosTabla.setModel(modelo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==dVista.btnGuardar){
            agregar(loggedUser.getUsuario_ID());
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
        
        
        if(e.getSource()==dVista.btnElementos){
            ElementoVista eVista = new ElementoVista(); 

            ControladorElemento c = new ControladorElemento(eVista); 

            eVista.setVisible(true);
            c.arrayMembers();
            eVista.setLocationRelativeTo(null);
            c.listar(eVista.ElementosTabla);
            c.setUser(this.loggedUser);

            this.dVista.setVisible(false);
            this.dVista.dispose();
        }
        
        if(e.getSource()==dVista.btnGrupos){
            Grupo g = new Grupo();
            GrupoVista gVista = new GrupoVista(); 

            ControladorGrupo c = new ControladorGrupo(gVista); 
            
            c.setUser(this.loggedUser);
            c.listar(gVista.tablaGrupo); 
            gVista.setVisible(true);
            gVista.setLocationRelativeTo(null);
            
            this.dVista.setVisible(false);
            this.dVista.dispose();
            
        }
        
        if(e.getSource()==dVista.btnUsuario){
            UsuariosVista uVista = new UsuariosVista(); 
        
            ControladorUsuarios c = new ControladorUsuarios(uVista); 
            uVista.setVisible(true);
            uVista.setLocationRelativeTo(null);

            c.setUser(this.loggedUser);
            c.startButtons();
            c.listarTablas();
            
            this.dVista.setVisible(false);
            this.dVista.dispose();
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
    
    public void agregar(Integer actualUser){
        Movimientos m = new Movimientos();
        System.out.println("CONTROLLER");
        String tipoMov = (String) dVista.comboEntrada.getSelectedItem();
        String elementoNombre = (String) dVista.comboElemento.getSelectedItem();
        
        if(!validation(dVista.txtCantidad.getText())){
            System.out.println("ERROR");
            return ;
        }
        
        Float cantidad = Float.valueOf(dVista.txtCantidad.getText());
        
        int result = dao.Agregar(new Movimientos(tipoMov, elementoNombre,cantidad), actualUser);
        if(result==1){
            listar(dVista.MovimientosTabla);
            ClearALL();
            
        }else{
            System.out.println("ERROR");
        }
        
        
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

