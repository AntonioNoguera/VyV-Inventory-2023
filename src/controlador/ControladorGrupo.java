package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Grupo;
import modelo.GrupoDAO;
import vistas.GrupoVista;

public class ControladorGrupo implements ActionListener{ 
    
    GrupoDAO dao = new GrupoDAO();
    Grupo g = new Grupo();
    GrupoVista gVista = new GrupoVista();
    DefaultTableModel modelo = new DefaultTableModel(); 
    
    public ControladorGrupo(GrupoVista v){
        this.gVista=v;  
    }
    
    @Override
    public void actionPerformed(ActionEvent e){ 
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
   
}
