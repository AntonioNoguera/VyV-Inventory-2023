package controlador;  

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Elemento;
import modelo.ElementosDAO; 
import vistas.ElementoVista; 

public class ControladorElemento implements ActionListener, KeyListener { 
    ElementosDAO dao = new ElementosDAO();
    Elemento e = new Elemento();
    ElementoVista eVista = new ElementoVista();
    DefaultTableModel modelo = new DefaultTableModel(); 
    
    public ControladorElemento(ElementoVista v){
        this.eVista=v;  
        this.eVista.btnGuardar.addActionListener(this);
        this.eVista.btnEliminar.addActionListener(this);
        this.eVista.btnActualizar.addActionListener(this);
        this.eVista.btnLimpiar.addActionListener(this);
        
        this.eVista.txtElementoCantidad.addKeyListener(this); 
        this.eVista.txtElementoDesc.addKeyListener(this); 
        this.eVista.txtElementoNombre.addKeyListener(this); 
        this.eVista.txtElementoUnidad.addKeyListener(this); 
        this.eVista.txtElementoID.addKeyListener(this); 
        
        this.eVista.ElementosTabla.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = eVista.ElementosTabla.getSelectedRow();
                    if (selectedRow != -1) {
                        // Obtener datos de la fila seleccionada
                        Object[] rowData = new Object[modelo.getColumnCount()];
                         
                        for (int i = 0; i < modelo.getColumnCount(); i++) {
                            rowData[i] = modelo.getValueAt(selectedRow, i);
                        }
                        eVista.txtElementoID.setText(String.valueOf(rowData[0]));
                        eVista.txtElementoNombre.setText(String.valueOf(rowData[1]));
                        eVista.txtElementoDesc.setText(String.valueOf(rowData[2]));
                        String[] splitedChain = String.valueOf(rowData[3]).split(" ");
                        eVista.txtElementoCantidad.setText(String.valueOf(splitedChain[0]));
                        eVista.txtElementoUnidad.setText(String.valueOf(splitedChain[1]));
                        eVista.comboBoxGrupo.setSelectedItem(String.valueOf(rowData[4]));
                                
                        System.out.println(Arrays.toString(rowData));
                        
                        
                        //Buttons After Selection
                        eVista.btnActualizar.setEnabled(true);
                        eVista.btnEliminar.setEnabled(true);
                        eVista.btnLimpiar.setEnabled(true);
                        eVista.btnGuardar.setEnabled(false);
                        eVista.txtElementoCantidad.setEnabled(false);
                        
                    }
                }
            }
        });
        baseStateButton();
    }
    
    public void baseStateButton(){
        this.eVista.btnEliminar.setEnabled(false);
        this.eVista.btnGuardar.setEnabled(false);
        this.eVista.btnLimpiar.setEnabled(false);
        this.eVista.btnActualizar.setEnabled(false);
        eVista.txtElementoCantidad.setEnabled(true);
        
        eVista.ElementosTabla.clearSelection();
    }
    
    public void listar(JTable tabla){ 
        modelo=(DefaultTableModel)tabla.getModel();
        modelo.setRowCount(0);
        List<Elemento> lista=dao.listar();
        Object[] object = new Object[6]; 
        for(int i=0;i<lista.size();i++){
            object[0] = lista.get(i).getElemento_ID(); 
            object[1] = lista.get(i).getElemento_Nombre(); 
            object[2] = lista.get(i).getElemento_Desc(); 
            object[3] = lista.get(i).getElemento_Cant()+" "+lista.get(i).getElemento_Unidad();
            object[4] = lista.get(i).getGrupo_Name();
            
            modelo.addRow(object); 
        }
        baseStateButton();
        eVista.ElementosTabla.setModel(modelo);
    }
    
    public void agregar(){ 
        String nombre = eVista.txtElementoNombre.getText();
        String descripcion = eVista.txtElementoDesc.getText();
        String cantidad = eVista.txtElementoCantidad.getText();
        String unidad = eVista.txtElementoUnidad.getText();
        String grupo = (String) eVista.comboBoxGrupo.getSelectedItem(); 
        
        if(!inputValidations(nombre, descripcion,cantidad,unidad)){
            return ;
        }
        
        int result = dao.Agregar(new Elemento(nombre,descripcion,
                Float.valueOf(cantidad),unidad,grupo));
        if(result==1){
            System.out.println("Ingresado con éxito");
            clearALL();
            
        }else{
            System.out.println("ERROR");
        }
        
        listar(eVista.ElementosTabla);
        baseStateButton();
    }
    
    public void actualizar(){  
        String ID = eVista.txtElementoID.getText();
        String nombre = eVista.txtElementoNombre.getText();
        String descripcion = eVista.txtElementoDesc.getText();
        String cantidad = eVista.txtElementoCantidad.getText();
        String unidad = eVista.txtElementoUnidad.getText();
        String grupo = (String) eVista.comboBoxGrupo.getSelectedItem(); 
        
        inputValidation(ID, nombre, descripcion, cantidad, unidad);
        
        int result = dao.Actualizar(new Elemento(Integer.valueOf(ID),nombre,descripcion,Float.valueOf(cantidad),unidad,grupo));
        if(result==1){
            System.out.println("Ingresado con éxito");
            clearALL();
            
        }else{
            System.out.println("ERROR");
        }
            
        listar(eVista.ElementosTabla);
        baseStateButton();
    }
    
    public void eliminar(){ 
        System.out.println("ELIMINAR");
        Elemento e = new Elemento();
        
        e.setElemento_ID(Integer.valueOf(eVista.txtElementoID.getText()));
        //Integer nombre = eVista.txtElementoNombre.getText(); 
        Object[] opciones = {"Sí, continuar", "No, cancelar"};

        int seleccion = JOptionPane.showOptionDialog(
            null,
            "\"Si presiona en OK además de eliminarse el elemento se eliminarán todos\nlos movimientos registrados del elemento\n¿Desea Continuar?\",",
            "Confirmación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0] // Botón predeterminado
        );

         if (seleccion == JOptionPane.YES_OPTION) {
            System.out.println("Usuario seleccionó 'Sí, continuar'.");
            if(dao.Eliminar(e)==1){
                System.out.println("Ingresado con éxito");
                clearALL();

            }else{
                System.out.println("ERROR");
            }

            listar(eVista.ElementosTabla);
            baseStateButton();
        } else if (seleccion == JOptionPane.NO_OPTION || seleccion == JOptionPane.CLOSED_OPTION) {
            System.out.println("Usuario seleccionó 'No, cancelar' o cerró el cuadro de diálogo.");
            // Realiza acciones relacionadas con la opción 'No, cancelar' o cierre del cuadro de diálogo
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==eVista.btnGuardar){ 
            agregar();
        }
        
        if(e.getSource()==eVista.btnEliminar){ 
            eliminar();
        }
        
        if(e.getSource()==eVista.btnActualizar){ 
            actualizar();
        } 
        
        if(e.getSource()==eVista.btnLimpiar){ 
            clearALL();
        }
    }
    
    public void clearALL(){
        eVista.txtElementoID.setText("");
        eVista.txtElementoNombre.setText("");
        eVista.txtElementoDesc.setText("");
        eVista.txtElementoCantidad.setText("");
        eVista.txtElementoUnidad.setText("");
        eVista.comboBoxGrupo.setSelectedIndex(0);
        baseStateButton();
    }
    
    public void arrayMembers(){
        JComboBox<String> comboBox = eVista.comboBoxGrupo;
        comboBox.removeAllItems();
        
        List<String> miembros = dao.elementos();
        for(String memberUnit : miembros){
            comboBox.addItem(memberUnit);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {    contentVerifier();  }

    @Override
    public void keyReleased(KeyEvent e) {   contentVerifier();  }
    
    public void contentVerifier(){
        if(
            !this.eVista.txtElementoCantidad.getText().isBlank() &&
            !this.eVista.txtElementoDesc.getText().isBlank() &&
            !this.eVista.txtElementoNombre.getText().isBlank() &&
            !this.eVista.txtElementoUnidad.getText().isBlank() && 
            this.eVista.txtElementoID.getText().isBlank() &&
            !this.eVista.comboBoxGrupo.getSelectedItem().equals("")
        ){
            
            this.eVista.btnGuardar.setEnabled(true);
            
        }else{
            this.eVista.btnGuardar.setEnabled(false);
        }
        
        this.eVista.btnLimpiar.setEnabled(true);
    }
    
    boolean inputValidations(String nombre, String description,String amount ,String unity){
        //Null Verification
        if(nombre.isBlank()){
            JOptionPane.showMessageDialog(null, "El nombre, no puede estar vacío.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(description.isBlank()){
            JOptionPane.showMessageDialog(null, "El descripción, no puede estar vacío.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(amount.isBlank()){
            JOptionPane.showMessageDialog(null, "La cantidad, no puede estar vacía.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(unity.isBlank()){
            JOptionPane.showMessageDialog(null, "La unidad, no puede estar vacía.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
        
        //Format Verification 
        if(!numberValidation(amount)){
            JOptionPane.showMessageDialog(null, "La cantidad tiene que ser un número entero o decimal.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
        
        //Lenght Verification 
        if(nombre.length()>30){
            JOptionPane.showMessageDialog(null, "La longitud máxima del nombre es de 30 caracteres.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(description.length()>255){
            JOptionPane.showMessageDialog(null, "La longitud máxima de la descripción es de 255 caracteres.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(amount.length()>32){
            JOptionPane.showMessageDialog(null, "La longitud máxima de la cantidad es de 32 caracteres.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(unity.length()>30){
            JOptionPane.showMessageDialog(null, "La longitud máxima de la unidad es de 30 caracteres.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        //values available
        if(Float.parseFloat(amount)<=0){
            JOptionPane.showMessageDialog(null, "El valor mínimo para declarar un elemento es de 0", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }

    
    boolean inputValidation(String id, String nombre, String description,String amount ,String unity){
        //Null Verification
        if(id.isBlank()){
            JOptionPane.showMessageDialog(null, "El campo ID, no puede estar vacío.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(nombre.isBlank()){
            JOptionPane.showMessageDialog(null, "El nombre, no puede estar vacío.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(description.isBlank()){
            JOptionPane.showMessageDialog(null, "El descripción, no puede estar vacío.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(amount.isBlank()){
            JOptionPane.showMessageDialog(null, "La cantidad, no puede estar vacía.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
         
        
        if(unity.isBlank()){
            JOptionPane.showMessageDialog(null, "La unidad, no puede estar vacía.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
        
        //Format Verification
        if(!numberValidation(id)){
            JOptionPane.showMessageDialog(null, "El ID tiene que ser un número.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(!numberValidation(amount)){
            JOptionPane.showMessageDialog(null, "El ID tiene que ser un número.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
        
        //Lenght Verification
        if(id.length()>11){
            JOptionPane.showMessageDialog(null, "La longitud máxima del ID es de 11 caracteres.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(nombre.length()>30){
            JOptionPane.showMessageDialog(null, "La longitud máxima del nombre es de 30 caracteres.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(description.length()>255){
            JOptionPane.showMessageDialog(null, "La longitud máxima de la descripción es de 255 caracteres.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(amount.length()>32){
            JOptionPane.showMessageDialog(null, "La longitud máxima de la cantidad es de 32 caracteres.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(unity.length()>30){
            JOptionPane.showMessageDialog(null, "La longitud máxima de la unidad es de 30 caracteres.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        //values available
        if(Float.parseFloat(amount)<=0){
            JOptionPane.showMessageDialog(null, "El valor mínimo para declarar un elemento es de 0", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
   
    
    public static boolean numberValidation(String texto) {  
        return texto.matches("\\d+(\\.\\d+)?");
    }
    
    public static boolean hasNumbersInIt(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
     
}
