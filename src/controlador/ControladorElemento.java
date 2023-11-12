package controlador;  

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
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
            object[4] = lista.get(i).getGrupo_ID(); 
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
            eVista.txtElementoNombre.setText(" ");
            eVista.txtElementoDesc.setText(" ");
            eVista.txtElementoCantidad.setText(" ");
            eVista.txtElementoUnidad.setText(" ");
            eVista.comboBoxGrupo.setSelectedIndex(0);
            
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
    }
}
