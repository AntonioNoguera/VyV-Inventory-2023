package controlador;  

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Elemento;
import modelo.ElementosDAO; 
import vistas.ElementosVista;

public class ControladorElemento implements ActionListener{ 
    ElementosDAO dao = new ElementosDAO();
    Elemento e = new Elemento();
    ElementosVista eVista = new ElementosVista();
    DefaultTableModel modelo = new DefaultTableModel(); 
    
    public ControladorElemento(ElementosVista v){
        this.eVista=v;  
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

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
