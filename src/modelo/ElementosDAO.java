package modelo;

import controlador.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
public class ElementosDAO {
    conexion conectar = new conexion();
    Connection con;
    
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
       String sql = "select * from tabla_elementos";
       List<Elemento> datos = new ArrayList<>();
       
       try{
           con=conectar.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
                Elemento e = new Elemento();
                e.setElemento_ID(rs.getInt(1));  
                e.setElemento_Nombre(rs.getString(2));  
                e.setElemento_Desc(rs.getString(3));  
                e.setElemento_Cant(rs.getFloat(4));  
                e.setElemento_Unidad(rs.getString(5));  
                e.setGrupo_ID(rs.getString(6)); 
                
                datos.add(e);
            } 
        }catch(Exception e){
            System.out.println("No se ejecuto el listado bien");
        }
        return datos;
    }
}
