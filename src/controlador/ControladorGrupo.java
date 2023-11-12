package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Grupo;
import modelo.GrupoDAO;
import vistas.GrupoVista;

public class ControladorGrupo implements ActionListener{ 
    
    GrupoDAO dao = new GrupoDAO(); 
    GrupoVista gVista = new GrupoVista();
    DefaultTableModel modelo = new DefaultTableModel(); 
    
    public ControladorGrupo(GrupoVista v){
        this.gVista=v;
        this.gVista.btnGuardar.addActionListener(this);
        this.gVista.btnEliminar.addActionListener(this);
        this.gVista.btnActualizar.addActionListener(this);
        
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
                    }
                }
            }
        });
        
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
    }
    
    public void agregar(){
        Grupo g = new Grupo();
        String nombre = gVista.txtGrupoNombre.getText();
        String descripcion = gVista.txtGrupoDesc.getText();
        g.setGrupo_Nombre(nombre);
        g.setGrupo_Desc(descripcion);
        
        int result = dao.Agregar(g);
        if(result==1){
            System.out.println("Ingresado con éxito");
            gVista.txtGrupoNombre.setText(" ");
            gVista.txtGrupoDesc.setText(" ");
            
        }else{
            System.out.println("ERROR");
        }
            
        listar(gVista.tablaGrupo);
    }
    
    public void eliminar(){
        Grupo g = new Grupo();
        String nombre = gVista.txtGrupoNombre.getText();
        String descripcion = gVista.txtGrupoDesc.getText();
        g.setGrupo_Nombre(nombre);
        g.setGrupo_Desc(descripcion);
        
        int result = dao.Eliminar(g);
        if(result==1){
            System.out.println("Ingresado con éxito");
            gVista.txtGrupoNombre.setText(" ");
            gVista.txtGrupoDesc.setText(" ");
            
        }else{
            System.out.println("ERROR");
        }
        
        listar(gVista.tablaGrupo);
    }
    
    public void actualizar(){
        Grupo g = new Grupo();
        String nombre = gVista.txtGrupoNombre.getText();
        String descripcion = gVista.txtGrupoDesc.getText();
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
    }
   
}
