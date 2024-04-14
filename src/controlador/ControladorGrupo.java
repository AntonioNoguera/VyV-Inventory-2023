package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List; 
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Grupo;
import modelo_dao.GrupoDAO;
import vistas.GrupoVista;

public class ControladorGrupo implements ActionListener, KeyListener { 
    
    GrupoDAO dao = new GrupoDAO(); 
    GrupoVista gVista = new GrupoVista();
    DefaultTableModel modelo = new DefaultTableModel(); 
    
    public ControladorGrupo(GrupoVista v){
        this.gVista=v;
        this.gVista.btnGuardar.addActionListener(this);
        this.gVista.btnEliminar.addActionListener(this);
        this.gVista.btnLimpiar.addActionListener(this);
        this.gVista.btnActualizar.addActionListener(this); 
        this.gVista.txtGrupoDesc.addKeyListener(this);
        this.gVista.txtGrupoNombre.addKeyListener(this);
        
        this.gVista.tablaGrupo.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = gVista.tablaGrupo.getSelectedRow();
                    if (selectedRow != -1) {
                        // Obtener datos de la fila seleccionada
                        Object[] rowData = new Object[modelo.getColumnCount()];
                         
                        for (int i = 0; i < modelo.getColumnCount(); i++) {
                            rowData[i] = modelo.getValueAt(selectedRow, i);
                        }
                        gVista.txtGroupID.setText(String.valueOf(rowData[0]));
                        gVista.txtGrupoNombre.setText(String.valueOf(rowData[1]));
                        gVista.txtGrupoDesc.setText(String.valueOf(rowData[2])); 
                        
                        gVista.btnActualizar.setEnabled(true);
                        gVista.btnEliminar.setEnabled(true);
                        gVista.btnLimpiar.setEnabled(true);
                        gVista.btnGuardar.setEnabled(false);
                    }
                }
            }
        });
        
        
        //EstadoBaseBotones
        buttonBaseState();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){ 
        if(e.getSource()==gVista.btnGuardar){
            agregar();
        }
        
        if(e.getSource()==gVista.btnEliminar){
            eliminar();
        }
        
        if(e.getSource()==gVista.btnActualizar){
            actualizar();
        }
        
        if(e.getSource()==gVista.btnLimpiar){
            clearALL();
        }
    }
    
    public void listar(JTable tabla){ 
        modelo=(DefaultTableModel)tabla.getModel();
        modelo.setRowCount(0);
        List<Grupo> lista=dao.listar();
        Object[] object = new Object[3]; 
        for(int i=0;i<lista.size();i++){
            object[0] = lista.get(i).getGrupo_ID();
            object[1] = lista.get(i).getGrupo_Nombre();
            object[2] = lista.get(i).getGrupo_Desc();
            modelo.addRow(object);
        }
        
        gVista.tablaGrupo.setModel(modelo);
        buttonBaseState();
    }
    
    public void agregar(){
        Grupo g = new Grupo();
        String nombre = gVista.txtGrupoNombre.getText();
        String descripcion = gVista.txtGrupoDesc.getText();
        g.setGrupo_Nombre(nombre);
        g.setGrupo_Desc(descripcion);
        
        if(!inputValidation(nombre,descripcion)){
            return ;
        }
        
        int result = dao.Agregar(g);
        if(result==1){
            System.out.println("Ingresado con éxito");
            gVista.txtGrupoNombre.setText(" ");
            gVista.txtGrupoDesc.setText(" ");
        }else{
            System.out.println("ERROR");
        }
        
        listar(gVista.tablaGrupo);
        buttonBaseState();
    }
    
    public void eliminar(){
        Grupo g = new Grupo(); 
        
        String id = gVista.txtGroupID.getText();
        
        if(id.isEmpty()){
            JOptionPane.showMessageDialog(null, "El id no puede ser nulo, verique el campo", "Alerta", JOptionPane.WARNING_MESSAGE);
            return ;
        }
        
        if(!numberValidation(id)){
            JOptionPane.showMessageDialog(null, "El id debe ser numérico, verique el campo", "Alerta", JOptionPane.WARNING_MESSAGE);
            return ;
        }
        
        g.setGrupo_ID(Integer.valueOf(id));
        
        int result = dao.Eliminar(g);
        if(result==1){
            System.out.println("Ingresado con éxito");
            clearALL();
            
        }else{
            System.out.println("ERROR");
        }
        
        listar(gVista.tablaGrupo);
        buttonBaseState();
    }
    
    public void actualizar(){
        Grupo g = new Grupo();
        String id = gVista.txtGroupID.getText();
        String nombre = gVista.txtGrupoNombre.getText();
        String descripcion = gVista.txtGrupoDesc.getText();
        
        if(!inputValidation(id, nombre,descripcion)){
            return ;
        }
        
        g.setGrupo_ID(Integer.valueOf(id));
        g.setGrupo_Nombre(nombre);
        g.setGrupo_Desc(descripcion);
        
        int result = dao.Actualizar(g);
        if(result==1){
            System.out.println("Ingresado con éxito");
            gVista.txtGrupoNombre.setText(" ");
            gVista.txtGrupoDesc.setText(" ");
            
        }else{
            System.out.println("ERROR");
        }
            
        listar(gVista.tablaGrupo);
        buttonBaseState();
    }
    
    boolean inputValidation(String Name, String Description){
        //null verification
        if(Name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre", 
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(Description.isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor, ingrese una descripción", 
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        //length verification
        if(Name.length()>30){
            JOptionPane.showMessageDialog(null, "El nombre excede la longitud permitida.", 
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
     
        if(Description.length()>255){
            JOptionPane.showMessageDialog(null, "La descripcion excede la longitud permitida.", 
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    boolean inputValidation(String id, String Name, String Description){
        //null verification
        if(id.isEmpty()){
            JOptionPane.showMessageDialog(null, "Verifíque que el id no esté vacío.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(Name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(Description.isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor, ingrese una descripción", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        //DataType Verification
        if(!numberValidation(id)){
            JOptionPane.showMessageDialog(null, "El id posee un formato inválido, verificar es necesario.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        //length verification
        if(Name.length()>30){
            JOptionPane.showMessageDialog(null, "El nombre excede la longitud permitida.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
     
        if(Description.length()>255){
            JOptionPane.showMessageDialog(null, "La descripcion excede la longitud permitida.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(id.length()>11){
            JOptionPane.showMessageDialog(null, "Verifíque que el id no esté vacío.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        
        
        return true;
    }
    
    public static boolean numberValidation(String texto) {  
        return texto.matches("\\d+(\\.\\d+)?");
    }

    //Visual BI
    @Override
    public void keyTyped(KeyEvent e) {contentVerfier();}

    @Override
    public void keyPressed(KeyEvent e) {
        contentVerfier();
    }

    @Override
    public void keyReleased(KeyEvent e) {contentVerfier();}
   
    public void clearALL(){
        gVista.txtGroupID.setText("");
        gVista.txtGrupoNombre.setText("");
        gVista.txtGrupoDesc.setText("");
        gVista.btnActualizar.setEnabled(false);
        gVista.btnEliminar.setEnabled(false);
        gVista.tablaGrupo.clearSelection();
        buttonBaseState();
    }
     
    void buttonBaseState(){
        this.gVista.btnActualizar.setEnabled(false);
        this.gVista.btnEliminar.setEnabled(false);
        this.gVista.btnGuardar.setEnabled(false);
        this.gVista.btnLimpiar.setEnabled(false);
    }
    
    void contentVerfier(){
        if(!this.gVista.txtGrupoDesc.getText().isBlank() && !this.gVista.txtGrupoNombre.getText().isBlank() && this.gVista.txtGroupID.getText().isBlank()){
            this.gVista.btnGuardar.setEnabled(true);
        }else{
            this.gVista.btnGuardar.setEnabled(false);
        }
        this.gVista.btnLimpiar.setEnabled(true);
    }
}