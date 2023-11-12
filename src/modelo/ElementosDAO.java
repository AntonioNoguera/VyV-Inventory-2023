package modelo;

import controlador.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
public class ElementosDAO {
    conexion conectar = new conexion();
    Connection con;
    
    PreparedStatement ps;
    PreparedStatement qs;
    ResultSet rs;
    ResultSet rsB;
    
    public List listar(){
       String sql = "select * from tabla_elementos";
       String sqlSelect = "select grupo_nombre from tabla_grupos where grupo_id = ";
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
                
                try{
                    qs=con.prepareStatement(sqlSelect + rs.getInt(6));
                    rsB=qs.executeQuery();
                    while(rsB.next()){
                        e.setGrupo_Name(rsB.getString(1));  
                    }
                }catch(SQLException err){
                    System.out.println("ERROR SQL: "+err);
                } 
                
                datos.add(e);
            } 
        }catch(SQLException e){
            System.out.println("No se ejecuto el listado bien");
        }
        return datos;
    }
    
    public int Agregar(Elemento e){
           String sql = "INSERT INTO tabla_elementos(elemento_nombre, elemento_desc, elemento_cant, elemento_unidad, grupo_id) VALUES (?,?,?,?,?)";
           try{
               con = conectar.conectar();
               ps=con.prepareStatement(sql);
               ps.setString(1, e.getElemento_Nombre());
               ps.setString(2, e.getElemento_Desc());
               ps.setFloat(3, e.getElemento_Cant());
               ps.setString(4, e.getElemento_Unidad());
               ps.setInt(5, e.getGrupo_ID());
               ps.executeUpdate();
               return 1;
               
           }catch(SQLException er){
               System.out.println("Error: "+er);
               return 0;
               
           }
           
    }
    
    public int Actualizar(Elemento e){
           String sql = "";
           try{
               con = conectar.conectar();
               ps=con.prepareStatement(sql);
               ps.setString(1, e.getElemento_Nombre());
               ps.setString(2, e.getElemento_Desc());
               ps.setFloat(3, e.getElemento_Cant());
               ps.setString(4, e.getElemento_Unidad());
               ps.setInt(5, e.getGrupo_ID());
               ps.executeUpdate();
               return 1;
               
           }catch(SQLException er){
               System.out.println("Error: "+er);
               return 0;
               
           }
           
    }
    
    public int Eliminar(Elemento e){
           String sql = "";
           try{
               con = conectar.conectar();
               ps=con.prepareStatement(sql);
               ps.setString(1, e.getElemento_Nombre());
               ps.setString(2, e.getElemento_Desc());
               ps.setFloat(3, e.getElemento_Cant());
               ps.setString(4, e.getElemento_Unidad());
               ps.setInt(5, e.getGrupo_ID());
               ps.executeUpdate();
               return 1;
               
           }catch(SQLException er){
               System.out.println("Error: "+er);
               return 0;
               
           }
           
    }
    public List elementos(){ 
       String sqlSelect = "select grupo_nombre from tabla_grupos";
        
       List<String> elementosCombo = new ArrayList<>();
       
       try{
           con=conectar.conectar();
            ps=con.prepareStatement(sqlSelect);
            rs=ps.executeQuery();
            
            while(rs.next()){  
                elementosCombo.add(rs.getString(1));
            } 
        }catch(SQLException e){
            System.out.println("No se ejecuto el listado bien: "+e);
        }
       
       return(elementosCombo);
    }
    
}
