package controlador;  

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Elemento;
import modelo.ElementosDAO; 
import vistas.ElementoVista; 

public class ControladorElemento implements ActionListener{ 
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
                    }
                }
            }
        });
        
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
        eVista.ElementosTabla.setModel(modelo);
    }
    
    public void agregar(){ 
        String nombre = eVista.txtElementoNombre.getText();
        String descripcion = eVista.txtElementoDesc.getText();
        Float cantidad = Float.valueOf(eVista.txtElementoCantidad.getText());
        String unidad = eVista.txtElementoUnidad.getText();
        Integer grupo = eVista.comboBoxGrupo.getSelectedIndex()+1; 
        
        int result = dao.Agregar(new Elemento(nombre,descripcion,cantidad,unidad,grupo));
        if(result==1){
            System.out.println("Ingresado con éxito");
            clearALL();
            
        }else{
            System.out.println("ERROR");
        }
            
        listar(eVista.ElementosTabla);
    }
    
    public void actualizar(){ 
        System.out.println("ACTUALIZAR");
        Integer ID = Integer.valueOf(eVista.txtElementoID.getText());
        String nombre = eVista.txtElementoNombre.getText();
        String descripcion = eVista.txtElementoDesc.getText();
        Float cantidad = Float.valueOf(eVista.txtElementoCantidad.getText());
        String unidad = eVista.txtElementoUnidad.getText();
        Integer grupo = eVista.comboBoxGrupo.getSelectedIndex()+1; 
        
        int result = dao.Actualizar(new Elemento(ID,nombre,descripcion,cantidad,unidad,grupo));
        if(result==1){
            System.out.println("Ingresado con éxito");
            clearALL();
            
        }else{
            System.out.println("ERROR");
        }
            
        listar(eVista.ElementosTabla);
    }
    
    public void eliminar(){ 
        System.out.println("ELIMINAR");
        Elemento e = new Elemento();
        
        e.setElemento_ID(Integer.valueOf(eVista.txtElementoID.getText()));
        //Integer nombre = eVista.txtElementoNombre.getText(); 
        
        int result = dao.Eliminar(e);
        if(result==1){
            System.out.println("Ingresado con éxito");
            clearALL();
            
        }else{
            System.out.println("ERROR");
        }
            
        listar(eVista.ElementosTabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==eVista.btnGuardar){
            System.out.println("SE LANZA");
            agregar();
        }
        
        if(e.getSource()==eVista.btnEliminar){
            System.out.println("SE LANZA");
            eliminar();
        }
        
        if(e.getSource()==eVista.btnActualizar){
            System.out.println("SE LANZA");
            actualizar();
        } 
        if(e.getSource()==eVista.btnLimpiar){
            System.out.println("SE LANZA");
            clearALL();
        }
    }
    
    public void clearALL(){
        eVista.txtElementoID.setText(" ");
        eVista.txtElementoNombre.setText(" ");
        eVista.txtElementoDesc.setText(" ");
        eVista.txtElementoCantidad.setText(" ");
        eVista.txtElementoUnidad.setText(" ");
        eVista.comboBoxGrupo.setSelectedIndex(0);
    }
    
    public void arrayMembers(){
        JComboBox<String> comboBox = eVista.comboBoxGrupo;
        comboBox.removeAllItems();
        
        List<String> miembros = dao.elementos();
        for(String memberUnit : miembros){
            comboBox.addItem(memberUnit);
        }
    }
    
}
