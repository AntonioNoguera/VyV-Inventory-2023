package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Grupo;
import modelo.GrupoDAO;
import vistas.GruposVista;

public class Controlador implements ActionListener{ 
    
    GrupoDAO dao = new GrupoDAO();
    Grupo g = new Grupo();
    GruposVista gVista = new GruposVista();
    DefaultTableModel modelo = new DefaultTableModel(); 
    
    public Controlador(GruposVista v){
        this.gVista=v; 
        this.gVista.btnListar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==gVista.btnListar){
            listar(gVista.tablaGrupo);
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
   
}
