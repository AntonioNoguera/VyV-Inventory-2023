package modelo;

import controlador.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 

public class DashboardDAO {
    conexion conectar = new conexion();
    Connection con;
    
    PreparedStatement ps;
    PreparedStatement qs;
    ResultSet rs;
    ResultSet rsB;
    
    public List listar(){
       String sql = "select * from tabla_movimientos";
       String sqlSelect = "select elemento_nombre from tabla_elementos where elemento_id = ";
       
       List<Movimientos> datos = new ArrayList<>();
       List<String> elementosCombo = new ArrayList<>();
       
       try{
           con=conectar.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
                Movimientos e = new Movimientos();
                e.setMovimiento_ID(rs.getInt(1));  
                e.setMovimiento_Tipo(rs.getString(2));  
                
                try{
                    qs=con.prepareStatement(sqlSelect + rs.getInt(3));
                    rsB=qs.executeQuery();
                    while(rsB.next()){
                        e.setElementoNombre(rsB.getString(1)); 
                        elementosCombo.add(rsB.getString(1));
                    }
                }catch(SQLException err){
                    System.out.println("ERROR SQL: "+err);
                }
                e.setMovimiento_Cant(rs.getFloat(4));  
                e.setMovimiento_Tiempo(rs.getString(5));   
                
                datos.add(e);
            } 
        }catch(SQLException e){
            System.out.println("No se ejecuto el listado bien: "+e);
        } 
       elementos();
        return datos;
    }
    
    public List elementos(){ 
       String sqlSelect = "select elemento_nombre from tabla_elementos";
        
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
    
    public int Agregar(Movimientos m){
           String sql = "INSERT INTO tabla_movimientos(movimiento_tipo,elemento_id,movimiento_cant) VALUES (?,?,?)";
           String sql2 = "SELECT elemento_id FROM tabla_elementos WHERE elemento_nombre = ?";
           
           try{
               con = conectar.conectar();
               ps=con.prepareStatement(sql);
               
               ps.setString(1, m.getMovimiento_Tipo());
               
               try{
                   qs=con.prepareStatement(sql2); 
                   qs.setString(1, m.getElementoNombre());
                   rs=qs.executeQuery();
                   while(rs.next()){  
                        ps.setInt(2,rs.getInt(1));
                    }
               }catch(SQLException e){
                   System.out.println("Error aaa"+e);
               }
               
               ps.setFloat(3, m.getMovimiento_Cant());
               
               ps.executeUpdate();
               return 1;
           }catch(SQLException e){
               System.out.println("Error"+e);
               return 0;
           }
    }
   
    
    public void Eliminar(Movimientos m){
        System.out.println("ELIMINAR");
    }
    
    public void Actualizar(Movimientos m){
        System.out.println("Actualizar");
    }
}
